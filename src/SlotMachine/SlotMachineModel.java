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
 * Model for SlotMachine.
 */
public class SlotMachineModel {
    private static final int MAXWEIGHT = 100;
    private ArrayList<Currency> machineMoney;
    private SlotMachineState inActive;
    private SlotMachineState active;
    private SlotMachineState winPrize;
    private SlotMachineState currentState;
    private SlotMachineState lastState;

    public SlotMachineModel() {
        machineMoney = new ArrayList<>();
        inActive = new SlotMachineInActive(this);
        active = new SlotMachineActive(this);
        winPrize = new SlotMachineWinPrize(this);
        currentState = inActive;
    }

    public void SetState(SlotMachineState newState) {
        lastState = currentState;
        currentState = newState;
    }


    ImageIcon GetRandomIcon() {
        return currentState.GetSlotImage();
    }

    public double ReturnTotalCash() {
        double totalCash = 0;
        if (!machineMoney.isEmpty()) {
            for (Currency cash : machineMoney) {
                totalCash += cash.Value();
            }
        }

        return totalCash;
    }


    String InsertCurrency(Currency currency) {
        return currentState.InsertCurrency(currency);
    }

    public void AddCash(Currency currency) {
        machineMoney.add(currency);
    }

    String RemoveCurrency(Currency currency) {
        return currentState.RemoveCurrency(currency);

    }

    public void CurrencyExchange(Currency currency) {

        int fiftyCentCount = 0;
        int euroCount = 0;
        int twoEuroCount = 0;

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

    boolean IsJackpot() {
        return currentState.IsJackpot();
    }


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

    public void ClearCurrency() {
        machineMoney.clear();
    }

    private int GetTotalWeight() {
        int weight = 0;
        for (Currency cash : machineMoney) {
            weight += cash.GetWeight();
        }
        return weight;
    }

    public boolean CheckCashFull() {
        return GetTotalWeight() > MAXWEIGHT;
    }


    String CheckPrize(ImageIcon one, ImageIcon two, ImageIcon three) {
        return currentState.CheckPrize(one, two, three);
    }


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



