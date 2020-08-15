package felixsam.github.com.foodordering.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import felixsam.github.com.foodordering.Globals;
import felixsam.github.com.foodordering.Models.User;
import felixsam.github.com.foodordering.R;
import felixsam.github.com.foodordering.activities.Edit_Customer_Activity;

public class ListUsersCustomAdapter extends RecyclerView.Adapter<ListUsersCustomAdapter.ViewHolder>{

    private String TAG = ListUsersCustomAdapter.class.getSimpleName();

    private List<User> mUsers;
    private String userName;
    private String name;
    private int userId;
    private Context mContext;

    public ListUsersCustomAdapter(List<User> users,Context context){
        mUsers = users;
        mContext = context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_userId;
        TextView tv_name;
        Button btn_switchUser;
        Button btn_editUser;

        public ViewHolder(View itemView){
            super(itemView);

            // initialize the view objects
            this.tv_userId = itemView.findViewById(R.id.tv_customer_userID);
            this.tv_name = itemView.findViewById(R.id.tv_customer_name);
            this.btn_switchUser = itemView.findViewById(R.id.btn_switch_user);
            this.btn_editUser = itemView.findViewById(R.id.btn_edit_user);


        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //Inflate the custom layout
        View v = inflater.inflate(R.layout.adapter_single_user,parent,false);

        return new ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        User user = mUsers.get(position);

        if (holder.tv_userId != null){
            holder.tv_userId.setText(String.valueOf(user.getUserID()));
        }

        if (holder.tv_name != null){
            holder.tv_name.setText(user.getFirstName());
        }

        holder.btn_switchUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               Log.i(TAG,"Switch User Button Clicked");

               Globals g = Globals.getInstance();
               g.setUser(user.getFirstName());
               g.setUser_ID(user.getUserID());
            }
        });

        holder.btn_editUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(TAG,"Edit User Button Clicked");
                int userId = user.getUserID();
                if (userId > -1){
                    Intent editScreenIntent = new Intent(mContext, Edit_Customer_Activity.class);
                    editScreenIntent.putExtra("id",user.getUserID());
                    editScreenIntent.putExtra("name",user.getFirstName());
                    mContext.startActivity(editScreenIntent);
                }else{
                    Snackbar.make(v,"No ID Associated with that name"
                            ,Snackbar.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount(){
        return mUsers.size();
    }


}
