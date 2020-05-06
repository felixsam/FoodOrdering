package felixsam.github.com.foodordering.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import felixsam.github.com.foodordering.Models.Customer;
import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.adapters.List_Customers_Custom_Adapter;

public class List_Customers extends AppCompatActivity {
    //For logging
    private static final String TAG = "List_Customers";

    private DatabaseHelper mDatabaseHelper;
    private Customer customer;
    private ArrayList<Customer> customer_list;
    private List_Customers_Custom_Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_customers);
        ListView mListView = findViewById(R.id.lv_customers);
        mDatabaseHelper = new DatabaseHelper(this);
        this.setTitle("List of Customers");
        Log.d(TAG, "populateListView: Displaying data in the ListView.");


        //create the list adapter and set the adapter
        ///ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        customer_list = new ArrayList<>();
        Cursor customer_data = mDatabaseHelper.getCustomer_ID_and_Name();
        int numRows = customer_data.getCount();
        if(numRows == 0){
            Log.d(TAG,"Number of Rows is: " + numRows);
            Toast.makeText(List_Customers.this,"The Database is empty :( ", Toast.LENGTH_LONG).show();
        }else{
            Log.d(TAG,"Hello");
            Log.d(TAG,"Number of Rows is: " + numRows);

            int i = 0;
            while(customer_data.moveToNext()){
                System.out.println("New Entry");
                System.out.println(customer_data.getInt(0)+" "+customer_data.getString(1));
                customer = new Customer(customer_data.getInt(0),customer_data.getString(1));
                customer_list.add(i,customer);
                i++;
            }
            adapter = new List_Customers_Custom_Adapter(this, customer_list);
            mListView.setAdapter(adapter);
        }

        //populateListView();
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
