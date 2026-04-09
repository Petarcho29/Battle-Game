package net.petarcho;

import java.util.Random;

public abstract class Character {
    protected Random random;
    protected String type;
    protected int health;
    protected int energy;
    protected final int maxEnergy;
    protected boolean canUseGadget;
    protected boolean targetedByAssassin;
    protected boolean reallyTargetedByAssassin;
    protected boolean targetedByMage;
    protected Character teamate;

    public Character(String type, int health) {
        this.type = type;
        this.energy = 0;
        this.health = health;
        this.maxEnergy = 100;
        this.canUseGadget = true;
        this.targetedByAssassin = false;
        this.reallyTargetedByAssassin = false;
        this.targetedByMage = false;
        this.random = new Random();
    }
    public boolean isAlive() {
        if (this.health <= 0) {
            this.health = 0;
            return false;
        }
        else
            return true;
    }
}
