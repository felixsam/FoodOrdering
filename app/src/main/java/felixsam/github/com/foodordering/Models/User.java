package felixsam.github.com.foodordering.Models;

public class User {

    private String userName;
    private String firstName;
    private String lastName;
    private String password;

    private int UserID;
    private String role;


    public User(int userID, String fName, String user){
        UserID = userID;
        firstName = fName;
        userName = user;
    }

    public User(int userID,String fName, String lName, String username){
        UserID = userID;
        firstName = fName;
        lastName = lName;
        userName = username;
    }

    public User(int userID,String fName, String lName, String username, String pWord){
        UserID = userID;
        firstName = fName;
        lastName = lName;
        userName = username;
        password = pWord;
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
