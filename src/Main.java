import Master.MasterController;
import Master.MasterModel;
import Master.MasterView;

import java.awt.*;

/**
 * Created by Marcel on 29-3-2016.
 */
public class Main {
    public static void main(String[] args) {
        CreateMaster();
    }

    private static void CreateMaster() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        MasterModel mModel = new MasterModel();
        MasterView mView = new MasterView(gd);
        MasterController mController = new MasterController(mModel, mView);
    }
}
