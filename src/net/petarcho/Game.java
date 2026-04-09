package net.petarcho;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private static Warrior warrior;
    private static Mage mage;
    private static Tank tank;
    private static Assassin assassin;
    private  static Random random;
    private static Scanner scanner;
    public static void start() {
        warrior = new Warrior();
        mage = new Mage();
        tank = new Tank();
        assassin = new Assassin();
        scanner = new Scanner(System.in);
        warrior.teamate = mage;
        mage.teamate = warrior;
        assassin.teamate = tank;
        tank.teamate = assassin;
        random = new Random();
        if (random.nextBoolean())
            player1Turn();

        else
            player2Turn();
    }

    private static void player1Turn() {
        System.out.println("------------\nPlayer 1\n------------");
        boolean done = true;
        System.out.println("Choose Character(" + ((warrior.isAlive()) ? "Warrior" : "") + ((mage.isAlive()) ? "Mage" : "") + ")");
        String attacker = scanner.nextLine().toLowerCase();
        while (!((attacker.equals("warrior") && warrior.isAlive()) || (attacker.equals("mage") && mage.isAlive()))) {
            System.out.println("This Character is eather dead or doesn't exist");
            System.out.println("Choose Character(" + ((warrior.isAlive()) ? "Warrior" : "") + ((mage.isAlive()) ? "Mage" : "") + ")");
            attacker = scanner.nextLine().toLowerCase();
        }
        while (done) {
            System.out.println("Enter Operation(basic, ultimate, gadget, di (display info)):");
            String o = scanner.nextLine().toLowerCase();
            if (o.equals("di")) {
                displayInfo();
            }
            while (!(o.equals("basic") || o.equals("ultimate") || o.equals("gadget"))) {
                System.out.println("You entered eather \"di\" or there isn't such operation");
                System.out.println("Enter Operation(basic, ultimate, gadget, di (display info)):");
                o = scanner.nextLine().toLowerCase();
                if (o.equals("di")) {
                    displayInfo();
                }
            }
            if (attacker.equals("warrior")) {
                switch (o) {
                    case "basic": {
                        String target = choosetargetp1();
                        if (target.equals("tank")) {
                            done = warrior.basic(tank);
                        }
                        if (target.equals("assassin")) {
                            done = warrior.basic(assassin);
                        }
                        break;
                    }
                    case "ultimate": {
                        String target = choosetargetp1();
                        if (target.equals("tank")) {
                            done = warrior.ultimate(tank);
                        }
                        if (target.equals("assassin")) {
                            done = warrior.ultimate(assassin);
                        }
                        break;
                    }
                    case "gadget": {
                        done = warrior.gadget();
                        break;
                    }
                }
            }
            if (attacker.equals("mage")) {
                switch (o) {
                    case "basic": {
                        String target = choosetargetp1();
                        if (target.equals("tank")) {
                            done = mage.basic(tank);
                        }
                        if (target.equals("assassin")) {
                            done = mage.basic(assassin);
                        }
                        break;
                    }
                    case "ultimate": {
                        done = mage.ultimate(tank, assassin);
                        break;
                    }
                    case "gadget": {
                        done = mage.gadget(warrior);
                        break;
                    }
                }
            }
        }
        if (attacker.equals("warrior") && warrior.energy <= 90)
            tank.energy += 10;
        if (attacker.equals("mage") && mage.energy <= 90)
            assassin.energy += 10;
        player2Turn();
    }


    private static void player2Turn() {
        System.out.println("------------\nPlayer 2\n------------");
        boolean done = true;
        System.out.println("Choose Character(" + ((tank.isAlive()) ? "Tank" : "") + ((assassin.isAlive()) ? "Assassin" : "") + ")");
        String attacker = scanner.nextLine().toLowerCase();
        while (!((attacker.equals("tank") && tank.isAlive()) || (attacker.equals("assassin") && assassin.isAlive()))) {
            System.out.println("This Character is eather dead or doesn't exist");
            System.out.println("Choose Character(" + ((tank.isAlive()) ? "Tank" : "") + ((assassin.isAlive()) ? "Assassin" : "") + ")");
            attacker = scanner.nextLine().toLowerCase();
        }
        while (done) {
            System.out.println("Enter Operation(basic, ultimate, gadget, di (display info)):");
            String o = scanner.nextLine().toLowerCase();
            if (o.equals("di")) {
                displayInfo();
            }
            while (!(o.equals("basic") || o.equals("ultimate") || o.equals("gadget"))) {
                System.out.println("You entered eather \"di\" or there isn't such operation");
                System.out.println("Enter Operation(basic, ultimate, gadget, di (display info)):");
                o = scanner.nextLine().toLowerCase();
                if (o.equals("di")) {
                    displayInfo();
                }
            }
            if (attacker.equals("tank")) {
                switch (o) {
                    case "basic": {
                        String target = choosetargetp2();
                        if (target.equals("warrior")) {
                            done = tank.basic(warrior);
                        }
                        if (target.equals("mage")) {
                            done = tank.basic(mage);
                        }
                        break;
                    }
                    case "ultimate": {
                        done = tank.ultimate();
                        break;
                    }
                    case "gadget": {
                        done = tank.gadget();
                        break;
                    }
                }
            }
            if (attacker.equals("assassin")) {
                switch (o) {
                    case "basic": {
                        String target = choosetargetp1();
                        if (target.equals("warrior")) {
                            done = assassin.basic(warrior);
                        }
                        if (target.equals("mage")) {
                            done = assassin.basic(mage);
                        }
                        break;
                    }
                    case "ultimate": {
                        String target = choosetargetp1();
                        if (target.equals("warrior")) {
                            done = assassin.ultimate(warrior);
                        }
                        if (target.equals("mage")) {
                            done = assassin.ultimate(mage);
                        }
                        break;
                    }
                    case "gadget": {
                        assassin.gadget();
                        break;
                    }
                }
            }
        }
        if (attacker.equals("tank") && tank.energy <= 90)
            tank.energy += 10;
        if (attacker.equals("assassin") && assassin.energy <= 90)
            assassin.energy += 10;
        if (assassin.skip){
            player2Turn();
            assassin.skip = false;
        }else {
            player1Turn();
        }
    }


    private static String choosetargetp1() {
        System.out.println("Choose Target(" + ((tank.isAlive()) ? "Tank" : "") + ((assassin.isAlive()) ? "Assassin" : "") + ")");
        String target = scanner.nextLine().toLowerCase();
        while (!((target.equals("tank") && tank.isAlive()) || (target.equals("assassin") && assassin.isAlive()))) {
            System.out.println("This Character is eather dead or doesn't exist");
            System.out.println("Choose Target(" + ((tank.isAlive()) ? "Tank" : "") + ((assassin.isAlive()) ? "Assassin" : "") + ")");
            target = scanner.nextLine().toLowerCase();
        }
        return target;
    }

    private static String choosetargetp2() {
        System.out.println("Choose Target(" + ((warrior.isAlive()) ? "Warrior":"") + ((mage.isAlive()) ? "Mage":"") + ")");
        String target = scanner.nextLine().toLowerCase();
        while (!((target.equals("warrior") && warrior.isAlive()) || (target.equals("mage") && mage.isAlive()))) {
            System.out.println("This Character is eather dead or doesn't exist");
            System.out.println("Choose Target(" + ((warrior.isAlive()) ? "Warrior":"") + ((mage.isAlive()) ? "Mage":"") + ")");
            target = scanner.nextLine().toLowerCase();
        }
        return target;
    }


    private static void displayInfo() {
    }
}
