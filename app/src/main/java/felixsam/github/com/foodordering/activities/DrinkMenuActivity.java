package felixsam.github.com.foodordering.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.steamcrafted.materialiconlib.MaterialMenuInflater;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Models.Item_Model_Display;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.adapters.ItemMenuAdapter;


public class DrinkMenuActivity extends AppCompatActivity {

    //set menu item names
    private final String[] drink_names_list = new String[]{
            "Pumpkin Spice Latte",
            "Milk Tea",
            "Frappuccino",
            "Orange Juice",
            "Coke",
            "Green Tea",
            "Mocha",
            "Milk"
    };


    //set item prices
    private final double[] drink_price_list = new double[]{
            1.99,
            2.99,
            3.99,
            4.99,
            5.99,
            6.99,
            7.99,
            8.99
    };


    //set item images
    private final int[] myImageList = new int[]{
            R.drawable.drink_pumpkin_spice,
            R.drawable.drink_milk_tea,
            R.drawable.drink_frappuccino,
            R.drawable.drink_orange,
            R.drawable.drink_coke,
            R.drawable.drink_green_tea,
            R.drawable.drink_mocha,
            R.drawable.drink_milk
    };

    private final int size_of_list = myImageList.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(" Drink Menu ");

        setContentView(R.layout.layout_item_menu_cards);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        DatabaseHelper myDB = new DatabaseHelper(this);

        RecyclerView rv_item_list_cards = findViewById(R.id.rv_item_list_cards);

        ArrayList<Item_Model_Display> itemModelDisplayArrayList = getModel();
        ItemMenuAdapter customAdapterItemMenu = new ItemMenuAdapter(this, R.layout.adapter_menu_item_cards, itemModelDisplayArrayList);

        // 6. For performance, tell OS RecyclerView won't change size
        rv_item_list_cards.setLayoutManager(new LinearLayoutManager(this));
        rv_item_list_cards.setHasFixedSize(true);

        rv_item_list_cards.setAdapter(customAdapterItemMenu);

    }


    //Display menu items
    private ArrayList<Item_Model_Display> getModel(){
        ArrayList<Item_Model_Display> list = new ArrayList<>();
        for(int i = 0; i < size_of_list; i++){

            Item_Model_Display itemModelDisplay = new Item_Model_Display();
            itemModelDisplay.setQuantity(1);
            itemModelDisplay.setItem_Name(drink_names_list[i]);
            itemModelDisplay.setPrice(drink_price_list[i]);
            itemModelDisplay.setImage_drawable(myImageList[i]);
            itemModelDisplay.setCategory("DRINK");
            list.add(itemModelDisplay);
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MaterialMenuInflater
                .with(this)
                .setDefaultColor(Color.BLACK)
                .inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_home) {
            //Toast.makeText(Drink_Menu_Activity.this, "Action clicked", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            return true;
        }
        if (id == R.id.action_list){
            Intent intent = new Intent(this, List_Added_Drinks.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
