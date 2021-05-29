package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import felixsam.github.com.foodordering.R;

public class AdapterItemDetails extends RecyclerView.Adapter<AdapterItemDetails.ViewHolder>{

    private static final String TAG = AdapterItemDetails.class.getSimpleName();

    private ArrayList<String> mItemExtras = new ArrayList<>();
    private Context mContext;

    public AdapterItemDetails(Context context, ArrayList<String> itemExtras){

        mItemExtras = itemExtras;
        mContext = context;
    }

    @Override
    public AdapterItemDetails.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_details, parent, false);
        AdapterItemDetails.ViewHolder holder = new AdapterItemDetails.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterItemDetails.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.itemExtra.setText(mItemExtras.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemExtras.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemExtra;

        public ViewHolder(View itemView) {
            super(itemView);
            itemExtra = itemView.findViewById(R.id.tv_itemExtra);
        }
    }
}
