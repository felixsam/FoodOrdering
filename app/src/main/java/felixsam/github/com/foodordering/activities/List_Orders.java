package felixsam.github.com.foodordering.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Models.OrderID;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.adapters.ListOrdersAdapter;

public class List_Orders extends AppCompatActivity {
    private DatabaseHelper mDatabaseHelper;
    private final String TAG = List_Orders.class.getSimpleName();
    private OrderID order_ID;
    private ArrayList<OrderID> orderID_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_orders);
        this.setTitle("All Orders");

        mDatabaseHelper = new DatabaseHelper(this);
        // Find ListView to populate
        RecyclerView rv_listOrders = findViewById(R.id.rv_listOrders);

        orderID_list = new ArrayList<OrderID>();

        //get the data and append to a list
        Cursor data = mDatabaseHelper.get_allorders();
        int numRows = data.getCount();
        if(numRows == 0){
            Log.i(TAG,"No order entries ");
        }else{
            Log.i(TAG,"Number of Rows is: " + numRows);

            int i = 0;
            while(data.moveToNext()){

                Integer userID = data.getInt(data.getColumnIndex(DatabaseHelper.ORDERS_COL2_CUSTOMER_ID));
                String username = mDatabaseHelper.getUserName(userID);

                order_ID = new OrderID(data.getInt(data.getColumnIndex(DatabaseHelper.ORDERS_COL1_ID))
                        , username
                        ,data.getString(data.getColumnIndex(DatabaseHelper.ORDERS_COL3_DATE))
                );
                orderID_list.add(i,order_ID);
                //order_list.add(i,data.getString(0));
                i++;
            }
            ListOrdersAdapter adapter = new ListOrdersAdapter(orderID_list,this);
            rv_listOrders.setAdapter(adapter);
            rv_listOrders.setLayoutManager(new LinearLayoutManager(this));


        }
    }
}
