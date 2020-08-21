package felixsam.github.com.foodordering;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import felixsam.github.com.foodordering.Models.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String DB_NAME = "myDatabase";

    //TABLE: CUSTOMERS
    public static final String TABLE_NAME_USERS = "users";
    public static final String USERS_COL1_ID = "_id";
    public static final String USERS_COL2_FIRST_NAME ="FIRST_NAME";
    public static final String USERS_COL3_LAST_NAME ="LAST_NAME";
    public static final String USERS_COL4_PHONE_NUMBER ="PHONE_NUMBER";
    public static final String USERS_COL5_LOGGED_IN = "LOG_IN_STATUS";
    public static final String USERS_COL6_ROLE = "ROLE";
    public static final String USERS_COL7_USERNAME = "USERNAME";
    public static final String USERS_COL8_PASSWORD = "PASSWORD";

    //TABLE: ITEMS
    public static final String TABLE_NAME_ITEMS = "items";
    public static final String ITEMS_COL1_ID = "_id";
    public static final String ITEMS_COL2_USER_ID = "USER_ID";
    public static final String ITEMS_COL3_FIRST_NAME = "FIRST_NAME";
    public static final String ITEMS_COL4_CATEGORY = "CATEGORY";
    public static final String ITEMS_COL5_ITEM_NAME = "ITEM_NAME";
    public static final String ITEMS_COL6_PRICE = "PRICE";
    public static final String ITEMS_COL7_QUANTITY = "QUANTITY";
    public static final String ITEMS_COL8_ORDERID = "ORDER_ID";

    //TABLE: ORDERS
    public static final String TABLE_NAME_ORDERS = "Orders";
    public static final String ORDERS_COL1_ID = "_id";
    public static final String ORDERS_COL2_CUSTOMER_ID = "CUST_ID";
    public static final String ORDERS_COL3_DATE = "DATE";
    public static final String ORDERS_COL4_STATUS = "STATUS";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
