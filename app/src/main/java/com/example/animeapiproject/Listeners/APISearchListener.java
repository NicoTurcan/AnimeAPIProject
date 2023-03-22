package com.example.animeapiproject.Listeners;

import com.example.animeapiproject.Models.APISearch;

//interface for APISearch items
public interface APISearchListener {

    void onResponse(APISearch response);
    void errorHandling(String msg);

}
