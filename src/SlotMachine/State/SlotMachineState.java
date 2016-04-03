package SlotMachine.State;

import Currency.Currency;

import javax.swing.*;

/**
 * SlotMachineState interface.
 */
public interface SlotMachineState {

    public ImageIcon GetSlotImage();

    public String InsertCurrency(Currency currency);

    public String RemoveCurrency(Currency currency);


}
