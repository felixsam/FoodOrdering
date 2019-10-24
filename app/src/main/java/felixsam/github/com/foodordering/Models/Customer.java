package felixsam.github.com.foodordering.Models;

public class Customer {
    private String Name;
    private Integer UserID;

    public Customer(Integer user_ID ,String name){
        UserID = user_ID;
        Name = name;
    }

    public String getName(){
        return Name;
    }

    public Integer getUserID(){
        return UserID;
    }

    public void setName(String new_name){
        Name = new_name;
    }

    public void setUserID(Integer new_userID){
        UserID = new_userID;
    }

}
