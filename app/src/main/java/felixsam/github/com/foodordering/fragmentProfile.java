package felixsam.github.com.foodordering;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import felixsam.github.com.foodordering.Models.Item_Model_Display;
import felixsam.github.com.foodordering.adapters.AdapterProfileCards;
import felixsam.github.com.foodordering.adapters.ItemMenuAdapter;

public class fragmentProfile extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        RecyclerView rvProfileList = rootView.findViewById(R.id.rv_profileList);

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = rootView.findViewById(R.id.navBar_Menu_Profile);
        bottomNavigationView.setSelectedItemId(R.id.bottom_navigation_item_profile);

        ArrayList<Integer> cardImages = new ArrayList<Integer>(){
            {
                add(R.drawable.customer_list);
                add(R.drawable.folder_bills);
                add(R.drawable.delivery);
                add(R.drawable.window_settings);
                add(R.drawable.agreement);
                add(R.drawable.sign_out);
            }
        };
        ArrayList<String> cardDescriptions = new ArrayList<String>(){
            {
                add("Customer List");
                add("Order List");
                add("Delivery Support");
                add("Settings");
                add("Terms of use");
                add("Sign out");
            }
        };

        AdapterProfileCards adapterProfileCards = new AdapterProfileCards(getActivity(),cardDescriptions,cardImages);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvProfileList.getContext(),
                layoutManager.getOrientation());
        rvProfileList.addItemDecoration(dividerItemDecoration);
        rvProfileList.setLayoutManager(layoutManager);
        rvProfileList.setNestedScrollingEnabled(false);
        rvProfileList.setAdapter(adapterProfileCards);

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
                        fragmentMenu fragmentMenu = new fragmentMenu();
                        transaction.add(android.R.id.content,fragmentMenu).addToBackStack(null).commit();
                        dismiss();
                        break;

                    case R.id.bottom_navigation_item_checkout:
                        System.out.println("Checkout");

                        fragmentPlaceOrder fragmentPlaceOrder = new fragmentPlaceOrder();
                        transaction.add(android.R.id.content,fragmentPlaceOrder).addToBackStack(null).commit();
                        dismiss();

                        break;

                    case R.id.bottom_navigation_item_favourites:
                        System.out.println("Cocktail Menu");
                        fragmentCocktailMenu fragmentCocktailMenu = new fragmentCocktailMenu();
                        transaction.add(android.R.id.content,fragmentCocktailMenu).addToBackStack(null).commit();
                        dismiss();
                        break;

                    case R.id.bottom_navigation_item_profile:
                        System.out.println("Profile");

                        break;
                }
                return true;
            }
        });
        //End of bottom hav handler

        return rootView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
    }


}
