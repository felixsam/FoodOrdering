package felixsam.github.com.foodordering.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Globals;
import felixsam.github.com.foodordering.Models.User;
import felixsam.github.com.foodordering.R;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = Login_Activity.class.getSimpleName();

    private AutoCompleteTextView autocomplete_text_username;
    private TextInputEditText autocomplete_text_password;
    private final Globals g = Globals.getInstance();
    private Boolean flag_users_exist = Boolean.FALSE;
    private ArrayAdapter<User> adapter_dropdown_username;
    private DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        autocomplete_text_username = findViewById(R.id.dropdown_username);
        autocomplete_text_password = findViewById(R.id.text_password);

        Button btn_login = findViewById(R.id.btn_login);
        Button btn_register = findViewById(R.id.login_btn_signup);

        database = new DatabaseHelper(this);

        //For development
        //pre-fill username for quick login
        autocomplete_text_username.setText(database.getTopUserName());
        autocomplete_text_password.setText(database.getPassword(database.getTopUserName()));

        ArrayList<User> list_users = new ArrayList<>();

        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);

        //hide support bar
        Objects.requireNonNull(getSupportActionBar()).hide();


        //populate username dropdown
        Cursor data_customers = database.getUserIdAndName();
        int data_entries = data_customers.getCount();

        if (data_entries == 0) {
            Log.i(TAG, "Number of Rows is: " + data_entries);
            Log.i(TAG, "Empty Database: no users");
        } else {
            //Users exist in the database
            flag_users_exist = Boolean.TRUE;
            Log.i(TAG, "Number of Rows is: " + data_entries);

            int i = 0;
            while (data_customers.moveToNext()) {
                Log.i(TAG,"New User");
                User user = new User(data_customers.getInt(data_customers.getColumnIndex(DatabaseHelper.USERS_COL1_ID)),
                        data_customers.getString(data_customers.getColumnIndex(DatabaseHelper.USERS_COL2_FIRST_NAME)),
                        data_customers.getString(data_customers.getColumnIndex(DatabaseHelper.USERS_COL7_USERNAME))
                );

                Log.i(TAG,"Adding user: " + data_customers.getInt(data_customers.getColumnIndex(DatabaseHelper.USERS_COL1_ID)) + " \n"
                        + data_customers.getString(data_customers.getColumnIndex(DatabaseHelper.USERS_COL2_FIRST_NAME)) + " \n"
                        + data_customers.getString(data_customers.getColumnIndex(DatabaseHelper.USERS_COL7_USERNAME))
                );

                list_users.add(i, user);
                Log.i(TAG,"Username added to list_users: " + list_users.get(i).getUserName());
                i++;
            }

            adapter_dropdown_username = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list_users);
            autocomplete_text_username.setAdapter(adapter_dropdown_username);
        }



    }

    //Repopulate username dropdown on resume from add customers
    @Override
    public void onResume(){
        super.onResume();
        if (flag_users_exist == Boolean.TRUE){
            adapter_dropdown_username.notifyDataSetChanged();
            autocomplete_text_username.setAdapter(adapter_dropdown_username);
        }

    }

    //Login Button: if username exists in database, login to main menu
    //Sign Up Button: Register new customer, go to add customer activity
    @Override
    public void onClick(View v){
        Intent intent;
        switch (v.getId()){

            case R.id.btn_login:
                String username = autocomplete_text_username.getText().toString();

                if (!database.exists_username(username)){
                    Snackbar.make(findViewById(android.R.id.content),"Cannot login, username does not exist",Snackbar.LENGTH_LONG).show();
                    break;
                }

                if (flag_users_exist == Boolean.TRUE && database.exists_username(username)){
                    User login_user = database.getUser(username);
                    String login_name = login_user.getFirstName();
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
                    Snackbar.make(findViewById(android.R.id.content),"Cannot login, no users registered", Snackbar.LENGTH_LONG).show();
                }

                break;

            case R.id.login_btn_signup:
                intent = new Intent(Login_Activity.this, AddCustomerActivity.class);
                intent.putExtra("PARENT_ACTIVITY_CLASS", getClass());
                startActivity(intent);
                break;
        }
    }


}
