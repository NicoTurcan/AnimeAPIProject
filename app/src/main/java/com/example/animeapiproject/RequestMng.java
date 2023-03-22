package com.example.animeapiproject;

import android.content.Context;
import android.widget.Toast;

import com.example.animeapiproject.Listeners.APISearchListener;
import com.example.animeapiproject.Listeners.DetailsAPISearchListener;
import com.example.animeapiproject.Models.APIDetailSearch;
import com.example.animeapiproject.Models.APISearch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestMng {
    Context context;
    Retrofit rFit = new Retrofit.Builder() // using retrofit to build url
            .baseUrl("https://api.jikan.moe/v4/") // base url of API
            .addConverterFactory(GsonConverterFactory.create()) // use GsonConverter, found it more efficient than the getJSONArray and Method (started with them in the beginning)
            .build(); // build the url

    public RequestMng(Context context) {
        this.context = context;
    }

    public void animeSearchMethod(APISearchListener l, String anime_title) {
        animeFetchInterface getAnime = rFit.create(RequestMng.animeFetchInterface.class); // animeFetchInterface item
        Call<APISearch> call = getAnime.callAnime(anime_title,true); // call APISearch object with the query given, always sfw options only

        call.enqueue(new Callback<APISearch>() {
            @Override
            public void onResponse(Call<APISearch> call, Response<APISearch> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "Could not get data, try again", Toast.LENGTH_SHORT).show();
                    return;
                }
                l.onResponse(response.body()); // if everything is alright and response seems fine, listener.onResponse is set to the body of the response (data)
            }

            @Override
            public void onFailure(Call<APISearch> call, Throwable t) {
                l.errorHandling("Could not find request");
            }
        });
    }

    //same thing here, just with another interface that has another query that uses the mal_id to get a specific anime
    public void animeSearchDetailsMethod(DetailsAPISearchListener l, int mal_id) {
        animeDetailsInterface getAnimeDetails = rFit.create(RequestMng.animeDetailsInterface.class);
        Call<APIDetailSearch> call = getAnimeDetails.callAnimeDetails(mal_id);

        call.enqueue(new Callback<APIDetailSearch>() {
            @Override
            public void onResponse(Call<APIDetailSearch> call, Response<APIDetailSearch> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "Could not get data, try again", Toast.LENGTH_SHORT).show();
                    return;
                }
                l.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<APIDetailSearch> call, Throwable t) {
                l.onError("Could not find request");
            }
        });
    }

    // get the query for search (baseURL + anime + q= + anime_title)
    public interface animeFetchInterface {
        @Headers({
                "Accept: application/json"
        })
        @GET("anime")
        Call<APISearch> callAnime(
                @Query("q") String anime_title,
                @Query("sfw") boolean sfw
        );
    }

    // get the path for search (baseURL + anime/ + id that got from the click)
    public interface animeDetailsInterface {
        @Headers({
                "Accept: application/json"
        })
        @GET("anime/{mal_id}")
        Call<APIDetailSearch> callAnimeDetails(
                @Path("mal_id") int mal_id
        );
    }


}
