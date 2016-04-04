package Master;

import Master.State.JackpotState;
import Master.State.MasterState;
import Master.State.NormalState;
import SlotMachine.SlotMachineController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for Master.
 */
public class MasterController {

    MasterControllerRunnable runner;
    private MasterModel mModel;
    private MasterView mView;
    private MasterState Jackpot;
    private MasterState Normal;
    private MasterState currentState;

    public MasterController(MasterModel mModel, MasterView mView) {
        this.mModel = mModel;
        this.mView = mView;
        this.mView.AddOkListener(new OkButtonListener());
        this.mView.AddResetListener(new ResetButtonListener());
        mView.SetJackpot(mModel.GetTotalCash());
        Jackpot = new JackpotState();
        Normal = new NormalState();
        currentState = Normal;
    }


    private void CreateSlotMachine(int number) {
        int width = mView.GetGraphicsDevice().getDisplayMode().getWidth();
        int height = mView.GetGraphicsDevice().getDisplayMode().getHeight();
        mModel.CreateMachines(number, width, height);
        runner = new MasterControllerRunnable();
        new Thread(runner).start();
    }

    private void RemoveMachines() {
        mModel.RemoveMachines();
        mView.ToggleOkButton();
        ResetCash();
        mView.SetJackpot(mModel.GetTotalCash());
    }

    void HitJackpot(int number) {
        currentState = Jackpot;
        currentState.DrawState(mView);
        mView.DisplayErrorMessage("Slot machine " + number + " won the " + mModel.GetTotalCash() + " euro Jackpot!");
        ResetCash();
    }

    private void ResetCash() {
        mModel.SetTotalCash(100);
    }


    private class OkButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (mView.GetNumberBox() > 0 && mView.GetNumberBox() <= 3) {
                    mView.ToggleOkButton();
                    CreateSlotMachine(mView.GetNumberBox());
                } else {
                    mView.DisplayErrorMessage("You can only create 1 to 3 slot machines!");
                }
            } catch (Exception ex) {
                mView.DisplayErrorMessage("Uh oh! Something went wrong! Exception:" + ex);
            }
        }
    }

    private class MasterControllerRunnable implements Runnable {

        public void run() {
            int JackpotStateCounter = 0;
            while (true) {
                mView.SetJackpot(mModel.GetTotalCash());
                for (SlotMachineController machine : mModel.GetMachines()) {
                    mModel.SetTotalCash(mModel.GetTotalCash() + machine.GetLastCurrency());
                    machine.ClearLastCurrency();

                    if (machine.IsJackpot()) {
                        JackpotStateCounter = 100;
                        HitJackpot(machine.GetNumber());
                        machine.ClearJackpot();
                    }
                }
                if (JackpotStateCounter > 0) {
                    JackpotStateCounter--;
                }
                if (JackpotStateCounter == 1) {
                    currentState = Normal;
                    currentState.DrawState(mView);
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ignored) {

                }
            }
        }
    }

    private class ResetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                RemoveMachines();
            } catch (Exception ex) {
                mView.DisplayErrorMessage("Uh oh! Something went wrong! Exception:" + ex);
            }
        }
    }
}
