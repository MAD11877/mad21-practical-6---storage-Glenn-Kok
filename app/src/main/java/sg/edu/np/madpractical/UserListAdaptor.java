package sg.edu.np.madpractical;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserListAdaptor extends RecyclerView.Adapter<UserListViewHolder> {
    ArrayList<User> data = new ArrayList<>();

    public UserListAdaptor(ArrayList<User> input){
        data = input;
    }

    public UserListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item;
        String currentUsername = data.get(viewType).getName();
        if (currentUsername.endsWith("7")){
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_layout2, parent, false);
        }
        else{
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_layout, parent, false);
        }
        UserListViewHolder holder = new UserListViewHolder(item);
        item.findViewById(R.id.users_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileView(data.get(holder.getAdapterPosition()), v.getContext());
            }
        });

        return holder;
    }

    public void onBindViewHolder(UserListViewHolder holder, int position){
        User user = data.get(position);
        holder.username.setText(user.getName());
        holder.description.setText(user.getDescription());
    }

    public int getItemCount(){
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static void profileView(User user, Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Profile");
        builder.setMessage(user.getName());
        builder.setCancelable(false);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("id", String.valueOf(user.getId()));
                context.startActivity(intent);
            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
