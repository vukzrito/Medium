package za.co.entuit.medium.articles;

import java.util.List;

import za.co.entuit.medium.data.Article;
import za.co.entuit.medium.data.ArticlesRepository;

/**
 * Created by RVukela on 2017/04/13.
 */

public class ArticlesPresenter implements ArticlesContract.UserActionsListener {
    private ArticlesContract.View view;
    private ArticlesRepository articlesRepository;

    public ArticlesPresenter(ArticlesContract.View view, ArticlesRepository articlesRepository) {
        this.view = view;
        this.articlesRepository = articlesRepository;
    }

    @Override
    public void loadArticles(boolean forceUpdate) {
        view.showProgressIndicator(true);
        articlesRepository.loadArticles(new ArticlesRepository.LoadArticlesCallback() {
            @Override
            public void onLoaded(List<Article> articles) {
                view.showArticles(articles);
                view.showProgressIndicator(false);

            }

            @Override
            public void onError(String errorMessage) {
                view.showErrorMessage(errorMessage);
                view.showProgressIndicator(false);

            }
        });
    }

    @Override
    public void openArticleDetail(String articleId) {
        view.showArticleDetail(articleId);
    }
}
