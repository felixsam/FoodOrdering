package felixsam.github.com.foodordering.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Models.User;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.adapters.ListUsersCustomAdapter;

public class List_Users extends AppCompatActivity {
    private static final String TAG = List_Users.class.getSimpleName();

    private DatabaseHelper mDatabaseHelper;
    private User user;
    private ArrayList<User> usersList;
    private ListUsersCustomAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setTitle("List of Customers");
        setContentView(R.layout.list_users);
        RecyclerView rv_users = findViewById(R.id.rv_users);

        mDatabaseHelper = new DatabaseHelper(this);


        Log.i(TAG, "Displaying data in the ListView.");

        //create the list adapter and set the adapter
        usersList = new ArrayList<>();
        Cursor user_data = mDatabaseHelper.getUserIdAndName();
        int numRows = user_data.getCount();
        if(numRows == 0){
            Log.d(TAG,"No rows to populate list " + numRows);
        }else{
            Log.i(TAG,"Number of Rows is: " + numRows);

            int i = 0;
            while(user_data.moveToNext()){
                Log.i(TAG,"New User Entry: ");
                Log.i(TAG,user_data.getInt(0) +" "
                        + user_data.getString(1) + " "
                        + user_data.getString(2));

                user = new User(user_data.getInt(user_data.getColumnIndex(mDatabaseHelper.USERS_COL1_ID)),
                        user_data.getString(user_data.getColumnIndex(mDatabaseHelper.USERS_COL2_FIRST_NAME)),
                        user_data.getString(user_data.getColumnIndex(mDatabaseHelper.USERS_COL7_USERNAME)));
                usersList.add(i, user);
                i++;
            }
            adapter = new ListUsersCustomAdapter(usersList,List_Users.this);
            rv_users.setAdapter(adapter);
            rv_users.setLayoutManager(new LinearLayoutManager(this));
        }
    }

}
