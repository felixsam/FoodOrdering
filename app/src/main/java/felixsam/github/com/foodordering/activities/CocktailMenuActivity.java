package felixsam.github.com.foodordering.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Api;

import net.steamcrafted.materialiconlib.MaterialMenuInflater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import felixsam.github.com.foodordering.Models.Cocktail;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.adapters.AdapterCocktail;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CocktailMenuActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    private RequestFuture<JSONObject> future;
    private RecyclerView recyclerView;
    private AdapterCocktail adapter_cocktail;
    private ArrayList<Cocktail> cocktail_list;
    private ArrayList<Cocktail> cocktail_list_default;
    ProgressBar simpleProgressBar;

    @Override
    protected  void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_cocktail);
        this.setTitle("Cocktail Search");
        mQueue = Volley.newRequestQueue(this);
        simpleProgressBar.setVisibility(View.VISIBLE);

        cocktail_list = new ArrayList<Cocktail>();
        cocktail_list_default = new ArrayList<Cocktail>();

        recyclerView = findViewById(R.id.rv_cocktail_search_results);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        load_default_cocktail_list();

        SearchView search_cocktail = findViewById(R.id.sv_cocktail_search);

        //adapter_cocktail = new Adapter_Cocktail(cocktail_list,getApplicationContext());

        search_cocktail.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(CocktailMenuActivity.this,"Search item is: " + query,Toast.LENGTH_LONG).show();
                //json_search_cocktail(query);
                search_cocktail_name(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        MaterialMenuInflater
                .with(this)
                .setDefaultColor(Color.BLACK)
                .inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_home) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            return true;
        }
        if (id == R.id.action_list){
            adapter_cocktail.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
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
                            adapter_cocktail = new AdapterCocktail(cocktail_list, CocktailMenuActivity.this);
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

        /*
        JsonObjectRequest request  = new JsonObjectRequest(url, null, future, future);

        mQueue.add(request);

        try{

            JSONObject response = null;
            while (response == null){
                try {
                    //Blocks thread for 30 seconds
                    response = future.get(30,TimeUnit.SECONDS);
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }


        } catch (ExecutionException e){

        } catch (TimeoutException e){

        }
*/
    }


    private void load_default_cocktail_list(){
        adapter_cocktail = new AdapterCocktail(cocktail_list_default, CocktailMenuActivity.this);

        for (char letter = 'a'; letter <= 'z' ; letter++){
            search_cocktail_by_letter(letter);

        }

        //search_cocktail_by_letter('a');
        recyclerView.setAdapter(adapter_cocktail);

        //recyclerView.smoothScrollToPosition(0);

    }

    private void searchCocktailRetrofit(char letter){
        String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?f=" + String.valueOf(letter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();



    }
    private void search_cocktail_by_letter(char search_item_letter){
        String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?f=" + String.valueOf(search_item_letter);

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
                            Collections.sort(cocktail_list_default);

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
