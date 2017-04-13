package za.co.entuit.medium.articles;

import java.util.List;

import za.co.entuit.medium.data.Article;

/**
 * Created by RVukela on 2017/04/12.
 */

public interface ArticlesContract {
    interface View{
        void showArticles(List<Article> articleList);
        void showProgressIndicator(boolean active);
        void showErrorMessage(String errorMessage);
        void showArticleDetail(String articleId);
    }

    interface UserActionsListener {
        void loadArticles(boolean forceUpdate);
        void openArticleDetail(String articleId);
    }
}
