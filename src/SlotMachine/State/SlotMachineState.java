package SlotMachine.State;

import Currency.Currency;

import javax.swing.*;

/**
 * SlotMachineState interface.
 */
public interface SlotMachineState {

    ImageIcon GetSlotImage();

    String InsertCurrency(Currency currency);

    String RemoveCurrency(Currency currency);

    String CheckPrize(ImageIcon one, ImageIcon two, ImageIcon three);

    boolean IsJackpot();


}
