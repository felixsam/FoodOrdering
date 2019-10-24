package felixsam.github.com.foodordering.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Globals;
import felixsam.github.com.foodordering.Models.Item_Model_Display;
import felixsam.github.com.foodordering.R;

public class CustomAdapter_Item_Menu extends BaseAdapter {
    private Context context;
    //TEST
    private ArrayList<Item_Model_Display> item_model_displayArrayList;
    private DatabaseHelper dbHelper;
    final AlertDialog.Builder d;
    LayoutInflater inflater;

    public CustomAdapter_Item_Menu(Context context, ArrayList<Item_Model_Display> item_model_displayArrayList) {

        this.context = context;

        //TEST
        this.item_model_displayArrayList = item_model_displayArrayList;

        this.dbHelper = new DatabaseHelper(context.getApplicationContext());
        this.d = new AlertDialog.Builder(context.getApplicationContext());


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
        return item_model_displayArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return item_model_displayArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        Globals g = Globals.getInstance();

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_menu_item_cards, null, true);



            holder.tv_item_name = (TextView) convertView.findViewById(R.id.item_name);
            holder.tv_item_quantity = (TextView) convertView.findViewById(R.id.et_item_quantity);
            holder.item_price = (TextView) convertView.findViewById(R.id.item_price);
            holder.btn_plus_quantity = (ImageButton) convertView.findViewById(R.id.plus_quantity);
            holder.btn_minus_quantity = (ImageButton) convertView.findViewById(R.id.minus_quantity);
            holder.item_image = (ImageView) convertView.findViewById(R.id.iv_item_image);

            //Quantity Textview
            holder.text_quantity = (TextView) convertView.findViewById(R.id.tv_item_menu_text_quantity);

            holder.btn_add_item = (ImageButton) convertView.findViewById(R.id.btn_add_item);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }


        holder.tv_item_name.setText(item_model_displayArrayList.get(position).getItem_Name());
        holder.tv_item_quantity.setText(String.valueOf(item_model_displayArrayList.get(position).getQuantity()));
        holder.item_price.setText("$" + item_model_displayArrayList.get(position).getPrice().toString());

        //ImageView
        holder.item_image.setImageResource(item_model_displayArrayList.get(position).getImage_drawable());

        holder.btn_plus_quantity.setTag(R.integer.btn_plus_view, convertView);
        holder.btn_plus_quantity.setTag(R.integer.btn_plus_pos, position);
        holder.btn_plus_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View tempview = (View) holder.btn_plus_quantity.getTag(R.integer.btn_plus_view);
                TextView tv = (TextView) tempview.findViewById(R.id.et_item_quantity);
                Integer pos = (Integer) holder.btn_plus_quantity.getTag(R.integer.btn_plus_pos);

                int number = Integer.parseInt(tv.getText().toString()) + 1;
                tv.setText(String.valueOf(number));

                item_model_displayArrayList.get(pos).setQuantity(number);

            }
        });

        holder.btn_minus_quantity.setTag(R.integer.btn_minus_view, convertView);
        holder.btn_minus_quantity.setTag(R.integer.btn_minus_pos, position);
        holder.btn_minus_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View tempview = (View) holder.btn_minus_quantity.getTag(R.integer.btn_minus_view);
                TextView tv = (TextView) tempview.findViewById(R.id.et_item_quantity);
                Integer pos = (Integer) holder.btn_minus_quantity.getTag(R.integer.btn_minus_pos);

                int number = Integer.parseInt(tv.getText().toString()) - 1;
                if (number < 0){
                    tv.setText(String.valueOf(0));
                    number = 0;
                }else{
                    tv.setText(String.valueOf(number));
                }

                item_model_displayArrayList.get(pos).setQuantity(number);

            }
        });

        holder.btn_add_item.setTag(R.integer.btn_add_drink_view,convertView);
        holder.btn_add_item.setTag(R.integer.btn_add_drink_pos, position);
        holder.btn_add_item.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                View tempview = (View) holder.btn_add_item.getTag(R.integer.btn_add_drink_view);
                Integer pos = (Integer) holder.btn_add_item.getTag(R.integer.btn_add_drink_pos);

                TextView fruit_name_tv = tempview.findViewById(R.id.item_name);
                TextView drink_price_tv = tempview.findViewById(R.id.item_price);
                TextView drink_quantity_tv = tempview.findViewById(R.id.et_item_quantity);
                String category = item_model_displayArrayList.get(pos).getCategory();


                Globals g = Globals.getInstance();

                String user_to_add = g.getUser();
                Integer userID_to_add = g.getUser_ID();

                String drink_price = drink_price_tv.getText().toString().replaceAll("[^a-zA-Z0-9]", "");
                String drink_quantity = drink_quantity_tv.getText().toString();

                String drink_name = fruit_name_tv.getText().toString();

                Toast.makeText(v.getContext(),"Added ItemModel"
                        + "\n ItemModel Name: " + drink_name
                        + "\n ItemModel Price: " + drink_price
                        + "\n ItemModel Quantity: " + drink_quantity
                        + "\n ItemModel Category: " + category, Toast.LENGTH_LONG).show();
                dbHelper.addData_items(user_to_add,drink_name,drink_price,drink_quantity,userID_to_add,category);
            }
        });


        //TESTING
        holder.text_quantity.setTag(R.integer.quantity_drink_view,convertView);
        holder.text_quantity.setTag(R.integer.quantity_drink_pos, position);
        holder.text_quantity.setOnClickListener(new View.OnClickListener(){
            @Override


            public void onClick(View v){
                View tempview = (View) holder.text_quantity.getTag(R.integer.quantity_drink_view);
                TextView fruit_name_tv = tempview.findViewById(R.id.item_name);
                TextView drink_quantity_tv = tempview.findViewById(R.id.et_item_quantity);

                String drink_quantity = drink_quantity_tv.getText().toString();

                String drink_name = fruit_name_tv.getText().toString();

                Toast.makeText(v.getContext(),"TESTING TESTING: " + drink_name + "\n " + drink_quantity , Toast.LENGTH_LONG).show();


            }

        });

        return convertView;
    }


    private class ViewHolder {

        private ImageButton btn_plus_quantity,btn_minus_quantity, btn_add_item;
        private TextView tv_item_name, tv_item_quantity, item_price, text_quantity;
        private ImageView item_image;
    }

}
