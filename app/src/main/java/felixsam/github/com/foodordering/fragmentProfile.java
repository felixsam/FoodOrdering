package felixsam.github.com.foodordering;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


        return rootView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
    }


}
