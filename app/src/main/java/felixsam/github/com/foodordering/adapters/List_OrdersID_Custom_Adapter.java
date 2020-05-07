package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Models.OrderID;
import felixsam.github.com.foodordering.R;

public class List_OrdersID_Custom_Adapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<OrderID> OrderIDList;


    public List_OrdersID_Custom_Adapter(Context context,int textViewResourceId, ArrayList<OrderID> OrderID_List){
        this.context = context;
        this.OrderIDList = OrderID_List;
        DatabaseHelper orderID_dbHelper = new DatabaseHelper(context.getApplicationContext());


    }
    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }



    //TEST
    @Override
    public int getCount() {
        return OrderIDList.size();
    }

    @Override
    public Object getItem(int position) {
        return OrderIDList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    private static class ViewHolder{
        private TextView order_id,customer_name,date;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        final ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = Objects.requireNonNull(inflater).inflate(R.layout.orderid_adapter_single_item,null,true);

            holder.order_id = convertView.findViewById(R.id.tv_orderid_ID);
            holder.customer_name = convertView.findViewById(R.id.tv_orderid_username);
            holder.date = convertView.findViewById(R.id.tv_orderid_date);

            convertView.setTag(holder);
        }else{
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder) convertView.getTag();
        }


        OrderID orderID = OrderIDList.get(position);

        holder.order_id.setText("Order ID: " + orderID.getOrderID().toString());

        holder.customer_name.setText("Customer Name: " + orderID.getUsername());
        holder.date.setText("Date: "+ (orderID.getDate()));

        return convertView;
    }
}
