package felixsam.github.com.foodordering.DatabaseTests.Items;

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

public class ItemPriceTest {

    private DatabaseHelper dbHelper;


    @Before
    public void setUp() throws Exception{
        dbHelper = new DatabaseHelper(ApplicationProvider.getApplicationContext());

    }

    @Test
    public void getItemPrice(){
        dbHelper.addData_items(1,"drinkName",12.34,3, "DRINKS");
        double itemPrice = dbHelper.getItemPrice(1);
        assertEquals(12.34,itemPrice,0.001);

    }

    @Test
    public void updateItemPrice(){
        dbHelper.addData_items(1,"drinkName",12.34,3, "DRINKS");
        dbHelper.updateItemPrice(1,3.33,"drinkName");

        double updatedItemPrice = dbHelper.getItemPrice(1);

        assertEquals(3.33,updatedItemPrice,0.001);
    }

    @After
    public void tearDown(){
        dbHelper.close();
    }

}
