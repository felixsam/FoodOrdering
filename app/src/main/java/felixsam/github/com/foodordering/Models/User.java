package felixsam.github.com.foodordering.Models;

public class User {

    private String userName;
    private String firstName;
    private String lastName;
    private String password;

    private String phoneNumber;

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

    public User(int userID,String fName, String lName, String username, String pWord, String pNumber){
        UserID = userID;
        firstName = fName;
        lastName = lName;
        userName = username;
        password = pWord;
        phoneNumber = pNumber;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    //Equality Check
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;

        // it checks if the argument is of the type User by comparing the classes
        // of the passed argument and this object.
        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        // type casting of the argument.
        User user = (User) obj;
        if(user.getUserID() == this.UserID &&
                (user.getFirstName().equals(this.firstName)) &&
                (user.getLastName().equals(this.lastName)) &&
                (user.getRole().equals(this.role)) &&
                (user.getPhoneNumber().equals(this.phoneNumber)))
            return true;

        return false;
    }

}
