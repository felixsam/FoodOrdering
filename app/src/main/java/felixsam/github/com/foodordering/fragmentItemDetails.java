package felixsam.github.com.foodordering;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.ArrayList;

import felixsam.github.com.foodordering.adapters.AdapterItemDetails;
import felixsam.github.com.foodordering.adapters.AdapterProfileCards;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static java.lang.Integer.valueOf;

public class fragmentItemDetails extends DialogFragment {

    double itemPrice;
    DecimalFormat df = new DecimalFormat("#.##");
    String itemName;
    int currentQuantity;
    String category;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_item_details,container,false);
        RecyclerView rvExtrasList = rootView.findViewById(R.id.rv_itemDetailsExtras);
        ImageView itemImage = rootView.findViewById(R.id.iv_itemDetails);
        TextView tv_itemName = rootView.findViewById(R.id.tv_itemDetailsTitle);
        TextView itemDesc = rootView.findViewById(R.id.tv_itemDetailsDescription);
        TextView tv_Quantity = rootView.findViewById(R.id.tv_itemDetailsQuantity);
        itemPrice = Double.parseDouble(getArguments().getString("ITEM_PRICE").replaceAll("[^\\d.]", ""));

        ImageButton btn_minusQuantity = rootView.findViewById(R.id.btn_itemDetailsMinusQuantity);
        ImageButton btn_plusQuantity = rootView.findViewById(R.id.btn_itemDetailsPlusQuantity);
        Button btn_addToOrder = rootView.findViewById(R.id.btn_itemDetails_addToOrder);
        Button btn_submit = rootView.findViewById(R.id.btn_itemDetails_addToOrder);

        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());


        Bundle bundle = getArguments();

        if (bundle != null){
            if (bundle.containsKey("ITEM_IMAGE")){
                itemImage.setImageResource(bundle.getInt("ITEM_IMAGE"));
            }

            if (bundle.containsKey("ITEM_NAME")){
                tv_itemName.setText(bundle.getString("ITEM_NAME"));
            }

            if (bundle.containsKey("ITEM_DESCRIPTION")){
                itemDesc.setText(bundle.getString("ITEM_DESCRIPTION"));
                category = bundle.getString("ITEM_DESCRIPTION");
            }

            if (bundle.containsKey("ITEM_PRICE")){
                btn_addToOrder.setText("ADD TO ORDER" + "    $" + itemPrice);
                itemName = bundle.getString("ITEM_NAME");
            }
        }

        btn_minusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuantity = valueOf(tv_Quantity.getText().toString()) - 1;
                if (currentQuantity > 0){
                    double newPrice = itemPrice * currentQuantity;
                    tv_Quantity.setText(String.valueOf(currentQuantity));
                    btn_addToOrder.setText("ADD TO ORDER" + "    $" + df.format(newPrice));
                }

            }
        });

        btn_plusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuantity = valueOf(tv_Quantity.getText().toString()) + 1;

                double newPrice = itemPrice * currentQuantity;
                tv_Quantity.setText(String.valueOf(currentQuantity));
                btn_addToOrder.setText("ADD TO ORDER" + "    $" + df.format(newPrice));

            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Globals g = Globals.getInstance();
                Integer userId = g.getUser_ID();

                Snackbar.make(v,"Added " + itemName
                                + "\nQuantity: " + currentQuantity
                        ,Snackbar.LENGTH_SHORT).show();

                Log.d(TAG,"Added " + itemName
                        + "\n Price: " + itemPrice
                        + "\n Quantity: " + currentQuantity
                        + "\n Category: " + category);

                dbHelper.addItem(userId,itemName,itemPrice,currentQuantity,category);
            }
        });



        ArrayList<String> extrasList = new ArrayList<String>(){
            {
                add("Sauce");
                add("Salmon");
                add("Salad");
                add("Unagi");
                add("Vegetables");
                add("Noodles");
            }
        };

        AdapterItemDetails adapterItemDetails = new AdapterItemDetails(getActivity(),extrasList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvExtrasList.getContext(),
                layoutManager.getOrientation());
        rvExtrasList.addItemDecoration(dividerItemDecoration);
        rvExtrasList.setLayoutManager(layoutManager);
        rvExtrasList.setNestedScrollingEnabled(false);
        rvExtrasList.setAdapter(adapterItemDetails);


        return rootView;

    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
    }

}
