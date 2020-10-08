package felixsam.github.com.foodordering.DatabaseTests;

import android.os.Build;

import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Date;

import felixsam.github.com.foodordering.DatabaseHelper;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk={Build.VERSION_CODES.O_MR1})

public class AddOrderTest {

    private DatabaseHelper dbHelper;

    @Before
    public void setUp() throws Exception{
        dbHelper = new DatabaseHelper(ApplicationProvider.getApplicationContext());
    }

    @Test
    public void addNewOrder(){
        Date date = new Date();
        dbHelper.addData_newOrder(1,date.toString());

        assert(dbHelper.existOrder(1));

    }

    @Test
    public void getOrderDate(){
        Date date = new Date();
        dbHelper.addData_newOrder(1,date.toString());
        String dateString = dbHelper.getorderDate(1);

        assertEquals(date.toString(),dateString);

    }

    @After
    public void tearDown(){

        dbHelper.close();
    }
}
