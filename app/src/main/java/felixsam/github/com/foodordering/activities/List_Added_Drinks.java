package felixsam.github.com.foodordering.activities;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Models.ItemModel;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.adapters.CustomAdapter_ItemList;

public class List_Added_Drinks extends AppCompatActivity {
    private DatabaseHelper myDB;
    private ArrayList<ItemModel> drinkList;
    private ListView listView_drinks;
    private ItemModel drnk;
    private final String TAG = "ListDrinks";
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

        drinkList = new ArrayList<>();
        Cursor data = myDB.getItemContents("DRINK");
        int numRows = data.getCount();
        if(numRows == 0){
            Log.d(TAG,"Number of Rows is: " + numRows);

            Toast.makeText(List_Added_Drinks.this,"The Database is empty  :(.",Toast.LENGTH_LONG).show();
        }else{
            Log.d(TAG,"Number of Rows is: " + numRows);

            int i=0;
            while(data.moveToNext()){
                //cakes = new ItemModel(data.getString(1),data.getString(2),data.getString(3));
                drnk = new ItemModel(data.getInt(data.getColumnIndex(DatabaseHelper.ITEMS_COL1_ID)),data.getString(data.getColumnIndex(DatabaseHelper.ITEMS_COL3_FIRST_NAME)),
                        data.getString(data.getColumnIndex(DatabaseHelper.ITEMS_COL5_ITEM_NAME)),
                        data.getInt(data.getColumnIndex(DatabaseHelper.ITEMS_COL6_PRICE)),
                        data.getInt(data.getColumnIndex(DatabaseHelper.ITEMS_COL7_QUANTITY)),
                        data.getInt(data.getColumnIndex(DatabaseHelper.ITEMS_COL2_USER_ID))
                );
                Log.d(TAG, "onCreate: colID is: " + data.getString(data.getColumnIndex(DatabaseHelper.ITEMS_COL1_ID)));

                drinkList.add(i,drnk);
                System.out.println(data.getString(data.getColumnIndex(DatabaseHelper.ITEMS_COL3_FIRST_NAME))+" "
                        +data.getString(data.getColumnIndex(DatabaseHelper.ITEMS_COL5_ITEM_NAME)) +" "
                        +data.getInt(data.getColumnIndex(DatabaseHelper.ITEMS_COL6_PRICE)) + " "
                        +data.getInt(data.getColumnIndex(DatabaseHelper.ITEMS_COL7_QUANTITY)) + " " +
                        +data.getInt(data.getColumnIndex(DatabaseHelper.ITEMS_COL2_USER_ID))
                );
                System.out.println(drinkList.get(i).getName());
                i++;
            }
            CustomAdapter_ItemList adapter =  new CustomAdapter_ItemList(this, R.layout.adapter_item_list_columns, drinkList);
            listView_drinks = (ListView) findViewById(R.id.drink_listView);
            listView_drinks.setAdapter(adapter);



            listView_drinks.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(final AdapterView<?> parent, View view, int position,
                                        long id)
                {
                    //String value = (String)adapter.getItemAtPosition(position);
                    //Toast.makeText(getApplicationContext(),"Testing ", Toast.LENGTH_LONG).show();
                    // assuming string and if you want to get the value on click of list item
                    // do what you intend to do on click of listview row

                    editDrink_dialog = new Dialog(List_Added_Drinks.this);
                    LayoutInflater customInflater = (LayoutInflater)List_Added_Drinks.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    View customLayout= Objects.requireNonNull(customInflater).inflate(R.layout.custom_dialog_edit_item, (ViewGroup) findViewById(R.id.root));
                    editDrink_dialog.setContentView(customLayout);
                    ViewGroup.LayoutParams layoutParams2= customLayout.getLayoutParams();
                    layoutParams2.height=900;
                    layoutParams2.width=900;



                    final TextView tv_quantity = (TextView) customLayout.findViewById(R.id.custom_dialog_tv_quantity);
                    TextView tv_item_name = (TextView) customLayout.findViewById(R.id.custom_dialog_tv_item_name);
                    TextView tv_item_price = (TextView) customLayout.findViewById(R.id.custom_dialog_tv_item_price);

                    Integer current_quantity = drinkList.get(position).getQuantity();
                    final String item_name = drinkList.get(position).getName();
                    Integer item_price = drinkList.get(position).getPrice();
                    final Integer colID = drinkList.get(position).getColID();

                    tv_item_name.setText("Item Name: " + item_name);
                    tv_item_price.setText("Price: $" + item_price.toString());
                    tv_quantity.setText(current_quantity.toString());

                    btn_ok = (Button) customLayout.findViewById(R.id.btn_update_entry);
                    btn_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //done what do you want to do\
                            Integer new_quantity = Integer.valueOf(tv_quantity.getText().toString());
                            Log.d(TAG,"Updating Data: colID is : " + colID.toString() + "Set new quantity as: " + new_quantity.toString());
                            myDB.updateItemQuantity(colID,new_quantity,item_name);
                            editDrink_dialog.dismiss();
                            recreate();
                        }
                    });

                    btn_delete = (Button)customLayout.findViewById(R.id.custom_dialog_delete);
                    btn_delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v){
                            Log.d(TAG,"Deleting Data: colID is : " + colID.toString() + "Item Name is: " + item_name);
                            myDB.delData_items(colID,item_name);
                            editDrink_dialog.dismiss();
                            recreate();
                        }

                    });
                    btn_cancel =(Button)customLayout.findViewById(R.id.cancel);
                    btn_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //done what do you want to do
                            editDrink_dialog.dismiss();
                        }
                    });



                    editDrink_dialog.show();


                }
            });

        }



    }
}
