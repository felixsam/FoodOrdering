package felixsam.github.com.foodordering.DatabaseTests.Checkout;

import android.os.Build;

import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Models.Checkout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk={Build.VERSION_CODES.O_MR1})

public class AddCheckoutTest {

    private DatabaseHelper dbHelper;

    @Before
    public void setUp() throws Exception{
        dbHelper = new DatabaseHelper(ApplicationProvider.getApplicationContext());
    }


    @Test
    public void getCheckoutItems(){
        dbHelper.addItem(1,"One",12.34,3, "DRINKS");
        dbHelper.addItem(1,"Two",12.34,3, "DRINKS");
        dbHelper.addItem(1,"Three",12.34,3, "DRINKS");

        ArrayList<Checkout> expectedCheckout = new ArrayList<>();
        expectedCheckout.add(new Checkout(1,"One",12.34,3,37.02));
        expectedCheckout.add(new Checkout(1,"Two",12.34,3,37.02));
        expectedCheckout.add(new Checkout(1,"Three",12.34,3,37.02));

        Collections.sort(expectedCheckout,Checkout::compareTo);

        for (int i = 0; i< expectedCheckout.size();i++){
            System.out.println(expectedCheckout.get(i));
        }

        ArrayList<Checkout> actualCheckout = dbHelper.getCheckoutItems(1);
        Collections.sort(actualCheckout,Checkout::compareTo);

        for (int i = 0; i< actualCheckout.size();i++){
            System.out.println(actualCheckout.get(i));
        }
        assertEquals(expectedCheckout,actualCheckout);

    }

    @Test
    public void checkoutItems(){
        dbHelper.addItem(1,"One",12.34,3, "DRINKS");
        dbHelper.addItem(1,"Two",12.34,3, "DRINKS");
        dbHelper.addItem(1,"Three",12.34,3, "DRINKS");

        //create an order
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Integer orderID = dbHelper.getOrderID(1,currentDateTimeString);

        dbHelper.checkoutItems(1,orderID);

        int[] expectedResult = new int[]{orderID,orderID,orderID};
        int[] actualResult = new int[3];

        actualResult[0] = dbHelper.getOrderId(1);
        actualResult[1] = dbHelper.getOrderId(2);
        actualResult[2] = dbHelper.getOrderId(3);

        assertTrue(Arrays.equals(expectedResult,actualResult));

    }


    @After
    public void tearDown(){

        dbHelper.close();
    }

}
