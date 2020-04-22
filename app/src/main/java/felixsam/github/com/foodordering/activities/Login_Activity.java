package felixsam.github.com.foodordering.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Globals;
import felixsam.github.com.foodordering.Models.User;
import felixsam.github.com.foodordering.R;

public class Login_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private String TAG = Login_Activity.class.getSimpleName();

    private Spinner dropdown_users;
    private AutoCompleteTextView autocomplete_text;
    private final Globals g = Globals.getInstance();
    private Boolean flag_users_exist = Boolean.FALSE;
    private ArrayAdapter<User> adapter_dropdown_users;
    private ArrayAdapter<User> adapter_dropdown_usrs;
    private TextInputEditText et_password;
    private DatabaseHelper database;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = new DatabaseHelper(this);
        ArrayList<User> list_users = new ArrayList<>();

        dropdown_users = findViewById(R.id.spinner);
        autocomplete_text = findViewById(R.id.dropdown_username);
        et_password = findViewById(R.id.text_password);

        Cursor data = database.getCustomer_ID_and_Name();
        int numRows = data.getCount();

        if (numRows == 0) {
            Log.i(TAG, "Number of Rows is: " + numRows);

            Toast.makeText(Login_Activity.this, "The Database is empty  :(.", Toast.LENGTH_LONG).show();
        } else {
            System.out.println("Else Statement");
            flag_users_exist = Boolean.TRUE;
            Log.i(TAG, "Number of Rows is: " + numRows);

            int i = 0;
            while (data.moveToNext()) {
                Log.i(TAG,"New User");
                User user = new User(data.getInt(data.getColumnIndex(DatabaseHelper.CUSTOMERS_COL1_ID)),
                        data.getString(data.getColumnIndex(DatabaseHelper.CUSTOMERS_COL2_FIRST_NAME)),
                        data.getString(data.getColumnIndex(DatabaseHelper.CUSTOMERS_COL7_USERNAME))
                );

                Log.i(TAG,"Adding user: " + data.getInt(data.getColumnIndex(DatabaseHelper.CUSTOMERS_COL1_ID)) + " "
                        + data.getString(data.getColumnIndex(DatabaseHelper.CUSTOMERS_COL2_FIRST_NAME)) + " "
                        + data.getString(data.getColumnIndex(DatabaseHelper.CUSTOMERS_COL7_USERNAME))
                );
                list_users.add(i, user);

                Log.i(TAG,"User added: " + list_users.get(i).getName());
                i++;

            }


            adapter_dropdown_users = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list_users);
            adapter_dropdown_usrs = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list_users);
            //adapter_dropdown_users.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            autocomplete_text.setAdapter(adapter_dropdown_usrs);

            dropdown_users.setAdapter(adapter_dropdown_users);
            dropdown_users.setOnItemSelectedListener(this);

            autocomplete_text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


        }

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        Button btn_register = findViewById(R.id.login_btn_register);
        btn_register.setOnClickListener(this);
    }


    private User getSelectedUser(){

        return (User) dropdown_users.getSelectedItem();
        }


    private void displayUserData(User user){
        String name = user.getName();
        int UserID = user.getUserID();

        String userData = "Name: " + name + "\n UserID: " + UserID;

        Toast.makeText(this,"Logged in: " + userData,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView parent, View view, int position, long id) {
        User user = (User) parent.getSelectedItem();
        displayUserData(user);

        //set as selected item.
        dropdown_users.setSelection(position);
    }

    @Override
    public void onResume(){
        super.onResume();
        if (flag_users_exist == Boolean.TRUE){
            adapter_dropdown_users.notifyDataSetChanged();
            dropdown_users.setAdapter(adapter_dropdown_users);
        }

    }

    @Override
    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btn_login:
                System.out.println("Clicked login button");
                username = autocomplete_text.getText().toString();

                if (!database.exists_username(username)){
                Toast.makeText(this, "Cannot login, No users registered", Toast.LENGTH_SHORT).show();
                }

                if (flag_users_exist == Boolean.TRUE && database.exists_username(username)){
                    //User login_user = getSelectedUser();
                    User login_user = database.getUser(username);
                    String login_name = login_user.getName();
                    int login_id = login_user.getUserID();


                    Log.i(TAG,"Setting global user - Login Name: " + login_name + "\n UserID: " + login_id);

                    g.setUser(login_name);
                    g.setUser_ID(login_id);

                    intent = new Intent(Login_Activity.this, MainActivity.class);
                    startActivity(intent);

                    //prevent going back to login activity
                    //Destroy login Activity
                    finish();
                }else{
                    Toast.makeText(this, "Cannot login, No users registered", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.login_btn_register:
                intent = new Intent(Login_Activity.this, Add_Customer.class);
                intent.putExtra("PARENT_ACTIVITY_CLASS", getClass());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }


}
