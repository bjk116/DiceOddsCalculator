package com.briankarabinchak;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class DiceOddCalculator extends JFrame {
    Simulation sim = new Simulation();

    public DiceOddCalculator(String title) {
        // Frame setup
        this.setSize(400, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle(title);

        //Layout and panel to house components
        MigLayout layout = new MigLayout("wrap 3", "[fill, grow]", "");
        JPanel panel = new JPanel(layout);

        //Components
        JLabel maxDiceLabel = new JLabel("Max value of die");
        JTextField maxDiceInput = new JTextField();
        JLabel protectionCriteriaLabel = new JLabel("Die protection Criteria:");
        JTextField protectionCriteriaInput = new JTextField("");
        JLabel numOfDiceLabel = new JLabel("Number of dice:");
        JTextField numOfDiceInput = new JTextField("");
        JButton addDice = new JButton("Add dice");
        JButton runSimulation = new JButton("Run Simulations");
        //Making Table
        String[] tableColumns = {"Dice Type", "Protection", "Nubmber of Dice"};
        Object[][] data = {{"6d", 5, 5}, {"20d",16,2}};
        JTable simParams = new JTable(data,tableColumns);
        simParams.setCellSelectionEnabled(true);
        JScrollPane tableScrollPane = new JScrollPane(simParams);
        ListSelectionModel cellSelectionModel = simParams.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //This next line makes the table take up the full height of the container
        simParams.setFillsViewportHeight(true);
        //Delete Button
        JButton deleteDie = new JButton("Delete Die");
        deleteDie.setEnabled(false);

        //Property Change events on table
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = simParams.getSelectedRow();
                if (selectedRow >= 0) {
                    deleteDie.setEnabled(true);
                } else {
                    deleteDie.setEnabled(false);
                }
            }
        });

        //Submit button logic
        runSimulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sim.clearDiceSet();
                for(int row = 0; row < simParams.getRowCount(); row++){
                    String diceType = simParams.getValueAt(row, 0).toString();
                    int protectionCriteria = Integer.parseInt(simParams.getValueAt(row, 1).toString());
                    int numOfDice = Integer.parseInt(simParams.getValueAt(row, 2).toString());
                    sim.addDiceSet(diceType, protectionCriteria, numOfDice);
                }
                sim.printDiceSet();
                String simulationsString = (String)JOptionPane.showInputDialog(
                        null,
                        "How many simulations do you want to run?",
                        "Simulations",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "10000"
                );
                try {
                    int simulations = Integer.parseInt(simulationsString);
                    double[] results = sim.run(simulations);
                    HistogramChart resultsChart = new HistogramChart(results);
                } catch (NumberFormatException e) {
                    //Alert user they need to enter a number
                    System.out.println("need a number dummy");
                }

            }
        });

        //add to panel
        panel.add(maxDiceLabel);
        panel.add(maxDiceInput, "wrap,grow,span 2");
        panel.add(protectionCriteriaLabel);
        panel.add(protectionCriteriaInput, "wrap,grow,span 2");
        panel.add(numOfDiceLabel);
        panel.add(numOfDiceInput, "grow, span 2");
        panel.add(addDice, "span, grow");
        panel.add(deleteDie, "span");
        panel.add(tableScrollPane, "span, grow");
        panel.add(runSimulation, "span");

        //Turn window on
        this.setContentPane(panel);
    }

    public static void main(String[] args) {
        DiceOddCalculator f = new DiceOddCalculator("Dice Odds Simulator");
        f.setVisible(true);
    }
}
