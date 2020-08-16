package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import felixsam.github.com.foodordering.Globals;
import felixsam.github.com.foodordering.Models.ItemModel;
import felixsam.github.com.foodordering.R;

public class ItemListAdapter extends ArrayAdapter<ItemModel> {
    Globals g = Globals.getInstance();
    private LayoutInflater mInflater;
    private ArrayList<ItemModel> arrayList_itemList;
    private int mViewResourceId;

    public ItemListAdapter(Context context, int textViewResourceId, ArrayList<ItemModel> arrayList_itemList){
        super(context, textViewResourceId, arrayList_itemList);
        this.arrayList_itemList = arrayList_itemList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);
        ;
        ItemModel item = arrayList_itemList.get(position);

        if (item != null) {
            TextView username = (TextView) convertView.findViewById(R.id.itemlist_tv_UserName);
            TextView item_name = (TextView) convertView.findViewById(R.id.itemlist_tv_itemName);
            TextView item_price = (TextView) convertView.findViewById(R.id.itemList_tv_itemPrice);
            TextView item_quantity = (TextView) convertView.findViewById(R.id.itemList_tv_itemQuantity);
            TextView user_id = (TextView) convertView.findViewById(R.id.itemList_tv_userID);

            //Get current user
            username.setText(item.getUserName());

            if (item_name != null) {
                item_name.setText(item.getName());
            }
            if (item_price != null) {
                item_price.setText("$" + String.valueOf(item.getPrice()));
            }
            if (item_quantity != null) {
                item_quantity.setText((item.getQuantity()).toString());

            }

            user_id.setText(item.getUserID().toString());
        }

        return convertView;
    }
}
