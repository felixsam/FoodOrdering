package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.fragmentItemDetails;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolder> {

    private static final String TAG = AdapterHome.class.getSimpleName();

    private ArrayList<Integer> mItemImages;
    private ArrayList<String> mItemNames;
    private ArrayList<String> mItemDescriptions;
    private ArrayList<String> mItemPrices;

    private Context mContext;


    public AdapterHome(Context context, ArrayList<Integer> itemImages, ArrayList<String> itemNames, ArrayList<String> itemDescriptions, ArrayList<String> itemPrices){

        mItemImages = itemImages;
        mItemNames = itemNames;
        mItemDescriptions = itemDescriptions;
        mItemPrices = itemPrices;
        mContext = context;
    }


    @Override
    public AdapterHome.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_item_card, parent, false);
        AdapterHome.ViewHolder holder = new AdapterHome.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterHome.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");



        Glide.with(mContext).load(mItemImages.get(position)).into(holder.image);
        holder.itemName.setText(mItemNames.get(position));
        holder.description.setText(mItemDescriptions.get(position));
        holder.itemPrice.setText(mItemPrices.get(position));

        holder.cv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.out.println("Hello Inside");
                System.out.println(mItemImages.get(position));
                System.out.println(mItemNames.get(position));
                System.out.println(mItemDescriptions.get(position));
                System.out.println(mItemPrices.get(position));


                Bundle arguments = new Bundle();
                arguments.putInt("ITEM_IMAGE",mItemImages.get(position));
                arguments.putString("ITEM_NAME",mItemNames.get(position));
                arguments.putString("ITEM_DESCRIPTION",mItemDescriptions.get(position));
                arguments.putString("ITEM_PRICE",mItemPrices.get(position));

                //initialize fragment Manager
                FragmentManager fragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentItemDetails fragmentFavourite = new fragmentItemDetails();
                fragmentFavourite.setArguments(arguments);
                transaction.add(android.R.id.content,fragmentFavourite).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView itemName;
        TextView description;
        TextView itemPrice;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_cardHome);
            itemName = itemView.findViewById(R.id.tv_cardHome_itemName);
            description = itemView.findViewById(R.id.tv_cardHome_itemDesc);
            itemPrice = itemView.findViewById(R.id.tv_cardHome_itemPrice);
            cv = itemView.findViewById(R.id.cv_adapter_cardHome);
        }
    }


}
