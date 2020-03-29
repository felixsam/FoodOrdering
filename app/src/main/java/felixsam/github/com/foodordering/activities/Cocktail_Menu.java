package felixsam.github.com.foodordering.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import felixsam.github.com.foodordering.R;

public class Cocktail_Menu extends AppCompatActivity {

    private TextView tv_cocktail;
    private RequestQueue mQueue;

    @Override
    protected  void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_cocktail);

        tv_cocktail = findViewById(R.id.tv_cocktail_text);
        Button buttonParse = findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });

    }

    private void jsonParse(){
        String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("drinks");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject drink = jsonArray.getJSONObject(i);

                                String drink_name = drink.getString("strDrink");
                                int drink_id = drink.getInt("idDrink");
                                String drink_glass = drink.getString("strGlass");

                                tv_cocktail.append(drink_name + ", " + String.valueOf(drink_id) + ", " + drink_glass + "\n\n");
                            }
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
