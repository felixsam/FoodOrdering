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

public class List_Orders_Card_Holder extends RecyclerView.ViewHolder{

    private TextView text_item_name, text_total_item_price, text_total_item_quantity;
    private ImageView item_image;
    private Context mcontext;
    private Order order;
    private Map<String,Integer> item_ImageMap;

    public List_Orders_Card_Holder(Context context, View itemView){
        super(itemView);

        this.item_ImageMap = new HashMap<>();

        item_ImageMap.put("Strawberry",R.drawable.cake_strawberry);
        item_ImageMap.put("Vanilla",R.drawable.cake_vanilla_sponge);
        item_ImageMap.put("Chocolate",R.drawable.cake_chocolate);
        item_ImageMap.put("Lemon Blueberry",R.drawable.cake_lemon_blueberry);
        item_ImageMap.put("Mango",R.drawable.cake_mango);
        item_ImageMap.put("Oreo",R.drawable.cake_oreo);
        item_ImageMap.put("Cheesecake",R.drawable.cake_cheesecake);
        item_ImageMap.put("Milk",R.drawable.drink_milk);
        item_ImageMap.put("Milk Tea",R.drawable.drink_milk_tea);
        item_ImageMap.put("Orange Juice",R.drawable.drink_orange);
        item_ImageMap.put("Coke",R.drawable.drink_coke);
        item_ImageMap.put("Green Tea",R.drawable.drink_green_tea);
        item_ImageMap.put("Frappucino",R.drawable.drink_frappuccino);
        item_ImageMap.put("Mocha",R.drawable.drink_mocha);
        item_ImageMap.put("Pumpkin Spice Latte",R.drawable.drink_pumpkin_spice);



        this.mcontext = context;
        this.item_image = itemView.findViewById(R.id.adapter_order_single_item_image);
        this.text_item_name = itemView.findViewById(R.id.adapter_order_single_text_item_name);
        this.text_total_item_price = itemView.findViewById(R.id.adapter_order_single_text_total_price);
        this.text_total_item_quantity = itemView.findViewById(R.id.adapter_order_single_text_total_quantity);
    }

    public void bind_List_Orders(Order order){
        this.order = order;

        Glide.with(mcontext).load(item_ImageMap.get(order.get_item_name())).into(this.item_image);
        this.text_item_name.setText(order.get_item_name());
        this.text_total_item_price.setText("Total Price: $" + order.get_totalPrice().toString());
        this.text_total_item_quantity.setText("Total Quantity: " + order.getQuantity().toString());
    }
}
