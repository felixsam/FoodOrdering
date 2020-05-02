package felixsam.github.com.foodordering.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import felixsam.github.com.foodordering.Models.Checkout;
import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Globals;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.adapters.Checkout_Custom_Adapter;

import static java.lang.Boolean.TRUE;

public class CheckoutActivity extends AppCompatActivity{
    DatabaseHelper mDatabaseHelper;
    private ListView lv_checkout;
    Checkout checkout;
    private String TAG = "Checkout_Activity";
    ArrayList<Checkout> checkout_list;

    private String username;
    private Integer userID;
    Button btn_submit;

    Globals g = Globals.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Checkout");
        setContentView(R.layout.activity_checkout);

        btn_submit = (Button) findViewById(R.id.btn_checkout_submit);
        mDatabaseHelper = new DatabaseHelper(this);
        lv_checkout = findViewById(R.id.lv_checkout);

        Log.d(TAG,"Displaying data in the listView");

        checkout_list = new ArrayList<Checkout>();

        username = g.getUser();
        userID = g.getUser_ID();

        TextView tv_username = (TextView) findViewById(R.id.tv_checkout_username);
        TextView tv_userid = (TextView) findViewById(R.id.tv_checkout_user_id);
        TextView tv_subtotal = (TextView) findViewById(R.id.tv_checkout_sub_total);
        TextView tv_tax = (TextView) findViewById(R.id.tv_checkout_tax);
        TextView tv_total = (TextView) findViewById(R.id.tv_checkout_total_price);

        tv_userid.setText("ID: " + userID.toString());
        tv_username.setText("Name: " + username);
        Double sub_total = 0.00;

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData_checkout(username,userID);
        int numRows = data.getCount();
        if(numRows == 0){
            Log.d(TAG,"Number of Rows is: " + numRows);
            toastMessage("The Database is empty :( ");
        }else{
            Log.d(TAG,"Number of Rows is: " + numRows);
            int i = 0;
            while(data.moveToNext()){
                //System.out.println(data.getString(1)+" "+data.getInt(2)+" "+data.getString(3)+ " " +data.getInt(4)+" " + data.getInt(5) + " " + data.getInt(6));
                checkout = new Checkout(data.getString(data.getColumnIndex(mDatabaseHelper.ITEMS_COL3_FIRST_NAME)),
                        data.getInt(data.getColumnIndex(mDatabaseHelper.ITEMS_COL2_USER_ID)),
                        data.getString(data.getColumnIndex(mDatabaseHelper.ITEMS_COL5_ITEM_NAME)),
                        data.getInt(data.getColumnIndex(mDatabaseHelper.ITEMS_COL6_PRICE)),
                        data.getInt(data.getColumnIndex("total_quantity")),
                        data.getInt(data.getColumnIndex("total_price")));
                sub_total += data.getInt(data.getColumnIndex("total_price"));
                checkout_list.add(i,checkout);
                i++;
            }

            //truncate to 2 decimal places
            DecimalFormat df = new DecimalFormat("#.##");
            Double tax = sub_total*0.12;
            Double grand_total = sub_total + tax;



            tv_subtotal.setText("Subtotal: $" + sub_total.toString());
            tv_tax.setText("Tax (12%): $" + df.format(tax).toString());
            tv_total.setText("Total Price: $" + df.format(grand_total).toString());
            Checkout_Custom_Adapter adapter = new Checkout_Custom_Adapter(this, R.layout.adapter_checkout_single_item, checkout_list);
            lv_checkout.setAdapter(adapter);

        }


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                boolean flag = mDatabaseHelper.addData_newOrder(userID,currentDateTimeString);
                if (flag == TRUE){
                    Log.d(TAG,"INSERTED CORRECTLY");
                }else{
                    Log.d(TAG,"INSERTION FAILED");
                }
                Integer orderID = mDatabaseHelper.getOrderID(userID,currentDateTimeString);
                Cursor add_order = mDatabaseHelper.getData_foodid_checkout(username,userID);
                int numRows = add_order.getCount();
                if(numRows == 0){
                    Log.d(TAG,"Number of Rows is: " + numRows);
                    toastMessage("Nothing to add to Order");
                }else{
                    Log.d(TAG,"Number of Rows is: " + numRows);
                    int i = 0;
                    while(add_order.moveToNext()){
                        //System.out.println(data.getString(1)+" "+data.getInt(2)+" "+data.getString(3)+ " " +data.getInt(4)+" " + data.getInt(5) + " " + data.getInt(6));
                        Log.d(TAG, "ENTRY NUMBER: " + i );
                        Log.d(TAG, " COL_ID " +  add_order.getInt(add_order.getColumnIndex(mDatabaseHelper.ITEMS_COL1_ID))
                        + " \n FIRST_NAME " + add_order.getString(add_order.getColumnIndex(mDatabaseHelper.ITEMS_COL3_FIRST_NAME))
                        + " \n USER_ID " + add_order.getInt(add_order.getColumnIndex(mDatabaseHelper.ITEMS_COL2_USER_ID))
                        + " \n ITEM_NAME " + add_order.getString(add_order.getColumnIndex(mDatabaseHelper.ITEMS_COL5_ITEM_NAME))
                        + " \n PRICE " + add_order.getInt(add_order.getColumnIndex(mDatabaseHelper.ITEMS_COL6_PRICE))
                        );
                        int itemID = add_order.getInt(add_order.getColumnIndex(mDatabaseHelper.ITEMS_COL1_ID));
                        mDatabaseHelper.setOrderID(itemID,orderID);
                        i++;
                    }
                }
                //toastMessage("Checkout successful");
                Snackbar.make(findViewById(R.id.checkout_layout),"Checkout successful",Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                getApplicationContext().startActivity(intent);


            }
        });
    }


    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
