package felixsam.github.com.foodordering.Models;

public class Checkout {
    private String user;
    private Integer user_id;
    private String item_name;
    private Integer item_price;
    private Integer total_quantity;
    private Integer Total_Amount;

    public Checkout(String username,Integer userid, String name_item, Integer price_item,Integer total_item_quantity,Integer total_price){
        user = username;
        user_id = userid;
        item_name = name_item;
        item_price = price_item;
        total_quantity = total_item_quantity;
        Total_Amount = total_price;

    }


    //Username
    public String getUserName(){
        return user ;
    }

    public void setUserName(String new_username){
        user = new_username;
    }


    //User ID
    public Integer getuserId(){
        return user_id;
    }

    public void setUser_id(Integer new_userid){
        user_id = new_userid;
    }



    //Item Name
    public String getItem_name(){
        return item_name;
    }

    public void setItem_name(String new_name){
        item_name = new_name;
    }


    //Item Price for 1 item
    public Integer getPrice(){
        return item_price;
    }

    public void setPrice(Integer new_price){
        item_price = new_price;
    }


    //Total Quantity
    public Integer getTotal_quantity(){
        return total_quantity;
    }

    public void setTotal_quantity(Integer new_quantity){
        total_quantity = new_quantity;
    }


    //Total Amount
    public Integer getTotal_Amount(){
        return Total_Amount;
    }

    public void setTotalAmount(Integer new_totalamount){

        Total_Amount = new_totalamount;
    }
}
