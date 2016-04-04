package SlotMachine.State;

import Currency.Currency;
import SlotMachine.SlotMachineModel;

import javax.swing.*;

/**
 * WinPrize State for SlotMachine.
 */
public class SlotMachineWinPrize implements SlotMachineState {
    //Fields
    private SlotMachineModel slotMachine;

    /**
     * Constructor
     *
     * @param slotMachine SlotMachineModel
     */
    public SlotMachineWinPrize(SlotMachineModel slotMachine) {
        this.slotMachine = slotMachine;
    }

    /**
     * Retrieve slot image
     *
     * @return Image
     */
    public ImageIcon GetSlotImage() {
        String path = "icons/";
        String ext = ".png";
        return new ImageIcon(path + "x" + ext);
    }

    //Not used in this state
    public String InsertCurrency(Currency currency) {
        return null;
    }

    public String RemoveCurrency(Currency currency) {
        return null;
    }

    public String CheckPrize(ImageIcon one, ImageIcon two, ImageIcon three) {
        return null;
    }

    /**
     * Return the Jackpot state
     *
     * @return True (current state is WinPrize/Jackpot)
     */
    public boolean IsJackpot() {
        return true;
    }
}
