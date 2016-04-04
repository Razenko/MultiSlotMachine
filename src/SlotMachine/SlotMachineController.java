package SlotMachine;

import Currency.Currency;
import Currency.FiftyCent;
import Currency.OneEuro;
import Currency.TwoEuros;
import Observer.SlotMachineSubject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Multi SlotMachine.
 * Design Patterns Eindopdracht.
 * Student: Marcel Schoeber
 * Studentnummer: 331910
 */

/**
 * Controller for SlotMachine.
 * Handles the user input and some machine logic
 */
public class SlotMachineController {

    //Fields
    private SlotMachineModel slotMachineModel;
    private SlotMachineView slotMachineView;
    private int SlotMachineNumber;
    private SlotMachineSubject subject;
    private double LastCurrency = 0;

    /**
     * Constructor
     *
     * @param slotMachineModel Model of machine
     * @param slotMachineView  View of machine
     * @param number           Machine's identification number
     */
    public SlotMachineController(SlotMachineModel slotMachineModel, SlotMachineView slotMachineView, int number) {
        this.slotMachineModel = slotMachineModel;
        this.slotMachineView = slotMachineView;
        this.slotMachineView.AddSpinListener(new SpinListener());
        this.slotMachineView.AddFiftyCentListener(new FiftyEuroCentListener());
        this.slotMachineView.AddOneEuroListener(new OneEuroListener());
        this.slotMachineView.AddTwoEuroListener(new TwoEuroListener());
        this.slotMachineView.SetCashLabel(String.valueOf(slotMachineModel.ReturnTotalCash()));
        subject = new SlotMachineSubject();
        subject.setNumber(number);
        SlotMachineNumber = number;
        slotMachineView.SetSlots(slotMachineModel.GetRandomIcon(), slotMachineModel.GetRandomIcon(), slotMachineModel.GetRandomIcon());
    }

    /**
     * Add cash to machine
     *
     * @param cash Currency to add
     */
    private void AddCash(Currency cash) {
        String message = slotMachineModel.InsertCurrency(cash);
        if (message == null) {
            slotMachineView.SetCashLabel(String.valueOf(slotMachineModel.ReturnTotalCash()));
            LastCurrency = cash.Value();
        } else {
            LastCurrency = 0;
            slotMachineView.DisplayErrorMessage(message);
        }
    }

    /**
     * Clear the Jackpot state
     */
    public void ClearJackpot() {
        slotMachineModel.SetState(slotMachineModel.getLastState());
    }

    /**
     * Check for Jackpot
     *
     * @return IsJackpot
     */
    public boolean IsJackpot() {
        return slotMachineModel.IsJackpot();
    }

    /**
     * Retrieve subject
     *
     * @return Subject
     */
    public SlotMachineSubject getSubject() {
        return subject;
    }

    /**
     * Return last currency inserted
     *
     * @return Currency value
     */
    public double GetLastCurrency() {
        return LastCurrency;
    }

    /**
     * Clear the last currency value
     */
    public void ClearLastCurrency() {
        LastCurrency = 0;
    }

    /**
     * Kill the view/GUI
     */
    public void KillView() {
        slotMachineView.KillScreen();
    }

    /**
     * Retrieve total cash in machine
     *
     * @return Total cash
     */
    public double GetCash() {
        return slotMachineModel.ReturnTotalCash();
    }

    /**
     * Retrieve the identification number of this machine
     *
     * @return Id
     */
    public int GetNumber() {
        return SlotMachineNumber;
    }

    /**
     * Executed when user presses the spin button
     */
    private class SpinListener implements ActionListener {

        /**
         * ActionEvent method
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {

            try {
                //Remove fifty cents on spin
                String message = slotMachineModel.RemoveCurrency(new FiftyCent());
                //Apply new slot images
                slotMachineView.SetSlots(slotMachineModel.GetRandomIcon(), slotMachineModel.GetRandomIcon(), slotMachineModel.GetRandomIcon());
                //Check to see if user has won anything
                String prizeMessage = slotMachineModel.CheckPrize(slotMachineView.GetSlotNumber(1), slotMachineView.GetSlotNumber(2), slotMachineView.GetSlotNumber(3));
                //Print the amount of cash currently residing in the machine
                slotMachineView.SetCashLabel(String.valueOf(slotMachineModel.ReturnTotalCash()));
                //Subject setWin set to false
                subject.setWin(false);
                if (message != null) {
                    /**
                     * Display an error message if something went wrong (message not null)
                     */
                    slotMachineView.DisplayErrorMessage(message);
                }
                /**
                 * If the prizeMessage is not null, user has won a prize
                 */
                if (prizeMessage != null) {
                    //Display prize message and change state to getWinPrize/Jackpot
                    subject.setWin(true);
                    slotMachineModel.SetState(slotMachineModel.getWinPrize());
                    slotMachineView.DisplayErrorMessage(prizeMessage);
                }
            } catch (Exception ex) {
                slotMachineView.DisplayErrorMessage("Uh oh! Something went wrong with spinning!! :" + ex);
            }
        }
    }

    /**
     * Executed when user presses the fifty cent button
     */
    private class FiftyEuroCentListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                AddCash(new FiftyCent());
            } catch (Exception ex) {
                slotMachineView.DisplayErrorMessage("Uh oh! Something went wrong with cash insertion!!");
            }
        }
    }

    /**
     * Executed when user presses the one euro button
     */
    private class OneEuroListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                AddCash(new OneEuro());
            } catch (Exception ex) {
                slotMachineView.DisplayErrorMessage("Uh oh! Something went wrong with spinning!!");
            }
        }
    }

    /**
     * Executed when user presses the two euro button
     */
    private class TwoEuroListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                AddCash(new TwoEuros());
            } catch (Exception ex) {
                slotMachineView.DisplayErrorMessage("Uh oh! Something went wrong with spinning!!");
            }
        }
    }

}

