package felixsam.github.com.foodordering.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import felixsam.github.com.foodordering.Models.Customer;
import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.adapters.List_Customers_Custom_Adapter;

public class List_Customers extends AppCompatActivity {
    private static final String TAG = List_Customers.class.getSimpleName();

    private DatabaseHelper mDatabaseHelper;
    private Customer customer;
    private ArrayList<Customer> customer_list;
    private List_Customers_Custom_Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setTitle("List of Customers");
        ListView mListView = findViewById(R.id.lv_customers);
        setContentView(R.layout.list_customers);

        mDatabaseHelper = new DatabaseHelper(this);


        Log.i(TAG, "Displaying data in the ListView.");

        //create the list adapter and set the adapter
        customer_list = new ArrayList<>();
        Cursor customer_data = mDatabaseHelper.getUserIdAndName();
        int numRows = customer_data.getCount();
        if(numRows == 0){
            Log.d(TAG,"No rows to populate list " + numRows);
        }else{
            Log.i(TAG,"Number of Rows is: " + numRows);

            int i = 0;
            while(customer_data.moveToNext()){
                Log.i(TAG,"New Row Entry: ");
                Log.i(TAG,customer_data.getInt(0)+" "+customer_data.getString(1));
                customer = new Customer(customer_data.getInt(0),customer_data.getString(1));
                customer_list.add(i,customer);
                i++;
            }
            adapter = new List_Customers_Custom_Adapter(this, customer_list);
            mListView.setAdapter(adapter);
        }
    }

}
