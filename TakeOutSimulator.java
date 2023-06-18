import java.util.Scanner;

// Create TakeOutSimulator class here
public class TakeOutSimulator{
  private Customer customer;
  private FoodMenu menu;
  private Scanner input;

  public TakeOutSimulator(Customer customer, FoodMenu menu){
    this.customer = customer;
    this.menu = menu;
    this.input = new Scanner(System.in);
  }

  private <T> T getOutputOnIntInput( String userInputPrompt, IntUserInputRetriever <T> intUserInputRetriever){
    while(true){
      System.out.println(userInputPrompt);
      if(input.hasNextInt()){
        int userInput = input.nextInt();
        input.nextLine();

         try{
        return intUserInputRetriever.produceOutputOnIntUserInput(userInput);
      }catch(IllegalArgumentException e){
        System.out.println( userInput + " is not a valid input, try again!");
      }
      }
     else{
        System.out.println("Input need to be an int type");
        input.next();
      }
    }

  }

public  boolean shouldSimulate(){
  String userPrompt = " 1 to prceed and 0 to stop";
  
  try{
    return getOutputOnIntInput(userPrompt, selection ->{
      if( selection ==1 ){
        System.out.println( " You have enough money to buy lowestfood ");
        return true;
      }
      else if(selection == 0){ 
        return false;
        }
        else{
          throw new IllegalArgumentException( " Invalid selection");
        }
    });
  }catch ( IllegalArgumentException e ){
    return shouldSimulate();
  }

  }
public Food getMenuSelection(){
  String userPrompt = " Todays Menu are\n"+ menu.toString()+"Choose a menu item";

  try{
    return getOutputOnIntInput( userPrompt, selection ->{
      Food food = menu.getFood(selection);
      if( food != null ){
        return food;
      }else{
        throw new IllegalArgumentException(" invald slection");
      }
    });
    
    }
    catch ( IllegalArgumentException e){
      return getMenuSelection();
  }
}

public boolean isStillOrderFood(){
  String userPrompt = " 1 to continue shopping and 0 to check out";

  try{
    return getOutputOnIntInput(userPrompt, selection ->{
      if( selection == 1){
        return true;
      }else if ( selection == 0){
        return false;
      }
      else{
        throw new IllegalArgumentException(" chose 1 or 0");
      }
    });
  }catch ( IllegalArgumentException e){
    return isStillOrderFood();
  }
}

public void checkOutCustomer(ShoppingBag<Food> shoppingBag){
  System.out.println(" payment is process");
   int totalPrice = shoppingBag.getTotalPrice();
   int newMoney = customer.getMoney() - totalPrice;
   customer.setMoney(newMoney);
   
   System.out.println( " Your remaining $$ is " + newMoney);
   System.out.println( "enjoy your food");
}

public void takeOutPrompt(){
  ShoppingBag<Food> shoppingBag = new ShoppingBag<>();
  int customerMoneyLeft = customer.getMoney();

  while(true){
    System.out.println("You have this much left" + customerMoneyLeft);
    System.out.println( " Chosee a menu item" + menu.toString());

    Food selectedFood = getMenuSelection();

    if( selectedFood != null){
      if( selectedFood.getPrice() <= customerMoneyLeft){
        shoppingBag.addItem(selectedFood);
        customerMoneyLeft -= selectedFood.getPrice();
        System.out.println("your total price is "+ shoppingBag.getTotalPrice());
      }
      else{
        System.out.println(" You dont have enough so pick another");
      }
    }

    boolean stillOrdering = isStillOrderFood();
    if( !stillOrdering ){
      checkOutCustomer(shoppingBag);
       break;
    }
   
  }
}

public void startTakeOutSimulator(){
  System.out.println(" Welcome to the restaurant!");
  System.out.println("Welcome "+customer.toString());

  while(shouldSimulate()){
    System.out.println("new Order");
    takeOutPrompt();
  }
}

}
