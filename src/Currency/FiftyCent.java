package Currency;

/**
 * Represents fifty eurocents.
 */
public class FiftyCent implements Currency {
    //Weight of coin
    public int GetWeight() {
        return 20;
    }

    //Value of coin
    public double Value() {
        return 0.50;
    }
}
