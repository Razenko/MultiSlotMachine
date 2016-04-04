package SlotMachine.State;

import Currency.Currency;
import SlotMachine.SlotMachineModel;

import javax.swing.*;

/**
 * InActive State for SlotMachine.
 */
public class SlotMachineInActive implements SlotMachineState {
    private SlotMachineModel slotMachine;

    public SlotMachineInActive(SlotMachineModel slotMachine) {
        this.slotMachine = slotMachine;
    }

    public ImageIcon GetSlotImage() {
        String path = "icons/";
        String ext = ".png";

        return new ImageIcon(path + "x" + ext);
    }


    public String InsertCurrency(Currency currency) {
        if (!slotMachine.CheckCashFull()) {
            this.slotMachine.AddCash(currency);
            slotMachine.SetState(slotMachine.getActive());
            return null;
        } else {
            return "The system is overloaded with cash!!";
        }
    }

    public String RemoveCurrency(Currency currency) {
        return "Please enter some cash!";
    }

    public String CheckPrize(ImageIcon one, ImageIcon two, ImageIcon three) {
        return null;
    }

    public boolean IsJackpot() {
        return false;
    }


}
