package SlotMachine;

import Currency.Currency;
import Currency.FiftyCent;
import Currency.OneEuro;
import Currency.TwoEuros;
import Observer.SlotMachineSubject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for SlotMachine.
 */
public class SlotMachineController {
    private SlotMachineModel slotMachineModel;
    private SlotMachineView slotMachineView;
    private int SlotMachineNumber;
    private SlotMachineSubject subject;
    private double LastCurrency = 0;
    private boolean IsJackpot = false;

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

    public void ClearJackpot() {
        IsJackpot = false;
    }

    public boolean IsJackpot() {
        return IsJackpot;
    }


    public SlotMachineSubject getSubject() {
        return subject;
        //return null;
    }

    public double GetLastCurrency() {
        return LastCurrency;
    }

    public void ClearLastCurrency() {
        LastCurrency = 0;
    }

    public void KillView() {
        slotMachineView.KillScreen();
    }

    public double GetCash() {
        return slotMachineModel.ReturnTotalCash();
    }

    public int GetNumber() {
        return SlotMachineNumber;
    }

    class SpinListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                String message = slotMachineModel.RemoveCurrency(new FiftyCent());
                slotMachineView.SetSlots(slotMachineModel.GetRandomIcon(), slotMachineModel.GetRandomIcon(), slotMachineModel.GetRandomIcon());
                String prizeMessage = slotMachineModel.CheckPrize(slotMachineView.GetSlotNumber(1), slotMachineView.GetSlotNumber(2), slotMachineView.GetSlotNumber(3));
                slotMachineView.SetCashLabel(String.valueOf(slotMachineModel.ReturnTotalCash()));
                subject.setWin(false);

                if (message != null) {
                    slotMachineView.DisplayErrorMessage(message);
                }
                if (prizeMessage != null) {
                    subject.setWin(true);
                    IsJackpot = true;
                    slotMachineView.DisplayErrorMessage(prizeMessage);
                }
            } catch (Exception ex) {
                slotMachineView.DisplayErrorMessage("Uh oh! Something went wrong with spinning!! :" + ex);

            }
        }
    }

    class FiftyEuroCentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


            try {

                AddCash(new FiftyCent());


            } catch (Exception ex) {
                slotMachineView.DisplayErrorMessage("Uh oh! Something went wrong with cash insertion!!");

            }
        }
    }

    class OneEuroListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                AddCash(new OneEuro());
            } catch (Exception ex) {
                slotMachineView.DisplayErrorMessage("Uh oh! Something went wrong with spinning!!");

            }
        }
    }

    class TwoEuroListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                AddCash(new TwoEuros());
            } catch (Exception ex) {
                slotMachineView.DisplayErrorMessage("Uh oh! Something went wrong with spinning!!");

            }
        }
    }

}

