package com.alexkaz.myrepos.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alexkaz.myrepos.MyApp;
import com.alexkaz.myrepos.R;
import com.alexkaz.myrepos.model.entities.RepoEntity;
import com.alexkaz.myrepos.model.entities.UserEntity;
import com.alexkaz.myrepos.presenter.UserReposPresenter;
import com.alexkaz.myrepos.ui.CircleTransform;
import com.alexkaz.myrepos.ui.UserInfoItemView;
import com.alexkaz.myrepos.ui.UserRepoRVAdapter;
import com.paginate.Paginate;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class UserReposActivity extends AppCompatActivity implements UserReposView {

    @Inject
    UserReposPresenter presenter;

    private View noConnView;
    private ProgressBar progressBar;
    private RecyclerView repoListRV;
    private UserRepoRVAdapter adapter;

    private ImageView userPhotoIV;
    private TextView userNameTV;
    private LinearLayout userInfoItemDiv;
    private TextView userFollowersCountTV;
    private TextView userReposCountTV;

    private boolean loadingInProgress = false;
    private boolean hasLoadedAllItems = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_repo_list);

        initComponents();
        presenter.loadUserInfo();
    }

    private void initComponents(){
        progressBar = findViewById(R.id.userReposPB);
        noConnView = findViewById(R.id.noConnLayout);

        userPhotoIV = findViewById(R.id.userPhotoIV);
        userNameTV = findViewById(R.id.userNameTV);
        userInfoItemDiv = findViewById(R.id.userInfoItemDiv);
        userFollowersCountTV = findViewById(R.id.userFollowersCountTV);
        userReposCountTV = findViewById(R.id.userReposCountTV);

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
        Picasso.with(this).load(user.getAvatarUrl()).transform(new CircleTransform()).into(userPhotoIV);

        if (user.getName() != null && !(user.getName().equals(""))){
            userNameTV.setText(user.getName());
        } else {
            userNameTV.setText(user.getLogin());
        }

        if (user.getBio() != null && !(user.getBio().equals(""))){
            userInfoItemDiv.addView(new UserInfoItemView(this, UserInfoItemView.BIO, user.getBio()));
        }
        if (user.getLocation() != null && !(user.getLocation().equals(""))){
            userInfoItemDiv.addView(new UserInfoItemView(this, UserInfoItemView.LOCATION, user.getLocation()));
        }
        if (user.getBlog() != null && !(user.getBlog().equals(""))){
            userInfoItemDiv.addView(new UserInfoItemView(this, UserInfoItemView.LINK, user.getBlog()));
        }

        userFollowersCountTV.setText(String.valueOf(user.getFollowers()));
        userReposCountTV.setText(String.valueOf(user.getPublicRepos()));
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
