package Currency;

/**
 * Represents two euros.
 */
public class TwoEuros implements Currency {
    //Weight of coin
    public int GetWeight() {
        return 20;
    }

    //Value of coin
    public double Value() {
        return 2;
    }
}
