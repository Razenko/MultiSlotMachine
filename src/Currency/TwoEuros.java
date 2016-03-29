package Currency;

/**
 * Represents two euros.
 */
public class TwoEuros implements Currency {
    @Override
    public int GetWeight() {
        return 20;
    }

    @Override
    public double Value() {
        return 2;
    }
}
