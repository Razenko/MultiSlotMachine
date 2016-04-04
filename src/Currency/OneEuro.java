package Currency;

/**
 * Represents one euro.
 */
public class OneEuro implements Currency {
    //Weight of coin
    public int GetWeight() {
        return 10;
    }

    //Value of coin
    public double Value() {
        return 1;
    }
}
