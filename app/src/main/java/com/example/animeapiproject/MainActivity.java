package com.example.animeapiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.example.animeapiproject.Adapters.MainAdapter;
import com.example.animeapiproject.Listeners.APISearchListener;
import com.example.animeapiproject.Listeners.OnAnimeClick;
import com.example.animeapiproject.Models.APISearch;

public class MainActivity extends AppCompatActivity implements OnAnimeClick{

    // set all items needed (I used a progress dialog
    SearchView searchBarMain;
    RecyclerView recyclerMain;
    RequestMng managerMain;
    MainAdapter adapterMain;
    ProgressDialog dialogMain;
    //didnt add a button because I thought from a users perspective. When Im on a phone, I never use buttons on apps. I always just press enter and its
    //slightly annoying when that doesn't work and I need to actually press a button to submit



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set them all
        searchBarMain = findViewById(R.id.search_bar);
        recyclerMain = findViewById(R.id.recycler_view1);
        managerMain = new RequestMng(this);
        dialogMain = new ProgressDialog(this);

        //recycler initialized with nothing so far, without this, i noticed my recycler view doesn't seem to work
        recyclerMain.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        adapterMain = new MainAdapter(this, null, this);
        recyclerMain.setAdapter(adapterMain);

        //search bar methods
        searchBarMain.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            //when query is submitted
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialogMain.setTitle("Loading..."); // show the progress dialog (i decided to use progress dialog because, in my opinion, its cleaner then a bar and I can change the color of the entire box to match my color pattern
                dialogMain.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.rgb(73,3,92))); // set color
                dialogMain.show();
                searchBarMain.clearFocus(); // hide keyboard
                managerMain.animeSearchMethod(apiListenerMain,query); // call anime search method from RequestMng
                return true; // return
            }

            //never used, but needs to be implemented
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    // api listener for APISearch
    private APISearchListener apiListenerMain = new APISearchListener() {
        @Override
        public void onResponse(APISearch response) {
            if(response==null){ // if response is null from the listener, let the user know
                Toast.makeText(MainActivity.this, "No information ", Toast.LENGTH_SHORT).show();
                return;
            }
            showAll(response); // if everything is all right, it is time to show all of the data from the response
            dialogMain.dismiss(); // dismiss the progress dialog
        }

        @Override
        public void errorHandling(String msg) {
            dialogMain.dismiss(); // dismiss the progress dialog
            Toast.makeText(MainActivity.this, "ERROR",Toast.LENGTH_SHORT).show(); // show user that an error occurred
        }
    };

    private void showAll(APISearch response) {
        recyclerMain.setLayoutManager(new GridLayoutManager(MainActivity.this,2)); // set layout manager with grid layout to 2 columns
        adapterMain = new MainAdapter(this, response.getData(), this); // adapter is set to data from response
        recyclerMain.setAdapter(adapterMain);

        if(recyclerMain.getAdapter() == null || recyclerMain.getAdapter().getItemCount() == 0){
            Toast.makeText(this,"Could not find data, please try again",Toast.LENGTH_SHORT).show(); // if there is no result, let the user know
        }

    }

    @Override
    public void onAnimeClick(int mal_id) {
        MainActivity.this.startActivity(new Intent(MainActivity.this, MoreInfoActivity.class) // once you click an anime from the recycler, send user to another activity with more details about the anime
                .putExtra("data", mal_id)); // put extra is used to send the id over to use another GET method to get information about one specific anime
    }
}