package Currency;

/**
 * Represents one euro.
 */
public class OneEuro implements Currency {
    public int GetWeight() {
        return 10;
    }

    public double Value() {
        return 1;
    }
}
