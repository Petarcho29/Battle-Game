package net.petarcho;

import java.lang.invoke.LambdaMetafactory;

public class Assassin extends Character{
    protected boolean defend;
    protected boolean skip;
    public Assassin() {
        super("Assassin", 800);
        this.defend = false;
        this.skip = false;
    }

    public boolean basic(Character target) {
        System.out.println("Assassin dealt 90 damage to " + target.type);
        target.health -= 90;
        afterAction(target);
        return false;
    }

    public boolean ultimate(Character target) {
        boolean result;
        if (this.energy >= 80) {
            System.out.println("Assassin targeted " + target.type);
            target.targetedByAssassin = true;
            this.energy -= 80;
            this.afterActionMage();
            ((Tank)(teamate)).afterActionMage();
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
            this.skip = true;
            this.canUseGadget = false;
            this.afterActionMage();
            ((Tank)(teamate)).afterActionMage();
            result = false;
        }
        else {
            System.out.println("The gadget has already been used!");
            result = true;
        }
        return result;
    }

    protected void afterAction(Character ch) {
        if (ch.type.equals("Warrior")) {
            if (((Warrior)(ch)).gadget) {
                if (defend){
                    defend = false;
                    System.out.println("Warrior reflected 90 damage to Assassin, but Assassin had a shield from Tank");
                }
                else {
                    this.health -= 90;
                    System.out.println("Warrior reflected 90 damage to Assassin");
                }
                ((Warrior)(ch)).gadget = false;
            }
        }
        if (targetedByMage){
            if (!defend) {
                this.health -= 140;
                System.out.println("Mage dealt 140 damage to Assassin");
            }else
                System.out.println("Assassin had shield from Tank so didn't take damage");
            defend = false;
        }
        ((Tank)(teamate)).afterActionMage();
    }
    protected void afterActionMage() {
        if (targetedByMage){
            if (!defend) {
                this.health -= 140;
                System.out.println("Mage dealt 140 damage to Assassin");
            }else
                System.out.println("Assassin had shield from Tank so didn't take damage");
            defend = false;
        }
    }
}
