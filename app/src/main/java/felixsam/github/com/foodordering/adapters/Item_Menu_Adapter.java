package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import felixsam.github.com.foodordering.Models.Item_Model_Display;

public class Item_Menu_Adapter extends RecyclerView.Adapter<Item_Menu_Card_Holder> {

    private final ArrayList<Item_Model_Display> item_menu;
    private Context context;
    private int itemResource;

    public Item_Menu_Adapter(Context context, int itemResource, ArrayList<Item_Model_Display> item_menu){
        // 1. Initialize our adapter
        this.item_menu = item_menu;
        this.context = context;
        this.itemResource = itemResource;
    }

    // 2. Override the onCreateViewHolder method
    @Override
    public Item_Menu_Card_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 3. Inflate the view and return the new ViewHolder
        View view = LayoutInflater.from(parent.getContext())
                .inflate(this.itemResource, parent, false);
        return new Item_Menu_Card_Holder(this.context, view);
    }

    // 4. Override the onBindViewHolder method
    @Override
    public void onBindViewHolder(Item_Menu_Card_Holder holder, int position) {

        // 5. Use position to access the correct menu item
        Item_Model_Display item_menu = this.item_menu.get(position);


        // 6. Bind the item_menu to the holder
        holder.bindItemMenu(item_menu);

    }

    @Override
    public int getItemCount() {

        return this.item_menu.size();
    }

}
