public class Warrior extends Character{
    private boolean gadget;
    public Warrior() {
        super("Warrior", 1200);
        gadget = false;
    }

    protected void basic(Character target) {
        System.out.println("Warrior dealt 120 damage to " + target.type);
        target.health -= 120;
    }

    protected boolean ultimate(Character target) {
        if (this.energy >= 50) {
            System.out.println("Warrior dealt 260 damage to " + target.type);
            target.health -= 260;
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
            System.out.println("Gadget activated!");
            this.gadget = true;
            this.canUseGadget = false;
            return false;
        }
        else {
            System.out.println("The gadget is already used!");
            return true;
        }
    }

}
