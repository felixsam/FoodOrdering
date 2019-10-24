package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import felixsam.github.com.foodordering.Models.Checkout;
import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.R;

public class Checkout_Custom_Adapter extends BaseAdapter {
    private Context context;
    private DatabaseHelper dbHelper;
    private ArrayList<Checkout> CheckoutList;
    private int mViewResourceId;
    private LayoutInflater mInflater;


    public Checkout_Custom_Adapter(Context context,int textViewResourceId, ArrayList<Checkout> CheckoutList){
        this.context = context;
        this.CheckoutList = CheckoutList;
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
        return CheckoutList.size();
    }

    @Override
    public Object getItem(int position) {
        return CheckoutList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    private class ViewHolder{
        private TextView item_name, item_total_quantity, total_price;
        private Button edit_checkout_item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        convertView = mInflater.inflate(mViewResourceId, null);

        Checkout checkout = CheckoutList.get(position);

        if (checkout != null) {
            TextView item_name = (TextView) convertView.findViewById(R.id.tv_checkout_item_name);
            TextView item_total_quantity = (TextView) convertView.findViewById(R.id.tv_checkout_item_total_quantity);
            TextView total_price = (TextView) convertView.findViewById(R.id.tv_checkout_item_total_price);
            TextView price = (TextView) convertView.findViewById(R.id.tv_checkout_item_price);


            if (item_name != null){
                item_name.setText("Item: " + checkout.getItem_name());
            }
            if (item_total_quantity != null){
                item_total_quantity.setText("Total Quantity: " + checkout.getTotal_quantity().toString());
            }
            if (price != null){
                price.setText("Price: $" + (checkout.getPrice().toString()));
            }
            if (total_price != null){
                total_price.setText("Total Price: $" + (checkout.getTotal_Amount().toString()));
            }

        }

        return convertView;
    }
}
