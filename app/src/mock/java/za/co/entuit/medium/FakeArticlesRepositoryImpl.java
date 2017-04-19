package za.co.entuit.medium;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import za.co.entuit.medium.data.Article;
import za.co.entuit.medium.data.ArticlesRepository;

/**
 * Created by RVukela on 2017/04/19.
 */
public class FakeArticlesRepositoryImpl implements ArticlesRepository {


    @Override
    public void loadArticles(@NonNull LoadArticlesCallback callback) {
        callback.onLoaded(generateArticles());
    }

    @Override
    public void loadArticle(@NonNull LoadArticleCallback callback) {
        callback.onLoaded(generateArticles().get(0));
    }

    private List<Article> generateArticles(){
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(new Article("Now Fitch downgrades SA to junk"));
        return articles;

    }
}
