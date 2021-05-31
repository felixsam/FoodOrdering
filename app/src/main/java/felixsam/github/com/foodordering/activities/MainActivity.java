package felixsam.github.com.foodordering.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import net.steamcrafted.materialiconlib.MaterialMenuInflater;

import java.util.ArrayList;
import java.util.Objects;

import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.adapters.AdapterHome;
import felixsam.github.com.foodordering.fragmentCocktailMenu;
import felixsam.github.com.foodordering.fragmentMenu;
import felixsam.github.com.foodordering.fragmentPlaceOrder;
import felixsam.github.com.foodordering.fragmentProfile;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        //hide support bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        //Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        RecyclerView rvTrending = findViewById(R.id.rv_home_trending);
        RecyclerView rvMostRecent = findViewById(R.id.rv_home_mostRecent);
        RecyclerView rvMostPopular = findViewById(R.id.rv_home_mostPopular);

        SearchView searchHome = findViewById(R.id.searchView_Home);

        searchHome.setQueryHint("Search");

        ArrayList<Integer> cardImages = new ArrayList<Integer>() {
            {
                add(R.drawable.chicken_salad);
                add(R.drawable.ribeye_steak);
                add(R.drawable.cucumber_salad);
                add(R.drawable.hamburger);
                add(R.drawable.thai_noodle);
            }
        };

        ArrayList<String> cardNames = new ArrayList<String>() {
            {
                add("Chicken Salad");
                add("Rib-eye Steak");
                add("Cucumber Salad");
                add("Hamburger");
                add("Thai noodle");
            }
        };

        ArrayList<String> cardDescriptions = new ArrayList<String>() {
            {
                add("Salad");
                add("Main Course");
                add("Salad");
                add("Main Course");
                add("Appetizer");
            }
        };

        ArrayList<Integer> cardImages2 = new ArrayList<Integer>() {
            {
                add(R.drawable.hamburger);
                add(R.drawable.beef_noodle_soup);
                add(R.drawable.brussel_sprout_sandwich);
                add(R.drawable.pork_chop);
                add(R.drawable.cucumber_salad);
            }
        };

        ArrayList<String> cardNames2 = new ArrayList<String>() {
            {
                add("Hamburger");
                add("Beef Noodle Soup");
                add("Sprout Sandwich");
                add("Pork Chop");
                add("Cucumber Salad");
            }
        };

        ArrayList<String> cardDescriptions2 = new ArrayList<String>() {
            {
                add("Main Course");
                add("Noodles");
                add("Appetizer");
                add("Main Course");
                add("Salad");
            }
        };

        ArrayList<Integer> cardImages3 = new ArrayList<Integer>() {
            {
                add(R.drawable.brussel_sprout_sandwich);
                add(R.drawable.scallops);
                add(R.drawable.hamburger);
                add(R.drawable.ribeye_steak);
                add(R.drawable.beef_noodle_soup);
                add(R.drawable.cuban_sandwich);
            }
        };

        ArrayList<String> cardNames3 = new ArrayList<String>() {
            {
                add("Sprout Sandwich");
                add("Scallops");
                add("Hamburger");
                add("Rib-eye Steak");
                add("Beef Noodle Soup");
                add("Cuban Sandwich");
            }
        };

        ArrayList<String> cardDescriptions3 = new ArrayList<String>() {
            {
                add("Appetizer");
                add("Hors d'oeuvre");
                add("Main Course");
                add("Main Course");
                add("Soups");
                add("Appetizer");
            }
        };

        ArrayList<String> itemPrices = new ArrayList<String>(){
            {
                add("$1.99");
                add("$1.99");
                add("$1.99");
                add("$1.99");
                add("$1.99");
            }

        };

        ArrayList<String> itemPrices2 = new ArrayList<String>(){
            {
                add("$1.99");
                add("$1.99");
                add("$1.99");
                add("$1.99");
                add("$1.99");
            }

        };

        ArrayList<String> itemPrices3 = new ArrayList<String>(){
            {
                add("$1.99");
                add("$1.99");
                add("$1.99");
                add("$1.99");
                add("$1.99");
                add("$1.99");
            }

        };

        AdapterHome adapterHome = new AdapterHome(MainActivity.this, cardImages, cardNames, cardDescriptions,itemPrices);
        AdapterHome adapterHomeMostRecent = new AdapterHome(MainActivity.this, cardImages3, cardNames3, cardDescriptions3,itemPrices3);
        AdapterHome adapterHomeMostPopular = new AdapterHome(MainActivity.this, cardImages2, cardNames2, cardDescriptions2,itemPrices2);


        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvTrending.getContext(),
                layoutManager.getOrientation());

        LinearLayoutManager layoutManagerMostPopular = new LinearLayoutManager(MainActivity.this);
        layoutManagerMostPopular.setOrientation(LinearLayoutManager.HORIZONTAL);
        DividerItemDecoration dividerItemDecorationMostPopular = new DividerItemDecoration(rvMostPopular.getContext(),
                layoutManagerMostPopular.getOrientation());


        LinearLayoutManager layoutManagerMostRecent = new LinearLayoutManager(MainActivity.this);
        layoutManagerMostRecent.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecorationMostRecent = new DividerItemDecoration(rvMostRecent.getContext(),
                layoutManagerMostRecent.getOrientation());



        // rvTrending.addItemDecoration(dividerItemDecoration);
        rvTrending.setLayoutManager(layoutManager);
        rvTrending.setNestedScrollingEnabled(false);
        rvTrending.setHasFixedSize(true);
        rvTrending.setAdapter(adapterHome);

        //rvMostPopular.addItemDecoration(dividerItemDecorationMostPopular);
        rvMostPopular.setLayoutManager(layoutManagerMostPopular);
        rvMostPopular.setNestedScrollingEnabled(false);
        rvMostPopular.setHasFixedSize(true);
        rvMostPopular.setAdapter(adapterHomeMostPopular);

        //rvMostRecent.addItemDecoration(dividerItemDecorationMostRecent);
        rvMostRecent.setLayoutManager(layoutManagerMostRecent);
        rvMostRecent.setNestedScrollingEnabled(false);
        rvMostRecent.setHasFixedSize(false);
        rvMostRecent.setAdapter(adapterHomeMostRecent);


        //Declare navigation view ID (bottomnav_view) in content_main
        bottomNavigationView = findViewById(R.id.bottom_home_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //initialize fragment Manager
                FragmentManager fragmentManger = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManger.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                switch (item.getItemId()){
                    case R.id.bottom_navigation_item_home:
                        System.out.println("Home");
                        break;

                    case R.id.bottom_navigation_item_menu:
                        System.out.println("Menu");
                        fragmentMenu fragmentMenu = new fragmentMenu();
                        transaction.add(android.R.id.content,fragmentMenu).addToBackStack(null).commit();
                        break;

                    case R.id.bottom_navigation_item_checkout:
                        System.out.println("Checkout");

                        fragmentPlaceOrder fragmentPlaceOrder = new fragmentPlaceOrder();
                        transaction.add(android.R.id.content,fragmentPlaceOrder).addToBackStack(null).commit();

                        break;

                    case R.id.bottom_navigation_item_favourites:
                        System.out.println("Cocktail Menu");
                        //fragmentFavourites fragmentFavourite = new fragmentFavourites();
                        fragmentCocktailMenu fragmentCocktailMenu = new fragmentCocktailMenu();
                        transaction.add(android.R.id.content,fragmentCocktailMenu).addToBackStack(null).commit();
                        break;

                    case R.id.bottom_navigation_item_profile:
                        System.out.println("Profile");

                        fragmentProfile fragmentProfile = new fragmentProfile();
                        transaction.add(android.R.id.content,fragmentProfile).addToBackStack(null).commit();

                        break;
                }
                return true;
            }
        });
        //End of bottom hav handler


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
        int count = getSupportFragmentManager().getBackStackEntryCount();
        bottomNavigationView.setSelectedItemId(R.id.bottom_navigation_item_home);

        if (count == 0) {
            super.onBackPressed();
            //additional code
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.bottom_navigation_item_home);
    }


}
