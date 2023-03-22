package com.example.animeapiproject.Models;

import java.util.List;

public class APISearch {

    //getting data as list of item objects, as the api is built
    List<ItemObject> data = null;

    //getter
    public List<ItemObject> getData() {
        return data;
    }

    //setter
    public void setData(List<ItemObject> data) {
        this.data = data;
    }

}
