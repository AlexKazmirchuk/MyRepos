package com.alexkaz.myrepos.model.services;

import com.alexkaz.myrepos.model.api.GitHubApi;
import com.alexkaz.myrepos.model.entities.RepoEntity;
import com.alexkaz.myrepos.model.entities.SearchReposWrapper;
import com.alexkaz.myrepos.model.entities.UserEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GitHubService {

    private GitHubApi gitHubApi;

    @Inject
    public GitHubService(GitHubApi gitHubApi) {
        this.gitHubApi = gitHubApi;
    }

    public Observable<List<RepoEntity>> getUserRepos(int page, int perPage){
        return gitHubApi.getUserRepos(page, perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SearchReposWrapper> getReposByName(String name){
        return gitHubApi.getReposByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SearchReposWrapper> getReposByName(String name, int page, int perPage){
        return gitHubApi.getReposByName(name, page, perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<UserEntity> getUser(){
        return gitHubApi.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
