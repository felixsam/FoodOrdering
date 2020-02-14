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

import java.util.ArrayList;

import felixsam.github.com.foodordering.Models.Customer;
import felixsam.github.com.foodordering.DatabaseHelper;
import felixsam.github.com.foodordering.Globals;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.activities.Edit_Customer;

public class List_Customers_Custom_Adapter extends BaseAdapter {

    private Context context;
    private DatabaseHelper dbHelper;
    private ArrayList<Customer> CustomerList;
    private int mViewResourceId;
    private LayoutInflater inflater;
    private static final String TAG = "List Customers Custom Adapter";


    public List_Customers_Custom_Adapter(Context context,int textViewResourceId, ArrayList<Customer> CustomerList){
        this.context = context;
        this.CustomerList = CustomerList;
        this.dbHelper = new DatabaseHelper(context.getApplicationContext());
        //mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;


    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
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


    private class ViewHolder{
        private TextView tv_name, tv_userID;
        protected Button btn_switch, btn_edit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final ViewHolder holder;


        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_single_customer, null, true);



            holder.tv_userID = (TextView) convertView.findViewById(R.id.tv_customer_userID);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_customer_name);
            holder.btn_switch = (Button) convertView.findViewById(R.id.customer_switch_button);
            holder.btn_edit = (Button) convertView.findViewById(R.id.customer_edit_button);


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
                String customer_last_name =  dbHelper.get_customer_last_name(customer_user_id).toString();
                System.out.println("LastName: " + customer_last_name);
                String customer_phone_number = dbHelper.get_customer_phone_number(customer_user_id).toString();
                System.out.println("Phone Number: " + customer_phone_number);

                String customer_role = dbHelper.get_customer_role(customer_user_id).toString();
                System.out.println("Customer Role: " + customer_role);


                if(customer_user_id > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + customer_user_id);
                    Intent editScreenIntent = new Intent(context, Edit_Customer.class);
                    editScreenIntent.putExtra("id",customer_user_id);
                    editScreenIntent.putExtra("name",customer_name);
                    editScreenIntent.putExtra("last_name",customer_last_name);
                    editScreenIntent.putExtra("phone_number",customer_phone_number);
                    editScreenIntent.putExtra("role",customer_role);
                    context.startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }

            }

        });

        return convertView;
    }
}
