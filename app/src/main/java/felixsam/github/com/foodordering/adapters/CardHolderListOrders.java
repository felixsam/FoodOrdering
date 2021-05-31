package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

import felixsam.github.com.foodordering.Models.Order;
import felixsam.github.com.foodordering.R;

public class CardHolderListOrders extends RecyclerView.ViewHolder{

    private final TextView text_item_name;
    private final TextView text_total_item_price;
    private final TextView text_total_item_quantity;
    private final ImageView item_image;
    private final Context mcontext;
    private final Map<String,Integer> item_ImageMap;

    public CardHolderListOrders(Context context, View itemView){
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

        item_ImageMap.put("Chicken Salad",R.drawable.chicken_salad);
        item_ImageMap.put("Chicken Wings",R.drawable.chicken_wings);
        item_ImageMap.put("Beef Noodle Soup",R.drawable.beef_noodle_soup);
        item_ImageMap.put("Cucumber Salad",R.drawable.cucumber_salad);
        item_ImageMap.put("Hamburger",R.drawable.hamburger);
        item_ImageMap.put("Scallops",R.drawable.scallops);
        item_ImageMap.put("Rib-eye Steak",R.drawable.ribeye_steak);
        item_ImageMap.put("Thai Noodle",R.drawable.thai_noodle);
        item_ImageMap.put("Sprout Sandwich",R.drawable.brussel_sprout_sandwich);
        item_ImageMap.put("Cuban Sandwich",R.drawable.cuban_sandwich);
        item_ImageMap.put("Pork Chop",R.drawable.pork_chop);
        item_ImageMap.put("Shrimp",R.drawable.shrimp);

        this.mcontext = context;
        this.item_image = itemView.findViewById(R.id.adapter_order_single_item_image);
        this.text_item_name = itemView.findViewById(R.id.adapter_order_single_text_item_name);
        this.text_total_item_price = itemView.findViewById(R.id.adapter_order_single_text_total_price);
        this.text_total_item_quantity = itemView.findViewById(R.id.adapter_order_single_text_total_quantity);
    }

    public void bind_List_Orders(Order order){

        Glide.with(mcontext).load(item_ImageMap.get(order.get_item_name())).into(this.item_image);
        this.text_item_name.setText(order.get_item_name());
        this.text_total_item_price.setText("Total Price: $" + String.valueOf(order.get_totalPrice()));
        this.text_total_item_quantity.setText("Total Quantity: " + order.getQuantity().toString());
    }
}
