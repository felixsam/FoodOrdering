package felixsam.github.com.foodordering.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        RecyclerView rv_order_items = findViewById(R.id.rv_orders);


        String TAG = List_Orders_Activity.class.getSimpleName();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        ArrayList<Order> order_list = new ArrayList<>();
        Integer OrderID = extras.getInt("ORDER_ID");
        Log.d(TAG, "ORDER ID: " + OrderID.toString());

        String user_name = extras.getString("NAME");

        String date = extras.getString("DATE");
        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData_orders(OrderID);
        int numRows = data.getCount();

        if(numRows == 0){
            Log.d(TAG,"No Data Entries" + numRows);
        }else{
            Log.d(TAG,"Number of Rows is: " + numRows);

            int i = 0;
            while(data.moveToNext()){
                Order order = new Order(data.getString(data.getColumnIndex(DatabaseHelper.ITEMS_COL3_FIRST_NAME))
                        , data.getString(data.getColumnIndex(DatabaseHelper.ITEMS_COL5_ITEM_NAME))
                        , data.getInt(data.getColumnIndex(DatabaseHelper.ITEMS_COL6_PRICE))
                        , data.getDouble(data.getColumnIndex("TOTAL_ITEMS_PRICE"))
                        , date
                        , OrderID
                        , data.getInt(data.getColumnIndex(DatabaseHelper.ITEMS_COL7_QUANTITY))
                );
                order_list.add(i, order);
                i++;
            }
            List_Orders_Custom_Adapter adapter = new List_Orders_Custom_Adapter(this, R.layout.adapter_order_single_item_card, order_list);
            rv_order_items.setLayoutManager(new LinearLayoutManager(this));
            rv_order_items.setHasFixedSize(true);
            rv_order_items.setAdapter(adapter);
        }
    }
}
