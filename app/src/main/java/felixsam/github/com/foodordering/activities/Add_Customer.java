package felixsam.github.com.foodordering.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.R;

public class Add_Customer extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Add_Customer";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent receivedIntent = getIntent();
        String parent_activity = receivedIntent.getStringExtra("PARENT_ACTIVITY");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_customer);
        this.setTitle("Add Customer");


        //DataBase
        mDatabaseHelper = new DatabaseHelper(this);
        editText = (EditText) findViewById(R.id.et_customer_name_field);
        btnAdd = (Button) findViewById(R.id.btn_customer_add);
        btnViewData = (Button) findViewById(R.id.btn_customer_view);


        btnAdd.setOnClickListener(this);
        btnViewData.setOnClickListener(this);


    }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_customer_add:
                String newEntry = editText.getText().toString();
                if (editText.length() != 0) {
                    AddData(newEntry);
                    editText.setText("");
                    finish();
                } else {
                    toastMessage("You must put something in the text field!");
                }
                break;

            case R.id.btn_customer_view:
                Intent intent = new Intent(Add_Customer.this, List_Customers.class);
                startActivity(intent);
                break;


        }
    }

    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData_customers(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!\n Added a New User");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }


}
