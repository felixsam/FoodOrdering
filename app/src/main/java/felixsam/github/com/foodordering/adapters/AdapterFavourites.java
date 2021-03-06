package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import felixsam.github.com.foodordering.R;

public class AdapterFavourites extends RecyclerView.Adapter<AdapterFavourites.ViewHolder> {

    private static final String TAG = AdapterFavourites.class.getSimpleName();

    private ArrayList<Integer> mItemImages;
    private ArrayList<String> mItemNames;
    private ArrayList<String> mItemDescriptions;
    private Context mContext;


    public AdapterFavourites(Context context, ArrayList<Integer> itemImages, ArrayList<String> itemNames, ArrayList<String> itemDescriptions){

        mItemImages = itemImages;
        mItemNames = itemNames;
        mItemDescriptions = itemDescriptions;
        mContext = context;
    }


    @Override
    public AdapterFavourites.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_favourite_item_card, parent, false);
        AdapterFavourites.ViewHolder holder = new AdapterFavourites.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterFavourites.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");



        Glide.with(mContext).load(mItemImages.get(position)).into(holder.image);
        holder.itemName.setText(mItemNames.get(position));
        holder.description.setText(mItemDescriptions.get(position));

    }

    @Override
    public int getItemCount() {
        return mItemNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView itemName;
        TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_cardFavourite);
            itemName = itemView.findViewById(R.id.tv_cardFavourite_itemName);
            description = itemView.findViewById(R.id.tv_cardFavourite_itemDesc);
        }
    }

}
