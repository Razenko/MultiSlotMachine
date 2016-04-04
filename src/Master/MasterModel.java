package Master;

import Observer.Observer;
import Observer.SlotMachineSubject;
import SlotMachine.SlotMachineController;
import SlotMachine.SlotMachineModel;
import SlotMachine.SlotMachineView;

import java.util.ArrayList;
/**
 * Multi SlotMachine.
 * Design Patterns Eindopdracht.
 * Student: Marcel Schoeber
 * Studentnummer: 331910
 */

/**
 * Model for Master.
 */
public class MasterModel {
    //Fields
    private SlotMachineSubject subject;
    private ArrayList<SlotMachineController> machines;
    private double totalCash = 100;

    /**
     * Constructor
     */
    public MasterModel() {
        subject = new SlotMachineSubject();
        machines = new ArrayList<>();
    }

    /**
     * Create SlotMachines
     *
     * @param number Number of machines
     * @param width  Start position (X-Pos)
     * @param height Start position (Y-Pos)
     */
    void CreateMachines(int number, int width, int height) {
        //Offset for X-Pos
        int widthModifier = 550;
        for (int i = 0; i < number; i++) {
            //Create model
            SlotMachineModel slotMachineModel = new SlotMachineModel();
            //Create view
            SlotMachineView slotMachineView = new SlotMachineView((width - (int) (width * 0.98) + widthModifier * i), height - (int) (height * 0.5), "SlotMachine " + (i + 1));
            //Create controller
            SlotMachineController slotMachineController = new SlotMachineController(slotMachineModel, slotMachineView, (i + 1));
            //Add to observers
            for (Observer observer : slotMachineController.getSubject().FetchObservers()) {
                subject.register(observer);
            }
            //Add to list
            machines.add(slotMachineController);
        }

    }

    /**
     * Return list
     *
     * @return List
     */
    ArrayList<SlotMachineController> GetMachines() {
        return machines;
    }

    /**
     * Get the total cash of Jackpot
     *
     * @return Total cash
     */
    double GetTotalCash() {
        return totalCash;
    }

    /**
     * Set the total cash of Jackpot
     *
     * @param cash Total cash
     */
    void SetTotalCash(double cash) {
        totalCash = cash;
    }

    /**
     * Remove all machines
     */
    void RemoveMachines() {
        for (SlotMachineController machine : machines) {
            machine.KillView();
        }
        machines.clear();
    }
}
