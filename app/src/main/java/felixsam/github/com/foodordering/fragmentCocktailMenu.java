package felixsam.github.com.foodordering;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import felixsam.github.com.foodordering.Models.Cocktail;
import felixsam.github.com.foodordering.Models.Item_Model_Display;
import felixsam.github.com.foodordering.activities.CocktailMenuActivity;
import felixsam.github.com.foodordering.adapters.AdapterCocktail;
import felixsam.github.com.foodordering.adapters.ItemMenuAdapter;

public class fragmentCocktailMenu extends DialogFragment {

    private RequestQueue mQueue;
    private RequestFuture<JSONObject> future;
    private RecyclerView recyclerView;
    private AdapterCocktail adapter_cocktail;
    private ArrayList<Cocktail> cocktail_list = new ArrayList<>();
    private ArrayList<Cocktail> cocktail_list_default = new ArrayList<>();
    ProgressBar simpleProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.activity_cocktail, container, false);

        recyclerView = rootView.findViewById(R.id.rv_cocktail_search_results);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        mQueue = Volley.newRequestQueue(getActivity());

        load_default_cocktail_list();
        SearchView search_cocktail = rootView.findViewById(R.id.sv_cocktail_search);

        search_cocktail.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getActivity(),"Search item is: " + query,Toast.LENGTH_LONG).show();
                //json_search_cocktail(query);
                search_cocktail_name(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return rootView;
    }



    private void search_cocktail_name(String search_item){

        String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + search_item;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("drinks");
                            String result = "";
                            cocktail_list.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject drink = jsonArray.getJSONObject(i);

                                String drink_name = drink.getString("strDrink");
                                int drink_id = drink.getInt("idDrink");
                                String drink_glass = drink.getString("strGlass");
                                //get drink thumbnail 100x100 size
                                String drink_thumbnail = drink.getString("strDrinkThumb") + "/preview";
                                String instructions = drink.getString("strInstructions");
                                String alcoholic  = drink.getString("strAlcoholic");
                                ArrayList<String> ingredients = new ArrayList<>();

                                //Add all the ingredients
                                for (int k = 1; k < 16; k++ ){
                                    String ingredient_index = "strIngredient" + k;
                                    String ingredient = drink.getString(ingredient_index);

                                    ingredients.add(ingredient);
                                }


                                Cocktail new_cocktail = new Cocktail(drink_name,drink_id,drink_glass);

                                new_cocktail.setImage_url(drink_thumbnail);

                                new_cocktail.setInstructions(instructions);
                                new_cocktail.setAlcoholic(alcoholic);
                                new_cocktail.setIngredients(ingredients);

                                cocktail_list.add(new_cocktail);
                            }
                            adapter_cocktail = new AdapterCocktail(cocktail_list,getActivity());
                            recyclerView.setAdapter(adapter_cocktail);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });

        mQueue.add(request);

    }


    private void load_default_cocktail_list(){
        adapter_cocktail = new AdapterCocktail(cocktail_list_default, getActivity());

        for (int i = 0; i < 10; i++){
            randomCocktail();
        }

        recyclerView.setAdapter(adapter_cocktail);

    }

    private void randomCocktail(){
        String url = "https://www.thecocktaildb.com/api/json/v1/1/random.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("drinks");
                            String result = "";
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject drink = jsonArray.getJSONObject(i);

                                String drink_name = drink.getString("strDrink");
                                int drink_id = drink.getInt("idDrink");
                                String drink_glass = drink.getString("strGlass");
                                //get drink thumbnail 100x100 size
                                String drink_thumbnail = drink.getString("strDrinkThumb") + "/preview";
                                String instructions = drink.getString("strInstructions");
                                String alcoholic  = drink.getString("strAlcoholic");
                                ArrayList<String> ingredients = new ArrayList<>();

                                //Add all the ingredients
                                for (int k = 1; k < 16; k++ ){
                                    String ingredient_index = "strIngredient" + k;
                                    String ingredient = drink.getString(ingredient_index);

                                    ingredients.add(ingredient);
                                }


                                Cocktail new_cocktail = new Cocktail(drink_name,drink_id,drink_glass);

                                new_cocktail.setImage_url(drink_thumbnail);

                                new_cocktail.setInstructions(instructions);
                                new_cocktail.setAlcoholic(alcoholic);
                                new_cocktail.setIngredients(ingredients);

                                cocktail_list_default.add(new_cocktail);
                            }

                            adapter_cocktail.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);


    }



}
