package felixsam.github.com.foodordering.DatabaseTests;

import android.os.Build;

import androidx.test.core.app.ApplicationProvider;

import org.checkerframework.checker.units.qual.A;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Models.Item;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


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
        dbHelper.addData_items(1,"drinkName",12.34,3, "DRINKS");
        String itemName = dbHelper.getItemName(1);
        assertEquals("drinkName",itemName);

    }

    @Test
    public void deleteItem(){
        dbHelper.addData_items(1,"drinkName",12.34,3, "DRINKS");
        dbHelper.delData_items(1,"drinkName");
        assertFalse(dbHelper.existItem(1));
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

    @Test
    public void getItemCategory(){
        dbHelper.addData_items(1,"drinkName",12.34,3, "DRINKS");
        String itemCategory = dbHelper.getItemCategory(1);
        assertEquals("DRINKS",itemCategory);
    }

    @Test
    public void updateItemCategory(){
        dbHelper.addData_items(1,"drinkName",12.34,3, "DRINKS");
        dbHelper.updateItemCategory(1,"CAKES");
        String itemCategory = dbHelper.getItemCategory(1);
        assertEquals("CAKES",itemCategory);
    }

    @Test
    public void getOrderId(){
        dbHelper.addData_items(1,"drinkName",12.34,3, "DRINKS");
        dbHelper.setOrderID(1,8);
        int orderId = dbHelper.getOrderId(1);

        assertEquals(8,orderId);
    }

    @Test
    public void getItemQuantity(){
        dbHelper.addData_items(1,"drinkName",12.34,3, "DRINKS");
        int itemQuantity = dbHelper.getItemQuantity(1);

        assertEquals(3,itemQuantity);
    }

    @Test
    public void updateItemQuantity(){
        dbHelper.addData_items(1,"drinkName",12.34,3, "DRINKS");
        dbHelper.updateItemQuantity(1,8);

        int updatedItemQuantity = dbHelper.getItemQuantity(1);

        assertEquals(8,updatedItemQuantity);
    }


    @Test
    public void getItemsByCategory(){
        dbHelper.addData_items(1,"One",12.34,3, "DRINKS");
        dbHelper.addData_items(1,"Two",12.34,3, "DRINKS");
        dbHelper.addData_items(1,"Three",12.34,3, "DRINKS");

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
