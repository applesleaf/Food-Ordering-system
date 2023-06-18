import java.util.HashMap;
import java.util.Map;

// Create ShoppingBag class here
public class ShoppingBag<T extends PricedItem>{
  public Map<T, Integer> shoppingBag;

  public ShoppingBag(){
    shoppingBag = new HashMap<>(); 
  }

  public void addItem(T item){
    if( shoppingBag.containsKey(item)){
      int newItem = shoppingBag.get(item);
      shoppingBag.put(item, newItem +1);
    }else{
      shoppingBag.put(item, 1);
    }
  }

  public int getTotalPrice(){
    int grandTotal = 0;
    for( Map.Entry<T,Integer> entry: shoppingBag.entrySet()){
      T item = entry.getKey();
      int quantity = entry.getValue();
      int itemPrice = item.getPrice().intValue();
      int totalPriceForItem = quantity * itemPrice;
      grandTotal += totalPriceForItem;
    }
    return grandTotal;   
  }
}