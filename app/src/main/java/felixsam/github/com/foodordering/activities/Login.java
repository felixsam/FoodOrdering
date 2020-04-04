package felixsam.github.com.foodordering.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Globals;
import felixsam.github.com.foodordering.Models.User;
import felixsam.github.com.foodordering.R;

public class Login extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner dropdown;
    private final Globals g = Globals.getInstance();
    //check for users
    private Boolean anyusers = Boolean.FALSE;
    private ArrayAdapter<User> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        DatabaseHelper login_db = new DatabaseHelper(this);
        ArrayList<User> userlist = new ArrayList<>();

        dropdown = findViewById(R.id.spinner);

        Cursor data = login_db.getCustomer_ID_and_Name();
        int numRows = data.getCount();
        String TAG = Login.class.getSimpleName();

        if (numRows == 0) {
            Log.i(TAG, "Number of Rows is: " + numRows);

            Toast.makeText(Login.this, "The Database is empty  :(.", Toast.LENGTH_LONG).show();
        } else {
            System.out.println("Else Statement");
            anyusers = Boolean.TRUE;
            Log.i(TAG, "Number of Rows is: " + numRows);

            int i = 0;
            while (data.moveToNext()) {
                System.out.println("New User");
                User user = new User(data.getInt(data.getColumnIndex(DatabaseHelper.CUSTOMERS_COL1_ID)),
                        data.getString(data.getColumnIndex(DatabaseHelper.CUSTOMERS_COL2_FIRST_NAME))
                );

                System.out.println("Adding user");

                userlist.add(i, user);
                System.out.println(data.getInt(data.getColumnIndex(DatabaseHelper.CUSTOMERS_COL1_ID)) + " "
                        + data.getString(data.getColumnIndex(DatabaseHelper.CUSTOMERS_COL2_FIRST_NAME))
                );
                System.out.println(userlist.get(i).getName());
                i++;

            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, userlist);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            System.out.println("Set Adapter");

            dropdown.setAdapter(adapter);
            dropdown.setOnItemSelectedListener(this);

        }
        System.out.println("Set Button");

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        Button btn_register = findViewById(R.id.login_btn_register);
        btn_register.setOnClickListener(this);
    }


    private User getSelectedUser(){
        return (User) dropdown.getSelectedItem();
        }


    private void displayUserData(User user){
        String name = user.getName();
        int UserID = user.getUserID();

        String userData = "Name: " + name + "\n UserID: " + UserID;

        Toast.makeText(this,userData,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView parent, View view, int position, long id) {
        User user = (User) parent.getSelectedItem();
        displayUserData(user);

        //set as selected item.
        dropdown.setSelection(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onResume(){
        super.onResume();
        if (anyusers == Boolean.TRUE){
            adapter.notifyDataSetChanged();
            dropdown.setAdapter(adapter);
        }

    }

    @Override
    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btn_login:
                System.out.println("Clicked login button");

                if (anyusers == Boolean.TRUE){
                    User login_user = getSelectedUser();
                    String login_name = login_user.getName();
                    int login_id = login_user.getUserID();


                    System.out.println("Login Name: " + login_name + "\n UserID: " + login_id);

                    g.setUser(login_name);
                    g.setUser_ID(login_id);

                    intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    //prevent going back to login activity
                    //Destroy login Activity
                    finish();
                }else{
                    Toast.makeText(this, "Cannot login, No users", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.login_btn_register:
                intent = new Intent(Login.this, Add_Customer.class);
                intent.putExtra("PARENT_ACTIVITY_CLASS", getClass());
                startActivity(intent);
                break;
        }
    }
}
