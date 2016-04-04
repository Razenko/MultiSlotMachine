package Master.State;

import Master.MasterView;

import javax.swing.*;


/**
 * Normal State for Master.
 */
public class NormalState implements MasterState {

    /**
     * Change Jackpot of image on view
     *
     * @param masterView
     */
    public void DrawState(MasterView masterView) {
        masterView.SetWinGraphic(new ImageIcon("icons/slotmachine.png"));
    }
}
