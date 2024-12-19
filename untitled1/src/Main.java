import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

class Food {
    String name;
    int type;//1 means desserts, 2 means nooodles & rice, 3 means main courses, 4 means salad, 5 means drinks
    double price;
    boolean exist;
    Food(String name, int type, double price, boolean exist) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.exist = true;
    }
}

class Driver {
    String[] names = {"Hamburger", "Irish Stew", "Margaret's Pizza", "Fish and chips", "Wellington Steak",
                    "Caesar Salad", "Pasta", "Paella", "Potato Salad", "Caramel Pudding", "Italian Ice Cream",
            "Tiramisu", "Coca Cola", "Red Wine", "Black Beer", "Cocktail"};
    int[] kind_exist ={0, 3, 2, 5, 2, 4};
    int flag=0;
    int cnt=2;
    int a=0;
    int gainedmoney=0;
    private List<Food> products = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public String change (int type_num) {
        String type;
        switch (type_num) {
            case 1-> type="dessert";
            case 2-> type="noodles & rice";
            case 3-> type="main course";
            case 4-> type="salad";
            case 5-> type="drinks";
            default->type="Error!";
        }
        return type;
    }
    public Driver() {
        initializeProducts();
    }

    private void initializeProducts() {
        //1 means desserts, 2 means noodles & rice, 3 means main courses, 4 means salad, 5 means drinks
        products.add(new Food("Hamburger", 3, 11, true));
        products.add(new Food("Irish Stew", 3, 20, true));
        products.add(new Food("Margaret's Pizza", 3, 16, true));
        products.add(new Food("Fish and chips", 3, 13, true));
        products.add(new Food("Wellington Steak", 3, 22, true));
        products.add(new Food("Caesar Salad", 4, 6, true));
        products.add(new Food("Pasta", 2, 12, true));
        products.add(new Food("Paella", 2, 16, true));
        products.add(new Food("Potato Salad", 4, 5, true));
        products.add(new Food("Caramel Pudding", 1, 5, true));
        products.add(new Food("Italian Ice Cream", 1, 4, true));
        products.add(new Food("Tiramisu", 1, 5, true));
        products.add(new Food("Coca Cola", 5, 2, true));
        products.add(new Food("Red Wine", 5, 19, true));
        products.add(new Food("Black Beer", 5, 7, true));
        products.add(new Food("Cocktail", 5, 5, true));
    }

    public void runMenu() {
        int option;
        do {
            if (cnt>=13){ option = 0; continue;}
            cnt++;
            option = mainMenu();
            if (flag == 1) {
                switch (option) {
                    case 1 -> printAllProducts();//预览所有菜品
                    case 2 -> printCurrentProducts(); // 假设这个方法会列出当前库存
                    case 3 -> skip();
                    default -> System.out.println("Invalid option entered: " + option);
                }
            }
            else {
                if (products.get(option - 1).type != products.get(a).type && kind_exist[products.get(option - 1).type] > 0) {
                    System.out.println("Your choice can't satisfy the customer's requirement because of its type! " +
                            "You can't gain money this time!");
                    continue;
                }if  (option < 17 && option > 0) {
                    if (!products.get(option - 1).exist) {
                        System.out.println("This dish has benn served before. Please choose another one.");
                        option=mainMenu();
                    }
                    //if (change(products.get(option - 1).type)!=change(products.get(a)))
                    products.get(option - 1).exist=false;
                    kind_exist[products.get(option - 1).type]--;
                    gainedmoney+=(int)products.get(option - 1).price;
                    System.out.println("You've gained $"+(int)products.get(option - 1).price+".");
                 }
                else {
                    System.out.println("Invalid option entered: " + option);
                }
            }
            System.out.println("\nPress enter key to continue...");
            input.nextLine();  // 清除输入缓冲区
        } while (option != 0);
        if (gainedmoney>=25) System.out.println("Well done! You have gained an income of $"+gainedmoney+".\nSo great!");
        else System.out.println("Don't be discouraged. You can try again.");
        System.out.println("Exiting...bye");
    }

    public int mainMenu() {
        flag=0;
        Random ran=new Random();
        int price[]={11, 20, 16, 13, 22, 6, 12, 16, 5, 5, 4, 5, 2, 19, 7, 5};
        if (cnt%3!=0) {
            System.out.println("""
                    Shop Menu
                    ---------
                       1) printAll Products
                       2) printCurrentProducts
                       3) skip
                       0) Exit
                    ==>>""");
            flag=1;
        }
        else {
            a=(int)ran.nextInt(16);
            System.out.println("Here comes a new customer!\n\n" +
                    "He/She wants a "+
                    change(products.get(a).type) +
                    ". \n\n" +
                    "You should satisfy his/her needs.");
            System.out.println("What we have: ");
            for (int i=0;i<16;i++)
            {
                System.out.print("  "+(i+1)+") "+names[i]+" ("+change(products.get(i).type)+") ");
                if (!products.get(i).exist)  System.out.println("(Sold out)");
                else System.out.println("");
            }
            System.out.println("""
                       0) Exit
                    ==>> """);
            flag=2;
        }
        return input.nextInt();
    }

    public void printAllProducts() {
        System.out.println("Products:");
        for (Food p : products) {
            System.out.println(p.name + ": $" + p.price);
        }
    }

    public void printCurrentProducts() {
        System.out.println("What we have: ");
        for (int i=0;i<16;i++)
        {
            System.out.print("  "+(i+1)+") "+names[i]+" ");
            if (!products.get(i).exist)  System.out.println("(Sold out)");
            else System.out.println("");
        }
        System.out.println("""
                       0) Exit
                    ==>> """);
    }
    public void skip() {
        ;
    }
}

public class Main {
    public static void main(String[] args) {
        Driver driver = new Driver();
        System.out.println("Welcome to your cafeteria!\n" +
                "You need to run this cafeteria properly to make it thrive!\n" +
                "Try your best to make most money as you can!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.runMenu();
    }
}
