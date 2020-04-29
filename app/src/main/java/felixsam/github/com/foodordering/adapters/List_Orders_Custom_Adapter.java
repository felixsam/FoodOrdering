package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Models.Order;
import felixsam.github.com.foodordering.R;

public class List_Orders_Custom_Adapter extends RecyclerView.Adapter<List_Orders_Custom_Adapter.MyViewHolder> {

    private Context context;
    private DatabaseHelper dbHelper;
    private ArrayList<Order> OrderList;
    private int mViewResourceId;
    private LayoutInflater mInflater;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView total_quantity;
        TextView item_name;
        TextView total_item_price;
        ImageView imgView;

        public MyViewHolder(View itemView){
            super(itemView);

            this.total_quantity = itemView.findViewById(R.id.adapter_order_single_text_total_quantity);
            this.item_name = itemView.findViewById(R.id.adapter_order_single_text_item_name);
            this.total_item_price = itemView.findViewById(R.id.adapter_order_single_text_total_price);
            this.imgView = itemView.findViewById(R.id.adapter_order_single_item_image);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType){
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_order_single_item_card,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position){
        holder.item_name.setText(OrderList.get(position).get_item_name());
        holder.total_item_price.setText(OrderList.get(position).get_totalPrice().toString());
        holder.total_quantity.setText(OrderList.get(position).getQuantity().toString());
    }




    @Override
    public int getItemCount() {
        return OrderList.size();
    }


}
