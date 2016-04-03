package Currency;

/**
 * Represents one euro.
 */
public class OneEuro implements Currency {
    @Override
    public int GetWeight() {
        return 10;
    }

    @Override
    public double Value() {
        return 1;
    }
}
