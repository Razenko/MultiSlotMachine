package Master;

import Observer.SlotMachineSubject;
import SlotMachine.SlotMachineController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controller for Master.
 */
public class MasterController {

    MasterControllerRunnable runner;
    private MasterModel mModel;
    private MasterView mView;
    private ArrayList<SlotMachineController> machines;
    private SlotMachineSubject subject;
    private double totalCash = 100;

    public MasterController(MasterModel mModel, MasterView mView) {
        this.mModel = mModel;
        this.mView = mView;
        this.mView.AddOkListener(new OkButtonListener());
        this.mView.AddResetListener(new ResetButtonListener());
        mView.SetJackpot(totalCash);
        machines = new ArrayList<>();
        subject = new SlotMachineSubject();
        //CreateSlotMachine();
    }


    private void CreateSlotMachine(int number) {
        int width = mView.GetGraphicsDevice().getDisplayMode().getWidth();
        int height = mView.GetGraphicsDevice().getDisplayMode().getHeight();
        machines = mModel.CreateMachines(number, width, height);
        runner = new MasterControllerRunnable();
        new Thread(runner).start();
    }

    private void RemoveMachines() {
        for (SlotMachineController machine : machines) {
            machine.KillView();
        }
        machines.clear();
        mView.ToggleOkButton();
        totalCash = 100;
        mView.SetJackpot(totalCash);


    }

    void HitJackpot(int number) {
        mView.DisplayErrorMessage("Slot machine " + number + " won the " + totalCash + "euro Jackpot!");
        totalCash = 100;
    }


    class OkButtonListener implements ActionListener {

        @Override
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

    class MasterControllerRunnable implements Runnable {

        @Override
        public void run() {


            while (true) {
                mView.SetJackpot(totalCash);

                for (SlotMachineController machine : machines) {
                    totalCash += machine.GetLastCurrency();
                    machine.ClearLastCurrency();
                    if (machine.IsJackpot()) {
                        HitJackpot(machine.GetNumber());
                    }
                }


                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    class ResetButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                RemoveMachines();


            } catch (Exception ex) {
                mView.DisplayErrorMessage("Uh oh! Something went wrong! Exception:" + ex);

            }

        }
    }
}
