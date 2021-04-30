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

import felixsam.github.com.foodordering.adapters.AdapterOrderItems;

public class fragmentMenu extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_menu,container,false);
        RecyclerView rvMenu = rootView.findViewById(R.id.rv_menuList);



        ArrayList<Integer> cardImages = new ArrayList<Integer>(){
            {
                add(R.drawable.drink_menu);
                add(R.drawable.cake_menu);
                add(R.drawable.cocktail_menu);
                add(R.drawable.checkout_menu);
                add(R.drawable.order_menu);
                add(R.drawable.customer_list);
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
                add("Customer List");
                add("Order List");
                add("Delivery Support");
                add("Settings");
                add("Terms of use");
                add("Sign out");
            }
        };


        AdapterOrderItems adapterOrderItems = new AdapterOrderItems(getActivity(),cardImages,cardNames,cardDescriptions);

        //GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2,GridLayoutManager.VERTICAL,false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvMenu.getContext(),
                layoutManager.getOrientation());
        rvMenu.addItemDecoration(dividerItemDecoration);
        rvMenu.setLayoutManager(layoutManager);
        rvMenu.setNestedScrollingEnabled(false);
        rvMenu.setHasFixedSize(true);
        rvMenu.setAdapter(adapterOrderItems);


        return rootView;
    }
}
