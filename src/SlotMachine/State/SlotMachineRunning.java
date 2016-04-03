package SlotMachine.State;

import Currency.Currency;
import SlotMachine.SlotMachineModel;

import javax.swing.*;

/**
 * Running State for SlotMachine.
 */
public class SlotMachineRunning implements SlotMachineState {
    SlotMachineModel slotMachine;

    public SlotMachineRunning(SlotMachineModel slotMachine) {
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
