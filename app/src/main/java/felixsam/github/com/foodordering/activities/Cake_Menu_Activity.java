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
import felixsam.github.com.foodordering.adapters.Item_Menu_Adapter;

public class Cake_Menu_Activity extends AppCompatActivity {


    //set menu names
    private final String[] list_cake_names = new String[]{
            "Strawberry",
            "Vanilla",
            "Chocolate",
            "Lemon Blueberry",
            "Mango",
            "Oreo",
            "Cheesecake",
            "Angel"
    };


    //set menu prices
    private final double[] list_cake_prices = new double[]{
            1.99,
            2.99,
            3.99,
            4.99,
            5.99,
            6.99,
            7.99,
            8.99
    };


    //set menu images
    private final int[] list_cake_thumbnails = new int[]{
            R.drawable.cake_strawberry,
            R.drawable.cake_vanilla_sponge,
            R.drawable.cake_chocolate,
            R.drawable.cake_lemon_blueberry,
            R.drawable.cake_mango,
            R.drawable.cake_oreo,
            R.drawable.cake_cheesecake,
            R.drawable.cake_angel
    };

    private final int size_cake_list = list_cake_thumbnails.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(" Cake Menu ");

        setContentView(R.layout.layout_item_menu_cards);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        DatabaseHelper myDB = new DatabaseHelper(this);

        RecyclerView rv = findViewById(R.id.rv_item_list_cards);

        ArrayList<Item_Model_Display> itemModelDisplayArrayList = getModel();
        Item_Menu_Adapter customAdapter = new Item_Menu_Adapter(this, R.layout.adapter_menu_item_cards, itemModelDisplayArrayList);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(customAdapter);


    }


    //display menu items
    private ArrayList<Item_Model_Display> getModel(){
        ArrayList<Item_Model_Display> list = new ArrayList<>();
        for(int i = 0; i < size_cake_list; i++){

            Item_Model_Display itemModelDisplay = new Item_Model_Display();
            itemModelDisplay.setQuantity(1);
            itemModelDisplay.setItem_Name(list_cake_names[i]);
            itemModelDisplay.setPrice(list_cake_prices[i]);
            itemModelDisplay.setImage_drawable(list_cake_thumbnails[i]);
            itemModelDisplay.setCategory("CAKE");
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
            Intent intent = new Intent(this, List_Added_Cakes.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
