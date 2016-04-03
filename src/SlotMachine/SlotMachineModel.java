package SlotMachine;

import Currency.Currency;
import Currency.FiftyCent;
import Currency.OneEuro;
import Currency.TwoEuros;
import SlotMachine.State.*;

import javax.swing.*;
import java.util.ArrayList;


/**
 * Model for SlotMachine.
 */
public class SlotMachineModel {
    static final int MAXWEIGHT = 100;
    ArrayList<Currency> machineMoney;
    SlotMachineState inActive;
    SlotMachineState active;
    SlotMachineState running;
    SlotMachineState winPrize;
    SlotMachineState currentState;

    public SlotMachineModel() {
        machineMoney = new ArrayList<>();
        inActive = new SlotMachineInActive(this);
        active = new SlotMachineActive(this);
        running = new SlotMachineRunning(this);
        winPrize = new SlotMachineWinPrize(this);
        currentState = inActive;
    }

    public void SetState(SlotMachineState newState) {
        currentState = newState;
    }

    public ImageIcon GetRandomIcon() {
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


    public String InsertCurrency(Currency currency) {
        return currentState.InsertCurrency(currency);
    }

    public void AddCash(Currency currency) {
        machineMoney.add(currency);
    }

    public String RemoveCurrency(Currency currency) {
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

        // machineMoney.remove(currency);

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
        if (GetTotalWeight() > MAXWEIGHT) {
            return true;
        }
        return false;
    }

    private String ImageToString(ImageIcon image) {
        return (image.getDescription().split("/")[1]).split(".png")[0];
    }


    public String CheckPrize(ImageIcon one, ImageIcon two, ImageIcon three) {
        //System.out.println("null");
        if (one != null || two != null || three != null) {
            //System.out.println("not null");

            String oneValue = ImageToString(one);
            String twoValue = ImageToString(two);
            String threeValue = ImageToString(three);
            //System.out.println(oneValue + " " + twoValue + " " + threeValue);

            if (oneValue.matches("bigwin") && twoValue.matches("bigwin") && threeValue.matches("bigwin")) {
                return "Boom Jackpot!";
            } else if (oneValue.matches(twoValue) && twoValue.matches(threeValue)) {
                InsertCurrency(new OneEuro());
            }

            if (oneValue.matches(twoValue) || oneValue.matches(threeValue)) {
                InsertCurrency(new FiftyCent());
                //System.out.println("Smallprize");
            }

            if (twoValue.matches(threeValue) || twoValue.matches(oneValue)) {
                InsertCurrency(new FiftyCent());
                //System.out.println("Smallprize");
            }
        }
        return null;


    }


    public SlotMachineState getInActive() {
        return inActive;
    }

    public SlotMachineState getActive() {
        return active;
    }

    public SlotMachineState getRunning() {
        return running;
    }

    public SlotMachineState getWinPrize() {
        return winPrize;
    }


}



