package felixsam.github.com.foodordering.Models;

public class Order {

    private String user;
    private String item_name;
    private double Price;
    private Integer Quantity;
    private Integer OrderID;
    private Integer Total_Amount;
    private String order_date;
    private double total_price;

    public Order(String username,String name_items, double price_item,double totalprice,String date,Integer order_ID, Integer quantity_item){
        OrderID = order_ID;
        user = username;
        item_name = name_items;
        total_price = totalprice;
        Price = price_item;
        Quantity = quantity_item;
        order_date = date;
    }


    public String get_date(){
        return order_date;
    }

    public void setDate(String new_date){
        order_date = new_date;
    }

    public String get_item_name(){

        return item_name;
    }

    public void set_item_name(String new_name){
        item_name = new_name;
    }

    public double get_totalPrice(){

        return total_price;
    }

    public void set_totalPrice(double new_price){
        total_price = new_price;
    }



    public double getPrice(){

        return Price;
    }

    public void setPrice(double new_price){
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

    public Integer getTotal_Amount(){

        return Total_Amount;
    }

    public void setTotalAmount(Integer new_totalamount){

        Total_Amount = new_totalamount;
    }

    public Integer getOrderID(){
        return OrderID; }

    private void setOrderID(Integer new_orderID) {

        OrderID = new_orderID;
    }

}
