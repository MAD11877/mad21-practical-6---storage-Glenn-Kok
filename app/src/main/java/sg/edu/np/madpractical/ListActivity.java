package sg.edu.np.madpractical;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    final private static String TAG = "List Activity";
    private  UserDBHandler userDBHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.v(TAG, "List Activity - onStart");
        userDBHandler = new UserDBHandler(this, null, null, 1);

        initDB();

    }

    private void initDB(){
        for (int i = 0; i < 20; i++){
            userDBHandler.addUser(new User("Name"+randNum(), "Description "+randNum(), randBool()));
        }
    }

    private int randNum(){
        Random rand = new Random();
        return rand.nextInt(999999999);
    }

    private boolean randBool(){
        Random rand = new Random();
        return rand.nextBoolean();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "List Activity - onStart");

        ArrayList<User> userList = userDBHandler.getUsers();
        RecyclerView recyclerView = findViewById(R.id.users_recyclerView);
        UserListAdaptor myAdaptor = new UserListAdaptor(userList);

        LinearLayoutManager myLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdaptor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "List Activity - onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "List Activity - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "List Activity - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "List Activity - onDestroy");
    }
}