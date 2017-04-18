package za.co.entuit.medium.data;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RVukela on 2017/04/13.
 */

public class ArticlesRepositoryImpl implements ArticlesRepository {
    ArrayList<Article> articles;
    private ArticlesApi api;

    public ArticlesRepositoryImpl(){
        api = new ArticlesApiImpl();
    }
    @Override
    public void loadArticles(@NonNull final LoadArticlesCallback callback) {
       api.getArticles(new ArticlesApi.ArticlesServiceCallback<List<Article>>() {
           @Override
           public void onLoaded(List<Article> articles) {
             callback.onLoaded(articles);
           }

           @Override
           public void onError(String errorMessage) {

                callback.onError(errorMessage);
           }
       });
        callback.onLoaded(articles);
    }

    @Override
    public void loadArticle(@NonNull LoadArticleCallback callback) {
        callback.onLoaded(articles.get(0));
    }
}
