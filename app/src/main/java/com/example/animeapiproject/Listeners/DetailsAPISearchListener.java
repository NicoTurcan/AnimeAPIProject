package com.example.animeapiproject.Listeners;

import com.example.animeapiproject.Models.APIDetailSearch;

//interface for DetailsAPISearch items
public interface DetailsAPISearchListener {
    void onResponse(APIDetailSearch response);
    void onError(String msg);
}
