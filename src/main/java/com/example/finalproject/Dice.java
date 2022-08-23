package com.example.finalproject;

import java.util.Random;

public class Dice {

    public int roll() {
        int roll = 1 + new Random().nextInt(20);
        return roll;
    }

    public int rollDamage(int r, int d, int s) {
        int damage;
        if (r >= d) {
            damage = s / 3;
            if (damage <= 0) {
                damage = 1;
            }
        } else {
            damage = 0;
        }
        return damage;
    }

}