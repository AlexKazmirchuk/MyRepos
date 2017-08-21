package com.alexkaz.myrepos.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alexkaz.myrepos.MyApp;
import com.alexkaz.myrepos.R;
import com.alexkaz.myrepos.model.entities.RepoEntity;
import com.alexkaz.myrepos.presenter.UserReposPresenter;
import com.alexkaz.myrepos.ui.UserRepoRVAdapter;

import java.util.List;

import javax.inject.Inject;

public class UserReposActivity extends AppCompatActivity implements UserReposView {

    @Inject
    UserReposPresenter presenter;

    private RecyclerView repoListRV;
    private UserRepoRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_repo_list);

        initPresenter();
    }

    private void initPresenter() {
        ((MyApp)getApplication()).getMyComponent().inject(this);
        presenter.bindView(this);
    }

    @Override
    public void showRepos(List<RepoEntity> userRepos) {
        //todo impl later
    }

    @Override
    public void showLoading() {
        //todo impl later
    }

    @Override
    public void showErrorMessage() {
        //todo impl later
    }

    @Override
    public void showNoConnectionMessage() {
        //todo impl later
    }
}
