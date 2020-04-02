package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import felixsam.github.com.foodordering.Models.Cocktail;
import felixsam.github.com.foodordering.R;

public class Adapter_Cocktail extends RecyclerView.Adapter<Adapter_Cocktail.ViewHolder> {
    private String cocktail_name;
    private Integer cocktail_id;
    private String cocktail_glass;
    private ArrayList<Cocktail> cocktail_list;
    private Context context;

    public Adapter_Cocktail(ArrayList<Cocktail> cocktail_list, Context context){
        this.cocktail_list = cocktail_list;

        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // this method will be called whenever our ViewHolder is created
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_single_cocktail, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        // this method will bind the data to the ViewHolder from whence it'll be shown to other Views

        final Cocktail cocktail = cocktail_list.get(position);

        if (holder.tv_cocktail_name != null){
            holder.tv_cocktail_name.setText((cocktail.getCocktail_name()));
        }
        if (holder.tv_cocktail_id != null){
            holder.tv_cocktail_id.setText((cocktail.getCocktail_ID().toString()));
        }
        if (holder.tv_cocktail_glass != null){
            holder.tv_cocktail_glass.setText((cocktail.getGlass_name()));
        }

        Glide.with(context).load(cocktail.getImage_url()).into(holder.iv_cocktail_thumb);

    }

    @Override
    public int getItemCount() {

        return cocktail_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        // define the View objects

        TextView tv_cocktail_name;
        TextView tv_cocktail_id;
        TextView tv_cocktail_glass;
        ImageView iv_cocktail_thumb;

        public ViewHolder(View itemView) {
            super(itemView);

            // initialize the View objects
            this.tv_cocktail_name = itemView.findViewById(R.id.tv_cocktail_name);
            this.tv_cocktail_id = itemView.findViewById(R.id.tv_cocktail_id);
            this.tv_cocktail_glass = itemView.findViewById(R.id.tv_cocktail_glass);
            this.iv_cocktail_thumb = itemView.findViewById(R.id.iv_cocktail_thumb);

        }

    }

}


