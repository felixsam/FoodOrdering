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
import felixsam.github.com.foodordering.adapters.ListUsersAdapter;

public class List_Users extends AppCompatActivity {
    private static final String TAG = List_Users.class.getSimpleName();

    private DatabaseHelper mDatabaseHelper;
    private ListUsersAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setTitle("List of Customers");
        setContentView(R.layout.list_users);
        RecyclerView rv_users = findViewById(R.id.rv_users);

        mDatabaseHelper = new DatabaseHelper(this);


        Log.i(TAG, "Displaying data in the ListView.");

        ArrayList<User> usersList = mDatabaseHelper.getAllUsers();
        adapter = new ListUsersAdapter(usersList,List_Users.this);
        rv_users.setAdapter(adapter);
        rv_users.setLayoutManager(new LinearLayoutManager(this));
    }
}

