package SlotMachine.State;

import Currency.Currency;
import SlotMachine.SlotMachineModel;

import javax.swing.*;

/**
 * WinPrize State for SlotMachine.
 */
public class SlotMachineWinPrize implements SlotMachineState {
    SlotMachineModel slotMachine;

    public SlotMachineWinPrize(SlotMachineModel slotMachine) {
        this.slotMachine = slotMachine;
    }

    @Override
    public ImageIcon GetSlotImage() {
        return null;
    }

    @Override
    public String InsertCurrency(Currency currency) {
        return null;
    }

    @Override
    public String RemoveCurrency(Currency currency) {
        return null;
    }
}
