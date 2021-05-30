package felixsam.github.com.foodordering.Models;

import java.util.Comparator;

public class Checkout implements Comparable<Checkout> {
    private Integer userId;
    private String itemName;
    private double itemPrice;
    private Integer totalQuantity;
    private double totalAmount;


    //Add category placeholder
    private String category;

    public Checkout(Integer userId, String itemName, double itemPrice, Integer totalQuantity, double totalAmount){
        this.userId = userId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.totalQuantity = totalQuantity;
        this.totalAmount = totalAmount;

    }

    //Category
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    //User ID
    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer newUserId){
        userId = newUserId;
    }



    //Item Name
    public String getItemName(){
        return itemName;
    }

    public void setItemName(String newName){
        itemName = newName;
    }


    //Item Price for 1 item
    public double getPrice(){
        return itemPrice;
    }

    public void setPrice(double newPrice){
        itemPrice = newPrice;
    }


    //Total Quantity
    public Integer getTotalQuantity(){
        return totalQuantity;
    }

    public void setTotalQuantity(Integer newTotalQuantity){
        totalQuantity = newTotalQuantity;
    }


    //Total Amount
    public double getTotalAmount(){
        return totalAmount;
    }

    public void setTotalAmount(double newTotalAmount){

        totalAmount = newTotalAmount;
    }

    //Show Checkout as a string
    @Override
    public String toString(){
        return itemName;
    }

    //Equality Check
    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;

        // it checks if the argument is of the type Item by comparing the classes
        // of the passed argument and this object.
        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        // type casting of the argument.
        Checkout checkout = (Checkout) obj;
        if(checkout.getUserId() == this.userId &&
                (checkout.getItemName().equals(this.itemName)) &&
                (checkout.getPrice() == this.itemPrice) &&
                (Math.abs(checkout.getTotalQuantity() - this.totalQuantity) < 0.01) &&
                (Math.abs(checkout.getTotalAmount() - this.totalAmount) < 0.01))
            return true;

        return false;
    }

    @Override
    public int compareTo(Checkout another){
        return itemName.compareTo(another.itemName);
    }

}
