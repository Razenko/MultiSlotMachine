package Observer;

/**
 * Observer for SlotMachine.
 */
public class SlotMachineObserver implements Observer {
    //Fields
    private double cash;
    private boolean isJackpot;
    private int number;
    private SlotMachineSubject subject;

    /**
     * Constructor
     *
     * @param subject Subject
     */
    public SlotMachineObserver(SlotMachineSubject subject) {
        this.subject = subject;
        subject.register(this);
    }

    /**
     * Return machine number
     *
     * @return Number
     */
    public int ReturnNumber() {
        return number;
    }

    /**
     * Return and clear cash
     *
     * @return Cash
     */
    public double ReturnClearCash() {
        double tempcash = cash;
        //cash = 0;
        return tempcash;
    }

    /**
     * Is Jackpot
     *
     * @return IsJackpot
     */
    public boolean ReturnIsJackpot() {
        return isJackpot;
    }

    /**
     * Reset Jackpot
     */
    public void ResetJackpot() {
        isJackpot = false;
    }

    /**
     * Update Observer
     *
     * @param number    Machine number
     * @param cash      Cash
     * @param isJackpot IsJackpot
     */
    public void Update(int number, double cash, boolean isJackpot) {
        this.number = number;
        this.cash = cash;
        this.isJackpot = isJackpot;

    }
}
