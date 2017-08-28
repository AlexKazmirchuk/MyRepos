package com.alexkaz.myrepos.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alexkaz.myrepos.MyApp;
import com.alexkaz.myrepos.R;
import com.alexkaz.myrepos.model.entities.RepoEntity;
import com.alexkaz.myrepos.model.entities.UserEntity;
import com.alexkaz.myrepos.presenter.UserReposPresenter;
import com.alexkaz.myrepos.ui.UserInfoView;
import com.alexkaz.myrepos.ui.UserRepoRVAdapter;
import com.paginate.Paginate;

import java.util.List;

import javax.inject.Inject;

public class UserReposActivity extends AppCompatActivity implements UserReposView {

    @Inject
    UserReposPresenter presenter;

    private View noConnView;
    private ProgressBar progressBar;
    private RecyclerView repoListRV;
    private UserRepoRVAdapter adapter;
    private UserInfoView userInfoView;

    private boolean loadingInProgress = false;
    private boolean hasLoadedAllItems = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_repo_list);

        configureActionBar();
        initComponents();
        presenter.loadUserInfo();
    }

    private void configureActionBar(){
        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }
    }

    private void initComponents(){
        progressBar = findViewById(R.id.userReposPB);
        noConnView = findViewById(R.id.noConnLayout);
        userInfoView = findViewById(R.id.userInfoView);

        initPresenter();
        initRecyclerView();
    }

    private void initPresenter() {
        ((MyApp)getApplication()).getMyComponent().inject(this);
        presenter.bindView(this);
    }

    private void initRecyclerView() {
        repoListRV = findViewById(R.id.repoListRV);
        adapter = new UserRepoRVAdapter();
        repoListRV.setLayoutManager(new LinearLayoutManager(this));
        repoListRV.setAdapter(adapter);

        Paginate.Callbacks callbacks = new Paginate.Callbacks() {
            @Override
            public void onLoadMore() {
                presenter.loadNextPage();
                loadingInProgress = true;
            }

            @Override
            public boolean isLoading() {
                return loadingInProgress;
            }

            @Override
            public boolean hasLoadedAllItems() {
                return hasLoadedAllItems;
            }
        };

        Paginate.with(repoListRV, callbacks)
                .setLoadingTriggerThreshold(1)
                .addLoadingListItem(false)
                .build();
    }

    @Override
    public void showUserInfo(UserEntity user) {
        userInfoView.setValues(user);
        userInfoView.show();
    }

    @Override
    public void showRepos(List<RepoEntity> userRepos) {
        if (userRepos.size() == 0 || userRepos.size() < 8){hasLoadedAllItems = true;}
        repoListRV.setVisibility(View.VISIBLE);
        adapter.add(userRepos);
        adapter.notifyDataSetChanged();
        loadingInProgress = false;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoConnectionMessage() {
        noConnView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideNoConnectionMessage() {
        noConnView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideRepos() {
        repoListRV.setVisibility(View.INVISIBLE);
    }
}
