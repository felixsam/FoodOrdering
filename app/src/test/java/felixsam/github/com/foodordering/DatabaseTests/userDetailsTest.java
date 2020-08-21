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

@RunWith(RobolectricTestRunner.class)
@Config(sdk={Build.VERSION_CODES.O_MR1})


public class userDetailsTest {

    private DatabaseHelper dbHelper;

    @Before
    public void setUp() throws Exception{
        dbHelper = new DatabaseHelper(ApplicationProvider.getApplicationContext());

    }

    @Test
    public void testGetPhoneNumber(){
        dbHelper.addNewUser("First","Last","123","username","password");

        assertEquals("123",dbHelper.getUserPhoneNumber("username"));
    }

    @Test
    public void testGetLastName(){
        dbHelper.addNewUser("First","Last","123","username","password");

        assertEquals("Last",dbHelper.getUserLastName("username"));
    }

    @Test
    public void testGetFirstName(){
        dbHelper.addNewUser("First","Last","123","username","password");
        assertEquals("First",dbHelper.getUserFirstName("username"));
    }

    @After
    public void tearDown(){
        dbHelper.close();
    }

}
