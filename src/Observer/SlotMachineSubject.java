package Observer;

import java.util.ArrayList;

/**
 * Observer.Subject for SlotMachine.
 */
public class SlotMachineSubject implements Subject {
    private ArrayList<Observer> observers = new ArrayList<>();
    private double cash;
    private boolean isJackpot;
    private int number;


    @Override
    public void register(Observer o) {
        observers.add(o);
    }


    @Override
    public void unregister(Observer o) {
        int observerIndex = observers.indexOf(o);
        observers.remove(observerIndex);
    }

    public ArrayList<Observer> FetchObservers() {
        return observers;
    }

    @Override
    public void notifyObserver() {
        if (!observers.isEmpty()) {
            for (Observer observer : observers) {
                observer.Update(number, cash, isJackpot);
            }
        }
    }

    public void setNumber(int number) {
        this.number = number;
        notifyObserver();
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
        notifyObserver();
    }

    public void setWin(boolean isJackpot) {
        this.isJackpot = isJackpot;
        notifyObserver();
    }
}
