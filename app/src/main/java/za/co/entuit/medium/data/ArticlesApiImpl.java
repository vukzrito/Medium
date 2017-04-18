package za.co.entuit.medium.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.entuit.medium.util.ApiClient;
import za.co.entuit.medium.util.ApiInterface;

/**
 * Created by RVukela on 2017/04/18.
 */

public class ArticlesApiImpl implements ArticlesApi {
    @Override
    public void getArticles(final ArticlesServiceCallback<List<Article>> callback) {
       ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Article>> call = api.getAllArticles();
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                callback.onLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });

    }

    @Override
    public void getArticle(ArticlesServiceCallback<Article> callback) {

    }
}
