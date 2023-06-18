import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    System.out.println( " what is your name");
    String customerName = input.next();

    System.out.println("How much money");
    int money = input.nextInt();
    
    Customer customer = new Customer ( customerName, money);
    TakeOutSimulator takeOutSimulator = new TakeOutSimulator( customer, new FoodMenu());

    takeOutSimulator.startTakeOutSimulator();

    }    
}
