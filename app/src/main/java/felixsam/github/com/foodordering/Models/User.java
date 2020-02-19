package felixsam.github.com.foodordering.Models;

public class User {

    private String name;
    private int UserID;
    private String role;


    public User (int userID, String username){
        UserID = userID;
        name = username;
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

    //Show name as values in spinner when calling toString()
    @Override
    public String toString(){
        return name;
    }

}
