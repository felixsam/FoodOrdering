package felixsam.github.com.foodordering.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.Objects;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Globals;
import felixsam.github.com.foodordering.Models.User;
import felixsam.github.com.foodordering.R;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = Login_Activity.class.getSimpleName();

    private AutoCompleteTextView autocomplete_text_username;
    private final Globals g = Globals.getInstance();
    private Boolean flag_users_exist = Boolean.FALSE;
    private ArrayAdapter<User> adapter_dropdown_username;
    private DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        autocomplete_text_username = findViewById(R.id.dropdown_username);
        Button btn_login = findViewById(R.id.btn_login);
        Button btn_register = findViewById(R.id.login_btn_signup);

        database = new DatabaseHelper(this);
        ArrayList<User> list_users = new ArrayList<>();

        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);

        //hide support bar
        Objects.requireNonNull(getSupportActionBar()).hide();


        //populate username dropdown
        Cursor data_customers = database.getCustomer_ID_and_Name();
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
                User user = new User(data_customers.getInt(data_customers.getColumnIndex(DatabaseHelper.CUSTOMERS_COL1_ID)),
                        data_customers.getString(data_customers.getColumnIndex(DatabaseHelper.CUSTOMERS_COL2_FIRST_NAME)),
                        data_customers.getString(data_customers.getColumnIndex(DatabaseHelper.CUSTOMERS_COL7_USERNAME))
                );

                Log.i(TAG,"Adding user: " + data_customers.getInt(data_customers.getColumnIndex(DatabaseHelper.CUSTOMERS_COL1_ID)) + " \n"
                        + data_customers.getString(data_customers.getColumnIndex(DatabaseHelper.CUSTOMERS_COL2_FIRST_NAME)) + " \n"
                        + data_customers.getString(data_customers.getColumnIndex(DatabaseHelper.CUSTOMERS_COL7_USERNAME))
                );

                list_users.add(i, user);
                Log.i(TAG,"Username added to list_users: " + list_users.get(i).getUser_name());
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
                System.out.println("Clicked login button");
                String username = autocomplete_text_username.getText().toString();

                if (!database.exists_username(username)){
                Toast.makeText(this, "Cannot login, Username doesn't exist", Toast.LENGTH_SHORT).show();
                }

                if (flag_users_exist == Boolean.TRUE && database.exists_username(username)){
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

            case R.id.login_btn_signup:
                intent = new Intent(Login_Activity.this, Add_Customer.class);
                intent.putExtra("PARENT_ACTIVITY_CLASS", getClass());
                startActivity(intent);
                break;
        }
    }


}
