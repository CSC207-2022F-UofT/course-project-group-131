import java.util.*;

public class TempDataStorage {
    Map<String, Item> inventory;

    TempDataStorage(Map<String, Item> inventory){
        this.inventory = inventory;
    }

    public void updateQuantity(String serinum, int modified_quantity){
        // modified_quantity can be both positive and negative!
        Item value = this.inventory.get(serinum);
        int quantity = value.getQuantity() + modified_quantity;
        value.setQuantity(quantity);
    }

    public boolean checkQuantity(String serinum, int decreased_quantity){
        Item value = this.inventory.get(serinum);
        int quantity = value.getQuantity();
        if (quantity - decreased_quantity >= 0){
            return true;
        }else{
            throw new Exception("Quantity will be going negative!")
        }
    }

    public void updatePrice(String serinum, double modified_price){
        // modified_price can be both positive and negative
        Item value = this.inventory.get(serinum);
        double price = value.getPrice() + modified_price;
        value.setPrice(price);
    }

    public boolean checkPrice(String serinum, double decreased_quantity){
        Item value = this.inventory.get(serinum);
        double price = value.getPrice();
        if (price - decreased_quantity >= 0){
            return true;
        }else{
            throw new Exception("Price will be going negative!")
        }
    }

    public Map<String, Item> getInventory(){
        return this.inventory;
    }
}
