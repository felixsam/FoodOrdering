package felixsam.github.com.foodordering.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Objects;

import felixsam.github.com.foodordering.Models.Cocktail;
import felixsam.github.com.foodordering.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class AdapterCocktail extends RecyclerView.Adapter<AdapterCocktail.ViewHolder> {
    private String cocktail_name;
    private Integer cocktail_id;
    private String cocktail_glass;
    private ArrayList<Cocktail> cocktail_list;
    private Context context;
    public AdapterCocktail(ArrayList<Cocktail> cocktail_list, Context context){
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
                /*Toast.makeText(context, cocktail.getCocktail_name() + "\n Instructions: \n"
                        + cocktail.getInstructions()
                        + "\n Ingredients: " + cocktail.getIngredients_single_string(), Toast.LENGTH_LONG).show();
                 */

                LayoutInflater customInflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
                final Dialog cocktail_dialog = new Dialog(context);

                View customLayout = Objects.requireNonNull(customInflater.inflate(R.layout.custom_dialog_cocktail, holder.cocktail_dialog_root));
                cocktail_dialog.setContentView(customLayout);
                ViewGroup.LayoutParams layoutParams2= customLayout.getLayoutParams();
                //layoutParams2.height=900;
                //layoutParams2.width=900;

                final TextView dialog_cocktail_name = customLayout.findViewById(R.id.dialog_tv_cocktail_name);
                final TextView dialog_cocktail_id = customLayout.findViewById(R.id.dialog_tv_cocktail_id);
                final TextView dialog_cocktail_glass = customLayout.findViewById(R.id.dialog_tv_cocktail_glass);
                final TextView dialog_cocktail_alcoholic = customLayout.findViewById(R.id.dialog_tv_cocktail_alcoholic);
                final ImageView dialog_cocktail_thumb = customLayout.findViewById(R.id.dialog_iv_cocktail_thumb);
                final TextView dialog_cocktail_instructions = customLayout.findViewById(R.id.dialog_tv_cocktail_instructions);
                final TextView dialog_cocktail_ingredients = customLayout.findViewById(R.id.dialog_tv_cocktail_ingredients);
                final Button btn_dialog_cocktail_ok = customLayout.findViewById(R.id.custom_dialog_cocktail_btn_ok);


                dialog_cocktail_name.setText("Name: " + cocktail.getCocktail_name());
                dialog_cocktail_id.setText("ID: " + cocktail.getCocktail_ID().toString());
                dialog_cocktail_glass.setText("Glass: " + cocktail.getGlass_name());
                dialog_cocktail_alcoholic.setText(cocktail.getAlcoholic());
                dialog_cocktail_instructions.setText("Instructions: \n" + cocktail.getInstructions());
                dialog_cocktail_ingredients.setText("Ingredients: " + cocktail.getIngredients_single_string());

                Glide.with(context).load(cocktail.getImage_url()).into(dialog_cocktail_thumb);

                btn_dialog_cocktail_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cocktail_dialog.dismiss();
                    }
                });


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
            this.cv_cocktail = itemView.findViewById(R.id.cv_cocktail_item);
            this.tv_cocktail_name = itemView.findViewById(R.id.tv_cocktail_name);
            this.tv_cocktail_id = itemView.findViewById(R.id.tv_cocktail_id);
            this.tv_cocktail_glass = itemView.findViewById(R.id.tv_cocktail_glass);
            this.iv_cocktail_thumb = itemView.findViewById(R.id.iv_cocktail_thumb);
            this.cocktail_dialog_root = itemView.findViewById(R.id.dialog_cocktail_root);

        }

    }

}