/*******************************************************************************************************************************************************
 CREATE DB TABLE
 *******************************************************************************************************************************************************/

        //CUSTOMERS TABLE
        String createTableCustomers =
                "CREATE TABLE " + TABLE_NAME_USERS + " (" +
                        USERS_COL1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        USERS_COL2_FIRST_NAME + " TEXT, " +
                        USERS_COL3_LAST_NAME + " TEXT, " +
                        USERS_COL4_PHONE_NUMBER + " TEXT, " +
                        USERS_COL5_LOGGED_IN + " TEXT DEFAULT 'FALSE', " +
                        USERS_COL6_ROLE + " TEXT DEFAULT 'Admin', " +
                        USERS_COL7_USERNAME + " TEXT, " +
                        USERS_COL8_PASSWORD + " TEXT, " +
                        "UNIQUE (" + USERS_COL7_USERNAME + ")"
                        + ")";

        //ITEMS TABLE
        String createTableItems =
                "CREATE TABLE " + TABLE_NAME_ITEMS + " (" +
                        ITEMS_COL1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ITEMS_COL2_USER_ID + " INTEGER," +
                        ITEMS_COL3_FIRST_NAME + " TEXT, " +
                        ITEMS_COL4_CATEGORY + " TEXT, " +
                        ITEMS_COL5_ITEM_NAME + " TEXT, " +
                        ITEMS_COL6_PRICE + " DOUBLE, " +
                        ITEMS_COL7_QUANTITY + " INTEGER," +
                        ITEMS_COL8_ORDERID + " INTEGER," +
                        " CONSTRAINT FK_USERID" +
                        " FOREIGN KEY " + "(" + ITEMS_COL2_USER_ID + ")" +
                        " REFERENCES " + TABLE_NAME_USERS + " (" + USERS_COL1_ID + ")"
                        + ")";

        //ORDERS TABLE
        String createTableOrders =
                "CREATE TABLE " + TABLE_NAME_ORDERS + " (" +
                        ORDERS_COL1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ORDERS_COL2_CUSTOMER_ID + " INTEGER, " +
                        ORDERS_COL3_DATE + " TEXT, " +
                        ORDERS_COL4_STATUS + " TEXT DEFAULT 'TO DO'"
                        + ")";

        db.execSQL(createTableCustomers);
        db.execSQL(createTableItems);
        db.execSQL(createTableOrders);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ORDERS);

        onCreate(db);
    }



    /*******************************************************************************************************************************************************
     SQL QUERIES FOR CUSTOMERS
     *******************************************************************************************************************************************************/

    public Cursor getUserIdAndName(){
        SQLiteDatabase db_customer = this.getWritableDatabase();

        return db_customer.rawQuery("SELECT " + USERS_COL1_ID
                + ", " + USERS_COL2_FIRST_NAME
                + ", " + USERS_COL7_USERNAME
                + " FROM " + TABLE_NAME_USERS, null);
    }

    public int getUserID(String username){
        int userID = -1;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor data = db.rawQuery("SELECT " + USERS_COL1_ID
                + " FROM " + TABLE_NAME_USERS
                + " WHERE " + USERS_COL7_USERNAME + " = '" +  username + "'",null);
        if (data.moveToFirst()){
            userID = data.getInt(data.getColumnIndex(USERS_COL1_ID));
        }

        return userID;

    }

    //get username of first entry in customers table
    public String getTopUserName(){
        String username = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + USERS_COL7_USERNAME
                + " FROM " + TABLE_NAME_USERS
                + " LIMIT 3 ",null);
        if (data.moveToFirst()){
            username = data.getString(data.getColumnIndex(USERS_COL7_USERNAME));
        }
        return username;
    }

    public String getPassword(String username){
        String password = "";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + USERS_COL8_PASSWORD
                + " FROM " + TABLE_NAME_USERS
                + " WHERE " + USERS_COL7_USERNAME + " = '" + username + "'",null);
        if (data.moveToFirst()){
            password = data.getString(data.getColumnIndex(USERS_COL8_PASSWORD));
        }

        return password;
    }

    public void updatePassword(String username,String oldPassword, String newPassword){

        if (!oldPassword.equals(getPassword(username))){
            Log.d(TAG,"Old password does not match");
        }else {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "UPDATE " + TABLE_NAME_USERS
                    + " SET " + USERS_COL8_PASSWORD + " = '" + newPassword + "'"
                    + " WHERE " + USERS_COL7_USERNAME + " = '" + username + "'";

            Log.d(TAG, "updatePassword: query: " + query);
            Log.d(TAG, "updatePassword: Setting password to " + newPassword  + "For username: " + username);
            db.execSQL(query);
        }
    }

    public User getUser(String username){
        User user;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + USERS_COL1_ID
                + ", " + USERS_COL2_FIRST_NAME
                + ", " + USERS_COL7_USERNAME
                + " FROM " + TABLE_NAME_USERS
                + " WHERE " + USERS_COL7_USERNAME + " = '" +  username + "'" ,null);

        if (data.moveToFirst()){
            return new User(data.getInt(data.getColumnIndex(USERS_COL1_ID)),
                    data.getString(data.getColumnIndex(USERS_COL2_FIRST_NAME)), data.getString(data.getColumnIndex(USERS_COL7_USERNAME))
            );
        }

        //TO DO
        //Handle case when username doesn't exist
        return new User(data.getInt(data.getColumnIndex(USERS_COL1_ID)),
                data.getString(data.getColumnIndex(USERS_COL2_FIRST_NAME)), data.getString(data.getColumnIndex(USERS_COL7_USERNAME))
        );


    }

    //check for existence of username
    public boolean exists_username(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT 1"
                + " FROM " + TABLE_NAME_USERS
                + " WHERE " + USERS_COL7_USERNAME + " = '" + username + "'" ,null);

        return data.getCount() != 0;
    }

    public String getUserName(Integer userID){
        //String userID_str = userID.toString();
        String username = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + USERS_COL2_FIRST_NAME
                + " FROM " + TABLE_NAME_USERS
                + " WHERE " + USERS_COL1_ID + " = " + userID ,null);
        if (data.moveToFirst()){
            username = data.getString(data.getColumnIndex(USERS_COL2_FIRST_NAME));
            System.out.println(data);
        }
        return username;

    }
    public String getUserLastName(String username){
        String last_name = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + USERS_COL3_LAST_NAME
                + " FROM " + TABLE_NAME_USERS
                + " WHERE " + USERS_COL7_USERNAME + " = " + username,null);
        Log.d(TAG,"Number of entries: " + data.getCount());

        if (data.getCount() > 0 ){
            data.moveToFirst();
            last_name = data.getString(data.getColumnIndex(USERS_COL3_LAST_NAME));
        }
        return last_name;

    }

    public String getUserFirstName(String username){
        String last_name = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + USERS_COL2_FIRST_NAME
                + " FROM " + TABLE_NAME_USERS
                + " WHERE " + USERS_COL7_USERNAME + " = " + username,null);
        Log.d(TAG,"Number of entries: " + data.getCount());

        if (data.getCount() > 0 ){
            data.moveToFirst();
            last_name = data.getString(data.getColumnIndex(USERS_COL2_FIRST_NAME));
        }
        return last_name;

    }


    public String getUserPhoneNumber(String username){
        String phone_number = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + USERS_COL4_PHONE_NUMBER
                + " FROM " + TABLE_NAME_USERS
                + " WHERE " + USERS_COL7_USERNAME + " = " + username,null);
        System.out.println("cursor.getcount()= " + data.getCount());

        if (data.getCount() > 0 ){
            data.moveToFirst();
            phone_number = data.getString(data.getColumnIndex(USERS_COL4_PHONE_NUMBER));
            System.out.println(data);
        }
        return phone_number;

    }

    public String getUserRole(Integer userID){
        String role = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + USERS_COL6_ROLE
                + " FROM " + TABLE_NAME_USERS
                + " WHERE " + USERS_COL1_ID + " = " + userID ,null);
        System.out.println("cursor.getcount()= " + data.getCount());

        if (data.getCount() > 0 ){
            data.moveToFirst();
            role = data.getString(data.getColumnIndex(USERS_COL6_ROLE));
            System.out.println(data);
        }
        return role;

    }

    public boolean addNewUser(String First_Name, String Last_Name, String Phone_Number, String User_Name, String Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Log.d(TAG,"PASSWORD TO INSERT IS : " + Password);
        values.put(USERS_COL2_FIRST_NAME, First_Name);
        values.put(USERS_COL3_LAST_NAME, Last_Name);
        values.put(USERS_COL4_PHONE_NUMBER, Phone_Number);
        values.put(USERS_COL7_USERNAME, User_Name);
        values.put(USERS_COL8_PASSWORD,Password);

        long result = db.insert(TABLE_NAME_USERS, null, values);

        //if data as inserted incorrectly it will return -1
        return result != -1;
    }

    public void updateUserRole(int id, String ROLE){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME_USERS
                + " SET " + USERS_COL6_ROLE + " = '" + ROLE + "'"
                + " WHERE " + USERS_COL1_ID + " = '" + id + "'";

        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting ROLE to " + ROLE  + "For USER ID: " + id);
        db.execSQL(query);
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateUserFullName(String newName, int id, String oldName, String new_last_name, String new_phone_number){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME_USERS
                + " SET " + USERS_COL2_FIRST_NAME + " = '" + newName + "'"
                + ", " + USERS_COL3_LAST_NAME + " = '" + new_last_name + "'"
                + ", " + USERS_COL4_PHONE_NUMBER + " = '" + new_phone_number
                + "' WHERE " + USERS_COL1_ID + " = '" + id + "'" +
                " AND " + USERS_COL2_FIRST_NAME + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName + " LastName: " + new_last_name + " Phone Number: " + new_phone_number);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     *
     */
    public void deleteUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME_USERS + " WHERE "
                + USERS_COL1_ID + " = '" + id + "'";
        Log.d(TAG, "deleteUser: query: " + query);
        db.execSQL(query);
    }


    /*******************************************************************************************************************************************************
     SQL QUERIES FOR ITEMS
     *******************************************************************************************************************************************************/
    //ADD TO ITEMS TABLE
    public boolean addData_items(String name, String drink, double price, String quantity, Integer userID, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ITEMS_COL2_USER_ID, userID);
        contentValues.put(ITEMS_COL3_FIRST_NAME, name);
        contentValues.put(ITEMS_COL4_CATEGORY, category);
        contentValues.put(ITEMS_COL5_ITEM_NAME, drink);
        contentValues.put(ITEMS_COL6_PRICE, price);
        contentValues.put(ITEMS_COL7_QUANTITY, quantity);

        long result = db.insert(TABLE_NAME_ITEMS, null, contentValues);

        //if date as inserted incorrectly it will return false
        return result != -1;
    }

    public void delData_items(Integer itemID,String ITEM_NAME){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME_ITEMS + " WHERE "
                + ITEMS_COL1_ID + " = '" + itemID + "'" +
                " AND " + ITEMS_COL5_ITEM_NAME + " = '" + ITEM_NAME + "'";
        Log.d(TAG, "delData_items: query: " + query);
        Log.d(TAG, "delData_items: Deleting " + ITEM_NAME + " from database.");
        db.execSQL(query);
    }

    public void updateItemQuantity(Integer itemID, Integer newQuantity, String itemName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME_ITEMS + " SET " + ITEMS_COL7_QUANTITY +
                " = '" + newQuantity + "' WHERE " + ITEMS_COL1_ID + " = '" + itemID + "'" +
                " AND " + ITEMS_COL5_ITEM_NAME + " = '" + itemName + "'";
        Log.d(TAG, "updateItemQuantity: query: " + query);
        Log.d(TAG, "updateItemQuantity: Setting Quantity to " + newQuantity);
        db.execSQL(query);
    }

    public void updateItemCategory(Integer itemID, String newCategory, String itemName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME_ITEMS + " SET " + ITEMS_COL7_QUANTITY +
                " = '" + newCategory + "' WHERE " + ITEMS_COL1_ID + " = '" + itemID + "'" +
                " AND " + ITEMS_COL5_ITEM_NAME + " = '" + itemName + "'";
        Log.d(TAG, "updateItemCategory: query: " + query);
        Log.d(TAG, "updateItemCategory: Setting Category to " + newCategory);
        db.execSQL(query);
    }


    public void updateItemPrice(Integer itemID, double newPrice, String itemName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME_ITEMS + " SET " + ITEMS_COL6_PRICE +
                " = '" + newPrice + "' WHERE " + ITEMS_COL1_ID + " = '" + itemID + "'" +
                " AND " + ITEMS_COL5_ITEM_NAME + " = '" + itemName + "'";
        Log.d(TAG, "updateItemPrice: query: " + query);
        Log.d(TAG, "updateItemPrice: Setting new Price to " + newPrice);
        db.execSQL(query);
    }

    public Cursor getItemContents(String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data_category = db.rawQuery("SELECT * " +
                "FROM " + TABLE_NAME_ITEMS +
                " WHERE " + ITEMS_COL4_CATEGORY + " = " + "'" + category + "'"
                + " AND " + ITEMS_COL8_ORDERID + " IS NULL", null);
        if (data_category.getCount()==0){
            System.out.println("Zero Count");
        }
        return data_category;
    }

    public Cursor getItemContents_by_userID(Integer userID) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data_userID = db.rawQuery("SELECT * " +
                "FROM " + TABLE_NAME_ITEMS +
                " WHERE " + ITEMS_COL2_USER_ID + " = " + "'" + userID + "'"  , null);
        if (data_userID.getCount()==0){
            System.out.println("Zero Count");
        }
        return data_userID;
    }

    public void setOrderID(Integer itemID, Integer OrderID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME_ITEMS
                + " SET " + ITEMS_COL8_ORDERID + " = " + OrderID
                + " WHERE " + ITEMS_COL1_ID + " = " + "'" + itemID + "'";

        Log.d(TAG, "updateItemQuantity: query: " + query);
        Log.d(TAG, "updateItemQuantity: Setting OrderID to " + OrderID);
        db.execSQL(query);
    }

    /*******************************************************************************************************************************************************
     SQL QUERIES FOR CHECKOUT
     *******************************************************************************************************************************************************/

    /*
    SELECT NAME, name_id, DRINK, PRICE, total_quantity, total_price
    FROM (SELECT *,SUM(quantity) AS total_quantity, SUM(price*quantity) AS total_price
    FROM drinks
    GROUP BY name_id,drink)
    WHERE NAME = "Tom" and name_id = 1
    GROUP BY drink,name_id

    */
    public Cursor getData_checkout(String username, Integer user_id){
        SQLiteDatabase db = this.getWritableDatabase();

        String query =
                "SELECT " + ITEMS_COL3_FIRST_NAME
                        + ", " + ITEMS_COL2_USER_ID
                        + ", " + ITEMS_COL5_ITEM_NAME
                        + ", " + ITEMS_COL6_PRICE
                        + ", total_quantity"
                        + ", total_price"
                        + " FROM "
                            + "(SELECT *, SUM(" + ITEMS_COL7_QUANTITY + ") AS total_quantity"
                            + ", SUM(" + ITEMS_COL6_PRICE + "*" + ITEMS_COL7_QUANTITY + ") AS total_price"
                                + " FROM " + TABLE_NAME_ITEMS
                                + " WHERE " + ITEMS_COL8_ORDERID + " IS NULL"
                                + " GROUP BY " + ITEMS_COL2_USER_ID + "," + ITEMS_COL5_ITEM_NAME + ")"
                        + " WHERE " + ITEMS_COL3_FIRST_NAME + " = " + "'" + username + "'"
                            + " AND " + ITEMS_COL2_USER_ID + " = " + user_id
                            + " AND " + ITEMS_COL8_ORDERID + " IS NULL"
                        + " GROUP BY " + ITEMS_COL5_ITEM_NAME + "," + ITEMS_COL2_USER_ID;


        return db.rawQuery(query, null);

    }

    public Cursor getData_foodid_checkout(String username, Integer user_id){
        SQLiteDatabase db = this.getWritableDatabase();

        String query =
                "SELECT " + ITEMS_COL1_ID
                        + "," + ITEMS_COL3_FIRST_NAME
                        + ", " + ITEMS_COL2_USER_ID
                        + ", " + ITEMS_COL5_ITEM_NAME
                        + ", " + ITEMS_COL6_PRICE
                        + " FROM " + TABLE_NAME_ITEMS
                        + " WHERE " + ITEMS_COL3_FIRST_NAME + " = " + "'" + username + "'" + " AND " + ITEMS_COL2_USER_ID + " = " + user_id + " AND " + ITEMS_COL8_ORDERID + " IS NULL";
        return db.rawQuery(query, null);
    }


    /*******************************************************************************************************************************************************
     SQL QUERIES FOR ORDERS
     *******************************************************************************************************************************************************/

    public Cursor getData_orders(Integer OrderID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + ITEMS_COL8_ORDERID
                + ", " + ITEMS_COL3_FIRST_NAME
                + ", " + ITEMS_COL5_ITEM_NAME
                + ", " + ITEMS_COL6_PRICE
                + ", " + "SUM(" + ITEMS_COL6_PRICE + ") AS 'TOTAL_ITEMS_PRICE'"
                + ", " + "SUM(" + ITEMS_COL7_QUANTITY + ") AS " + ITEMS_COL7_QUANTITY
                + " FROM " + TABLE_NAME_ITEMS
                + " WHERE " + ITEMS_COL8_ORDERID + " = " + OrderID
                + " GROUP BY " + ITEMS_COL5_ITEM_NAME;
        Log.d("Get_DATA_ORDERS QUERY", query);
        return db.rawQuery(query, null);
    }


    public Cursor get_allorders(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + ORDERS_COL1_ID
                + ", " + ORDERS_COL2_CUSTOMER_ID
                + ", " + ORDERS_COL3_DATE
                + " FROM " + TABLE_NAME_ORDERS;
        return db.rawQuery(query, null);
    }



    public String getorderDate(Integer OrderID){
        SQLiteDatabase db = this.getWritableDatabase();
        String date = "";
        String query = "SELECT " + ORDERS_COL3_DATE
                + " FROM " + TABLE_NAME_ORDERS
                + " WHERE " + ORDERS_COL1_ID + " = " + OrderID;

        Cursor data = db.rawQuery(query, null);
        if (data.moveToFirst()){
            date = data.getString(data.getColumnIndex(ORDERS_COL3_DATE));
            System.out.println(data);
        }
        data.moveToNext();
        return date;

    }

    //ADD TO Orders TABLE
    public boolean addData_newOrder(Integer userID, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ORDERS_COL2_CUSTOMER_ID, userID);
        contentValues.put(ORDERS_COL3_DATE, date);


        long result = db.insert(TABLE_NAME_ORDERS, null, contentValues);

        //if date as inserted incorrectly it will return -1
        return result != -1;
    }

    public void deleteOrder(Integer OrderID){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE " + TABLE_NAME_ITEMS +
                " SET " + ITEMS_COL8_ORDERID + " = NULL" +
                " WHERE " + ITEMS_COL8_ORDERID + " = " + OrderID + "'";

        db.execSQL(query);
    }

    //Get orderID
    public Integer getOrderID(Integer userID,String date){
        SQLiteDatabase db = this.getWritableDatabase();


        //placeholder for orderID debugging
        Integer orderID = -1;
        String query = "SELECT " + ORDERS_COL1_ID +
                " FROM " + TABLE_NAME_ORDERS +
                " WHERE " + ORDERS_COL2_CUSTOMER_ID + " = " + userID
                + " AND " + ORDERS_COL3_DATE + " = " + "'" + date + "'";
        Cursor data = db.rawQuery(query, null);
        if (data.moveToFirst()){
            orderID = data.getInt(data.getColumnIndex(ORDERS_COL1_ID));
            System.out.println(data);
        }
        data.moveToNext();
        return orderID;

    }
}
