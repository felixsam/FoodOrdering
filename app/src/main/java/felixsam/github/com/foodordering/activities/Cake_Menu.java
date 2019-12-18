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

public class Cake_Menu extends AppCompatActivity {
    DatabaseHelper myDB;

    private RecyclerView rv;
    public static ArrayList<Item_Model_Display> itemModelDisplayArrayList;


    private Item_Menu_Adapter customAdapter;

    //private Button btnnext;

    //set menu names
    private String[] drink_names_list = new String[]{
            "Strawberry Cake",
            "Vanilla Spongecake",
            "Chocolate Cake",
            "Lemon Blueberry Cake",
            "Mango Cake",
            "Oreo Cake",
            "Cheesecake",
            "Angel Cake"
    };


    //set menu prices
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


    //set menu images
    private int[] myImageList = new int[]{
            R.drawable.cake_strawberry,
            R.drawable.cake_vanilla_sponge,
            R.drawable.cake_chocolate,
            R.drawable.cake_lemon_blueberry,
            R.drawable.cake_mango,
            R.drawable.cake_oreo,
            R.drawable.cake_cheesecake,
            R.drawable.cake_angel
    };

    private int sizeoflist = myImageList.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(" Cake Menu ");

        setContentView(R.layout.layout_item_menu_cards);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        myDB = new DatabaseHelper(this);

        rv = (RecyclerView) findViewById(R.id.rv_item_list_cards);
        Button btnnext = (Button) findViewById(R.id.btn_next_item_list_cards);
        btnnext.setText("Cake Menu");

        itemModelDisplayArrayList = getModel();
        customAdapter = new Item_Menu_Adapter(this, R.layout.adapter_menu_item_cards,itemModelDisplayArrayList);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(customAdapter);


        //Move to Cake Menu Orders
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cake_Menu.this, List_Added_Cakes.class);
                startActivity(intent);
            }
        });
    }


    //display menu items
    private ArrayList<Item_Model_Display> getModel(){
        ArrayList<Item_Model_Display> list = new ArrayList<>();
        for(int i = 0; i < sizeoflist; i++){

            Item_Model_Display itemModelDisplay = new Item_Model_Display();
            itemModelDisplay.setQuantity(1);
            itemModelDisplay.setItem_Name(drink_names_list[i]);
            itemModelDisplay.setPrice(pricelist[i]);
            itemModelDisplay.setImage_drawable(myImageList[i]);
            itemModelDisplay.setCategory("CAKE");
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
            Toast.makeText(Cake_Menu.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
