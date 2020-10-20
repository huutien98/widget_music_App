package com.shinmusic.Service;

public class APIService {
    private  static  String base_url = "https://shingetsu.000webhostapp.com";

    public  static Dataservice getService(){
        return  APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
