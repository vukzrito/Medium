package za.co.entuit.medium.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RVukela on 2017/04/18.
 */

public class ApiClient {
    public static  final  String BASE_URL = Constants.API_URL;
    public static Retrofit retrofit =null;

    public static  Retrofit getClient(){
        if (retrofit == null){
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}