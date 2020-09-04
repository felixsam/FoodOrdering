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

public class AddItemTest {
    private DatabaseHelper dbHelper;

    @Before
    public void setUp() throws Exception{
        dbHelper = new DatabaseHelper(ApplicationProvider.getApplicationContext());

    }

    @Test
    public void getItemName(){
        dbHelper.addData_items(1,"drinkName",12.34,4, "DRINKS");
        String itemName = dbHelper.getItemName(1);
        assertEquals("drinkName",itemName);

    }

    @After
    public void tearDown(){
        dbHelper.close();
    }
}
