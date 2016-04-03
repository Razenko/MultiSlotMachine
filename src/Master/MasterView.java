package Master;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View for Master.
 */
public class MasterView extends JFrame {
    GraphicsDevice gd;
    JLabel nmbText;
    JButton setNr;
    JButton resetNr;
    JLabel Jackpot;
    private JTextField numberMachines;

    public MasterView(GraphicsDevice gd) {
        this.gd = gd;
        JPanel mPanel = new JPanel();
        mPanel.setLayout(null);
        mPanel.setBackground(Color.decode("#f5f6f7"));
        numberMachines = new JTextField();
        numberMachines.setBounds(100, 300, 50, 50);
        numberMachines.setFont(new Font("SERIF", Font.BOLD, 36));
        nmbText = new JLabel();
        nmbText.setBounds(5, 300, 100, 50);
        nmbText.setFont(new Font("SERIF", Font.BOLD, 16));
        nmbText.setText("Set Number:");
        Jackpot = new JLabel();
        Jackpot.setBounds(200, 100, 500, 100);
        Jackpot.setFont(new Font("SERIF", Font.BOLD, 36));
        setNr = new JButton();
        resetNr = new JButton();
        setNr.setBounds(160, 300, 75, 50);
        resetNr.setBounds(245, 300, 75, 50);
        setNr.setFont(new Font("SERIF", Font.BOLD, 16));
        resetNr.setFont(new Font("SERIF", Font.BOLD, 16));
        setNr.setText("OK");
        resetNr.setText("Reset");

        this.add(numberMachines);
        this.add(nmbText);
        this.add(setNr);
        this.add(resetNr);
        this.add(Jackpot);
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setLocation(width - (int) (width * 0.9), height - (int) (height * 0.97));
        this.setTitle("SlotMachine Manager");
        this.setResizable(false);
        this.add(mPanel);
        this.setVisible(true);


    }

    void ToggleOkButton() {
        if (setNr.isEnabled()) {
            setNr.setEnabled(false);
        } else if (setNr.isEnabled() == false) {
            setNr.setEnabled(true);
        }

    }

    void SetJackpot(double jackpot) {
        Jackpot.setText("Current Jackpot: €" + jackpot);
        Jackpot.revalidate();
        Jackpot.repaint();
    }

    void AddOkListener(ActionListener listen) {
        setNr.addActionListener(listen);
    }

    void AddResetListener(ActionListener listen) {
        resetNr.addActionListener(listen);
    }

    GraphicsDevice GetGraphicsDevice() {
        return gd;
    }

    public int GetNumberBox() {
        try {
            return Integer.parseInt(numberMachines.getText());
        } catch (Exception ex) {
            DisplayErrorMessage("You need to put numbers in here!");
            return 0;
        }
    }

    public void DisplayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
