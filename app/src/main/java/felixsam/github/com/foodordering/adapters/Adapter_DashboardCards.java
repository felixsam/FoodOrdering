package felixsam.github.com.foodordering.adapters;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.activities.Add_Customer;
import felixsam.github.com.foodordering.activities.Cake_Menu;
import felixsam.github.com.foodordering.activities.CheckoutActivity;
import felixsam.github.com.foodordering.activities.Cocktail_Menu;
import felixsam.github.com.foodordering.activities.Drink_Menu_Activity;
import felixsam.github.com.foodordering.activities.List_Orders_By_ID;
import felixsam.github.com.foodordering.activities.MainActivity;

public class Adapter_DashboardCards extends RecyclerView.Adapter<Adapter_DashboardCards.MyViewHolder>{
    private Context mContext;
    private Integer[] mImage;
    private String[] mTitle;
    private String[] msubTitle;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subtitle;
        ImageView imgView;
        CardView cardview;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.cardview = (CardView) itemView.findViewById(R.id.cv_dashboard_item);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.subtitle = (TextView) itemView.findViewById(R.id.subtitle);
            this.imgView = (ImageView) itemView.findViewById(R.id.imgcar);
        }
    }

    public Adapter_DashboardCards(Context mContext, Integer[] image, String[] title, String[] subTitle) {
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
                    Intent intent = new Intent(v.getContext(), Drink_Menu_Activity.class);
                    mContext.startActivity(intent);
                }else if (position == 1) {
                    Intent intent = new Intent(v.getContext(), Cake_Menu.class);
                    mContext.startActivity(intent);
                }else if (position == 2) {
                    Intent intent = new Intent(v.getContext(), CheckoutActivity.class);
                    mContext.startActivity(intent);
                }else if (position == 3){
                    Intent intent = new Intent(v.getContext(), List_Orders_By_ID.class);
                    mContext.startActivity(intent);
                }else if (position == 4){
                    Intent intent = new Intent(v.getContext(), Add_Customer.class);
                    intent.putExtra("PARENT_ACTIVITY_CLASS", MainActivity.class);
                    mContext.startActivity(intent);
                }else if (position == 5){
                    Intent intent = new Intent(v.getContext(), Cocktail_Menu.class);
                    mContext.startActivity(intent);
                }

                System.out.println("Clicked");
            }
        });


        holder.title.setText(mTitle[position]);
        holder.subtitle.setText(msubTitle[position]);
        //Picasso.get().load(mImage[position]).into(holder.imgView);
        Glide.with(mContext).load(mImage[position]).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return mTitle.length;
    }

}
