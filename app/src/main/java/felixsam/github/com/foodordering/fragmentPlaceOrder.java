package felixsam.github.com.foodordering;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import felixsam.github.com.foodordering.adapters.AdapterOrderItems;

public class fragmentPlaceOrder extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_place_order, container, false);
        RecyclerView rvPlaceOrder = rootView.findViewById(R.id.rv_placeOrder);

        TextView tvTableNumber = rootView.findViewById(R.id.tv_placeOrder_tableNumber);
        TextView tvSubtotalAmount = rootView.findViewById(R.id.tv_placeOrder_subtotalPrice);
        TextView tvTaxAmount = rootView.findViewById(R.id.tv_placeOrder_taxPrice);
        TextView tvTotalAmount = rootView.findViewById(R.id.tv_placeOrder_totalPrice);

        tvTableNumber.setText("Table #7");
        tvSubtotalAmount.setText("$123");
        tvTaxAmount.setText("$123");
        tvTotalAmount.setText("$123");



        ArrayList<Integer> cardImages = new ArrayList<Integer>(){
            {
                add(R.drawable.ribeye_steak);
                add(R.drawable.cuban_sandwich);
                add(R.drawable.pork_chop);
                add(R.drawable.hamburger);
                add(R.drawable.thai_noodle);
                add(R.drawable.chicken_salad);
            }
        };

        ArrayList<String> cardNames = new ArrayList<String>(){
            {
                add("Rib-eye Steak");
                add("Cuban Sandwich");
                add("Pork Chop");
                add("Hamburger");
                add("Thai Noodle");
                add("Chicken Salad");
            }
        };

        ArrayList<String> cardDescriptions = new ArrayList<String>(){
            {
                add("Main Course");
                add("Appetizer");
                add("Hors d'oeuvre");
                add("Main Course");
                add("Soups");
                add("Salads");
            }
        };


        AdapterOrderItems adapterOrderItems = new AdapterOrderItems(getActivity(),cardImages,cardNames,cardDescriptions);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvPlaceOrder.getContext(),
                layoutManager.getOrientation());
        rvPlaceOrder.addItemDecoration(dividerItemDecoration);
        rvPlaceOrder.setLayoutManager(layoutManager);
        rvPlaceOrder.setNestedScrollingEnabled(false);
        rvPlaceOrder.setAdapter(adapterOrderItems);


        return rootView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
    }


}
