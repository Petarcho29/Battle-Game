package net.petarcho;

public abstract class Character {
    protected String type;
    protected int health;
    protected int energy;
    protected final int maxEnergy;
    protected boolean canUseGadget;

    public Character(String type, int health) {
        this.type = type;
        this.energy = 0;
        this.health = health;
        this.maxEnergy = 100;
        this.canUseGadget = true;
    }
}
