package felixsam.github.com.foodordering.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Models.Item_Model_Display;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.adapters.Item_Menu_Adapter;


public class Drink_Menu extends AppCompatActivity {
    DatabaseHelper myDB;

    private RecyclerView rv;
    public static ArrayList<Item_Model_Display> itemModelDisplayArrayList;

    private Item_Menu_Adapter customAdapterItemMenu;
    private Button btnnext;
    private String[] drink_names_list = new String[]{
            "Milk",
            "Milk Tea",
            "Orange Juice",
            "Coke","Green Tea",
            "Frappucino",
            "Mocha",
            "Pumpkin Spice Latte"
    };

    private Integer[] pricelist = new Integer[]{
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8
    };

    private int[] myImageList = new int[]{
            R.drawable.drink_milk,
            R.drawable.drink_milk_tea,
            R.drawable.drink_orange,
            R.drawable.drink_coke,
            R.drawable.drink_green_tea,
            R.drawable.drink_frappuccino,
            R.drawable.drink_mocha,
            R.drawable.drink_pumpkin_spice
    };

    private int size_of_list = myImageList.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(" Drink Menu ");


        setContentView(R.layout.layout_item_menu_cards);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        myDB = new DatabaseHelper(this);

        rv = (RecyclerView) findViewById(R.id.rv_item_list_cards);

        btnnext = (Button) findViewById(R.id.btn_next_item_list_cards);

        itemModelDisplayArrayList = getModel();
        customAdapterItemMenu = new Item_Menu_Adapter(this,R.layout.adapter_menu_item_cards,itemModelDisplayArrayList);

        // 6. For performance, tell OS RecyclerView won't change size
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);

        rv.setAdapter(customAdapterItemMenu);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Drink_Menu.this, List_Added_Drinks.class);
                startActivity(intent);
            }
        });



    }

    private ArrayList<Item_Model_Display> getModel(){
        ArrayList<Item_Model_Display> list = new ArrayList<>();
        for(int i = 0; i < size_of_list; i++){

            Item_Model_Display itemModelDisplay = new Item_Model_Display();
            itemModelDisplay.setQuantity(1);
            itemModelDisplay.setItem_Name(drink_names_list[i]);
            itemModelDisplay.setPrice(pricelist[i]);
            itemModelDisplay.setImage_drawable(myImageList[i]);
            itemModelDisplay.setCategory("DRINK");
            list.add(itemModelDisplay);
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            Toast.makeText(Drink_Menu.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
