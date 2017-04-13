package za.co.entuit.medium.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by RVukela on 2017/04/13.
 */

public interface ArticlesRepository {

    public interface LoadArticlesCallback{
        void onLoaded(List<Article> articles);
        void onError(String errorMessage);
    }

    public interface  LoadArticleCallback{
        void onLoaded(Article article);
        void onError(String errorMessage);
    }


    void loadArticles(@NonNull  LoadArticlesCallback callback);
    void loadArticle(@NonNull LoadArticleCallback callback);

}
