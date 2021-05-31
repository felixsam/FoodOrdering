package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import felixsam.github.com.foodordering.DialogFragment_Edit_ItemEntry;
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

        holder.getCardView().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Implement onClick
                System.out.println(checkout_item.getItemName());

                Bundle bundle = new Bundle();
                String item_name = checkout_item.getItemName();
                double item_price = checkout_item.getPrice();
                Integer colID = checkout_item.getUserId();
                Integer quantity = checkout_item.getTotalQuantity();

                bundle.putString("ITEM_NAME",item_name);
                bundle.putDouble("ITEM_PRICE",item_price);
                bundle.putInt("COL_ID",colID);
                bundle.putInt("QUANTITY", quantity);

                FragmentManager fragmentManger = ((AppCompatActivity)context).getSupportFragmentManager();
                DialogFragment_Edit_ItemEntry newFragment = new DialogFragment_Edit_ItemEntry();
                newFragment.setArguments(bundle);
                FragmentTransaction transaction = fragmentManger.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.add(android.R.id.content,newFragment).addToBackStack(null).commit();

            }


        });


        holder.getBtn_edit().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Implement onClick
                System.out.println("BTN EDIT");

            }
        });

        holder.getBtn_delete().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Implement onClick
                System.out.println("BTN DELETE");

            }
        });




    }

    @Override
    public int getItemCount(){
        return this.CheckoutList.size();
    }




}
