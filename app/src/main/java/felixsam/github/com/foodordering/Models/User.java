package felixsam.github.com.foodordering.Models;

public class User {

    private String user_name;
    private String name;
    private int UserID;
    private String role;


    public User(int userID, String Name, String user){
        UserID = userID;
        name = Name;
        user_name = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public void setRole(String new_role){
        role = new_role;
    }

    public String getRole(){
        return role;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


    //Show name as dropdown options in spinner when calling toString()
    @Override
    public String toString(){
        return user_name;
    }

}
