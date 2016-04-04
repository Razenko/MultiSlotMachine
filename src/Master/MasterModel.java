package Master;

import Observer.Observer;
import Observer.SlotMachineSubject;
import SlotMachine.SlotMachineController;
import SlotMachine.SlotMachineModel;
import SlotMachine.SlotMachineView;

import java.util.ArrayList;

/**
 * Model for Master.
 */
public class MasterModel {
    private SlotMachineSubject subject;
    private ArrayList<SlotMachineController> machines;
    private double totalCash = 100;

    public MasterModel() {
        subject = new SlotMachineSubject();
        machines = new ArrayList<>();
    }

    void CreateMachines(int number, int width, int height) {
        int widthModifier = 550;
        for (int i = 0; i < number; i++) {
            SlotMachineModel slotMachineModel = new SlotMachineModel();
            SlotMachineView slotMachineView = new SlotMachineView((width - (int) (width * 0.98) + widthModifier * i), height - (int) (height * 0.5), "SlotMachine " + (i + 1));
            SlotMachineController slotMachineController = new SlotMachineController(slotMachineModel, slotMachineView, (i + 1));
            for (Observer observer : slotMachineController.getSubject().FetchObservers()) {
                subject.register(observer);
            }
            machines.add(slotMachineController);
        }

    }

    ArrayList<SlotMachineController> GetMachines() {
        return machines;
    }

    double GetTotalCash() {
        return totalCash;
    }

    void SetTotalCash(double cash) {
        totalCash = cash;
    }

    void RemoveMachines() {
        for (SlotMachineController machine : machines) {
            machine.KillView();
        }
        machines.clear();
    }
}
