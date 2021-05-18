package felixsam.github.com.foodordering;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import felixsam.github.com.foodordering.adapters.AdapterMenu;
import felixsam.github.com.foodordering.adapters.AdapterOrderItems;

public class fragmentMenu extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_menu,container,false);
        RecyclerView rvMenu = rootView.findViewById(R.id.rv_menuList);



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
                add("Brussel Sprout Sandwich");
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


        AdapterMenu adapterMenu = new AdapterMenu(getActivity(),cardImages,cardNames,cardDescriptions);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2,GridLayoutManager.VERTICAL,false);

        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvMenu.getContext(),
                layoutManager.getOrientation());
        //rvMenu.addItemDecoration(dividerItemDecoration);
        rvMenu.setLayoutManager(layoutManager);
        rvMenu.setNestedScrollingEnabled(false);
        rvMenu.setHasFixedSize(true);
        rvMenu.setAdapter(adapterMenu);


        return rootView;
    }
}
