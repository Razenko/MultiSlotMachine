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

    //Fields
    MasterControllerRunnable runner;
    private MasterModel mModel;
    private MasterView mView;
    private MasterState Jackpot;
    private MasterState Normal;
    private MasterState currentState;

    /**
     * Constructor
     *
     * @param mModel Master (SlotMachine Manager) Model
     * @param mView  Master (SlotMachine Manager) View
     */
    public MasterController(MasterModel mModel, MasterView mView) {
        this.mModel = mModel;
        this.mView = mView;
        //Add listeners for buttons
        this.mView.AddOkListener(new OkButtonListener());
        this.mView.AddResetListener(new ResetButtonListener());
        //Initialize jackpot cash value
        mView.SetJackpot(mModel.GetTotalCash());
        //Add states and set current state
        Jackpot = new JackpotState();
        Normal = new NormalState();
        currentState = Normal;
        currentState.DrawState(mView);
    }


    /**
     * Create new Slot Machines
     *
     * @param number Number of machines
     */
    private void CreateSlotMachine(int number) {
        //Get Width/Height of screen resolution
        int width = mView.GetGraphicsDevice().getDisplayMode().getWidth();
        int height = mView.GetGraphicsDevice().getDisplayMode().getHeight();
        //Create the machines
        mModel.CreateMachines(number, width, height);
        //Start observer thread
        runner = new MasterControllerRunnable();
        new Thread(runner).start();
    }

    /**
     * Remove all machines and reset jackpot
     */
    private void RemoveMachines() {
        mModel.RemoveMachines();
        mView.ToggleOkButton();
        ResetCash();
        mView.SetJackpot(mModel.GetTotalCash());
    }

    /**
     * Hit the jackpot
     *
     * @param number Machine that has hit the jackpot
     */
    void HitJackpot(int number) {
        //Change state to jackpot
        currentState = Jackpot;
        //Draw jackpot image
        currentState.DrawState(mView);
        //Display celebratory message
        mView.DisplayErrorMessage("Slot machine " + number + " won the grand " + mModel.GetTotalCash() + " euro Jackpot!");
        //Reset jackpot
        ResetCash();
    }

    /**
     * Reset the cash value back to default
     */
    private void ResetCash() {
        mModel.SetTotalCash(100);
    }


    /**
     * Executed when user presses the OK button
     */
    private class OkButtonListener implements ActionListener {
        /**
         * ActionEvent
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            try {
                //Check if input is within bounds
                if (mView.GetNumberBox() > 0 && mView.GetNumberBox() <= 3) {
                    //Disable OK button
                    mView.ToggleOkButton();
                    //Create a new slot machine
                    CreateSlotMachine(mView.GetNumberBox());
                } else {
                    //Error if out of bounds
                    mView.DisplayErrorMessage("You can only create 1 to 3 slot machines!");
                }
            } catch (Exception ex) {
                //General error
                mView.DisplayErrorMessage("Uh oh! Something went wrong! Exception:" + ex);
            }
        }
    }

    /**
     * Observer thread
     */
    private class MasterControllerRunnable implements Runnable {

        public void run() {
            //Counter for Jackpot image (to keep image on screen for a short while when jackpot has hit)
            int JackpotStateCounter = 0;
            //Thread loop
            while (true) {
                //Update cash
                mView.SetJackpot(mModel.GetTotalCash());
                //Check all machines if new cash has been inserted
                for (SlotMachineController machine : mModel.GetMachines()) {
                    //Add cash to jackpot if user has inserted currency
                    mModel.SetTotalCash(mModel.GetTotalCash() + machine.GetLastCurrency());
                    machine.ClearLastCurrency();

                    //Check if slot machine has hit jackpot
                    if (machine.IsJackpot()) {
                        JackpotStateCounter = 100;
                        HitJackpot(machine.GetNumber());
                        machine.ClearJackpot();
                    }
                }

                //Decrement jackpot image counter
                if (JackpotStateCounter > 0) {
                    JackpotStateCounter--;
                }

                //Reset the jackpot image back to normal
                if (JackpotStateCounter == 1) {
                    currentState = Normal;
                    currentState.DrawState(mView);
                }

                //Thread delay of 20 ms: Slow down thread to prevent cpu nuking
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ignored) {
                    //Mandatory try/catch
                }
            }
        }
    }

    /**
     * Listener for reset button
     */
    private class ResetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                //Reset everything
                RemoveMachines();
            } catch (Exception ex) {
                mView.DisplayErrorMessage("Uh oh! Something went wrong! Exception:" + ex);
            }
        }
    }
}
