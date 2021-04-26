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

    }

    @Override
    public int getItemCount() {
        return mCardDescriptions.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_profileCard);
            description = itemView.findViewById(R.id.tv_profileCardText);
        }
    }

}
