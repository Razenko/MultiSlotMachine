package SlotMachine.State;

import Currency.Currency;
import SlotMachine.SlotMachineModel;

import javax.swing.*;

/**
 * InActive State for SlotMachine.
 */
public class SlotMachineInActive implements SlotMachineState {

    //Fields
    private SlotMachineModel slotMachine;

    /**
     * Constructor
     *
     * @param slotMachine SlotMachineModel
     */
    public SlotMachineInActive(SlotMachineModel slotMachine) {
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

    /**
     * Insert currency
     *
     * @param currency currency to insert
     * @return Message
     */
    public String InsertCurrency(Currency currency) {
        if (!slotMachine.CheckCashFull()) {
            this.slotMachine.AddCash(currency);
            slotMachine.SetState(slotMachine.getActive());
            return null;
        } else {
            return "The system is overloaded with cash!!";
        }
    }

    /**
     * Cannot remove currency (machine state is InActive)
     *
     * @param currency
     * @return
     */
    public String RemoveCurrency(Currency currency) {
        return "Please enter some cash!";
    }

    //Unused
    public String CheckPrize(ImageIcon one, ImageIcon two, ImageIcon three) {
        return null;
    }

    //No Jackpot
    public boolean IsJackpot() {
        return false;
    }


}
