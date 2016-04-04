package SlotMachine;

import Currency.Currency;
import Currency.FiftyCent;
import Currency.OneEuro;
import Currency.TwoEuros;
import SlotMachine.State.SlotMachineActive;
import SlotMachine.State.SlotMachineInActive;
import SlotMachine.State.SlotMachineState;
import SlotMachine.State.SlotMachineWinPrize;

import javax.swing.*;
import java.util.ArrayList;
/**
 * Multi SlotMachine.
 * Design Patterns Eindopdracht.
 * Student: Marcel Schoeber
 * Studentnummer: 331910
 */

/**
 * Model for SlotMachine.
 * Stores data and states.
 */
public class SlotMachineModel {
    //Fields
    private static final int MAXWEIGHT = 100;
    private ArrayList<Currency> machineMoney;
    private SlotMachineState inActive;
    private SlotMachineState active;
    private SlotMachineState winPrize;
    private SlotMachineState currentState;
    private SlotMachineState lastState;

    /**
     * Constructor
     */
    public SlotMachineModel() {
        machineMoney = new ArrayList<>();
        inActive = new SlotMachineInActive(this);
        active = new SlotMachineActive(this);
        winPrize = new SlotMachineWinPrize(this);
        currentState = inActive;
    }

    /**
     * Set a new state, and store the previous state.
     *
     * @param newState New state to change to
     */
    public void SetState(SlotMachineState newState) {
        lastState = currentState;
        currentState = newState;
    }

    /**
     * Retrieve a random ImageIcon for the slots
     *
     * @return ImageIcon representing a slot item
     */
    ImageIcon GetRandomIcon() {
        return currentState.GetSlotImage();
    }

    /**
     * Returns the total cash in the machine
     *
     * @return Yotal cash
     */
    public double ReturnTotalCash() {
        double totalCash = 0;
        if (!machineMoney.isEmpty()) {
            for (Currency cash : machineMoney) {
                totalCash += cash.Value();
            }
        }
        return totalCash;
    }


    /**
     * Insert new currency into the machine
     * Redirected to the current state
     *
     * @param currency Currency to insert
     * @return Message to return
     */
    String InsertCurrency(Currency currency) {
        return currentState.InsertCurrency(currency);
    }

    /**
     * Add currency directly to machineMoney list
     *
     * @param currency Currency to add
     */
    public void AddCash(Currency currency) {
        machineMoney.add(currency);
    }

    String RemoveCurrency(Currency currency) {
        return currentState.RemoveCurrency(currency);
    }

    /**
     * Swaps out currency to always ensure there are fifty cent coins in the machine,
     * as these are consumed on every spin.
     *
     * @param currency Currency to insert
     */
    public void CurrencyExchange(Currency currency) {

        int fiftyCentCount = 0;
        int euroCount = 0;
        int twoEuroCount = 0;

        //Take stock of all types of currency currently in the machine
        for (Currency cash : machineMoney) {
            if (cash instanceof FiftyCent) {
                fiftyCentCount++;
            }
            if (cash instanceof OneEuro) {
                euroCount++;
            }
            if (cash instanceof TwoEuros) {
                twoEuroCount++;
            }
        }

        //Convert to a lesser currency value if needed
        if (euroCount == 0 && twoEuroCount > 0) {
            RemoveCash(new TwoEuros());
            AddCash(new OneEuro());
            AddCash(new OneEuro());
        }

        if (fiftyCentCount == 0 && euroCount > 0) {
            RemoveCash(new OneEuro());
            AddCash(new FiftyCent());
            AddCash(new FiftyCent());
        }
    }

    /**
     * Return bool of IsJackpot state.
     *
     * @return bool of IsJackpot
     */
    boolean IsJackpot() {
        return currentState.IsJackpot();
    }

    /**
     * Remove currency from the machine
     *
     * @param currency Currency to remove
     */
    public void RemoveCash(Currency currency) {
        if (!machineMoney.isEmpty()) {
            Currency removeCash = null;
            for (Currency cash : machineMoney) {
                if (cash.Value() == currency.Value()) {
                    removeCash = cash;
                    break;
                }
            }
            machineMoney.remove(removeCash);
        }
    }

    /**
     * Clear all currency
     */
    public void ClearCurrency() {
        machineMoney.clear();
    }

    /**
     * Retrieve the total of weight of all currency currently residing in the machine
     *
     * @return Total Weight
     */
    private int GetTotalWeight() {
        int weight = 0;
        for (Currency cash : machineMoney) {
            weight += cash.GetWeight();
        }
        return weight;
    }

    /**
     * Check if machine is full (of cash)
     *
     * @return Is full?
     */
    public boolean CheckCashFull() {
        return GetTotalWeight() > MAXWEIGHT;
    }


    /**
     * Check if the player has won a prize
     *
     * @param one   First slot
     * @param two   Second slot
     * @param three Third slot
     * @return Message to return
     */
    String CheckPrize(ImageIcon one, ImageIcon two, ImageIcon three) {
        return currentState.CheckPrize(one, two, three);
    }

    //The retrieval of different states:
    public SlotMachineState getInActive() {
        return inActive;
    }

    public SlotMachineState getActive() {
        return active;
    }

    public SlotMachineState getWinPrize() {
        return winPrize;
    }

    public SlotMachineState getLastState() {
        return lastState;
    }


}



