package felixsam.github.com.foodordering.DatabaseTests.Items;

import android.os.Build;

import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Models.Item;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk={Build.VERSION_CODES.O_MR1})

public class ItemCategoryTest {

    private DatabaseHelper dbHelper;

    @Before
    public void setUp() throws Exception{
        dbHelper = new DatabaseHelper(ApplicationProvider.getApplicationContext());

    }

    @Test
    public void getItemCategory(){
        dbHelper.addItem(1,"drinkName",12.34,3, "DRINKS");
        String itemCategory = dbHelper.getItemCategory(1);
        assertEquals("DRINKS",itemCategory);
    }

    @Test
    public void updateItemCategory(){
        dbHelper.addItem(1,"drinkName",12.34,3, "DRINKS");
        dbHelper.updateItemCategory(1,"CAKES");
        String itemCategory = dbHelper.getItemCategory(1);
        assertEquals("CAKES",itemCategory);
    }

    @Test
    public void getItemsByCategory(){
        dbHelper.addItem(1,"One",12.34,3, "DRINKS");
        dbHelper.addItem(1,"Two",12.34,3, "DRINKS");
        dbHelper.addItem(1,"Three",12.34,3, "DRINKS");

        ArrayList<Item> itemsExpected = new ArrayList<>();

        itemsExpected.add(new Item(1,1,"One",12.34,3,"DRINKS",0));
        itemsExpected.add(new Item(2,1,"Two",12.34,3,"DRINKS",0));
        itemsExpected.add(new Item(3,1,"Three",12.34,3,"DRINKS",0));

        for (int i = 0; i< itemsExpected.size();i++){
            System.out.println(itemsExpected.get(i));
        }

        ArrayList<Item> itemsActual = dbHelper.getItemContentsByCategory("DRINKS");

        for (int i = 0; i< itemsActual.size();i++){
            System.out.println(itemsActual.get(i));
        }

        assertEquals(itemsExpected,itemsActual);
    }

    @After
    public void tearDown(){
        dbHelper.close();
    }

}
