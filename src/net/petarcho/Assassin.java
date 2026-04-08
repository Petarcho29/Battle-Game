package net.petarcho;

public class Assassin extends Character{
    protected boolean defend;
    protected boolean skip;
    public Assassin() {
        super("Assassin", 800);
        this.defend = false;
        this.skip = false;
    }

    public void basic(Character target) {
        System.out.println("Assassin dealt 90 damage to " + target.type);
        target.health -= 90;
    }

    public boolean ultimate(Character target) {
        if (this.energy >= 70) {
            System.out.println("Assasin targeted " + target.type);
            target.targetedByAssassin = true;
            this.energy -= 70;
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
            this.skip = true;
            this.canUseGadget = false;
            return false;
        }
        else {
            System.out.println("The gadget has already been used!");
            return true;
        }
    }
}
