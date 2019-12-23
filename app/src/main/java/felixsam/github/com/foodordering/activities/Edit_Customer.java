package felixsam.github.com.foodordering.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Globals;
import felixsam.github.com.foodordering.R;

public class Edit_Customer extends AppCompatActivity {

    Globals g = Globals.getInstance();

    private static final String TAG = "Edit_Customer";

    private Button btnSave;
    private Button btnDelete;
    private EditText et_first_name,et_last_name,et_phone_number;

    DatabaseHelper mDatabaseHelper;

    private String selectedName,selectedLastName,selectPhoneNumber;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_customer_data);
        btnSave = (Button) findViewById(R.id.btn_save_customer);
        btnDelete = (Button) findViewById(R.id.btn_delete_customer);
        et_first_name = (EditText) findViewById(R.id.customer_edit_name);
        et_last_name = (EditText) findViewById(R.id.customer_edit_lastname);
        et_phone_number = (EditText) findViewById(R.id.customer_edit_phonenumber);

        mDatabaseHelper = new DatabaseHelper(this);
        this.setTitle("Edit User");

        //get the intent extra from the List_Customers
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");
        selectedLastName = receivedIntent.getStringExtra("last_name");
        selectPhoneNumber = receivedIntent.getStringExtra("phone_number");

        //set the text to show the current selected name
        et_first_name.setText(selectedName);
        et_last_name.setText(selectedLastName);
        et_phone_number.setText(selectPhoneNumber);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name = et_first_name.getText().toString();
                String last_name = et_last_name.getText().toString();
                String phone_number = et_phone_number.getText().toString();

                if(!first_name.equals("")){
                    mDatabaseHelper.updateName(first_name,selectedID,selectedName,last_name,phone_number);
                }else{
                    toastMessage("You must enter a name");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                et_first_name.setText("");
                toastMessage("removed from database");
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
