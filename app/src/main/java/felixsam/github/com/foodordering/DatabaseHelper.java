package felixsam.github.com.foodordering;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    Globals g = Globals.getInstance();

    private static final String TAG = "DatabaseHelper";

    private static final String DB_NAME = "myDatabase";

    //TABLE: CUSTOMERS
    public static final String TABLE_NAME_CUSTOMERS = "customers";
    public static final String CUSTOMERS_COL1_ID = "_id";
    public static final String CUSTOMERS_COL2_FIRST_NAME ="FIRST_NAME";
    public static final String CUSTOMERS_COL3_PHONE_NUMBER ="PHONE_NUMBER";
    public static final String CUSTOMERS_COL4_LOGGED_IN = "LOG_IN_STATUS";

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

    //ORDERS
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
                "CREATE TABLE " + TABLE_NAME_CUSTOMERS + " (" +
                        CUSTOMERS_COL1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        CUSTOMERS_COL2_FIRST_NAME + " TEXT, " +
                        CUSTOMERS_COL3_PHONE_NUMBER + " TEXT, " +
                        CUSTOMERS_COL4_LOGGED_IN + " TEXT DEFAULT 'FALSE' "
                        + ")";

        //DRINKS TABLE
        String createTableItems =
                "CREATE TABLE " + TABLE_NAME_ITEMS + " (" +
                        ITEMS_COL1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ITEMS_COL2_USER_ID + " INTEGER," +
                        ITEMS_COL3_FIRST_NAME + " TEXT, " +
                        ITEMS_COL4_CATEGORY + " TEXT, " +
                        ITEMS_COL5_ITEM_NAME + " INTEGER, " +
                        ITEMS_COL6_PRICE + " TEXT, " +
                        ITEMS_COL7_QUANTITY + " INTEGER," +
                        ITEMS_COL8_ORDERID + " TEXT," +
                        " CONSTRAINT FK_USERID" +
                        " FOREIGN KEY " + "(" + ITEMS_COL2_USER_ID + ")" +
                        " REFERENCES " + TABLE_NAME_CUSTOMERS + " (" + CUSTOMERS_COL1_ID + ")"
                        + ")";

        //ORDERS TABLE
        String createTableOrders =
                "CREATE TABLE " + TABLE_NAME_ORDERS + " (" +
                        ORDERS_COL1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ORDERS_COL2_CUSTOMER_ID + " INTEGER, " +
                        ORDERS_COL3_DATE + " TEXT, " +
                        ORDERS_COL4_STATUS + " TEXT"
                        + ")";

        db.execSQL(createTableCustomers);
        db.execSQL(createTableItems);
        db.execSQL(createTableOrders);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CUSTOMERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ORDERS);

        onCreate(db);
    }



    /*******************************************************************************************************************************************************
     SQL QUERIES FOR CUSTOMERS
     *******************************************************************************************************************************************************/

    public Cursor getCustomerContents() {
        SQLiteDatabase db_customer = this.getWritableDatabase();
        Cursor data = db_customer.rawQuery("SELECT * FROM " + TABLE_NAME_CUSTOMERS, null);

        return data;
    }

    public Cursor getCustomer_ID_and_Name(){
        SQLiteDatabase db_customer = this.getWritableDatabase();
        Cursor data = db_customer.rawQuery("SELECT " + CUSTOMERS_COL1_ID
                + ", " + CUSTOMERS_COL2_FIRST_NAME +
                " FROM " + TABLE_NAME_CUSTOMERS, null);

        return data;
    }


    public String getUserName(Integer userID){
        //String userID_str = userID.toString();
        String username = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + CUSTOMERS_COL2_FIRST_NAME
                + " FROM " + TABLE_NAME_CUSTOMERS
                + " WHERE " +  CUSTOMERS_COL1_ID + " = " + userID ,null);
        if (data.moveToFirst()){
            username = data.getString(data.getColumnIndex(CUSTOMERS_COL2_FIRST_NAME));
            System.out.println(data);
        }
        return username;

    }
    public boolean addData_customers(String First_Name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CUSTOMERS_COL2_FIRST_NAME, First_Name);
        //contentValues.put(CUSTOMERS_COL3_LAST_NAME, Last_Name);
        //contentValues.put(CUSTOMERS_COL4_PHONE_NUMBER, Phone_Number);

        long result = db.insert(TABLE_NAME_CUSTOMERS, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME_CUSTOMERS + " SET " + CUSTOMERS_COL2_FIRST_NAME +
                " = '" + newName + "' WHERE " + CUSTOMERS_COL1_ID + " = '" + id + "'" +
                " AND " + CUSTOMERS_COL2_FIRST_NAME + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME_CUSTOMERS + " WHERE "
                + CUSTOMERS_COL1_ID + " = '" + id + "'" +
                " AND " + CUSTOMERS_COL2_FIRST_NAME + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + CUSTOMERS_COL1_ID + " FROM " + TABLE_NAME_CUSTOMERS +
                " WHERE " + CUSTOMERS_COL2_FIRST_NAME + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    /*******************************************************************************************************************************************************
     SQL QUERIES FOR ITEMS
     *******************************************************************************************************************************************************/
    //ADD TO DRINKS TABLE
    public boolean addData_items(String name, String drink, String price, String quantity, Integer userID, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ITEMS_COL2_USER_ID, userID);
        contentValues.put(ITEMS_COL3_FIRST_NAME, name);
        contentValues.put(ITEMS_COL4_CATEGORY, category);
        contentValues.put(ITEMS_COL5_ITEM_NAME, drink);
        contentValues.put(ITEMS_COL6_PRICE, price);
        contentValues.put(ITEMS_COL7_QUANTITY, quantity);

        long result = db.insert(TABLE_NAME_ITEMS, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
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


        Cursor data = db.rawQuery(query, null);
        return data;

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
                        + " WHERE " + ITEMS_COL3_FIRST_NAME + " = " + "'" + username + "'" + " AND " + ITEMS_COL2_USER_ID + " = " + user_id;
        Cursor data = db.rawQuery(query, null);
        return data;
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
                + ", " + ITEMS_COL7_QUANTITY
                + " FROM " + TABLE_NAME_ITEMS
                + " WHERE " + ITEMS_COL8_ORDERID + " = " + OrderID ;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor get_allorders(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + ORDERS_COL1_ID
                + ", " + ORDERS_COL2_CUSTOMER_ID
                + ", " + ORDERS_COL3_DATE
                + " FROM " + TABLE_NAME_ORDERS;
        Cursor data = db.rawQuery(query, null);
        return data;
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

    //ADD TO DRINKS TABLE
    public boolean addData_newOrder(Integer userID, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ORDERS_COL2_CUSTOMER_ID, userID);
        contentValues.put(ORDERS_COL3_DATE, date);


        long result = db.insert(TABLE_NAME_ORDERS, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Integer getOrderID(Integer userID,String date){
        SQLiteDatabase db = this.getWritableDatabase();
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
