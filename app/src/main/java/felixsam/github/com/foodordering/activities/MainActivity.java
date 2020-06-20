package felixsam.github.com.foodordering.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.steamcrafted.materialiconlib.MaterialMenuInflater;

import java.util.Objects;

import felixsam.github.com.foodordering.adapters.Adapter_DashboardCards;
import felixsam.github.com.foodordering.R;

public class MainActivity extends AppCompatActivity {


    private final Integer[] drawableArray = {
            R.drawable.drink_menu,
            R.drawable.cake_menu,
            R.drawable.cocktail_menu,
            R.drawable.checkout_menu,
            R.drawable.order_menu,
            R.drawable.customer_list,
            R.drawable.delivery_menu

    };
    private final String[] titleArray = {
            "Drinks",
            "Cakes",
            "Cocktails",
            "Checkout",
            "Orders",
            "Sign Up",
            "Directions"
    };

    private final String[] subtitleArray = {
            "Open Drinks Menu",
            "Open Cakes Menu",
            "Make cocktails",
            "Checkout for current User",
            "See active orders",
            "Register new customers",
            "Open Directions Menu"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hide support bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        //Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        RecyclerView recyclerView = findViewById(R.id.rv);


        Adapter_DashboardCards dashboard_cards = new Adapter_DashboardCards(MainActivity.this, drawableArray, titleArray, subtitleArray);
        recyclerView.setAdapter(dashboard_cards);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Toast.makeText(MainActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
