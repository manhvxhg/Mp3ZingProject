package com.example.manhnd16.mp3zingproject.service;

/**
 * Created by mac on 7/1/18.
 */

public class ApiService {

    private static String baseURL = "https://wormphoto.000webhostapp.com/Server/";

    public static ServiceListener getService() {
        return APIRetrofitClient.getClient(baseURL).create(ServiceListener.class);
    }
}
