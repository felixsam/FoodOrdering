package felixsam.github.com.foodordering;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import felixsam.github.com.foodordering.adapters.AdapterHome;
import felixsam.github.com.foodordering.adapters.AdapterMenu;

public class fragmentHome extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView rvTrending = rootView.findViewById(R.id.rv_home_trending);
        RecyclerView rvMostRecent = rootView.findViewById(R.id.rv_home_mostRecent);
        RecyclerView rvMostPopular = rootView.findViewById(R.id.rv_home_mostPopular);

        SearchView searchHome = rootView.findViewById(R.id.searchView_Home);

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
                add("Brussel Sprout Sandwich");
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
                add("Brussel Sprout Sandwich");
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


        AdapterHome adapterHome = new AdapterHome(getActivity(), cardImages, cardNames, cardDescriptions);
        AdapterHome adapterHomeMostRecent = new AdapterHome(getActivity(), cardImages2, cardNames2, cardDescriptions2);
        AdapterHome adapterHomeMostPopular = new AdapterHome(getActivity(), cardImages3, cardNames3, cardDescriptions3);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvTrending.getContext(),
                layoutManager.getOrientation());

        LinearLayoutManager layoutManagerMostPopular = new LinearLayoutManager(getActivity());
        layoutManagerMostPopular.setOrientation(LinearLayoutManager.HORIZONTAL);
        DividerItemDecoration dividerItemDecorationMostPopular = new DividerItemDecoration(rvMostPopular.getContext(),
                layoutManagerMostPopular.getOrientation());


        LinearLayoutManager layoutManagerMostRecent = new LinearLayoutManager(getActivity());
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





        return rootView;
    }
}
