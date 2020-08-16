package felixsam.github.com.foodordering.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Globals;
import felixsam.github.com.foodordering.R;

public class EditCustomerActivity extends AppCompatActivity {

    private static final String TAG = "Edit_Customer";

    private TextInputEditText edit_customer_first_name, edit_customer_last_name, edit_customer_phone_number;
    private AutoCompleteTextView autocomplete_user_role;
    private String current_role;
    private DatabaseHelper mDatabaseHelper;

    private String edit_customer_selectedName;
    private int selectedID;
    private String role = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Globals g = Globals.getInstance();
        this.setTitle("Edit User");
        setContentView(R.layout.activity_edit_customer);
        Button btnSave = findViewById(R.id.btn_save_customer);
        Button btnDelete = findViewById(R.id.btn_delete_customer);
        edit_customer_first_name = findViewById(R.id.customer_edit_name);
        edit_customer_last_name = findViewById(R.id.customer_edit_lastname);
        edit_customer_phone_number = findViewById(R.id.customer_edit_phonenumber);
        autocomplete_user_role = findViewById(R.id.customer_edit_role);


        mDatabaseHelper = new DatabaseHelper(this);

        //get the intent extra from the List_Customers
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        edit_customer_selectedName = receivedIntent.getStringExtra("name");
        String edit_customer_selectLastName = receivedIntent.getStringExtra("last_name");
        String edit_customer_selectPhoneNumber = receivedIntent.getStringExtra("phone_number");
        String edit_customer_selectRole = receivedIntent.getStringExtra("role");

        //set the text to show the current selected name
        edit_customer_first_name.setText(edit_customer_selectedName);
        edit_customer_last_name.setText(edit_customer_selectLastName);
        edit_customer_phone_number.setText(edit_customer_selectPhoneNumber);
        autocomplete_user_role.setText(edit_customer_selectRole);

        String [] items = new String[]{"Admin", "Customer", "Worker"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        autocomplete_user_role.setAdapter(adapter);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name = Objects.requireNonNull(edit_customer_first_name.getText()).toString();
                String last_name = Objects.requireNonNull(edit_customer_last_name.getText()).toString();
                String phone_number = Objects.requireNonNull(edit_customer_phone_number.getText()).toString();
                role = autocomplete_user_role.getText().toString();

                final Integer user_id = g.getUser_ID();
                mDatabaseHelper.updateUserRole(user_id,role);

                if(!first_name.equals("")){
                    mDatabaseHelper.updateUserFullName(first_name,selectedID, edit_customer_selectedName,last_name,phone_number);
                    Snackbar.make(findViewById(android.R.id.content),"Changes Saved!",Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(findViewById(android.R.id.content),"You must enter a name",Snackbar.LENGTH_LONG).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteUser(selectedID, edit_customer_selectedName);
                edit_customer_first_name.setText("");
                edit_customer_last_name.setText("");

                Snackbar.make(findViewById(android.R.id.content),"Removed from the Database!",Snackbar.LENGTH_LONG).show();
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
