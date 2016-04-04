package SlotMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * Multi SlotMachine.
 * Design Patterns Eindopdracht.
 * Student: Marcel Schoeber
 * Studentnummer: 331910
 */

/**
 * View for SlotMachine.
 * Represents the GUI component of SlotMachine.
 */
public class SlotMachineView extends JFrame {

    //Fields
    private JLabel iconOne = new JLabel();
    private JLabel iconTwo = new JLabel();
    private JLabel iconThree = new JLabel();
    private JButton spinButton = new JButton();
    private JButton fiftyCentButton = new JButton();
    private JButton oneEuroButton = new JButton();
    private JButton twoEuroButton = new JButton();
    private JLabel cashLabel = new JLabel();

    /**
     * Constructor
     *
     * @param xLocation X-Pos
     * @param yLocation Y-Pos
     * @param title     Title of Window
     */
    public SlotMachineView(int xLocation, int yLocation, String title) {
        InitGui(xLocation, yLocation, title);
    }

    /**
     * Create Gui
     *
     * @param xLocation X-Pos
     * @param yLocation Y-Pos
     * @param title     Title of Window
     */
    void InitGui(int xLocation, int yLocation, String title) {
        JPanel sPanel = new JPanel();
        sPanel.setLayout(null);
        sPanel.add(iconOne);
        sPanel.add(iconTwo);
        sPanel.add(iconThree);
        sPanel.setBackground(Color.decode("#f5f6f7"));
        iconOne.setBounds(50, 100, 100, 100);
        iconTwo.setBounds(175, 100, 100, 100);
        iconThree.setBounds(300, 100, 100, 100);
        iconOne.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        iconTwo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        iconThree.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sPanel.add(spinButton);
        sPanel.add(fiftyCentButton);
        sPanel.add(oneEuroButton);
        sPanel.add(twoEuroButton);
        sPanel.add(cashLabel);
        spinButton.setText("Spin!");
        spinButton.setBounds(125, 225, 100, 100);
        spinButton.setSize(new Dimension(200, 50));
        fiftyCentButton.setBounds(310, 300, 100, 100);
        fiftyCentButton.setSize(new Dimension(50, 50));
        oneEuroButton.setBounds(370, 300, 100, 100);
        oneEuroButton.setSize(new Dimension(50, 50));
        twoEuroButton.setBounds(430, 300, 100, 100);
        twoEuroButton.setSize(new Dimension(50, 50));
        cashLabel.setBounds(370, 225, 100, 100);
        cashLabel.setSize(new Dimension(100, 50));
        cashLabel.setFont(new Font("SERIF", Font.BOLD, 18));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setLocation(xLocation, yLocation);
        this.setTitle(title);
        this.setResizable(false);
        this.add(sPanel);
        ImageLoader();
        this.setVisible(true);
    }

    /**
     * Set slot images/icons
     *
     * @param one   First Slot
     * @param two   Second Slot
     * @param three Third Slot
     */
    void SetSlots(ImageIcon one, ImageIcon two, ImageIcon three) {
        iconOne.setIcon(one);
        iconTwo.setIcon(two);
        iconThree.setIcon(three);
        iconOne.revalidate();
        iconTwo.revalidate();
        iconThree.revalidate();
        iconOne.repaint();
        iconTwo.repaint();
        iconThree.repaint();
    }

    /**
     * Disable view
     */
    void KillScreen() {
        this.setVisible(false);
        this.dispose();
    }

    /**
     * Retrieve Slot image
     *
     * @param number Slot number
     * @return ImageIcon of slot
     */
    ImageIcon GetSlotNumber(int number) {

        if (number == 1) {
            return (ImageIcon) iconOne.getIcon();
        }
        if (number == 2) {
            return (ImageIcon) iconTwo.getIcon();
        }
        if (number == 3) {
            return (ImageIcon) iconThree.getIcon();
        }
        return null;
    }

    /**
     * Display amount of cash in machine
     *
     * @param value Amount of cash
     */
    void SetCashLabel(String value) {
        cashLabel.setText("Cash: €" + value);
        cashLabel.revalidate();
        cashLabel.repaint();
    }

    /**
     * Load image for currency buttons
     */
    private void ImageLoader() {
        fiftyCentButton.setIcon(new ImageIcon("icons/50eurocent.png"));
        oneEuroButton.setIcon(new ImageIcon("icons/oneuro.png"));
        twoEuroButton.setIcon(new ImageIcon("icons/twoeuro.png"));
    }

    /*
        Listener for buttons
     */
    void AddSpinListener(ActionListener listen) {
        spinButton.addActionListener(listen);
    }

    void AddFiftyCentListener(ActionListener listen) {
        fiftyCentButton.addActionListener(listen);
    }

    void AddOneEuroListener(ActionListener listen) {
        oneEuroButton.addActionListener(listen);
    }

    void AddTwoEuroListener(ActionListener listen) {
        twoEuroButton.addActionListener(listen);
    }

    /**
     * Display a (error) message
     *
     * @param errorMessage Message to display
     */
    void DisplayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }


}
