package net.petarcho;

public class Tank extends Character{

    public Tank() {
        super("Tank", 1600);
    }

    public boolean basic(Character target) {
        System.out.println("Tank dealt 100 damage to " + target.type);
        target.health -= 100;
        this.afterAction(target, 100);
        return false;
    }

    public boolean ultimate() {
        boolean result;
        if (this.energy >= 80) {
            System.out.println("Tank is defending Assassin from incoming attack" + ((Assassin)(teamate)).type);
            ((Assassin)(teamate)).defend = true;
            this.energy -= 80;
            this.afterActionMage();
            ((Assassin)(teamate)).afterActionMage();
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
            if (this.health >= 1200)
                this.health = 1600;
            else
                this.health += 400;
            System.out.println("Tank Healed to " + this.health);
            this.canUseGadget = false;
            this.afterActionMage();
            ((Assassin)(teamate)).afterActionMage();
            result = false;
        }
        else {
            System.out.println("The gadget is already used!");
            result = true;
        }
        return result;
    }

    protected void afterAction(Character ch, int amount) {
        if (ch.type.equals("Warrior")) {
            if (((Warrior)(ch)).gadget) {
                this.health -= amount;
                ((Warrior) (ch)).gadget = false;

            }
        }
        if (targetedByMage){
            this.health -= 140;
            System.out.println("Mage dealt 140 damage to Tank");
        }
        ((Assassin)(teamate)).afterActionMage();
    }
    protected void afterActionMage() {
        if (targetedByMage){
            this.health -= 140;
            System.out.println("Mage dealt 140 damage to Tank");
        }
    }
}
