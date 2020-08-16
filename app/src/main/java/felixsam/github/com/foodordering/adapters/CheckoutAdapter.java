package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import felixsam.github.com.foodordering.Models.Checkout;

public class CheckoutAdapter extends RecyclerView.Adapter<CardHolderCheckout> {
    private final Context context;
    private final ArrayList<Checkout> CheckoutList;
    private final int mViewResourceId;


    public CheckoutAdapter(Context context, int textViewResourceId, ArrayList<Checkout> CheckoutList){
        this.context = context;
        this.CheckoutList = CheckoutList;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;

    }

    @Override
    public CardHolderCheckout onCreateViewHolder(ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).
                inflate(mViewResourceId, parent, false);
        return new CardHolderCheckout(this.context,view);
    }

    @Override
    public void onBindViewHolder(CardHolderCheckout holder, int position){

        Checkout checkout_item = this.CheckoutList.get(position);

        holder.bindCheckoutItem(checkout_item);
    }

    @Override
    public int getItemCount(){
        return this.CheckoutList.size();
    }




}
