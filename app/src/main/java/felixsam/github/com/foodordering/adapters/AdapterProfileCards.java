package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.activities.AddCustomerActivity;
import felixsam.github.com.foodordering.activities.DrinkMenuActivity;
import felixsam.github.com.foodordering.activities.List_Orders;
import felixsam.github.com.foodordering.activities.MainActivity;
import felixsam.github.com.foodordering.activities.MapActivity;
import felixsam.github.com.foodordering.activities.OrdersActivity;
import felixsam.github.com.foodordering.fragmentCakeMenu;
import felixsam.github.com.foodordering.fragmentCheckout;
import felixsam.github.com.foodordering.fragmentCocktailMenu;

public class AdapterProfileCards extends RecyclerView.Adapter<AdapterProfileCards.ViewHolder> {

    private static final String TAG = AdapterProfileCards.class.getSimpleName();

    private ArrayList<String> mCardDescriptions = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>();
    private Context mContext;



    public AdapterProfileCards(Context context, ArrayList<String> cardDescriptions, ArrayList<Integer> images ) {
        mCardDescriptions = cardDescriptions;
        mImages = images;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_profile_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");



        Glide.with(mContext).load(mImages.get(position)).into(holder.image);
        holder.description.setText(mCardDescriptions.get(position));

        holder.cv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //implement onClick

                if (position == 0 ){
                    //TODO Customer List

                }else if (position == 1) {
                    //Order List
                    Intent intent = new Intent(v.getContext(), List_Orders.class);
                    mContext.startActivity(intent);
                }else if (position == 2){
                    //TODO Delivery Support

                }else if (position == 3) {
                    //TODO Settings

                }else if (position == 4){
                    //TODO Terms of use

                }else if (position == 5) {
                    //TODO Sign out

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCardDescriptions.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView description;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_profileCard);
            description = itemView.findViewById(R.id.tv_profileCardText);
            cv = itemView.findViewById(R.id.cv_profileMenuItem);
        }
    }

}
