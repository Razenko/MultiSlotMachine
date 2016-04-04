package Master.State;

import Master.MasterView;

import javax.swing.*;

/**
 * Jackpot State for Master.
 */
public class JackpotState implements MasterState {

    public void DrawState(MasterView masterView) {
        masterView.SetWinGraphic(new ImageIcon("icons/jackpot.png"));
    }
}
