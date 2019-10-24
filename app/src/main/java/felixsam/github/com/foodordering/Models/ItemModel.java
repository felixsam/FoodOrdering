package felixsam.github.com.foodordering.Models;

import felixsam.github.com.foodordering.Globals;

public class ItemModel {
    private String user;
    private String Name;
    private Integer Price;
    private Integer Quantity;
    private Integer userID;
    private Integer colID;

    Globals g = Globals.getInstance();

    public ItemModel(Integer ID, String username, String name_drink, Integer price_drink, Integer quantity_drink, Integer user_ID){
        colID = ID;
        user = username;
        Name = name_drink;
        Price = price_drink;
        Quantity = quantity_drink;
        userID = user_ID;
    }

    public String getName(){
        return Name;
    }

    public void setName(String new_name){
        Name = new_name;
    }

    public Integer getPrice(){
        return Price;
    }

    public void setPrice(Integer new_price){
        Price = new_price;
    }

    public Integer getQuantity(){
        return Quantity;
    }

    public void setQuantity(Integer new_quantity){
        Quantity = new_quantity;
    }

    public String getUserName(){
        return user ;
    }

    public void setUserName(String new_username){
        user = new_username;
    }

    public Integer getUserID(){return userID;}

    public void setUserID(Integer new_userID){userID = new_userID; }

    public Integer getColID(){
        return colID;
    }

    public void setColID(Integer new_colID){
        colID = new_colID;
    }
}
