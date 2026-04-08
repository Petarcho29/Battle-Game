package net.petarcho;

public class Tank extends Character{

    public Tank() {
        super("Tank", 1600);
    }

    public void basic(Character target) {
        System.out.println("Tank dealt 100 damage to " + target.type);
        target.health -= 100;
    }

    public boolean ultimate(Assassin target) {
        if (this.energy >= 80) {
            System.out.println("Tank is defending Assassin from incoming attack" + target.type);
            target.defend = true;
            this.energy -= 80;
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
            if (this.health >= 1200)
                this.health = 1600;
            else
                this.health += 400;
            System.out.println("Tank Healed to " + this.health);
            this.canUseGadget = false;
            return false;
        }
        else {
            System.out.println("The gadget is already used!");
            return true;
        }
    }
}
