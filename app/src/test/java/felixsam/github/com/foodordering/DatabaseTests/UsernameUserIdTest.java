package felixsam.github.com.foodordering.DatabaseTests;

import android.os.Build;

import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import felixsam.github.com.foodordering.DatabaseHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(RobolectricTestRunner.class)
@Config(sdk={Build.VERSION_CODES.O_MR1})
public class UsernameUserIdTest {

    private DatabaseHelper dbHelper;

    @Before
    public void setUp() throws Exception{
        dbHelper = new DatabaseHelper(ApplicationProvider.getApplicationContext());

    }

    @Test
    public void testGetTopUserName(){
        dbHelper.addNewUser("First","Last","123","username1","password");
        dbHelper.addNewUser("First","Last","123","username2","password");

        String topUserName = dbHelper.getTopUserName();
        System.out.println("The top username is : " + topUserName);
        assertEquals(topUserName,"username1");
    }

    @Test
    public void testGetUserID(){
        dbHelper.addNewUser("First","Last","123","userId","password");
        dbHelper.addNewUser("First","Last","123","userIdTwo","password");
        int userId = dbHelper.getUserID("userId");
        int userIdTwo = dbHelper.getUserID("userIdTwo");
        System.out.println("UserID: " + userId);
        assertEquals(1,userId);

    }

    @Test
    public void testUsernameExists(){
        dbHelper.addNewUser("First","Last","123","usernameExists","password");
        assertTrue( dbHelper.exists_username("usernameExists") );
    }

    @After
    public void tearDown(){
        dbHelper.close();
    }


}
