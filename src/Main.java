import Master.MasterController;
import Master.MasterModel;
import Master.MasterView;

import java.awt.*;

/**
 * Multi SlotMachine.
 * Design Patterns Eindopdracht.
 * Student: Marcel Schoeber
 * Studentnummer: 331910
 */

/**
 * Main Class
 * Starts up SlotMachine Manager (Master)
 */
public class Main {
    public static void main(String[] args) {
        CreateMaster();
    }

    /* Creates SlotMachine Manager */
    private static void CreateMaster() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        MasterModel mModel = new MasterModel();
        MasterView mView = new MasterView(gd);
        MasterController mController = new MasterController(mModel, mView);
    }
}
