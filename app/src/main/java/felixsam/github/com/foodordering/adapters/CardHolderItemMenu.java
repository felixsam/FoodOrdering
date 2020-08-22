package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Globals;
import felixsam.github.com.foodordering.Models.Item_Model_Display;
import felixsam.github.com.foodordering.R;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static java.lang.String.valueOf;

public class CardHolderItemMenu extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ImageView item_image;
    private final TextView tv_item_name;
    private final TextView tv_item_quantity;
    private final TextView item_price;
    private String category;

    private final Context mContext;
    private final DatabaseHelper dbHelper;



    public CardHolderItemMenu(Context context, View itemView){
        super(itemView);

        //set the context
        this.mContext = context;
        this.item_image = itemView.findViewById(R.id.iv_item_image);


        ImageButton btn_plus_quantity = itemView.findViewById(R.id.plus_quantity);
        Drawable drawable_plus_quantity = MaterialDrawableBuilder.with(mContext)
                .setIcon(MaterialDrawableBuilder.IconValue.PLUS)
                .setColor(Color.BLACK)
                .setToActionbarSize()
                .build();
        btn_plus_quantity.setImageDrawable(drawable_plus_quantity);

        ImageButton btn_minus_quantity = itemView.findViewById(R.id.minus_quantity);
        Drawable drawable_minus_quantity = MaterialDrawableBuilder.with(mContext)
                .setIcon(MaterialDrawableBuilder.IconValue.MINUS)
                .setColor(Color.BLACK)
                .setToActionbarSize()
                .build();
        btn_minus_quantity.setImageDrawable(drawable_minus_quantity);

        Drawable drawable_add_item = MaterialDrawableBuilder.with(mContext)
                .setIcon(MaterialDrawableBuilder.IconValue.ARROW_DOWN_BOLD_BOX_OUTLINE)
                .setColor(Color.BLACK)
                .setToActionbarSize()
                .build();
        Button btn_add_item = itemView.findViewById(R.id.btn_add_item);
        //btn_add_item.setImageDrawable(drawable_add_item);
        //btn_add_item.setImageDrawable(null);

        this.tv_item_name = itemView.findViewById(R.id.item_name);
        this.tv_item_quantity = itemView.findViewById(R.id.et_item_quantity);
        this.item_price = itemView.findViewById(R.id.item_price);
        this.dbHelper = new DatabaseHelper(context.getApplicationContext());


        itemView.setOnClickListener(this);
        btn_plus_quantity.setOnClickListener(this);
        btn_minus_quantity.setOnClickListener(this);
        btn_add_item.setOnClickListener(this);


    }

    public void bindItemMenu(Item_Model_Display item_model){

        //bind the data to the ViewHolder
        //Picasso.get().load(item_model.getImage_drawable()).into(this.item_image);
        Glide.with(mContext).load(item_model.getImage_drawable()).into(this.item_image);

        this.tv_item_name.setText(item_model.getItem_Name());
        this.tv_item_quantity.setText(valueOf(item_model.getQuantity()));
        this.item_price.setText("$" + valueOf(item_model.getPrice()));
        this.category = item_model.getCategory();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.plus_quantity:
                int add_one = Integer.parseInt(this.tv_item_quantity.getText().toString()) + 1;
                if (add_one < 0){
                this.tv_item_quantity.setText(valueOf(0));
                    add_one = 0;
                }else{
                this.tv_item_quantity.setText(valueOf(add_one));
                }


                break;

            case R.id.minus_quantity:
                int minus_one = Integer.parseInt(this.tv_item_quantity.getText().toString()) - 1;
                if (minus_one < 0){
                    this.tv_item_quantity.setText(valueOf(0));
                    minus_one = 0;
                }else{
                    this.tv_item_quantity.setText(valueOf(minus_one));
                }
                break;

            case R.id.btn_add_item:
                Globals g = Globals.getInstance();

                Integer userID_to_add = g.getUser_ID();

                String item_price = this.item_price.getText().toString().replaceAll("[^0-9.]", "");
                double item_price_double = Double.valueOf(item_price);
                int item_quantity = Integer.parseInt(this.tv_item_quantity.getText().toString());

                String item_name = this.tv_item_name.getText().toString();

                Snackbar.make(v,"Added " + item_name
                                + "\nQuantity: " + item_quantity
                        ,Snackbar.LENGTH_SHORT).show();

                Log.d(TAG,"Added " + item_name
                        + "\n Price: " + item_price
                        + "\n Quantity: " + item_quantity
                        + "\n Category: " + category);

                dbHelper.addData_items(userID_to_add,item_name,item_price_double,item_quantity,category);
                break;
        }
    }
}
