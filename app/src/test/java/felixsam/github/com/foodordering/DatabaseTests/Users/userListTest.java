package felixsam.github.com.foodordering.DatabaseTests.Users;

import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import felixsam.github.com.foodordering.DatabaseHelper;

public class userListTest {

    private DatabaseHelper dbHelper;

    @Before
    public void setUp() throws Exception{
        dbHelper = new DatabaseHelper(ApplicationProvider.getApplicationContext());

    }

    @Test
    public void getUserList(){

    }


    @After
    public void tearDown(){
        dbHelper.close();
    }


}
