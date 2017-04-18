package za.co.entuit.medium.data;

import java.util.List;

/**
 * Created by RVukela on 2017/04/18.
 */

public interface ArticlesApi {

    interface  ArticlesServiceCallback<T>{
        void onLoaded(T articles);
        void onError(String errorMessage);
    }

    void getArticles( ArticlesServiceCallback<List<Article>> callback);
    void getArticle(ArticlesServiceCallback<Article> callback);
}
