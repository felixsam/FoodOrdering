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

import felixsam.github.com.foodordering.adapters.AdapterHome;
import felixsam.github.com.foodordering.adapters.AdapterMenu;

public class fragmentHome extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView rvTrending = rootView.findViewById(R.id.rv_home_trending);
        RecyclerView rvMostRecent = rootView.findViewById(R.id.rv_home_mostRecent);
        RecyclerView rvMostPopular = rootView.findViewById(R.id.rv_home_mostPopular);

        ArrayList<Integer> cardImages = new ArrayList<Integer>() {
            {
                add(R.drawable.cake_cheesecake);
                add(R.drawable.cake_lemon_blueberry);
                add(R.drawable.cake_chocolate);
                add(R.drawable.cake_vanilla_sponge);
                add(R.drawable.cake_angel);
                add(R.drawable.cake_mango);
                add(R.drawable.cake_oreo);
                add(R.drawable.cake_strawberry);
                add(R.drawable.cocktail_menu);
                add(R.drawable.checkout_menu);
                add(R.drawable.order_menu);
                add(R.drawable.customer_list);
            }
        };

        ArrayList<String> cardNames = new ArrayList<String>() {
            {
                add("Name 1");
                add("Name 2");
                add("Name 3");
                add("Name 4");
                add("Name 5");
                add("Name 6");
                add("Name 1");
                add("Name 2");
                add("Name 3");
                add("Name 4");
                add("Name 5");
                add("Name 6");
            }
        };

        ArrayList<String> cardDescriptions = new ArrayList<String>() {
            {
                add("Customer List");
                add("Order List");
                add("Delivery Support");
                add("Settings");
                add("Terms of use");
                add("Sign out");
                add("Customer List");
                add("Order List");
                add("Delivery Support");
                add("Settings");
                add("Terms of use");
                add("Sign out");
            }
        };


        AdapterHome adapterHome = new AdapterHome(getActivity(), cardImages, cardNames, cardDescriptions);
        AdapterHome adapterHomeMostRecent = new AdapterHome(getActivity(), cardImages, cardNames, cardDescriptions);
        AdapterHome adapterHomeMostPopular = new AdapterHome(getActivity(), cardImages, cardNames, cardDescriptions);


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



        rvTrending.addItemDecoration(dividerItemDecoration);
        rvTrending.setLayoutManager(layoutManager);
        rvTrending.setNestedScrollingEnabled(false);
        rvTrending.setHasFixedSize(true);
        rvTrending.setAdapter(adapterHome);

        rvMostPopular.addItemDecoration(dividerItemDecorationMostPopular);
        rvMostPopular.setLayoutManager(layoutManagerMostPopular);
        rvMostPopular.setNestedScrollingEnabled(false);
        rvMostPopular.setHasFixedSize(true);
        rvMostPopular.setAdapter(adapterHomeMostPopular);

        rvMostRecent.addItemDecoration(dividerItemDecorationMostRecent);
        rvMostRecent.setLayoutManager(layoutManagerMostRecent);
        rvMostRecent.setNestedScrollingEnabled(false);
        rvMostRecent.setHasFixedSize(true);
        rvMostRecent.setAdapter(adapterHomeMostRecent);





        return rootView;
    }
}
