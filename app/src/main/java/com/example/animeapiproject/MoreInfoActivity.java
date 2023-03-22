package com.example.animeapiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animeapiproject.Listeners.DetailsAPISearchListener;
import com.example.animeapiproject.Models.APIDetailSearch;
import com.squareup.picasso.Picasso;

public class MoreInfoActivity extends AppCompatActivity {
    //set all views and objects we need
    TextView animeName, animeRating, animeDate, animeStatus, animePlot,animeEpisodes,animeStudio,animeGenre,animeDuration,animeBackground;
    ImageView animePoster2;
    LinearLayout detailsLinear;
    RequestMng requestManager;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        // set them all so we can manipulate them from xml
        animeName = findViewById(R.id.anime_name_view);
        animeRating = findViewById(R.id.animeRating);
        animeDate = findViewById(R.id.release_date);
        animeStatus = findViewById(R.id.animeStatus);
        animePlot = findViewById(R.id.animePlot);
        animePoster2 = findViewById(R.id.animePoster2);
        animeEpisodes = findViewById(R.id.episodeNumber);
        animeStudio = findViewById(R.id.studioName);
        animeGenre = findViewById(R.id.animeGenre);
        animeDuration = findViewById(R.id.animeDuration);
        animeBackground = findViewById(R.id.background);
        detailsLinear =  findViewById(R.id.detailsLinearLayout);

        requestManager = new RequestMng(this);

        int mal_id = getIntent().getIntExtra("data",0); // the id as previously mentioned

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading..."); // progress dialog again with custom background color to match
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.rgb(73,3,92)));
        progressDialog.show();

        requestManager.animeSearchDetailsMethod(listener, mal_id); // call anime search details from RequestMng with the id
    }

    // same thing as in MainActivity
    private DetailsAPISearchListener listener = new DetailsAPISearchListener() {
        @Override
        public void onResponse(APIDetailSearch response) {
            progressDialog.dismiss();
            if(response==null){
                Toast.makeText(MoreInfoActivity.this, "Error, response is null", Toast.LENGTH_SHORT).show();
                return;
            }
            showAll(response);
        }

        @Override
        public void onError(String msg) {
            progressDialog.dismiss();
            Toast.makeText(MoreInfoActivity.this, "Error, reached onError", Toast.LENGTH_SHORT).show();
        }
    };

    //show all of the information you want from the response data
    private void showAll(APIDetailSearch response) {
        detailsLinear.setVisibility(View.VISIBLE); // so the there is nothing behind the progress dialog when Loading... appears

        if(response.data.getTitle() == null){
            animeName.setText("N/A");
        }
        else{
            animeName.setText(response.data.getTitle());
        }

        if(response.data.getRating()==null){
            animeRating.setText("Rated: N/A");
        }
        else{
            animeRating.setText("Rated: "+response.data.getRating());
        }

        if(response.data.getAired().getString() == null){
            animeDate.setText("Date: N/A");
        }
        else{
            animeDate.setText("Date(s): "+response.data.getAired().getString());
        }

        if(response.data.getStatus() == null){
            animeStatus.setText("N/A");
        }
        else{
            animeStatus.setText(response.data.getStatus());
        }

        if(response.data.getSynopsis()==null){
            animePlot.setText("Synopsis: N/A");
        }
        else {
            animePlot.setText(response.data.getSynopsis());
        }

        if(response.data.getEpisodes()==null){
            animeEpisodes.setText("Number of episodes: N/A");
        }
        else {
            animeEpisodes.setText("Number of episodes: "+response.data.getEpisodes());
        }

        if(response.data.getStudio()==null){
            animeStudio.setText("Studio: N/A");
        }
        else {
            animeStudio.setText("Studio: "+response.data.getStudio().get(0).getName());
        }

        if(response.data.getGenres()==null){
            animeGenre.setText("Genre: N/A");
        }
        else {
            animeGenre.setText("Genre: "+response.data.getGenres().get(0).getName());
        }

        if(response.data.getDuration()==null){
            animeDuration.setText("Duration: N/A");
        }
        else {
            animeDuration.setText("Duration: "+response.data.getDuration());
        }

        if(response.data.getBackground()==null){
            animeBackground.setText("Extra Info: N/A");
        }
        else {
            animeBackground.setText("Extra Info: "+response.data.getBackground());
        }

        try{
            Picasso.get().load(response.data.getImages().getJpg().getImage_url()).into(animePoster2);
        }
        catch(Exception e){
            e.printStackTrace();
        }




    }


}