package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

import felixsam.github.com.foodordering.Models.Checkout;
import felixsam.github.com.foodordering.R;

public class CardHolderCheckout extends RecyclerView.ViewHolder{
    private final Context mcontext;
    private final TextView tv_item_name;
    private final TextView tv_item_quantity;
    private final TextView tv_item_price;
    private final TextView tv_total_item_price;
    private Checkout checkout_model;
    private final Map<String,Integer> item_ImageMap;
    private final ImageView item_image;

    public CardHolderCheckout(Context context, View itemView){
        super(itemView);
        this.item_ImageMap = new HashMap<>();

        item_ImageMap.put("Strawberry Cake",R.drawable.cake_strawberry);
        item_ImageMap.put("Vanilla Cake",R.drawable.cake_vanilla_sponge);
        item_ImageMap.put("Chocolate Cake",R.drawable.cake_chocolate);
        item_ImageMap.put("Blueberry Cake",R.drawable.cake_lemon_blueberry);
        item_ImageMap.put("Mango Cake",R.drawable.cake_mango);
        item_ImageMap.put("Oreo Cake",R.drawable.cake_oreo);
        item_ImageMap.put("Cheesecake",R.drawable.cake_cheesecake);
        item_ImageMap.put("Angel Cake",R.drawable.cake_angel);
        item_ImageMap.put("Milk",R.drawable.drink_milk);
        item_ImageMap.put("Milk Tea",R.drawable.drink_milk_tea);
        item_ImageMap.put("Orange Juice",R.drawable.drink_orange);
        item_ImageMap.put("Coke",R.drawable.drink_coke);
        item_ImageMap.put("Green Tea",R.drawable.drink_green_tea);
        item_ImageMap.put("Frappuccino",R.drawable.drink_frappuccino);
        item_ImageMap.put("Mocha",R.drawable.drink_mocha);
        item_ImageMap.put("Pumpkin Spice Latte",R.drawable.drink_pumpkin_spice);


        this.mcontext = context;
        this.item_image = itemView.findViewById(R.id.adapter_checkout_item_image);
        this.tv_item_name = itemView.findViewById(R.id.tv_checkout_item_name);
        this.tv_item_price = itemView.findViewById(R.id.tv_checkout_item_price);
        this.tv_item_quantity = itemView.findViewById(R.id.tv_checkout_item_total_quantity);
        this.tv_total_item_price = itemView.findViewById(R.id.tv_checkout_item_total_price);
    }

    public void bindCheckoutItem(Checkout checkout){
        this.checkout_model = checkout;

        Glide.with(mcontext).load(item_ImageMap.get(checkout.getItemName())).into(this.item_image);

        this.tv_item_name.setText(checkout_model.getItemName());
        this.tv_item_price.setText("Price: $"+ checkout_model.getPrice());
        this.tv_item_quantity.setText("Quantity: " +checkout_model.getTotalQuantity().toString());
        this.tv_total_item_price.setText("Total Price: $" + checkout_model.getTotalAmount());
    }
}
