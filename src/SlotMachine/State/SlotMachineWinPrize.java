package SlotMachine.State;

import Currency.Currency;
import SlotMachine.SlotMachineModel;

import javax.swing.*;

/**
 * WinPrize State for SlotMachine.
 */
public class SlotMachineWinPrize implements SlotMachineState {
    private SlotMachineModel slotMachine;

    public SlotMachineWinPrize(SlotMachineModel slotMachine) {
        this.slotMachine = slotMachine;
    }

    public ImageIcon GetSlotImage() {
        String path = "icons/";
        String ext = ".png";
        return new ImageIcon(path + "x" + ext);
    }

    public String InsertCurrency(Currency currency) {
        return null;
    }

    public String RemoveCurrency(Currency currency) {
        return null;
    }

    public String CheckPrize(ImageIcon one, ImageIcon two, ImageIcon three) {
        return null;
    }

    public boolean IsJackpot() {
        return true;
    }
}
