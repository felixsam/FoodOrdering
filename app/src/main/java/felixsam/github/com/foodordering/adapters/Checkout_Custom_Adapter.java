package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import felixsam.github.com.foodordering.Models.Checkout;
import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.R;

public class Checkout_Custom_Adapter extends RecyclerView.Adapter<Checkout_Card_Holder> {
    private Context context;
    private ArrayList<Checkout> CheckoutList;
    private int mViewResourceId;
    private LayoutInflater mInflater;


    public Checkout_Custom_Adapter(Context context,int textViewResourceId, ArrayList<Checkout> CheckoutList){
        this.context = context;
        this.CheckoutList = CheckoutList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;

    }

    @Override
    public Checkout_Card_Holder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).
                inflate(mViewResourceId, parent, false);
        return new Checkout_Card_Holder(this.context,view);
    }

    @Override
    public void onBindViewHolder(Checkout_Card_Holder holder, int position){

        Checkout checkout_item = this.CheckoutList.get(position);

        holder.bindCheckoutItem(checkout_item);
    }

    @Override
    public int getItemCount(){
        return this.CheckoutList.size();
    }




}
