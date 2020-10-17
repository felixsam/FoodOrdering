package felixsam.github.com.foodordering.DatabaseTests;

import android.os.Build;

import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Date;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Models.Order;

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

    @Test
    public void getOrderId(){
        //Populate database with items
        dbHelper.addData_items(1,"One",12.34,3, "DRINKS");
        dbHelper.addData_items(1,"Two",12.34,3, "DRINKS");
        dbHelper.addData_items(1,"Three",12.34,3, "DRINKS");

        //Set Order ID
        dbHelper.setOrderID(1,8);
        dbHelper.setOrderID(2,5);
        dbHelper.setOrderID(3,3);

        ArrayList<Integer> expectedOrderId = new ArrayList<>();
        expectedOrderId.add(8);
        expectedOrderId.add(5);
        expectedOrderId.add(3);

        ArrayList<Integer> actualOrderId = new ArrayList<>();

        for (int i = 1; i <= expectedOrderId.size(); i++){
            actualOrderId.add(dbHelper.getOrderId(i));
        }

        assertEquals(expectedOrderId,actualOrderId);


    }

    @After
    public void tearDown(){

        dbHelper.close();
    }
}
