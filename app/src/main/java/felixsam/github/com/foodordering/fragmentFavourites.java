package felixsam.github.com.foodordering;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import felixsam.github.com.foodordering.adapters.AdapterFavourites;
import felixsam.github.com.foodordering.adapters.AdapterOrderItems;

public class fragmentFavourites extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_favourites,container,false);
        RecyclerView rvFavouritesList = rootView.findViewById(R.id.rv_favouritesList);



        ArrayList<Integer> cardImages = new ArrayList<Integer>(){
            {
                add(R.drawable.drink_menu);
                add(R.drawable.cake_menu);
                add(R.drawable.cocktail_menu);
                add(R.drawable.checkout_menu);
                add(R.drawable.order_menu);
                add(R.drawable.customer_list);
            }
        };

        ArrayList<String> cardNames = new ArrayList<String>(){
            {
                add("Name 1");
                add("Name 2");
                add("Name 3");
                add("Name 4");
                add("Name 5");
                add("Name 6");
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


        AdapterFavourites adapterFavourites = new AdapterFavourites(getActivity(),cardImages,cardNames,cardDescriptions);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvFavouritesList.getContext(),
                layoutManager.getOrientation());
        rvFavouritesList.addItemDecoration(dividerItemDecoration);
        rvFavouritesList.setLayoutManager(layoutManager);
        rvFavouritesList.setNestedScrollingEnabled(false);
        rvFavouritesList.setAdapter(adapterFavourites);


        return rootView;
    }


}
