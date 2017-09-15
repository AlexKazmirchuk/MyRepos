package com.alexkaz.myrepos.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.alexkaz.myrepos.MyApp;
import com.alexkaz.myrepos.R;
import com.alexkaz.myrepos.model.entities.RepoEntity;
import com.alexkaz.myrepos.presenter.SearchReposPresenter;
import com.alexkaz.myrepos.ui.CustomToast;
import com.alexkaz.myrepos.ui.RepoRVAdapter;
import com.paginate.Paginate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchReposActivity extends AppCompatActivity implements SearchReposView {

    @Inject
    SearchReposPresenter presenter;

    private ImageButton searchBtn;
    private EditText searchET;
    private RecyclerView searchReposRV;
    private RepoRVAdapter adapter;
    private ProgressBar searchReposPB;

    private boolean loadingInProgress = false;
    private boolean hasLoadedAllItems = false;
    private boolean searchBtnPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_repos);

        configureActionBar();
        initComponents();
    }

    private void configureActionBar(){
        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setTitle("Search");
        }
    }

    private void initComponents(){
        searchET = findViewById(R.id.searchET);
        searchReposPB = findViewById(R.id.searchReposPB);

        initPresenter();
        initRecyclerView();
        initButton();
    }

    private void initPresenter(){
        ((MyApp)getApplication()).getMyComponent().inject(this);
        presenter.bindView(this);
    }

    private void initRecyclerView(){
        searchReposRV = findViewById(R.id.searchReposRV);
        adapter = new RepoRVAdapter();
        searchReposRV.setLayoutManager(new LinearLayoutManager(this));
        searchReposRV.setAdapter(adapter);

        Paginate.Callbacks callbacks = new Paginate.Callbacks() {
            @Override
            public void onLoadMore() {
                if (searchBtnPressed){
                    presenter.load();
                    loadingInProgress = true;
                }
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

        Paginate.with(searchReposRV, callbacks)
                .setLoadingTriggerThreshold(1)
                .addLoadingListItem(false)
                .build();
    }

    private void initButton() {
        searchBtn = findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(event ->{
            presenter.search();
            searchBtnPressed = true;
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) adapter.getItems());
        outState.putBoolean("progressBar_showed", searchReposPB.isShown());
        outState.putBoolean("loadingInProgress",loadingInProgress);
        outState.putBoolean("hasLoadedAllItems",hasLoadedAllItems);
        outState.putBoolean("searchBtnPressed",searchBtnPressed);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);

        adapter.add(state.getParcelableArrayList("list"));
        adapter.notifyDataSetChanged();
        loadingInProgress = state.getBoolean("loadingInProgress");
        hasLoadedAllItems = state.getBoolean("hasLoadedAllItems");
        searchBtnPressed = state.getBoolean("searchBtnPressed");
        if (state.getBoolean("progressBar_showed", false)){
            showLoading();
        } else {
            hideLoading();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.reset();
    }

    @Override
    public String getQueryText() {
        return searchET.getText().toString();
    }

    @Override
    public void showRepos(List<RepoEntity> userRepos) {
        if (userRepos != null){
            if (userRepos.size() == 0 || userRepos.size() < 8){hasLoadedAllItems = true;}
            adapter.add(userRepos);
        }
        searchReposRV.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        loadingInProgress = false;
    }

    @Override
    public void clearUpList() {
        adapter.clear();
        searchReposRV.setVisibility(View.INVISIBLE);
        hasLoadedAllItems = false;
    }

    @Override
    public void showLoading() {
        searchReposPB.setVisibility(View.VISIBLE);
    }

    @Override
    public void showWarningMessage(String message) {
        new CustomToast(this).showMessage(message);
    }

    @Override
    public void hideLoading() {
        searchReposPB.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideRepos() {
        searchReposRV.setVisibility(View.INVISIBLE);
    }
}
