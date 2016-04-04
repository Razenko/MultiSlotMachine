package Master;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View for Master.
 */
public class MasterView extends JFrame {
    private GraphicsDevice gd;
    private JLabel nmbText;
    private JButton setNr;
    private JButton resetNr;
    private JLabel jackpot;
    private JTextField numberMachines;
    private JLabel winGraphic;

    public MasterView(GraphicsDevice gd) {
        InitGui(gd);
    }

    private void InitGui(GraphicsDevice gd) {
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
        jackpot = new JLabel();
        jackpot.setBounds(200, 100, 500, 100);
        jackpot.setFont(new Font("SERIF", Font.BOLD, 36));
        winGraphic = new JLabel();
        winGraphic.setBounds(250, 0, 350, 150);
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
        this.add(jackpot);
        this.add(winGraphic);
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

    public void SetWinGraphic(ImageIcon image) {
        winGraphic.setIcon(image);
        winGraphic.revalidate();
        winGraphic.repaint();
    }


    void ToggleOkButton() {
        if (setNr.isEnabled()) {
            setNr.setEnabled(false);
        } else if (!setNr.isEnabled()) {
            setNr.setEnabled(true);
        }

    }

    void SetJackpot(double jackpot) {
        this.jackpot.setText("Current Jackpot: €" + jackpot);
        this.jackpot.revalidate();
        this.jackpot.repaint();
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

    int GetNumberBox() {
        try {
            return Integer.parseInt(numberMachines.getText());
        } catch (Exception ex) {
            DisplayErrorMessage("You need to put numbers in here!");
            return 0;
        }
    }

    void DisplayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
