package felixsam.github.com.foodordering.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Models.Order;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.adapters.List_Orders_Custom_Adapter;

public class List_Orders_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_orders_test);
        this.setTitle("My Orders");

        DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
        // Find ListView to populate
        ListView lv_order_items = findViewById(R.id.lv_orders_test);

        TextView order_id = findViewById(R.id.tv_order_customer_id);
        TextView customer_name = findViewById(R.id.order_tv_customer_name);


        String TAG = "Orders_TAG";
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //ArrayList<String> order_list;
        ArrayList<Order> order_list = new ArrayList<>();
        Integer OrderID = extras.getInt("ORDER_ID");
        Log.d(TAG, "ORDER ID: " + OrderID.toString());
        order_id.setText("OrderID: " + OrderID.toString());

        String user_name = extras.getString("NAME");
        customer_name.setText(user_name);

        String date = extras.getString("DATE");
        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData_orders(OrderID);
        int numRows = data.getCount();
        //String date = mDatabaseHelper.getorderDate(OrderID);

        if(numRows == 0){
            Log.d(TAG,"Number of Rows is: " + numRows);
            Toast.makeText(List_Orders_Activity.this,"The Database is empty :( ", Toast.LENGTH_LONG).show();
        }else{
            Log.d(TAG,"Number of Rows is: " + numRows);

            int i = 0;
            while(data.moveToNext()){
                //System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getInt(3)+ " " +data.getInt(4)+" " + data.getString(8) + " " + data.getString(9));
                Order order = new Order(data.getString(data.getColumnIndex(DatabaseHelper.ITEMS_COL3_FIRST_NAME))
                        , data.getString(data.getColumnIndex(DatabaseHelper.ITEMS_COL5_ITEM_NAME))
                        , data.getInt(data.getColumnIndex(DatabaseHelper.ITEMS_COL6_PRICE))
                        , data.getInt(data.getColumnIndex("TOTAL_ITEMS_PRICE"))
                        , date
                        , OrderID
                        , data.getInt(data.getColumnIndex(DatabaseHelper.ITEMS_COL7_QUANTITY))
                );
                order_list.add(i, order);
                //order_list.add(i,data.getString(0));
                i++;
            }
            List_Orders_Custom_Adapter adapter = new List_Orders_Custom_Adapter(this, R.layout.order_adapter_single_item, order_list);
            lv_order_items.setAdapter(adapter);
        }
    }

// --Commented out by Inspection START (3/25/2020 11:05 PM):
//    /**
//     * customizable toast
//     * @param message
//     */
//    private void toastMessage(String message){
//        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
//    }
// --Commented out by Inspection STOP (3/25/2020 11:05 PM)

}