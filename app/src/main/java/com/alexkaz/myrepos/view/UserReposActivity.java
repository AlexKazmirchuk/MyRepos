package com.alexkaz.myrepos.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alexkaz.myrepos.R;
import com.alexkaz.myrepos.model.entities.RepoEntity;
import com.alexkaz.myrepos.ui.UserRepoRVAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserReposActivity extends AppCompatActivity {

    private RecyclerView repoListRV;
    private UserRepoRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_repo_list);

        init();
    }

    private void init(){
        repoListRV = findViewById(R.id.repoListRV);
        repoListRV.setLayoutManager(new LinearLayoutManager(this));
        List<RepoEntity> userRepos = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            RepoEntity repoEntity = new RepoEntity();
            repoEntity.setLanguage("java");
            repoEntity.setName("Tetris");
            repoEntity.setDescription("This app is very cool , it is the best");
            repoEntity.setForksCount(34);
            repoEntity.setStargazersCount(432);
            repoEntity.setWatchersCount(4);
            userRepos.add(repoEntity);
        }
        adapter = new UserRepoRVAdapter(userRepos);
        repoListRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
