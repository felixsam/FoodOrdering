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
import static org.junit.Assert.assertNotEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk={Build.VERSION_CODES.O_MR1})

public class UserPasswordTest {
    private DatabaseHelper dbHelper;

    @Before
    public void setUp() throws Exception{
        dbHelper = new DatabaseHelper(ApplicationProvider.getApplicationContext());

    }

    @Test
    public void testPasswordIsCorrect(){
        dbHelper.addNewUser("First","Last","123","username","password");
        String password = dbHelper.getPassword("username");
        assertEquals("password",password);
    }

    @Test
    public void testUpdatedPassword(){
        dbHelper.addNewUser("First","Last","123","username","oldPassword");
        dbHelper.updatePassword("username","oldPassword","new");
        String newPassword = dbHelper.getPassword("username");
        assertEquals("new",newPassword);
    }

    @Test
    public void testPasswordUpdateFail(){
        dbHelper.addNewUser("First","Last","123","username","oldPassword");
        dbHelper.updatePassword("username","new","newPassword");
        String newPassword = dbHelper.getPassword("username");
        assertNotEquals("newPassword",newPassword);
    }

    @After
    public void tearDown(){
        dbHelper.close();
    }

}
