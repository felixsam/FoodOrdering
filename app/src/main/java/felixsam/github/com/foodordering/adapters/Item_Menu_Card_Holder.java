package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Globals;
import felixsam.github.com.foodordering.Models.Item_Model_Display;
import felixsam.github.com.foodordering.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Item_Menu_Card_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ImageView item_image;
    private TextView tv_item_name, tv_item_quantity, item_price, text_quantity;
    private ImageButton btn_plus_quantity,btn_minus_quantity, btn_add_item;
    private String category;

    private Context mcontext;
    private Item_Model_Display item_model;
    private DatabaseHelper dbHelper;



    public Item_Menu_Card_Holder(Context context, View itemView){
        super(itemView);

        //set the context
        this.mcontext = context;
        this.item_image = (ImageView) itemView.findViewById(R.id.iv_item_image);

        this.btn_plus_quantity = (ImageButton) itemView.findViewById(R.id.plus_quantity);
        this.btn_minus_quantity = (ImageButton) itemView.findViewById(R.id.minus_quantity);
        this.btn_add_item = (ImageButton) itemView.findViewById(R.id.btn_add_item);

        this.tv_item_name = (TextView) itemView.findViewById(R.id.item_name);
        this.tv_item_quantity = (TextView) itemView.findViewById(R.id.et_item_quantity);
        this.item_price = (TextView) itemView.findViewById(R.id.item_price);
        this.text_quantity = (TextView) itemView.findViewById(R.id.tv_item_menu_text_quantity);
        this.dbHelper = new DatabaseHelper(context.getApplicationContext());


        itemView.setOnClickListener(this);
        btn_plus_quantity.setOnClickListener(this);
        btn_minus_quantity.setOnClickListener(this);
        btn_add_item.setOnClickListener(this);


    }

    public void bindItemMenu(Item_Model_Display item_model){

        //bind the data to the ViewHolder
        this.item_model = item_model;
        //Picasso.get().load(item_model.getImage_drawable()).into(this.item_image);
        Glide.with(mcontext).load(item_model.getImage_drawable()).into(this.item_image);

        this.tv_item_name.setText(item_model.getItem_Name());
        this.tv_item_quantity.setText(String.valueOf(item_model.getQuantity()));
        this.item_price.setText("$" + item_model.getPrice().toString());
        this.category = item_model.getCategory();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.plus_quantity:
                int add_one = Integer.parseInt(this.tv_item_quantity.getText().toString()) + 1;
                if (add_one < 0){
                this.tv_item_quantity.setText(String.valueOf(0));
                    add_one = 0;
                }else{
                this.tv_item_quantity.setText(String.valueOf(add_one));
                }


                break;

            case R.id.minus_quantity:
                int minus_one = Integer.parseInt(this.tv_item_quantity.getText().toString()) - 1;
                if (minus_one < 0){
                    this.tv_item_quantity.setText(String.valueOf(0));
                    minus_one = 0;
                }else{
                    this.tv_item_quantity.setText(String.valueOf(minus_one));
                }
                break;

            case R.id.btn_add_item:
                Globals g = Globals.getInstance();

                String user_to_add = g.getUser();
                Integer userID_to_add = g.getUser_ID();

                String item_price = this.item_price.getText().toString().replaceAll("[^a-zA-Z0-9]", "");
                String item_quantity = this.tv_item_quantity.getText().toString();

                String item_name = this.tv_item_name.getText().toString();

                Toast.makeText(this.mcontext,"Added " + item_name
                        + "\n Price: " + item_price
                        + "\n Quantity: " + item_quantity
                        + "\n Category: " + category, Toast.LENGTH_LONG).show();

                Log.d(TAG,"Added " + item_name
                        + "\n Price: " + item_price
                        + "\n Quantity: " + item_quantity
                        + "\n Category: " + category);

                dbHelper.addData_items(user_to_add,item_name,item_price,item_quantity,userID_to_add,category);
                break;
        }
    }
}
