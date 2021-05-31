package felixsam.github.com.foodordering.adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import felixsam.github.com.foodordering.DialogFragment_Edit_ItemEntry;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.activities.AddCustomerActivity;
import felixsam.github.com.foodordering.activities.CakeMenuActivity;
import felixsam.github.com.foodordering.activities.CheckoutActivity;
import felixsam.github.com.foodordering.activities.CocktailMenuActivity;
import felixsam.github.com.foodordering.activities.DrinkMenuActivity;
import felixsam.github.com.foodordering.activities.List_Orders;
import felixsam.github.com.foodordering.activities.MainActivity;
import felixsam.github.com.foodordering.activities.MapActivity;
import felixsam.github.com.foodordering.fragmentCakeMenu;
import felixsam.github.com.foodordering.fragmentCheckout;
import felixsam.github.com.foodordering.fragmentCocktailMenu;

public class AdapterDashboardCards extends RecyclerView.Adapter<AdapterDashboardCards.MyViewHolder>{
    private Context mContext;
    private Integer[] mImage;
    private String[] mTitle;
    private String[] msubTitle;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subtitle;
        ImageView imgView;
        MaterialCardView cardview;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.cardview = itemView.findViewById(R.id.cv_dashboard_item);
            this.title = itemView.findViewById(R.id.title);
            this.subtitle = itemView.findViewById(R.id.subtitle);
            this.imgView = itemView.findViewById(R.id.imgcar);
        }
    }

    public AdapterDashboardCards(Context mContext, Integer[] image, String[] title, String[] subTitle) {
        this.mContext = mContext;
        this.mImage = image;
        this.mTitle = title;
        this.msubTitle = subTitle;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dashboard_cards, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.cardview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //implement onClick

                if (position == 0 ){
                    Intent intent = new Intent(v.getContext(), DrinkMenuActivity.class);
                    mContext.startActivity(intent);
                }else if (position == 1) {


                    FragmentManager fragmentManger = ((AppCompatActivity) mContext).getSupportFragmentManager();
                    fragmentCakeMenu newFragment = new fragmentCakeMenu();
                    FragmentTransaction transaction = fragmentManger.beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.add(android.R.id.content,newFragment).addToBackStack(null).commit();



//                    Intent intent = new Intent(v.getContext(), CakeMenuActivity.class);
//                    mContext.startActivity(intent);
                }else if (position == 2){

                    FragmentManager fragmentManger = ((AppCompatActivity) mContext).getSupportFragmentManager();
                    fragmentCocktailMenu newFragment = new fragmentCocktailMenu();
                    FragmentTransaction transaction = fragmentManger.beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.add(android.R.id.content,newFragment).addToBackStack(null).commit();

//                    Intent intent = new Intent(v.getContext(), CocktailMenuActivity.class);
//                    mContext.startActivity(intent);


                }else if (position == 3) {

                    FragmentManager fragmentManger = ((AppCompatActivity) mContext).getSupportFragmentManager();
                    fragmentCheckout newFragment = new fragmentCheckout();
                    FragmentTransaction transaction = fragmentManger.beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.add(android.R.id.content,newFragment).addToBackStack(null).commit();

//                    Intent intent = new Intent(v.getContext(), CheckoutActivity.class);
//                    mContext.startActivity(intent);
                }else if (position == 4){
                    Intent intent = new Intent(v.getContext(), List_Orders.class);
                    mContext.startActivity(intent);
                }else if (position == 5) {
                    Intent intent = new Intent(v.getContext(), AddCustomerActivity.class);
                    intent.putExtra("PARENT_ACTIVITY_CLASS", MainActivity.class);
                    mContext.startActivity(intent);
                }else if (position == 6){
                    Intent intent = new Intent(v.getContext(), MapActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });


        holder.title.setText(mTitle[position]);
        holder.subtitle.setText(msubTitle[position]);
        Glide.with(mContext).load(mImage[position]).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return mTitle.length;
    }

}
