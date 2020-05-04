package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import felixsam.github.com.foodordering.Models.Checkout;
import felixsam.github.com.foodordering.R;

public class Checkout_Card_Holder extends RecyclerView.ViewHolder{
    private Context mcontext;
    private TextView tv_item_name, tv_item_quantity, tv_item_price,tv_total_item_price;
    private Checkout checkout_model;

    public Checkout_Card_Holder(Context context, View itemView){
        super(itemView);

        this.mcontext = context;
        this.tv_item_name = itemView.findViewById(R.id.tv_checkout_item_name);
        this.tv_item_price = itemView.findViewById(R.id.tv_checkout_item_price);
        this.tv_item_quantity = itemView.findViewById(R.id.tv_checkout_item_total_quantity);
        this.tv_total_item_price = itemView.findViewById(R.id.tv_checkout_item_total_price);
    }

    public void bindCheckoutItem(Checkout checkout){
        this.checkout_model = checkout;

        this.tv_item_name.setText(checkout_model.getItem_name());
        this.tv_item_price.setText("Price: $"+ checkout_model.getPrice().toString());
        this.tv_item_quantity.setText("Quantity: " +checkout_model.getTotal_quantity().toString());
        this.tv_total_item_price.setText("Total Price: $" + checkout_model.getTotal_Amount().toString());
    }
}
