package SlotMachine.State;

import Currency.Currency;
import Currency.FiftyCent;
import Currency.OneEuro;
import SlotMachine.SlotMachineModel;
import SystemIterator.SystemIterator;

import javax.swing.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * Active State for SlotMachine.
 */
public class SlotMachineActive implements SlotMachineState {

    //Fields
    private SlotMachineModel slotMachine;
    private SystemIterator icons;

    /**
     * Constructor
     *
     * @param slotMachine SlotMachineModel
     */
    public SlotMachineActive(SlotMachineModel slotMachine) {
        this.slotMachine = slotMachine;
        icons = new Icons();
    }

    /**
     * Iterate through and return a random slot image (to be used on spin)
     * Used the iterator pattern
     *
     * @return Image
     */
    public ImageIcon GetSlotImage() {
        Iterator currentIcons = icons.CreateIterator();
        Random rnd = new Random();
        int rnum = rnd.nextInt(9);
        int count = 0;
        ImageIcon theImage = null;
        while (currentIcons.hasNext()) {
            if (count == rnum) {
                theImage = (ImageIcon) currentIcons.next();
                break;
            }
            currentIcons.next();
            count++;
        }
        return theImage;
    }


    /**
     * Insert currency
     *
     * @param currency Currency to insert
     * @return Message
     */
    public String InsertCurrency(Currency currency) {
        if (!slotMachine.CheckCashFull()) {
            this.slotMachine.AddCash(currency);
            slotMachine.SetState(slotMachine.getActive());
            return null;
        } else {
            return "The system is overloaded with cash!! Please play some more!";
        }
    }

    /**
     * Remover currency
     *
     * @param currency Currency to remove
     * @return Message
     */
    public String RemoveCurrency(Currency currency) {
        if (this.slotMachine.ReturnTotalCash() >= currency.Value()) {
            this.slotMachine.CurrencyExchange(currency);
            this.slotMachine.RemoveCash(currency);
            return null;
        } else {
            slotMachine.SetState(slotMachine.getInActive());
            return "Not enough cash in System! Please insert coins!";
        }
    }

    /**
     * Check if the user has won a prize
     * Possible states:
     * Three "Big Win" images = Jackpot
     * Three of a kind = One free euro
     * Two of a kind = Free fifty cent
     *
     * @param one   First slot
     * @param two   Second slot
     * @param three Third slot
     * @return Message
     */
    public String CheckPrize(ImageIcon one, ImageIcon two, ImageIcon three) {
        if (one != null || two != null || three != null) {

            String oneValue = ImageToString(one);
            String twoValue = ImageToString(two);
            String threeValue = ImageToString(three);

            if (oneValue.matches("bigwin") && twoValue.matches("bigwin") && threeValue.matches("bigwin")) {
                return "Boom Jackpot!";
            } else if (oneValue.matches(twoValue) && twoValue.matches(threeValue)) {
                InsertCurrency(new OneEuro());
            }

            if (oneValue.matches(twoValue) || oneValue.matches(threeValue)) {
                InsertCurrency(new FiftyCent());
            }

            if (twoValue.matches(threeValue) || twoValue.matches(oneValue)) {
                InsertCurrency(new FiftyCent());
            }
        }
        return null;
    }

    //No Jackpot (wrong state)
    public boolean IsJackpot() {
        return false;
    }

    /**
     * Return image description (name of image without path or extension)
     *
     * @param image Image to use
     * @return Description
     */
    private String ImageToString(ImageIcon image) {
        return (image.getDescription().split("/")[1]).split(".png")[0];
    }


    /**
     * Iterator for images/icons
     */
    private class Icons implements SystemIterator {

        //Create array of images and their respective file names
        ImageIcon[] icons = new ImageIcon[9];
        String[] iconNames = {"banana", "bar", "berry", "bigwin", "cherry", "lemon", "melon", "orange", "seven"};
        String path = "icons/";
        String ext = ".png";

        //Load the images
        Icons() {
            LoadIcons();
        }

        private void LoadIcons() {
            for (int i = 0; i < iconNames.length; i++) {
                ImageIcon image = new ImageIcon(path + iconNames[i] + ext);
                icons[i] = image;
            }
        }

        //Return as Iterator
        public Iterator CreateIterator() {
            return Arrays.asList(icons).iterator();
        }
    }
}
