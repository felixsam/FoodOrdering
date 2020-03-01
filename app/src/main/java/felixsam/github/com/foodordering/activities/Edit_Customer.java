package felixsam.github.com.foodordering.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Globals;
import felixsam.github.com.foodordering.Models.User;
import felixsam.github.com.foodordering.R;

public class Edit_Customer extends AppCompatActivity {

    Globals g = Globals.getInstance();

    private static final String TAG = "Edit_Customer";

    private Button btnSave;
    private Button btnDelete;
    private EditText edit_customer_firstname, edit_customer_lastname, edit_customer_phone_number;
    private Spinner dropdown;
    private ArrayAdapter<String> adapter;
    private String current_role;
    DatabaseHelper mDatabaseHelper;
    private EditText edit_customer_role;

    private String edit_customer_selectedName, edit_customer_selectLastName, edit_customer_selectPhoneNumber, edit_customer_selectRole;
    private int selectedID;
    private String role = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_customer_data);
        btnSave = (Button) findViewById(R.id.btn_save_customer);
        btnDelete = (Button) findViewById(R.id.btn_delete_customer);
        edit_customer_firstname = (EditText) findViewById(R.id.customer_edit_name);
        edit_customer_lastname = (EditText) findViewById(R.id.customer_edit_lastname);
        edit_customer_phone_number = (EditText) findViewById(R.id.customer_edit_phonenumber);
        edit_customer_role = (EditText) findViewById(R.id.customer_edit_role);


        mDatabaseHelper = new DatabaseHelper(this);
        this.setTitle("Edit User");
        final Globals g = Globals.getInstance();


        //get the intent extra from the List_Customers
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        edit_customer_selectedName = receivedIntent.getStringExtra("name");
        edit_customer_selectLastName = receivedIntent.getStringExtra("last_name");
        edit_customer_selectPhoneNumber = receivedIntent.getStringExtra("phone_number");
        edit_customer_selectRole = receivedIntent.getStringExtra("role");

        //set the text to show the current selected name
        edit_customer_firstname.setText(edit_customer_selectedName);
        edit_customer_lastname.setText(edit_customer_selectLastName);
        edit_customer_phone_number.setText(edit_customer_selectPhoneNumber);
        edit_customer_role.setText(edit_customer_selectRole);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name = edit_customer_firstname.getText().toString();
                String last_name = edit_customer_lastname.getText().toString();
                String phone_number = edit_customer_phone_number.getText().toString();
                role = getSelectedRole();

                final Integer user_id = g.getUser_ID();
                mDatabaseHelper.update_customer_role(user_id,role);

                if(!first_name.equals("")){
                    mDatabaseHelper.updateName(first_name,selectedID, edit_customer_selectedName,last_name,phone_number);
                }else{
                    toastMessage("You must enter a name");
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID, edit_customer_selectedName);
                edit_customer_firstname.setText("");
                edit_customer_lastname.setText("");

                toastMessage("removed from database");
            }
        });

        //Spinner
        dropdown = findViewById(R.id.edit_customer_spinner);
        String [] items = new String[]{"Admin", "Customer", "Worker"};
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dropdown.setAdapter(adapter);

        dropdown.setPrompt(edit_customer_selectRole);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //User user = (User) parent.getSelectedItem();
                //displayUserData(user);


                //set as selected item.
                dropdown.setSelection(position);

                //
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    public String getSelectedRole(){
        String role = (String) dropdown.getSelectedItem();
        return role;
    }
    public User getSelectedUser(){
        User user = (User) dropdown.getSelectedItem();
        return user;
    }

    public void displayUserData(User user){
        String name = user.getName();
        String role = user.getRole();
        int UserID = user.getUserID();

        String userData = "Name: " + name + "\n UserID: " + UserID + "role" + role;

        Toast.makeText(this,userData,Toast.LENGTH_SHORT).show();
    }


    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
