package net.petarcho;

public class Warrior extends Character{
    protected boolean gadget;
    public Warrior() {
        super("Warrior", 1200);
        gadget = false;
    }

    public boolean basic(Character target) {
        System.out.println("Warrior dealt 120 damage to " + target.type);
        target.health -= 120;
        this.afterAction();
        ((Mage)(teamate)).afterAction();
        return false;
    }

    public boolean ultimate(Character target) {
        boolean result;
        if (this.energy >= 50) {
            System.out.println("Warrior dealt 260 damage to " + target.type);
            target.health -= 260;
            this.energy -= 50;
            this.afterAction();
            ((Mage)(teamate)).afterAction();
            result = false;
        }
        else {
            System.out.println("Not enough energy!");
            result = true;
        }
        return result;
    }

    public boolean gadget() {
        boolean result;
        if (this.canUseGadget) {
            System.out.println("Gadget activated!");
            this.gadget = true;
            this.canUseGadget = false;
            this.afterAction();
            ((Mage)(teamate)).afterAction();
            result = false;
        }
        else {
            System.out.println("The gadget has already been used!");
            result = true;
        }
        return result;
    }

    protected void afterAction() {
        if (targetedByAssassin && reallyTargetedByAssassin) {
            if (random.nextBoolean() && random.nextBoolean()) {
                this.health -= 350;
                System.out.println("Assassin dealt 350 damage to Mage");
                targetedByAssassin = false;
                reallyTargetedByAssassin = false;
            }
        }
        if (targetedByAssassin && !reallyTargetedByAssassin) {
            reallyTargetedByAssassin = true;
        }
    }
}
