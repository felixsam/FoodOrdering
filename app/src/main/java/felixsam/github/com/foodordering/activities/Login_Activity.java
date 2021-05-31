package felixsam.github.com.foodordering.activities;

import android.content.Intent;
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

    private AutoCompleteTextView autoCompleteTextUsername;
    private TextInputEditText autoCompleteTextPassword;
    private final Globals g = Globals.getInstance();
    private Boolean flagUsersExist = Boolean.FALSE;
    private ArrayAdapter<User> adapterDropdownUsername;
    private DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        autoCompleteTextUsername = findViewById(R.id.dropdown_username);
        autoCompleteTextPassword = findViewById(R.id.text_password);

        Button btnLogin = findViewById(R.id.btn_login);
        Button btnSignUp = findViewById(R.id.login_btn_signup);

        database = new DatabaseHelper(this);

        //For development
        //pre-fill username for quick login
        autoCompleteTextUsername.setText(database.getTopUserName());
        autoCompleteTextPassword.setText(database.getPassword(database.getTopUserName()));

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        //hide support bar
        Objects.requireNonNull(getSupportActionBar()).hide();


        ArrayList<User> listUsernames = database.getAllUsers();
        adapterDropdownUsername = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listUsernames);
        autoCompleteTextUsername.setAdapter(adapterDropdownUsername);

    }

    //Repopulate username dropdown on resume from add customers
    @Override
    public void onResume(){
        super.onResume();
        if (flagUsersExist == Boolean.TRUE){
            adapterDropdownUsername.notifyDataSetChanged();
            autoCompleteTextUsername.setAdapter(adapterDropdownUsername);
        }

    }

    //Login Button: if username exists in database, login to main menu
    //Sign Up Button: Register new customer, go to add customer activity
    @Override
    public void onClick(View v){
        Intent intent;
        switch (v.getId()){

            case R.id.btn_login:
                String username = autoCompleteTextUsername.getText().toString();

                if (!database.usernameExists(username)){
                    Snackbar.make(findViewById(android.R.id.content),"Cannot login, username does not exist",Snackbar.LENGTH_LONG).show();
                    break;
                }

                if (database.usernameExists(username)){
                    User loginUsername = database.getUser(username);
                    String loginFirstName = loginUsername.getFirstName();
                    int loginUserId = loginUsername.getUserID();

                    Log.i(TAG,"Setting global user - Login Name: " + loginFirstName + "\n UserID: " + loginUserId);
                    g.setUser(loginFirstName);
                    g.setUser_ID(loginUserId);

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
