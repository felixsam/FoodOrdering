package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import felixsam.github.com.foodordering.Models.OrderID;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.activities.OrdersActivity;

public class List_Orders_Custom_Adapter extends RecyclerView.Adapter<List_Orders_Custom_Adapter.ViewHolder> {

    private String TAG = List_Orders_Custom_Adapter.class.getSimpleName();

    private List<OrderID> mOrders;
    private Context mContext;

    public List_Orders_Custom_Adapter(List<OrderID> orders,Context context){
        mOrders = orders;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_orderId;
        TextView tv_userName;
        TextView tv_orderDate;
        Button btn_view;

        public ViewHolder(View itemView){
            super(itemView);


            //initialize the view objects
            this.tv_orderId = itemView.findViewById(R.id.tv_orderList_orderId);
            this.tv_userName = itemView.findViewById(R.id.tv_orderList_userName);
            this.tv_orderDate = itemView.findViewById(R.id.tv_orderList_date);
            this.btn_view = itemView.findViewById(R.id.btn_orderList_view);


        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //Inflate the custom layout
        View v = inflater.inflate(R.layout.adapter_order_list_single_order,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        OrderID order = mOrders.get(position);

        if (holder.tv_orderId != null){
            holder.tv_orderId.setText(String.valueOf(order.getOrderID()));
        }

        if (holder.tv_userName != null){
            holder.tv_userName.setText(order.getUsername());
        }

        if (holder.tv_orderDate != null){
            holder.tv_orderDate.setText(order.getDate());
        }

        holder.btn_view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(TAG,"View Order Button Clicked ");

                int orderId = order.getOrderID();
                String date = order.getDate();
                String userName = order.getUsername();

                Bundle extras = new Bundle();

                Intent intentOrdersActivity = new Intent(mContext, OrdersActivity.class);

                if (orderId != 0 && date != null && userName != null){
                    extras.putInt("ORDER_ID",orderId);
                    extras.putString("DATE",order.getDate());
                    extras.putString("NAME",order.getUsername());
                    intentOrdersActivity.putExtras(extras);
                    mContext.startActivity(intentOrdersActivity);
                }else{
                    Snackbar.make(v,"Cannot go to Orders Activity: problem with orderId, date or userName"
                            ,Snackbar.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount(){
        return mOrders.size();
    }

}
