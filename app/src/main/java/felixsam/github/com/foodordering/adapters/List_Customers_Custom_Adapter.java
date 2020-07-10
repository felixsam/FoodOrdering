package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;

import felixsam.github.com.foodordering.Models.Customer;
import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Globals;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.activities.Edit_Customer_Activity;

public class List_Customers_Custom_Adapter extends BaseAdapter {

    private final Context context;
    private final DatabaseHelper dbHelper;
    private final ArrayList<Customer> CustomerList;
    private static final String TAG = "List Customers Custom Adapter";


    public List_Customers_Custom_Adapter(Context context, ArrayList<Customer> CustomerList){
        this.context = context;
        this.CustomerList = CustomerList;
        this.dbHelper = new DatabaseHelper(context.getApplicationContext());
        //mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }



    //TEST
    @Override
    public int getCount() {
        return CustomerList.size();
    }

    @Override
    public Object getItem(int position) {
        return CustomerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    private static class ViewHolder{
        private TextView tv_name, tv_userID;
        Button btn_switch;
        Button btn_edit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final ViewHolder holder;


        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = Objects.requireNonNull(inflater).inflate(R.layout.adapter_single_customer, null, true);



            holder.tv_userID = convertView.findViewById(R.id.tv_customer_userID);
            holder.tv_name = convertView.findViewById(R.id.tv_customer_name);
            holder.btn_switch = convertView.findViewById(R.id.customer_switch_button);
            holder.btn_edit = convertView.findViewById(R.id.customer_edit_button);


            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        Customer customer = CustomerList.get(position);

        holder.tv_name.setText(customer.getName());
        holder.tv_userID.setText(customer.getUserID().toString());


        holder.btn_switch.setTag(R.integer.btn_switch_view,convertView);
        holder.btn_switch.setTag(R.integer.btn_switch_pos,position);
        holder.btn_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){


                View tempview = (View) holder.btn_switch.getTag(R.integer.btn_switch_view);

                TextView cust_name = tempview.findViewById(R.id.tv_customer_name);
                TextView cust_userID = tempview.findViewById(R.id.tv_customer_userID);

                String customer_name = cust_name.getText().toString();
                Integer customer_userID = Integer.valueOf(cust_userID.getText().toString());

                //Add user and userID
                Globals g = Globals.getInstance();
                g.setUser(customer_name);
                g.setUser_ID(customer_userID);

                Toast.makeText(v.getContext(),"Switch to Name: " + customer_name + " UserID: " + customer_userID, Toast.LENGTH_LONG).show();


            }

        });

        holder.btn_edit.setTag(R.integer.btn_edit_view,convertView);
        holder.btn_edit.setTag(R.integer.btn_edit_pos,position);
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                View tempview = (View) holder.btn_edit.getTag(R.integer.btn_edit_view);
                TextView cust_name = tempview.findViewById(R.id.tv_customer_name);
                TextView cust_userid = tempview.findViewById(R.id.tv_customer_userID);

                String customer_name = cust_name.getText().toString();
                Integer customer_user_id = Integer.valueOf(cust_userid.getText().toString());
                System.out.println("User id: " + customer_user_id.toString());
                String customer_last_name = dbHelper.get_customer_last_name(customer_user_id);
                System.out.println("LastName: " + customer_last_name);
                String customer_phone_number = dbHelper.get_customer_phone_number(customer_user_id);
                System.out.println("Phone Number: " + customer_phone_number);

                String customer_role = "";
                if (dbHelper.get_customer_role(customer_user_id) != null ){
                    customer_role = dbHelper.get_customer_role(customer_user_id);
                    System.out.println("Customer Role: " + customer_role);
                }


                if(customer_user_id > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + customer_user_id);
                    Intent editScreenIntent = new Intent(context, Edit_Customer_Activity.class);
                    editScreenIntent.putExtra("id",customer_user_id);
                    editScreenIntent.putExtra("name",customer_name);
                    editScreenIntent.putExtra("last_name",customer_last_name);
                    editScreenIntent.putExtra("phone_number",customer_phone_number);
                    editScreenIntent.putExtra("role",customer_role);
                    context.startActivity(editScreenIntent);
                }
                else{
                    Snackbar.make(v,"No ID Associated with that name"
                            ,Snackbar.LENGTH_SHORT).show();                }

            }

        });

        return convertView;
    }
}
