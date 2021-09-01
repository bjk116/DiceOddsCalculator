package com.briankarabinchak;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Simulation {
    int simulations;
    int numberOfDie;
    ArrayList<Die> testingDice = new ArrayList<Die>();
    HashMap<Integer, Integer> results = new HashMap<Integer, Integer>();

    public Simulation() {
        this.simulations = 100;
    }

    public Simulation(int simulations) {
        this.simulations = simulations;
    }

    public int parseDiceType(String diceType) {
        return Integer.parseInt(diceType.replaceAll("d", ""));
    }

    public void addDiceSet(int maxValue, int protectionCriteria, int numberOfDie) {
        for(int i = 0; i < numberOfDie; i++) {
            this.testingDice.add(new Die(maxValue, protectionCriteria));
        }
    }

    public void addDiceSet(String dieType, int protectionCriteria, int numberOfDie) {
        int maxValue = parseDiceType(dieType);
        addDiceSet(maxValue, protectionCriteria, numberOfDie);
    }

    public void setSimulations(int simulations) {
        this.simulations = simulations;
    }

    public void manuallySetParameters() {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("What is the maximum value of this die? -1 to exit.");
            int maxValue = input.nextInt();
            if (maxValue == -1)
                break;
            System.out.println("What is the protection criteria of this die? -1 to exit");
            int protectionCriteria = input.nextInt();
            if (protectionCriteria == -1)
                break;
            System.out.println("How many of these die are being rolled? -1 to exit");
            int numberOfDie = input.nextInt();
            if (numberOfDie == -1)
                break;
        }
        initializeResults();
    }

    private void initializeResults() {
        // <= because its necessary
        for (int i = 0; i <= testingDice.size(); i++) {
            results.put(i, 0);
        }
    }

    public double[] run(int simulations) {
        System.out.println("Running simulation...");
        double[] resultSet = new double[simulations];
        for(int i = 0; i < simulations; i++) {
            if (i%1000 == 0){
                System.out.println("In simulation " + i);
            }
            int totalNegatedDamage = 0;
            for (Die temp: this.testingDice) {
//                System.out.println("Just rolled a " + temp.roll());
                temp.roll();
                if (temp.negatedDamage()) {
                    totalNegatedDamage++;
                }
            }
            // updates or creates key
            this.results.put(totalNegatedDamage, this.results.getOrDefault(totalNegatedDamage, totalNegatedDamage) + 1);
            resultSet[i] = (double)totalNegatedDamage;
        }
        System.out.println(this.results);
        return resultSet;
    }

    public void clearDiceSet() {
        this.testingDice.clear();
    }

    public void printDiceSet() {
        System.out.println("Current dice set in Simulation is:");
        for(int i = 0; i < this.testingDice.size(); i++) {
            Die tempDie = this.testingDice.get(i);
            tempDie.print();
        }
    }
}
