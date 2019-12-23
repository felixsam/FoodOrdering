package felixsam.github.com.foodordering.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.R;

public class Add_Customer extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Add_Customer";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private Intent intent;
    private EditText et_first_name_field, et_last_name_field, et_phone_number_field;
    Class nextActivityClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Get previous Activity
        Bundle extras = getIntent().getExtras();
        nextActivityClass = (Class<Activity>)extras.getSerializable("PARENT_ACTIVITY_CLASS");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_customer);
        this.setTitle("Add Customer");


        //DataBase
        mDatabaseHelper = new DatabaseHelper(this);
        et_first_name_field = (EditText) findViewById(R.id.et_customer_name_field);
        et_last_name_field = (EditText) findViewById(R.id.et_last_name_field);
        et_phone_number_field = (EditText) findViewById(R.id.et_phone_number_field);

        btnAdd = (Button) findViewById(R.id.btn_customer_add);
        btnViewData = (Button) findViewById(R.id.btn_customer_view);


        btnAdd.setOnClickListener(this);
        btnViewData.setOnClickListener(this);


    }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_customer_add:
                String First_Name_Entry = et_first_name_field.getText().toString();
                String Last_Name_Entry = et_last_name_field.getText().toString();
                String Phone_Number_Entry = et_phone_number_field.getText().toString();

                if (et_first_name_field.length() != 0) {
                    AddData(First_Name_Entry,Last_Name_Entry,Phone_Number_Entry);
                    et_first_name_field.setText("");
                    intent = new Intent(Add_Customer.this,nextActivityClass);
                    startActivity(intent);
                    finish();

                } else {
                    toastMessage("You must put something in the text field!");
                }
                break;

            case R.id.btn_customer_view:
                intent = new Intent(Add_Customer.this, List_Customers.class);
                startActivity(intent);
                break;


        }
    }

    public void AddData(String f_name, String l_name, String p_number) {
        boolean insertData = mDatabaseHelper.addData_customers(f_name,l_name,p_number);

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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,getClass());
        startActivity(intent);
        super.onBackPressed();
    }

}
