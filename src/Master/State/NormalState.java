package Master.State;

import Master.MasterView;


/**
 * Normal State for Master.
 */
public class NormalState implements MasterState {

    public void DrawState(MasterView masterView) {
        masterView.SetWinGraphic(null);
    }
}
