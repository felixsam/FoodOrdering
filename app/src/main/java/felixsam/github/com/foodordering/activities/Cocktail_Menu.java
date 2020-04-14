package felixsam.github.com.foodordering.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import felixsam.github.com.foodordering.Models.Cocktail;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.adapters.Adapter_Cocktail;

public class Cocktail_Menu extends AppCompatActivity {

    private RequestQueue mQueue;
    private SearchView search_cocktail;
    private String search_item;
    private RecyclerView recyclerView;
    private Adapter_Cocktail adapter_cocktail;
    private ArrayList<Cocktail> cocktail_list;

    @Override
    protected  void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_cocktail);

        cocktail_list = new ArrayList<Cocktail>();

        recyclerView = findViewById(R.id.rv_cocktail_search_results);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        search_item = "";
        search_cocktail = findViewById(R.id.sv_cocktail_search);

        //adapter_cocktail = new Adapter_Cocktail(cocktail_list,getApplicationContext());

        search_cocktail.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(Cocktail_Menu.this,"Search item is: " + query,Toast.LENGTH_LONG).show();
                //json_search_cocktail(query);
                load_cocktail_json(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search_item = newText;
                return false;
            }
        });

        mQueue = Volley.newRequestQueue(this);


    }


    private void load_cocktail_json(String search_item){
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
                                    String ingredient_index = "strIngredient" + String.valueOf(k);
                                    String ingredient = drink.getString(ingredient_index);

                                    ingredients.add(ingredient);
                                }


                                Cocktail new_cocktail = new Cocktail(drink_name,drink_id,drink_glass);

                                if (drink_thumbnail != null){
                                    new_cocktail.setImage_url(drink_thumbnail);
                                }

                                new_cocktail.setInstructions(instructions);
                                new_cocktail.setAlcoholic(alcoholic);
                                new_cocktail.setIngredients(ingredients);

                                cocktail_list.add(new_cocktail);
                            }
                            adapter_cocktail = new Adapter_Cocktail(cocktail_list,Cocktail_Menu.this);
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

    /*
    private void json_search_cocktail(String search_item){

        //String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita";
        String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + search_item;

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
                                result = result + drink_name + ", " + String.valueOf(drink_id) + ", " + drink_glass + "\n\n";
                            }
                            tv_cocktail.setText(result);
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

     */
}
