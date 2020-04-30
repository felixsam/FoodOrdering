package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import felixsam.github.com.foodordering.Models.Order;

public class List_Orders_Card_Holder extends RecyclerView.ViewHolder{

    private TextView text_item_name, text_item_price, text_total_item_price, text_total_item_quantity;
    private ImageView item_image;
    private Context mcontext;
    private Order order;

    public List_Orders_Card_Holder(Context context, View itemView){
        super(itemView);

        this.mcontext = context;
    }

    public void bind_List_Orders(Order order){

    }
}
