package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Models.Order;

public class AdapterOrders extends RecyclerView.Adapter<CardHolderListOrders> {

    private final Context context;
    private DatabaseHelper dbHelper;
    private final ArrayList<Order> OrderList;
    private final int mViewResourceId;
    private LayoutInflater mInflater;

    public AdapterOrders(Context context, int itemResource, ArrayList<Order> order){
        this.OrderList = order;
        this.context = context;
        this.mViewResourceId = itemResource;
    }


    @Override
    public CardHolderListOrders onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(this.mViewResourceId,parent,false);
        return new CardHolderListOrders(this.context,view);
    }


    @Override
    public void onBindViewHolder(CardHolderListOrders holder, int position){

        Order order_item = this.OrderList.get(position);

        holder.bind_List_Orders(order_item);

    }




    @Override
    public int getItemCount() {
        return this.OrderList.size();
    }


}
