package felixsam.github.com.foodordering;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import felixsam.github.com.foodordering.adapters.AdapterMenu;
import felixsam.github.com.foodordering.adapters.AdapterOrderItems;

public class fragmentMenu extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_menu,container,false);
        RecyclerView rvMenu = rootView.findViewById(R.id.rv_menuList);

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = rootView.findViewById(R.id.navBar_Menu);
        bottomNavigationView.setSelectedItemId(R.id.bottom_navigation_item_menu);

        ArrayList<Integer> cardImages = new ArrayList<Integer>() {
            {
                add(R.drawable.chicken_salad);
                add(R.drawable.chicken_wings);
                add(R.drawable.beef_noodle_soup);
                add(R.drawable.cucumber_salad);
                add(R.drawable.hamburger);
                add(R.drawable.scallops);
                add(R.drawable.ribeye_steak);
                add(R.drawable.thai_noodle);
                add(R.drawable.brussel_sprout_sandwich);
                add(R.drawable.cuban_sandwich);
                add(R.drawable.pork_chop);
                add(R.drawable.shrimp);
            }
        };

        ArrayList<String> cardNames = new ArrayList<String>(){
            {
                add("Chicken Salad");
                add("Chicken Wings");
                add("Beef Noodle Soup");
                add("Cucumber Salad");
                add("Hamburger");
                add("Scallops");
                add("Rib-eye Steak");
                add("Thai Noodle");
                add("Sprout Sandwich");
                add("Cuban Sandwich");
                add("Pork Chop");
                add("Shrimp");
            }
        };

        ArrayList<String> cardDescriptions = new ArrayList<String>(){
            {
                add("Salad");
                add("Appetizer");
                add("Soup");
                add("Salad");
                add("Main Course");
                add("Appetizer");
                add("Main Course");
                add("Noodles");
                add("Appetizer");
                add("Appetizer");
                add("Main Course");
                add("Hors d'oeuvre");
            }
        };

        ArrayList<String> cardPrices = new ArrayList<String>(){
            {
                add("$1.99");
                add("$2.99");
                add("$3.99");
                add("$1.99");
                add("$2.99");
                add("$3.99");
                add("$1.99");
                add("$2.99");
                add("$3.99");
                add("$1.99");
                add("$2.99");
                add("$3.99");
            }
        };


        AdapterMenu adapterMenu = new AdapterMenu(getActivity(),cardImages,cardNames,cardDescriptions,cardPrices);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2,GridLayoutManager.VERTICAL,false);

        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvMenu.getContext(),
                layoutManager.getOrientation());
        //rvMenu.addItemDecoration(dividerItemDecoration);
        rvMenu.setLayoutManager(layoutManager);
        rvMenu.setNestedScrollingEnabled(false);
        rvMenu.setHasFixedSize(true);
        rvMenu.setAdapter(adapterMenu);



        //bottom hav handler
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //initialize fragment Manager
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                switch (item.getItemId()){
                    case R.id.bottom_navigation_item_home:
                        System.out.println("Home");
                        dismiss();
                        break;

                    case R.id.bottom_navigation_item_menu:
                        System.out.println("Menu");
                        break;

                    case R.id.bottom_navigation_item_checkout:
                        System.out.println("Checkout");

                        fragmentPlaceOrder fragmentPlaceOrder = new fragmentPlaceOrder();
                        transaction.add(android.R.id.content,fragmentPlaceOrder).addToBackStack(null).commit();
                        dismiss();

                        break;

                    case R.id.bottom_navigation_item_favourites:
                        System.out.println("Cocktail Menu");
                        //fragmentFavourites fragmentFavourite = new fragmentFavourites();
                        fragmentCocktailMenu fragmentCocktailMenu = new fragmentCocktailMenu();
                        transaction.add(android.R.id.content,fragmentCocktailMenu).addToBackStack(null).commit();
                        dismiss();
                        break;

                    case R.id.bottom_navigation_item_profile:
                        System.out.println("Profile");

                        fragmentProfile fragmentProfile = new fragmentProfile();
                        transaction.add(android.R.id.content,fragmentProfile).addToBackStack(null).commit();
                        dismiss();

                        break;
                }
                return true;
            }
        });
        //End of bottom hav handler


        return rootView;
    }
}
