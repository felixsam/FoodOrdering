package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

import felixsam.github.com.foodordering.Models.Cocktail;
import felixsam.github.com.foodordering.R;

public class Adapter_Cocktail extends RecyclerView.Adapter<Adapter_Cocktail.ViewHolder> {
    private String cocktail_name;
    private Integer cocktail_id;
    private String cocktail_glass;
    private ArrayList<Cocktail> cocktail_list;

    public Adapter_Cocktail(ArrayList<Cocktail> cocktail_list, Context context){
        this.cocktail_list = cocktail_list;

        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_cocktail, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Cocktail cocktail = cocktail_list.get(position);

    }

    @Override
    public int getItemCount() {

        return cocktail_list.size();
    }

}


