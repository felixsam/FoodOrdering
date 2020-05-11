package felixsam.github.com.foodordering.Models;

public class Item_Model_Display {
    private int quantity;
    private String item_name;
    private int image_drawable;
    private double price;
    private String category;


    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int new_quantity){
        this.quantity = new_quantity;
    }

    public String getItem_Name(){
        return item_name;
    }

    public void setItem_Name(String new_name){
        this.item_name = new_name;
    }

    public int getImage_drawable(){
        return image_drawable;
    }

    public void setImage_drawable(int image_drawable){
        this.image_drawable = image_drawable;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double new_price){
        this.price = new_price;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String new_category){
        category = new_category;
    }
}
