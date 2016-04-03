package SlotMachine.State;

import Currency.Currency;
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

    SlotMachineModel slotMachine;

    public SlotMachineActive(SlotMachineModel slotMachine) {
        this.slotMachine = slotMachine;
    }

    public ImageIcon GetSlotImage() {
        SystemIterator icons = new Icons();
        Iterator currentIcons = icons.createIterator();
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

    @Override
    public String InsertCurrency(Currency currency) {
        if (!slotMachine.CheckCashFull()) {
            this.slotMachine.AddCash(currency);
            slotMachine.SetState(slotMachine.getActive());
            return null;
        } else {
            return "The system is overloaded with cash!! Please play some more!";
        }
    }

    @Override
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


    class Icons implements SystemIterator {

        ImageIcon[] icons = new ImageIcon[9];
        String[] iconNames = {"banana", "bar", "berry", "bigwin", "cherry", "lemon", "melon", "orange", "seven"};
        String path = "icons/";
        String ext = ".png";

        public Icons() {
            LoadIcons();
        }

        private void LoadIcons() {
            for (int i = 0; i < iconNames.length; i++) {
                ImageIcon image = new ImageIcon(path + iconNames[i] + ext);
                icons[i] = image;
                // System.out.println(icons[i].getDescription());
            }

        }

        @Override
        public Iterator createIterator() {
            return Arrays.asList(icons).iterator();
        }


    }
}
