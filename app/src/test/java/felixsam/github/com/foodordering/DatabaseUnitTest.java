package felixsam.github.com.foodordering;

import android.os.Build;

import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import felixsam.github.com.foodordering.activities.MapActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(RobolectricTestRunner.class)
@Config(sdk={Build.VERSION_CODES.O_MR1})
public class DatabaseUnitTest {

    private DatabaseHelper dbHelper;

    @Before
    public void setUp() throws Exception{
        dbHelper = new DatabaseHelper(ApplicationProvider.getApplicationContext());

    }

    @Test
    public void testGetTopUserName(){
        dbHelper.addData_customers("First","Last","123","username1");
        dbHelper.addData_customers("First","Last","123","username2");

        String topUserName = dbHelper.getTopUserName();
        System.out.println("The top username is : " + topUserName);
        assertEquals(topUserName,"username1");
    }

    @Test
    public void testGetUserID(){
        dbHelper.addData_customers("First","Last","123","userId");
        dbHelper.addData_customers("First","Last","123","userIdTwo");
        int userId = dbHelper.getUserID("userId");
        int userIdTwo = dbHelper.getUserID("userIdTwo");
        System.out.println("UserID: " + userId);
        assertEquals(1,userId);

    }

    @Test
    public void testUsernameExists(){
        dbHelper.addData_customers("First","Last","123","usernameExists");
        assertTrue( dbHelper.exists_username("usernameExists") );
    }

    @After
    public void tearDown(){
        dbHelper.close();
    }


}
