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
import com.alexkaz.myrepos.presenter.UserReposPresenter;
import com.alexkaz.myrepos.ui.UserRepoRVAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UserReposActivity extends AppCompatActivity implements UserReposView {

    @Inject
    UserReposPresenter presenter;

    private View noConnView;
    private ProgressBar progressBar;
    private RecyclerView repoListRV;
    private UserRepoRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_repo_list);

        initComponents();
    }

    private void initComponents(){
        progressBar = findViewById(R.id.userReposPB);
        noConnView = findViewById(R.id.noConnLayout);
        initRecyclerView();
        initPresenter();
    }

    private void initPresenter() {
        ((MyApp)getApplication()).getMyComponent().inject(this);
        presenter.bindView(this);
        presenter.loadRepos();
    }

    private void initRecyclerView() {
        repoListRV = findViewById(R.id.repoListRV);
        adapter = new UserRepoRVAdapter(new ArrayList<>());
        repoListRV.setLayoutManager(new LinearLayoutManager(this));
        repoListRV.setAdapter(adapter);
    }

    @Override
    public void showRepos(List<RepoEntity> userRepos) {
        repoListRV.setVisibility(View.VISIBLE);
        adapter.add(userRepos);
        adapter.notifyDataSetChanged();
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
