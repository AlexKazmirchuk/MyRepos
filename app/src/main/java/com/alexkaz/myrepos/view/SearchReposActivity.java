package com.alexkaz.myrepos.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alexkaz.myrepos.MyApp;
import com.alexkaz.myrepos.R;
import com.alexkaz.myrepos.model.entities.RepoEntity;
import com.alexkaz.myrepos.presenter.SearchReposPresenter;
import com.alexkaz.myrepos.ui.RepoRVAdapter;
import com.paginate.Paginate;

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
    private View noConnView;

    private boolean loadingInProgress = false;
    private boolean hasLoadedAllItems = false;
    private boolean searchBtnPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_repos);

        initComponents();
    }

    private void initComponents(){
        searchET = findViewById(R.id.searchET);
        searchReposPB = findViewById(R.id.searchReposPB);
        noConnView = findViewById(R.id.noConnLayout);

        initPresenter();
        initRecyclerView();
        initButton();
    }

    private void initPresenter(){
        ((MyApp)getApplication()).getMyComponent().inject(this);
        presenter.bindView(this);
    }

    private void initRecyclerView(){
        //todo implement later
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
        adapter.notifyDataSetChanged();
        hasLoadedAllItems = false;
    }

    @Override
    public void showLoading() {
        searchReposPB.setVisibility(View.VISIBLE);
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
        searchReposPB.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideNoConnectionMessage() {
        noConnView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideRepos() {
        searchReposRV.setVisibility(View.INVISIBLE);
    }
}
