package com.example.finalproject;

import java.util.Random;

public class Monster {

    private String name;
    private int health, strength, dexterity, intelligence;

    public Monster (String name) {
        setMonsterName(name);
        health = rollStatsDie();
        strength = (health * 2);
        dexterity = (health * 2);
        intelligence = (health * 2);
    }

    private int rollStatsDie () {
        int roll = 1 + new Random().nextInt(6);
        return roll;
    }

    public int getMonsterHealth () {
        return health;
    }

    public int getMonsterStrength () {
        return strength;
    }

    public int getMonsterIntelligence () {
        return intelligence;
    }

    public int getMonsterDexterity () {
        return dexterity;
    }

    public void setMonsterName(String name) {
        this.name = name;
    }

    public String getMonsterName() {
        return name;
    }

}
