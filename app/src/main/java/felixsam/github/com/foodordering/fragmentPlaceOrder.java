package felixsam.github.com.foodordering;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.Nullable;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import felixsam.github.com.foodordering.Models.Checkout;
import felixsam.github.com.foodordering.activities.MainActivity;
import felixsam.github.com.foodordering.adapters.AdapterOrderItems;

import static java.lang.Boolean.TRUE;

public class fragmentPlaceOrder extends DialogFragment {

    private final Globals g = Globals.getInstance();
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_place_order, container, false);
        final DatabaseHelper dbHelper = new DatabaseHelper(getActivity());


        RecyclerView rvPlaceOrder = rootView.findViewById(R.id.rv_placeOrder);

        TextView tvTableNumber = rootView.findViewById(R.id.tv_placeOrder_tableNumber);
        TextView tvSubtotalAmount = rootView.findViewById(R.id.tv_placeOrder_subtotalPrice);
        TextView tvTaxAmount = rootView.findViewById(R.id.tv_placeOrder_taxPrice);
        TextView tvTotalAmount = rootView.findViewById(R.id.tv_placeOrder_totalPrice);

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = rootView.findViewById(R.id.navBar_Checkout);
        bottomNavigationView.setSelectedItemId(R.id.bottom_navigation_item_checkout);

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
                        break;

                    case R.id.bottom_navigation_item_checkout:
                        System.out.println("Checkout");
                        break;

                    case R.id.bottom_navigation_item_favourites:
                        System.out.println("Cocktail Menu");
                        fragmentCocktailMenu fragmentCocktailMenu = new fragmentCocktailMenu();
                        transaction.add(android.R.id.content,fragmentCocktailMenu).addToBackStack(null).commit();
                        dismiss();
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



        Button btn_submit = rootView.findViewById(R.id.btn_placeOrder);



        ArrayList<Integer> itemImages = new ArrayList<>();
        ArrayList<String> itemNames = new ArrayList<>();
        ArrayList<String> itemPrices = new ArrayList<>();
        ArrayList<String> itemCategories = new ArrayList<>();

        String username = g.getUser();
        int userID = g.getUser_ID();





        ArrayList<Checkout> checkout_list = new ArrayList<>();

        checkout_list = dbHelper.getCheckoutItems(userID);

        Double subTotal = 0.00;


        //Get the subtotal, tax and grandtotal
        for (int i = 0; i< checkout_list.size();i++){
            subTotal += checkout_list.get(i).getTotalAmount();
            itemImages.add(getImageId(checkout_list.get(i).getItemName()));
            itemNames.add(checkout_list.get(i).getItemName());
            itemCategories.add(checkout_list.get(i).getCategory());
            itemPrices.add("$" + checkout_list.get(i).getTotalAmount());
        }

        //truncate to 2 decimal places with 00 trailing zeros
        DecimalFormat df = new DecimalFormat("#.00");
        Double tax = subTotal*0.12;
        Double grandTotal = subTotal + tax;

        tvTableNumber.setText("Table #7");
        tvSubtotalAmount.setText("$" + df.format(subTotal));
        tvTaxAmount.setText("$" + df.format(tax));
        tvTotalAmount.setText("$" + df.format(grandTotal));




        AdapterOrderItems adapterOrderItems = new AdapterOrderItems(getActivity(),itemImages,itemNames,itemCategories,itemPrices);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvPlaceOrder.getContext(),
                layoutManager.getOrientation());
        rvPlaceOrder.addItemDecoration(dividerItemDecoration);
        rvPlaceOrder.setLayoutManager(layoutManager);
        rvPlaceOrder.setNestedScrollingEnabled(false);
        rvPlaceOrder.setAdapter(adapterOrderItems);



        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Clicked submit");

                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                boolean flag = dbHelper.addOrder(userID,currentDateTimeString);
                if (flag == TRUE){
                    Log.d(TAG,"INSERTED CORRECTLY");
                    Snackbar.make(v,"Submitted Order"
                            ,Snackbar.LENGTH_SHORT).show();
                    dismiss();
                }else{
                    Log.d(TAG,"INSERTION FAILED");
                    Snackbar.make(v,"Order submission failed"
                            ,Snackbar.LENGTH_SHORT).show();
                }



                Integer orderID = dbHelper.getOrderID(userID,currentDateTimeString);

                dbHelper.checkoutItems(userID,orderID);

            }
        });

        return rootView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
    }

    public int getImageId(String itemName){
        HashMap<String,Integer> item_ImageMap = new HashMap<>();

        item_ImageMap.put("Chicken Salad",R.drawable.chicken_salad);
        item_ImageMap.put("Chicken Wings",R.drawable.chicken_wings);
        item_ImageMap.put("Beef Noodle Soup",R.drawable.beef_noodle_soup);
        item_ImageMap.put("Cucumber Salad",R.drawable.cucumber_salad);
        item_ImageMap.put("Hamburger",R.drawable.hamburger);
        item_ImageMap.put("Scallops",R.drawable.scallops);
        item_ImageMap.put("Rib-eye Steak",R.drawable.ribeye_steak);
        item_ImageMap.put("Thai Noodle",R.drawable.thai_noodle);
        item_ImageMap.put("Sprout Sandwich",R.drawable.brussel_sprout_sandwich);
        item_ImageMap.put("Cuban Sandwich",R.drawable.cuban_sandwich);
        item_ImageMap.put("Pork Chop",R.drawable.pork_chop);
        item_ImageMap.put("Shrimp",R.drawable.shrimp);

        return item_ImageMap.get(itemName);
    }


}
