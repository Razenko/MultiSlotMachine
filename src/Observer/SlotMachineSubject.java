package Observer;

import java.util.ArrayList;

/**
 * Subject for SlotMachine.
 */
public class SlotMachineSubject implements Subject {
    //Fields
    private ArrayList<Observer> observers = new ArrayList<>();
    private double cash;
    private boolean isJackpot;
    private int number;

    /**
     * Register Observer
     *
     * @param o Observer
     */
    public void register(Observer o) {
        observers.add(o);
    }

    /**
     * Unregister Observer
     *
     * @param o
     */
    public void unregister(Observer o) {
        int observerIndex = observers.indexOf(o);
        observers.remove(observerIndex);
    }

    /**
     * Return ArrayList of Observers
     *
     * @return
     */
    public ArrayList<Observer> FetchObservers() {
        return observers;
    }

    /**
     * Notify Observer
     */
    public void notifyObserver() {
        if (!observers.isEmpty()) {
            for (Observer observer : observers) {
                observer.Update(number, cash, isJackpot);
            }
        }
    }

    /**
     * Set machine number
     *
     * @param number Machine number
     */
    public void setNumber(int number) {
        this.number = number;
        notifyObserver();
    }

    /**
     * Retrieve cash
     *
     * @return cash
     */
    public double getCash() {
        return cash;
    }

    /**
     * Set the cash value
     *
     * @param cash cash value
     */
    public void setCash(double cash) {
        this.cash = cash;
        notifyObserver();
    }

    /**
     * Set IsJackpot value
     *
     * @param isJackpot IsJackpot value
     */
    public void setWin(boolean isJackpot) {
        this.isJackpot = isJackpot;
        notifyObserver();
    }
}
