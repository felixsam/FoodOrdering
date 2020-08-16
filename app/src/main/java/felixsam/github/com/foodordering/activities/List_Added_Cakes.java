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


public class List_Added_Cakes extends AppCompatActivity {
    private final String TAG = "ListCakes";
    private DatabaseHelper myDB;
    private ArrayList<ItemModel> cakeList;
    private ListView listview_cakes;
    private ItemModel cakes;
    private Button btn_ok;
    private Button btn_cancel;
    private Button btn_delete;
    private Dialog editCakeDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_added_items);
        this.setTitle("List of Cake Orders");
        TextView tv_cake_column = findViewById(R.id.item_column_header1);
        TextView tv_title_header = findViewById(R.id.tv_list_added_items_title);
        TextView tv_description_header = findViewById(R.id.tv_list_added_items_description);

        tv_title_header.setText("Cakes");
        tv_description_header.setText("List of Added Cakes");

        tv_cake_column.setText("Cake Name");

        myDB = new DatabaseHelper(this);

        //hide support bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        cakeList = new ArrayList<>();

        //Populate list of added cakes
        Cursor data_cakes = myDB.getItemContents("CAKE");
        int cake_entries = data_cakes.getCount();
        if(cake_entries == 0){
            Toast.makeText(List_Added_Cakes.this,"List is Empty",Toast.LENGTH_LONG).show();
        }else{
            Log.d(TAG,"Number of Rows is: " + cake_entries);
            int i=0;
            while(data_cakes.moveToNext()){
                cakes = new ItemModel(data_cakes.getInt(data_cakes.getColumnIndex(DatabaseHelper.ITEMS_COL1_ID)),
                        data_cakes.getString(data_cakes.getColumnIndex(DatabaseHelper.ITEMS_COL3_FIRST_NAME)),
                        data_cakes.getString(data_cakes.getColumnIndex(DatabaseHelper.ITEMS_COL5_ITEM_NAME)),
                        data_cakes.getInt(data_cakes.getColumnIndex(DatabaseHelper.ITEMS_COL6_PRICE)),
                        data_cakes.getInt(data_cakes.getColumnIndex(DatabaseHelper.ITEMS_COL7_QUANTITY)),
                        data_cakes.getInt(data_cakes.getColumnIndex(DatabaseHelper.ITEMS_COL2_USER_ID))
                );
                Log.d(TAG, "onCreate: colID is: " + data_cakes.getString(data_cakes.getColumnIndex(DatabaseHelper.ITEMS_COL1_ID)));

                cakeList.add(i, cakes);
               Log.i(TAG,data_cakes.getString(data_cakes.getColumnIndex(DatabaseHelper.ITEMS_COL3_FIRST_NAME))+" "
                        +data_cakes.getString(data_cakes.getColumnIndex(DatabaseHelper.ITEMS_COL5_ITEM_NAME)) +" "
                        +data_cakes.getInt(data_cakes.getColumnIndex(DatabaseHelper.ITEMS_COL6_PRICE)) + " "
                        +data_cakes.getInt(data_cakes.getColumnIndex(DatabaseHelper.ITEMS_COL7_QUANTITY)) + " " +
                        +data_cakes.getInt(data_cakes.getColumnIndex(DatabaseHelper.ITEMS_COL2_USER_ID))
                );
                Log.i(TAG,cakeList.get(i).getName());
                i++;
            }

            ItemListAdapter customAdapterItemList_cakes =  new ItemListAdapter(this,R.layout.adapter_item_list_columns, cakeList);
            listview_cakes = findViewById(R.id.lv_itemlist);
            listview_cakes.setAdapter(customAdapterItemList_cakes);

            //Open Dialog to edit Cake Details
            listview_cakes.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(final AdapterView<?> parent, View view, int position,
                                        long id) {

                    //Pass details to dialog fragment
                    Bundle bundle = new Bundle();
                    String item_name = cakeList.get(position).getName();
                    double item_price = cakeList.get(position).getPrice();
                    Integer colID = cakeList.get(position).getColID();
                    Integer quantity = cakeList.get(position).getQuantity();

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
