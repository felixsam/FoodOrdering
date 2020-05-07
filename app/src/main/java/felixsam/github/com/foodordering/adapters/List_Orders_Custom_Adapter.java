package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Models.Order;

public class List_Orders_Custom_Adapter extends RecyclerView.Adapter<List_Orders_Card_Holder> {

    private final Context context;
    private DatabaseHelper dbHelper;
    private final ArrayList<Order> OrderList;
    private final int mViewResourceId;
    private LayoutInflater mInflater;

    public List_Orders_Custom_Adapter(Context context, int itemResource, ArrayList<Order> order){
        this.OrderList = order;
        this.context = context;
        this.mViewResourceId = itemResource;
    }


    @Override
    public List_Orders_Card_Holder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(this.mViewResourceId,parent,false);
        return new List_Orders_Card_Holder(this.context,view);
    }


    @Override
    public void onBindViewHolder(List_Orders_Card_Holder holder,int position){

        Order order_item = this.OrderList.get(position);

        holder.bind_List_Orders(order_item);

    }




    @Override
    public int getItemCount() {
        return this.OrderList.size();
    }


}
