package za.co.entuit.medium.articles;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import za.co.entuit.medium.data.Article;
import za.co.entuit.medium.data.ArticlesRepository;

import static org.mockito.Mockito.verify;

/**
 * Created by RVukela on 2017/04/19.
 */
public class ArticlesPresenterTest {

    private static List<Article> ARTICLES = Lists.newArrayList(new Article("Test article 1"), new Article("Test article 2"));
    @Mock
    private ArticlesContract.View view;
    @Mock
    private ArticlesRepository repository;
    @Captor
    private ArgumentCaptor<ArticlesRepository.LoadArticlesCallback> loadArticlesCallbackArgumentCaptor;

    private ArticlesPresenter presenter;


    @Before
    public void setupArticlesPresenter(){
        MockitoAnnotations.initMocks(this);
        presenter = new ArticlesPresenter(view, repository);
    }

    @Test
    public void clickOnArticle_ShowsArticleDetail(){
        presenter.openArticleDetail("1");

        verify(view).showArticleDetail("1");
    }

    @Test
    public void loadArticlesFromRepositoryIntoView(){
        presenter.loadArticles(true);
        verify(repository).loadArticles(loadArticlesCallbackArgumentCaptor.capture());
        loadArticlesCallbackArgumentCaptor.getValue().onLoaded(ARTICLES);

        verify(view).showProgressIndicator(false);
        verify(view).showArticles(ARTICLES);
    }

}