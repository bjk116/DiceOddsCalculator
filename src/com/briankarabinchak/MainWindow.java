package com.briankarabinchak;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    private JPanel mainPanel;
    private JComboBox diceTypeOptions;
    private JLabel DiceTypeOptionsLabel;
    private JTextField protectionCriteria;
    private JLabel NumberOfDieLabel;
    private JTextField numberOfDie;
    private JButton RunSimulationBtn;
    private JScrollPane tableScrollPane;
    private Object[][] diceSetsData = {};
    private String[] diceSetsHeaders = {"Dice Type", "Protection", "Number of Dice"};
    private JTable diceSets = new JTable(diceSetsData, diceSetsHeaders);
    Simulation sim = new Simulation();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Add Dice Group");
        frame.setContentPane(new MainWindow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public MainWindow() {
        RunSimulationBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //This action adds the dice setup to the table, which will be parsed row by row and added to the simulation at the appropriate time
                String diceType = (String) diceTypeOptions.getItemAt(diceTypeOptions.getSelectedIndex());
                int protectionCriteriaNum = Integer.parseInt(protectionCriteria.getText());
                int numberOfDiceForSim = Integer.parseInt(numberOfDie.getText());
                System.out.println("Attemping simulations on a " + diceType + " with protection at " + protectionCriteriaNum + " with " + numberOfDiceForSim + " dice.");
                int maxValue = sim.parseDiceType(diceType);
            }
        });
    }
}
