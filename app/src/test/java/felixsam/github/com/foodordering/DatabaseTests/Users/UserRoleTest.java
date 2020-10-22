package felixsam.github.com.foodordering.DatabaseTests.Users;

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

@RunWith(RobolectricTestRunner.class)
@Config(sdk={Build.VERSION_CODES.O_MR1})

public class UserRoleTest {
    private DatabaseHelper dbHelper;

    @Before
    public void setUp() throws Exception{
        dbHelper = new DatabaseHelper(ApplicationProvider.getApplicationContext());

    }

    @Test
    public void testDefaultUserRole(){
        dbHelper.addNewUser("First","Last","123","username1","password");
        String userRole = dbHelper.getUserRole(1);
        System.out.println("userRole: " + userRole);

        assertEquals("Admin",userRole);
    }

    @Test
    public void testUpdateUserRole(){
        dbHelper.addNewUser("First","Last","123","username1","password");
        String userRole = dbHelper.getUserRole(1);
        System.out.println("userRole before: " + userRole);

        dbHelper.updateUserRole(1,"Customer");
        String updatedUserRole = dbHelper.getUserRole(1);
        System.out.println("userRole after update: " + updatedUserRole);
        assertEquals("Customer",updatedUserRole);

    }

    @After
    public void tearDown(){

        dbHelper.close();
    }
}
