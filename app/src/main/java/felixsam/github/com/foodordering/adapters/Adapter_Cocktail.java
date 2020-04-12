package felixsam.github.com.foodordering.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Objects;

import felixsam.github.com.foodordering.Models.Cocktail;
import felixsam.github.com.foodordering.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {

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




        holder.cv_cocktail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, cocktail.getCocktail_name() + "\n Instructions: \n"
                        + cocktail.getInstructions()
                        + "\n Ingredients: " + cocktail.getIngredients_single_string(), Toast.LENGTH_LONG).show();


                LayoutInflater customInflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
                Dialog cocktail_dialog = new Dialog(context);

                View customLayout = Objects.requireNonNull(customInflater.inflate(R.layout.custom_dialog_cocktail, holder.cocktail_dialog_root));
                cocktail_dialog.setContentView(customLayout);
                ViewGroup.LayoutParams layoutParams2= customLayout.getLayoutParams();
                layoutParams2.height=900;
                layoutParams2.width=900;

                final TextView tv_cocktail_name = customLayout.findViewById(R.id.tv_cocktail_name);
                tv_cocktail_name.setText(cocktail.getCocktail_name());



                cocktail_dialog.show();


            }
        });

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
        CardView cv_cocktail;
        ViewGroup cocktail_dialog_root;

        public ViewHolder(View itemView) {
            super(itemView);

            // initialize the View objects
            Dialog cocktail_dialog = new Dialog(context);
            this.cv_cocktail = itemView.findViewById(R.id.cv_cocktail_item);
            this.tv_cocktail_name = itemView.findViewById(R.id.tv_cocktail_name);
            this.tv_cocktail_id = itemView.findViewById(R.id.tv_cocktail_id);
            this.tv_cocktail_glass = itemView.findViewById(R.id.tv_cocktail_glass);
            this.iv_cocktail_thumb = itemView.findViewById(R.id.iv_cocktail_thumb);
            this.cocktail_dialog_root = itemView.findViewById(R.id.dialog_cocktail_root);

        }

    }

}


