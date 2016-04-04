package Observer;

/**
 * Observer.Observer for SlotMachine.
 */
public class SlotMachineObserver implements Observer {
    private double cash;
    private boolean isJackpot;
    private int number;
    private SlotMachineSubject subject;

    public SlotMachineObserver(SlotMachineSubject subject) {
        this.subject = subject;
        subject.register(this);
    }

    public int ReturnNumber() {
        return number;
    }

    public double ReturnClearCash() {
        double tempcash = cash;
        //cash = 0;
        return tempcash;
    }

    public boolean ReturnIsJackpot() {
        return isJackpot;
    }

    public void ResetJackpot() {
        isJackpot = false;
    }

    public void Update(int number, double cash, boolean isJackpot) {
        this.number = number;
        this.cash = cash;
        this.isJackpot = isJackpot;

    }
}
