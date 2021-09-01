package com.briankarabinchak;

import java.util.concurrent.ThreadLocalRandom;

public class Die {
    //Just in case we want to examine
    int currentValue;
    int minValue=1;
    int maxValue;
    int negationCriteria;

    public Die() {
        this.maxValue = 6;
        this.negationCriteria=5;
    }

    public Die(int maxValue, int negationCriteria) {
        this.maxValue = maxValue;
        this.negationCriteria = negationCriteria;
    }

    public int roll() {
        this.currentValue = ThreadLocalRandom.current().nextInt(this.minValue, this.maxValue+1);
        return this.currentValue;
    }

    public boolean negatedDamage() {
        //is the current value of the dice enough to negate damage?
        return this.currentValue >= this.negationCriteria;
    }

    public void print() {
        System.out.println("Die maxValue:" + this.maxValue + ", protection:" + this.negationCriteria + "" + "currentValue: " + this.currentValue);
    }
}