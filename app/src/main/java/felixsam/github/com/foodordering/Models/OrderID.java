package felixsam.github.com.foodordering.Models;

public class OrderID {
    private Integer orderID;
    private String username;
    private String date;

    public OrderID(Integer order_ID, String user_name, String order_date){
        orderID = order_ID;
        username = user_name;
        date = order_date;
    }

    public Integer getOrderID(){
        return orderID;
    }

    public void setOrderID(Integer new_orderID){
        orderID = new_orderID;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String new_username){
        username = new_username;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String new_date){
        date = new_date;
    }


}
