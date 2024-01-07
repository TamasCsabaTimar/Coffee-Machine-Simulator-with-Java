package machine;

import java.util.Scanner;

public class CoffeeMachine {

    enum State {
        MENU, BUY, EXIT,
        FILL_WATER, FILL_MILK, FILL_COFFEE_BEANS, FILL_DISPOSABLE_CUPS
    }

    private static final int[][] BEVERAGES = {
            {250,   0, 16, 4}, //espresso
            {350,  75, 20, 7}, //latte
            {200, 100, 12, 6}  //cappuccino
    };

    private State state = State.MENU;
    private int water = 400;
    private int milk = 540;
    private int coffeeBeans = 120;
    private int disposableCups = 9;
    private int money = 550;

    public void command(String msg) {
        if(state == State.MENU) {
            switch (msg) {
                case "buy" -> state = State.BUY;
                case "fill" -> state = State.FILL_WATER;
                case "take" -> take();
                case "remaining" -> remaining();
                case "exit" -> state = State.EXIT;
            }
        } else if (state == State.BUY) {
            if(!"back".equals(msg)) {
                int type = Integer.parseInt(msg);
                if(isAvailable(type)) {
                    water -= BEVERAGES[type - 1][0];
                    milk -= BEVERAGES[type - 1][1];
                    coffeeBeans -= BEVERAGES[type - 1][2];
                    disposableCups--;
                    money += BEVERAGES[type - 1][3];

                    System.out.println("I have enough resources, making you a coffee!");
                }
            }
            state = State.MENU;
        } else if (state == State.FILL_WATER) {
            water += Integer.parseInt(msg);
            state = State.FILL_MILK;
        } else if (state == State.FILL_MILK) {
            milk += Integer.parseInt(msg);
            state = State.FILL_COFFEE_BEANS;
        } else if (state == State.FILL_COFFEE_BEANS) {
            coffeeBeans += Integer.parseInt(msg);
            state = State.FILL_DISPOSABLE_CUPS;
        } else if (state == State.FILL_DISPOSABLE_CUPS) {
            disposableCups += Integer.parseInt(msg);
            state = State.MENU;
        }
    }


    private void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    private boolean isAvailable(int type) {
        if(water < BEVERAGES[type-1][0]) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < BEVERAGES[type-1][1]) {
            System.out.println("Sorry, not enough water!");
        } else if (coffeeBeans < BEVERAGES[type-1][2]) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (disposableCups == 0) {
            System.out.println("Sorry, not enough disposable cup!");
        } else {
            return true;
        }
        return false;
    }

    private void remaining() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", water);
        System.out.printf("%d ml of milk\n", milk);
        System.out.printf("%d g of coffee beans\n", coffeeBeans);
        System.out.printf("%d disposable cups\n", disposableCups);
        System.out.printf("$%d of money\n", money);
    }

    static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String typeStr = scanner.nextLine();
        coffeeMachine.command(typeStr);
    }

    static void fill() {
        System.out.println("Write how many ml of water you want to add:");
        coffeeMachine.command(scanner.nextLine());
        System.out.println("Write how many ml of milk you want to add:");
        coffeeMachine.command(scanner.nextLine());
        System.out.println("Write how many grams of coffee beans you want to add:");
        coffeeMachine.command(scanner.nextLine());
        System.out.println("Write how many disposable cups you want to add:");
        coffeeMachine.command(scanner.nextLine());
    }

    static CoffeeMachine coffeeMachine = new CoffeeMachine();
    static Scanner scanner = new Scanner(System.in);
    static void menu() {
        System.out.println();

        String action;
        do {
            System.out.println();
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            action = scanner.nextLine();
            coffeeMachine.command(action);
            switch (action) {
                case "fill" -> fill();
                case "buy" -> buy();
            }
        } while (!"exit".equals(action));
    }

    public static void main(String[] args) {
        menu();
    }
}
