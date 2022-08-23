package com.example.finalproject;
import java.util.Random;

public class Player {

    private String name;
    private int health, strength, dexterity, intelligence;
    private int totalGold;

    public Player (String name) {
        setPlayerName(name);
        health = 20;
        strength = rollStatsDie();
        dexterity = rollStatsDie();
        intelligence = rollStatsDie();
        totalGold = 0;
    }

    public int rollStatsDie () {
        int roll = 1 + new Random().nextInt(6);
        roll += 1 + new Random().nextInt(6);
        roll += 1 + new Random().nextInt(6);
        return roll;
    }

    public void setPlayerName(String name) {
        this.name = name;
    }

    public String getPlayerName() {
        return name;
    }

    public int getPlayerStrength() {
        return strength;
    }

    public int getPlayerDexterity() {
        return dexterity;
    }

    public int getPlayerIntelligence() {
        return intelligence;
    }

    public int getPlayerHealth() {
        return health;
    }

    public int getPlayerGold() {
        return totalGold;
    }


}
