package Master;

import SlotMachine.SlotMachineController;
import SlotMachine.SlotMachineModel;
import SlotMachine.SlotMachineView;

import java.util.ArrayList;

/**
 * Model for Master.
 */
public class MasterModel {

    public ArrayList<SlotMachineController> CreateMachines(int number, int width, int height) {
        int widthModifier = 550;
        ArrayList<SlotMachineController> machines = new ArrayList<>();


        for (int i = 0; i < number; i++) {
            SlotMachineModel slotMachineModel = new SlotMachineModel();
            SlotMachineView slotMachineView = new SlotMachineView((width - (int) (width * 0.9) + widthModifier * i), height - (int) (height * 0.5), "SlotMachine " + (i + 1));
            SlotMachineController slotMachineController = new SlotMachineController(slotMachineModel, slotMachineView, (i + 1));
            machines.add(slotMachineController);
        }
        return machines;
    }
}
