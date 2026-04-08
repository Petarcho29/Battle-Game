package net.petarcho;

public class Mage extends Character{
    protected boolean doBasic;
    protected boolean targetedByAssassin;
    public Mage() {
        super("Mage", 900);
        this.doBasic = false;
    }

    public boolean basic(Character target) {
        System.out.println("Mage will deal damage to " + target.type);
        doBasic = true;
        return false;
    }

    public boolean ultimate(Character t1, Character t2) {
        if (this.energy >= 60) {
            System.out.println("Mage dealt 180 damage to both Tank and Assassin");
            t1.health -= 180;
            t2.health -= 180;
            this.energy -= 50;
            return false;
        }
        else {
            System.out.println("Not enough energy!");
            return true;
        }
    }

    public boolean gadget(Character target) {
        if (this.canUseGadget) {
            System.out.println("Gadget activated!");
            if (this.health >= 480)
                this.health = 900;
            else
                this.health += 420;
            System.out.println("Mage Healed to " + this.health);
            if (target.health >= 820)
                target.health = 1200;
            else
                target.health += 380;
            System.out.println("Warrior Healed to " + target.health);

            return false;
        }else {
            System.out.println("The gadget has already been used!");
            return true;
        }

    }
}
