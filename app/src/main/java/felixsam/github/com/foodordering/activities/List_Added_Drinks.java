package felixsam.github.com.foodordering.activities;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Objects;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.DialogFragment_Edit_ItemEntry;
import felixsam.github.com.foodordering.Models.ItemModel;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.adapters.ItemListAdapter;

public class List_Added_Drinks extends AppCompatActivity {
    private final String TAG = "ListDrinks";
    private DatabaseHelper myDB;
    private ArrayList<ItemModel> drinkList;
    private ListView listView_drinks;
    private ItemModel drnk;
    private Button btn_ok;
    private Button btn_cancel;
    private Button btn_delete;
    private Dialog editDrink_dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_added_items);
        this.setTitle("List of Drinks Orders");
        myDB = new DatabaseHelper(this);

        TextView tv_title_header = findViewById(R.id.tv_list_added_items_title);
        TextView tv_description_header = findViewById(R.id.tv_list_added_items_description);

        tv_title_header.setText("Drinks");
        tv_description_header.setText("List of Added Drinks");

        //hide support bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        drinkList = new ArrayList<>();

        //Populate list of added drinks
        Cursor data_drinks = myDB.getItemContents("DRINK");
        int drink_entries = data_drinks.getCount();
        if(drink_entries == 0){
            Log.d(TAG,"Number of Rows is: " + drink_entries);

            Toast.makeText(List_Added_Drinks.this,"List is Empty",Toast.LENGTH_LONG).show();
        }else{
            Log.d(TAG,"Number of Rows is: " + drink_entries);

            int i=0;
            while(data_drinks.moveToNext()){
                drnk = new ItemModel(data_drinks.getInt(data_drinks.getColumnIndex(DatabaseHelper.ITEMS_COL1_ID)),
                        data_drinks.getString(data_drinks.getColumnIndex(DatabaseHelper.ITEMS_COL3_FIRST_NAME)),
                        data_drinks.getString(data_drinks.getColumnIndex(DatabaseHelper.ITEMS_COL5_ITEM_NAME)),
                        data_drinks.getInt(data_drinks.getColumnIndex(DatabaseHelper.ITEMS_COL6_PRICE)),
                        data_drinks.getInt(data_drinks.getColumnIndex(DatabaseHelper.ITEMS_COL7_QUANTITY)),
                        data_drinks.getInt(data_drinks.getColumnIndex(DatabaseHelper.ITEMS_COL2_USER_ID))
                );
                Log.d(TAG, "onCreate: colID is: " + data_drinks.getString(data_drinks.getColumnIndex(DatabaseHelper.ITEMS_COL1_ID)));

                drinkList.add(i,drnk);
                Log.i(TAG,data_drinks.getString(data_drinks.getColumnIndex(DatabaseHelper.ITEMS_COL3_FIRST_NAME)) + " "
                        + data_drinks.getString(data_drinks.getColumnIndex(DatabaseHelper.ITEMS_COL5_ITEM_NAME)) + " "
                        + data_drinks.getInt(data_drinks.getColumnIndex(DatabaseHelper.ITEMS_COL6_PRICE)) + " "
                        + data_drinks.getInt(data_drinks.getColumnIndex(DatabaseHelper.ITEMS_COL7_QUANTITY)) + " " +
                        + data_drinks.getInt(data_drinks.getColumnIndex(DatabaseHelper.ITEMS_COL2_USER_ID))
                );
                Log.i(TAG,drinkList.get(i).getName());
                i++;
            }
            ItemListAdapter customAdapterItemList_drinks =  new ItemListAdapter(this, R.layout.adapter_item_list_columns, drinkList);
            listView_drinks = (ListView) findViewById(R.id.lv_itemlist);
            listView_drinks.setAdapter(customAdapterItemList_drinks);



            //Open Dialog to Edit Drink Details
            listView_drinks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(final AdapterView<?> parent,
                                        View view,
                                        int position,
                                        long id) {

                    //Pass details to dialog fragment
                    Bundle bundle = new Bundle();
                    String item_name = drinkList.get(position).getName();
                    double item_price = drinkList.get(position).getPrice();
                    Integer colID = drinkList.get(position).getColID();
                    Integer quantity = drinkList.get(position).getQuantity();

                    bundle.putString("ITEM_NAME",item_name);
                    bundle.putDouble("ITEM_PRICE",item_price);
                    bundle.putInt("COL_ID",colID);
                    bundle.putInt("QUANTITY", quantity);

                    FragmentManager fragmentManger = getSupportFragmentManager();
                    DialogFragment_Edit_ItemEntry newFragment = new DialogFragment_Edit_ItemEntry();
                    newFragment.setArguments(bundle);
                    FragmentTransaction transaction = fragmentManger.beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.add(android.R.id.content,newFragment).addToBackStack(null).commit();
                }

            });

        }



    }
}
