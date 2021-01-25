package felixsam.github.com.foodordering.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

        Button btnSubmit = findViewById(R.id.btn_checkout_submit);
        mDatabaseHelper = new DatabaseHelper(this);
        RecyclerView rvCheckout = findViewById(R.id.rv_checkout);

        Log.d(TAG,"Displaying data in the listView");

        ArrayList<Checkout> checkout_list = new ArrayList<>();

        username = g.getUser();
        userID = g.getUser_ID();

        TextView tvUsername = findViewById(R.id.tv_checkout_username);
        TextView tvUserId = findViewById(R.id.tv_checkout_user_id);
        TextView tvSubtotal = findViewById(R.id.tv_checkout_sub_total);
        TextView tvTax = findViewById(R.id.tv_checkout_tax);
        TextView tvTotal = findViewById(R.id.tv_checkout_total_price);

        tvUserId.setText("ID: " + userID.toString());
        tvUsername.setText("Name: " + username);
        Double subTotal = 0.00;


        checkout_list = mDatabaseHelper.getCheckoutItems(userID);

        //Get the subtotal, tax and grandtotal
        for (int i = 0; i< checkout_list.size();i++){
            subTotal += checkout_list.get(i).getTotalAmount();
        }

        //truncate to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        Double tax = subTotal*0.12;
        Double grandTotal = subTotal + tax;



        tvSubtotal.setText("Subtotal: $" + df.format(subTotal));
        tvTax.setText("Tax (12%): $" + df.format(tax));
        tvTotal.setText("Total Price: $" + df.format(grandTotal));



        CheckoutAdapter adapter = new CheckoutAdapter(this, R.layout.adapter_checkout_single_item, checkout_list);
        rvCheckout.setLayoutManager(new LinearLayoutManager(this));
        rvCheckout.setHasFixedSize(true);
        rvCheckout.setAdapter(adapter);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                boolean flag = mDatabaseHelper.addOrder(userID,currentDateTimeString);
                if (flag == TRUE){
                    Log.d(TAG,"INSERTED CORRECTLY");
                }else{
                    Log.d(TAG,"INSERTION FAILED");
                    toastMessage("Cannot create a new order");
                }



                Integer orderID = mDatabaseHelper.getOrderID(userID,currentDateTimeString);

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
