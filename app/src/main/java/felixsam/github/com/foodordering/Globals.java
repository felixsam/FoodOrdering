package felixsam.github.com.foodordering;


//Global variables for new development and testing

public class Globals {
    private static Globals instance;



    // Global variable

    private String user ="Felix";
    private Integer user_ID = -1;
    private String role = "Customer"; //Default Role

    // Restrict the constructor from being instantiated
    private Globals(){}


    public String getUser(){

        return this.user;
    }


    public void setUser(String new_user){

        this.user = new_user;
    }


    public Integer getUser_ID(){
        return this.user_ID;
    }

    public void setUser_ID(Integer new_userID){
        this.user_ID = new_userID;
    };


    public void setRole(String new_role){
        this.role = new_role;
    }

    public String getRole(){
        return this.role;
    }

    ////

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }
}
