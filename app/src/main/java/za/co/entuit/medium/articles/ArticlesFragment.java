package za.co.entuit.medium.articles;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import za.co.entuit.medium.R;
import za.co.entuit.medium.data.Article;
import za.co.entuit.medium.data.ArticlesAdapter;
import za.co.entuit.medium.data.ArticlesRepositoryImpl;

/**
 * Created by RVukela on 2017/04/12.
 */

public class ArticlesFragment extends Fragment implements ArticlesContract.View {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArticlesContract.UserActionsListener userActionsListener;
    private ArticlesAdapter articlesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_articles, container, false);
        userActionsListener = new ArticlesPresenter(this, new ArticlesRepositoryImpl());
        articlesAdapter =new ArticlesAdapter();
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.articles_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userActionsListener.loadArticles(true);
            }
        });
        recyclerView = (RecyclerView) rootView.findViewById(R.id.articles_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(articlesAdapter);

        return rootView;
    }

    @Override
    public void showArticles(List<Article> articleList) {
            articlesAdapter.replaceData(articleList);
    }

    @Override
    public void onStart() {
        super.onStart();
        userActionsListener.loadArticles(false);
    }

    @Override
    public void showProgressIndicator(boolean active) {
        swipeRefreshLayout.setRefreshing(active);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Snackbar.make(getView(), "Failed to load articles", Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userActionsListener.loadArticles(true);
                    }
                });
    }

    @Override
    public void showArticleDetail(String articleId) {

    }
}
