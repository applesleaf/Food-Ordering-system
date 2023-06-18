import java.util.ArrayList;
import java.util.List;

// Create FoodMenu class here
public class FoodMenu{
  private List<Food> menu;

  public FoodMenu(){
    menu = new ArrayList<>();
    addFood();
  }

  public void addFood(){
     Food food1 = new Food("Chicken Curry","with rice",12);
     Food food2 = new Food("Pizza","with beef, mushroom",10);
     Food food3 = new Food("Tacos","hard shell and beef", 7);

     menu.add(food1);
     menu.add(food2);
     menu.add(food3);
  }

  public Food getFood( int index){
    try{
      int menuIndex = index -1;
      return menu.get(menuIndex);
    }catch (IndexOutOfBoundsException e){
      return null;
    }
  }

  public Food getLowestCostFood(){
    Food lowestCostFood = menu.get(0);

    for( Food food: menu){
      if(food.getPrice()< lowestCostFood.getPrice()){
        lowestCostFood = food;
      }
    }
    return lowestCostFood;
  }

 
}