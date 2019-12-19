package felixsam.github.com.foodordering.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Models.OrderID;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.adapters.List_OrdersID_Custom_Adapter;

public class List_Orders_By_ID extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    private ListView lv_orders_by_id;
    private String TAG = "List_Orders_By_ID";
    private String username  = "username";
    private Integer userID = -1;
    OrderID order_ID;
    ArrayList<OrderID> orderID_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_orders_test);
        this.setTitle("My Orders");

        mDatabaseHelper = new DatabaseHelper(this);
        // Find ListView to populate
        lv_orders_by_id = findViewById(R.id.lv_orders_test);


        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        orderID_list = new ArrayList<OrderID>();
        //get the data and append to a list
        Cursor data = mDatabaseHelper.get_allorders();
        int numRows = data.getCount();
        if(numRows == 0){
            Log.d(TAG,"Number of Rows is: " + numRows);
            Toast.makeText(List_Orders_By_ID.this,"The Database is empty :( ", Toast.LENGTH_LONG).show();
        }else{
            Log.d(TAG,"Number of Rows is: " + numRows);

            int i = 0;
            while(data.moveToNext()){
                //System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getInt(3)+ " " +data.getInt(4)+" " + data.getString(8) + " " + data.getString(9));

                userID = data.getInt(data.getColumnIndex(mDatabaseHelper.ORDERS_COL2_CUSTOMER_ID));
                username = mDatabaseHelper.getUserName(userID);

                order_ID = new OrderID(data.getInt(data.getColumnIndex(mDatabaseHelper.ORDERS_COL1_ID))
                        ,username
                        ,data.getString(data.getColumnIndex(mDatabaseHelper.ORDERS_COL3_DATE))
                );
                orderID_list.add(i,order_ID);
                //order_list.add(i,data.getString(0));
                i++;
            }
            List_OrdersID_Custom_Adapter adapter = new List_OrdersID_Custom_Adapter(this, R.layout.orderid_adapter_single_item, orderID_list);
            lv_orders_by_id.setAdapter(adapter);

            lv_orders_by_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(final AdapterView<?> parent, View view, int position,
                                        long id){
                    //get orderID and date
                    final Integer OrderID = orderID_list.get(position).getOrderID();
                    final String date = orderID_list.get(position).getDate();
                    Toast.makeText(getApplicationContext(),"OrderID:  " + OrderID.toString() + "\nDate: " + date, Toast.LENGTH_LONG).show();



                    //Add orderID to pass to List_Orders
                    Bundle extras = new Bundle();

                    Intent intent = new Intent(getApplicationContext(),List_Orders.class);

                    extras.putInt("ORDER_ID",OrderID);
                    extras.putString("DATE",date);

                    intent.putExtras(extras);

                    startActivity(intent);
                }

            });
        }
    }
}
