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

        tvTableNumber.setText("Table #8");
        tvSubtotalAmount.setText("$888");
        tvTaxAmount.setText("$888");
        tvTotalAmount.setText("$888");



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
