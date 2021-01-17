package felixsam.github.com.foodordering.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import felixsam.github.com.foodordering.Models.Checkout;
import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Globals;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.adapters.CheckoutAdapter;

import static java.lang.Boolean.TRUE;

public class CheckoutActivity extends AppCompatActivity{
    private DatabaseHelper mDatabaseHelper;
    private final String TAG = "Checkout_Activity";

    private String username;
    private Integer userID;

    private final Globals g = Globals.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Checkout");
        setContentView(R.layout.activity_checkout);

        Button btn_submit = findViewById(R.id.btn_checkout_submit);
        mDatabaseHelper = new DatabaseHelper(this);
        RecyclerView rv_checkout = findViewById(R.id.rv_checkout);

        Log.d(TAG,"Displaying data in the listView");

        ArrayList<Checkout> checkout_list = new ArrayList<>();

        username = g.getUser();
        userID = g.getUser_ID();

        TextView tv_username = findViewById(R.id.tv_checkout_username);
        TextView tv_userId = findViewById(R.id.tv_checkout_user_id);
        TextView tv_subtotal = findViewById(R.id.tv_checkout_sub_total);
        TextView tv_tax = findViewById(R.id.tv_checkout_tax);
        TextView tv_total = findViewById(R.id.tv_checkout_total_price);

        tv_userId.setText("ID: " + userID.toString());
        tv_username.setText("Name: " + username);
        Double sub_total = 0.00;


        checkout_list = mDatabaseHelper.getCheckoutItems(userID);

        for (int i = 0; i< checkout_list.size();i++){
            sub_total += checkout_list.get(i).getTotalAmount();
        }

        //truncate to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        Double tax = sub_total*0.12;
        Double grand_total = sub_total + tax;



        tv_subtotal.setText("Subtotal: $" + df.format(sub_total));
        tv_tax.setText("Tax (12%): $" + df.format(tax));
        tv_total.setText("Total Price: $" + df.format(grand_total));
        CheckoutAdapter adapter = new CheckoutAdapter(this, R.layout.adapter_checkout_single_item, checkout_list);
        rv_checkout.setLayoutManager(new LinearLayoutManager(this));
        rv_checkout.setHasFixedSize(true);
        rv_checkout.setAdapter(adapter);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                boolean flag = mDatabaseHelper.addOrder(userID,currentDateTimeString);
                if (flag == TRUE){
                    Log.d(TAG,"INSERTED CORRECTLY");
                }else{
                    Log.d(TAG,"INSERTION FAILED");
                }





                Integer orderID = mDatabaseHelper.getOrderID(userID,currentDateTimeString);
//                Cursor add_order = mDatabaseHelper.getData_foodid_checkout(userID);
//                int numRows = add_order.getCount();
//                if(numRows == 0){
//                    Log.d(TAG,"Number of Rows is: " + numRows);
//                    toastMessage("Nothing to add to Order");
//                }else{
//                    Log.d(TAG,"Number of Rows is: " + numRows);
//                    int i = 0;
//                    while(add_order.moveToNext()){
//                        //System.out.println(data.getString(1)+" "+data.getInt(2)+" "+data.getString(3)+ " " +data.getInt(4)+" " + data.getInt(5) + " " + data.getInt(6));
//                        Log.d(TAG, "ENTRY NUMBER: " + i );
//                        Log.d(TAG, " COL_ID " +  add_order.getInt(add_order.getColumnIndex(DatabaseHelper.ITEMS_COL1_ID))
//                        + " \n USER_ID " + add_order.getInt(add_order.getColumnIndex(DatabaseHelper.ITEMS_COL2_USER_ID))
//                        + " \n ITEM_NAME " + add_order.getString(add_order.getColumnIndex(DatabaseHelper.ITEMS_COL3_ITEM_NAME))
//                        + " \n PRICE " + add_order.getDouble(add_order.getColumnIndex(DatabaseHelper.ITEMS_COL4_PRICE))
//                        );
//                        int itemID = add_order.getInt(add_order.getColumnIndex(DatabaseHelper.ITEMS_COL1_ID));
//                        mDatabaseHelper.setOrderID(itemID,orderID);
//                        i++;
//                    }
//                }

                mDatabaseHelper.checkoutItems(userID,orderID);
                finish();


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
