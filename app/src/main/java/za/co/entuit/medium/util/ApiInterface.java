package za.co.entuit.medium.util;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import za.co.entuit.medium.data.Article;

/**
 * Created by RVukela on 2017/04/18.
 */

public interface ApiInterface {

    @GET("Articles/")
    Call<List<Article>> getAllArticles();


}
