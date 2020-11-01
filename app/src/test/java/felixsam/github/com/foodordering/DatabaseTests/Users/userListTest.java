package felixsam.github.com.foodordering.DatabaseTests.Users;

import android.os.Build;

import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Models.User;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk={Build.VERSION_CODES.O_MR1})

public class userListTest {

    private DatabaseHelper dbHelper;

    @Before
    public void setUp() throws Exception{
        dbHelper = new DatabaseHelper(ApplicationProvider.getApplicationContext());

    }

    @Test
    public void getUserList(){

        dbHelper.addNewUser("One","Last","123","user1","password");
        dbHelper.addNewUser("Two","Last","123","user2","password");
        dbHelper.addNewUser("Three","Last","123","user3","password");

        ArrayList<User> expectedUserList = new ArrayList<>();
        User user1 = new User(1,"One","Last","user1","password","123");
        User user2 = new User(2,"Two","Last","user2","password","123");
        User user3 = new User(3,"Three","Last","user3","password","123");

        user1.setRole("Admin");
        user2.setRole("Admin");
        user3.setRole("Admin");

        expectedUserList.add(user1);
        expectedUserList.add(user2);
        expectedUserList.add(user3);

        ArrayList<User> actualUserList = dbHelper.getAllUsers();

        assertEquals(expectedUserList,actualUserList);
    }


    @After
    public void tearDown(){
        dbHelper.close();
    }


}
