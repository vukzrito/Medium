package za.co.entuit.medium.data;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by RVukela on 2017/04/13.
 */

public class ArticlesRepositoryImpl implements ArticlesRepository {
    ArrayList<Article> articles;
    @Override
    public void loadArticles(@NonNull LoadArticlesCallback callback) {
        articles = new ArrayList<>();
        articles.add(new Article("Now Fitch downgrades SA to junk"));
        callback.onLoaded(articles);
    }

    @Override
    public void loadArticle(@NonNull LoadArticleCallback callback) {
        callback.onLoaded(articles.get(0));
    }
}
