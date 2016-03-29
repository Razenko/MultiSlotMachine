package Currency;

/**
 * Represents fifty eurocents.
 */
public class FiftyCent implements Currency {
    @Override
    public int GetWeight() {
        return 5;
    }

    @Override
    public double Value() {
        return 0.50;
    }
}
