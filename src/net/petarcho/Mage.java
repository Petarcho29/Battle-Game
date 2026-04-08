package net.petarcho;

public class Mage extends Character{
    private boolean doBasic;
    public Mage() {
        super("net.petarcho.Mage", 900);
        this.doBasic = false;
    }

    protected boolean basic(Character target) {
        System.out.println("net.petarcho.Mage will deal damage to " + target.type);
        doBasic = true;
        return false;
    }

    protected boolean ultimate(Character t1, Character t2) {
        if (this.energy >= 60) {
            System.out.println("net.petarcho.Mage dealt 180 damage to both Tank and Assassin");
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

    protected boolean gadget(Character target) {
        if (this.canUseGadget) {
            if (this.health >= 480)
                this.health = 900;
            else
                this.health += 420;
            if (target.health >= 820)
                target.health = 1200;
            else
                target.health += 380;
            return false;
        }else {
            System.out.println("The gadget is already used!");
            return true;
        }

    }
}
