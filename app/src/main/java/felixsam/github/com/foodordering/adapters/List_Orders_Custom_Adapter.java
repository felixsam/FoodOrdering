package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Models.Order;
import felixsam.github.com.foodordering.R;

public class List_Orders_Custom_Adapter extends BaseAdapter {

    private Context context;
    private DatabaseHelper dbHelper;
    private ArrayList<Order> OrderList;
    private int mViewResourceId;
    private LayoutInflater mInflater;


    public List_Orders_Custom_Adapter(Context context,int textViewResourceId, ArrayList<Order> OrderList){
        this.context = context;
        this.OrderList = OrderList;
        this.dbHelper = new DatabaseHelper(context.getApplicationContext());
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;


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
        return OrderList.size();
    }

    @Override
    public Object getItem(int position) {
        return OrderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    private class ViewHolder{
        private TextView tv_user_name, tv_drinks, tv_total_price;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        convertView = mInflater.inflate(mViewResourceId, null);
        ;
        Order order = OrderList.get(position);

        if (order != null) {
            TextView order_id = (TextView) convertView.findViewById(R.id.order_id);
            //TextView customer_name = (TextView) convertView.findViewById(R.id.order_user_name);
            TextView item_name = (TextView) convertView.findViewById(R.id.order_item_name);
            TextView item_price = (TextView) convertView.findViewById(R.id.order_item_price);
            //TextView order_date = (TextView) convertView.findViewById(R.id.order_date);

            if (order_id != null){
                order_id.setText("Order ID: " + order.getOrderID().toString());
            }
            if (item_name != null){
                item_name.setText("Item Name: "+ (order.get_item_name()));
            }
            if (item_price != null){
                item_price.setText("Combined Price: $" + (order.getPrice().toString()));
            }

        }

        return convertView;
    }
}
