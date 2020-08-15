package felixsam.github.com.foodordering.Models;

public class User {

    private String userName;
    private String firstName;


    private String lastName;
    private int UserID;
    private String role;


    public User(int userID, String Name, String user){
        UserID = userID;
        firstName = Name;
        userName = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    //Show userName as dropdown options in spinner when calling toString()
    @Override
    public String toString(){
        return userName;
    }

}
