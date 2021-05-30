package sg.edu.np.madpractical;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserListViewHolder extends RecyclerView.ViewHolder {
    TextView username;
    TextView description;

    public UserListViewHolder(View itemView){
        super(itemView);
        username = itemView.findViewById(R.id.users_name);
        description = itemView.findViewById(R.id.users_des);

    }

}
