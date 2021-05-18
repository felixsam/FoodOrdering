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
                add(R.drawable.cuban_sandwich);
                add(R.drawable.chicken_salad);
                add(R.drawable.shrimp);
                add(R.drawable.beef_noodle_soup);
                add(R.drawable.hamburger);
                add(R.drawable.ribeye_steak);
            }
        };

        ArrayList<String> cardNames = new ArrayList<String>(){
            {
                add("Cuban Sandwich");
                add("Chicken Salad");
                add("Shrimp");
                add("Beef Noodle Soup");
                add("Hamburger");
                add("Rib-eye Steak");
            }
        };

        ArrayList<String> cardDescriptions = new ArrayList<String>(){
            {
                add("Appetizer");
                add("Salad");
                add("Hors d'oeuvre");
                add("Main Course");
                add("Main Course");
                add("Main Course");
            }
        };


        AdapterFavourites adapterFavourites = new AdapterFavourites(getActivity(),cardImages,cardNames,cardDescriptions);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvFavouritesList.getContext(),
                layoutManager.getOrientation());
        //rvFavouritesList.addItemDecoration(dividerItemDecoration);
        rvFavouritesList.setLayoutManager(layoutManager);
        rvFavouritesList.setNestedScrollingEnabled(false);
        rvFavouritesList.setAdapter(adapterFavourites);


        return rootView;
    }


}
