package com.alexkaz.myrepos.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexkaz.myrepos.R;
import com.alexkaz.myrepos.model.entities.RepoEntity;

import java.util.ArrayList;
import java.util.List;

public class RepoRVAdapter extends RecyclerView.Adapter<RepoRVAdapter.UserRepoVH> {

    private List<RepoEntity> userRepos = new ArrayList<>();

    public RepoRVAdapter() {
    }

    public void add(List<RepoEntity> userRepos){
        if (userRepos != null){
            this.userRepos.addAll(userRepos);
        }
    }

    public void clear(){
        userRepos.clear();
    }

    @Override
    public UserRepoVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_repo_item, parent, false);
        return new UserRepoVH(v);
    }

    @Override
    public void onBindViewHolder(UserRepoVH holder, int position) {
        RepoEntity item = userRepos.get(position);

        holder.langTV.setText(item.getLanguage());
        holder.titleTV.setText(item.getName());
        holder.descriptionTV.setText(item.getDescription());
        holder.forksTV.setText(String.valueOf(item.getForksCount()));
        holder.starsTV.setText(String.valueOf(item.getStargazersCount()));
        holder.watchersTV.setText(String.valueOf(item.getWatchersCount()));
    }

    @Override
    public int getItemCount() {
        return userRepos.size();
    }

    static class UserRepoVH extends RecyclerView.ViewHolder {
        private TextView langTV,titleTV,descriptionTV,forksTV,starsTV,watchersTV;

        UserRepoVH(View v) {
            super(v);

            langTV = v.findViewById(R.id.langTV);
            titleTV = v.findViewById(R.id.titleTV);
            descriptionTV = v.findViewById(R.id.descriptionTV);
            forksTV = v.findViewById(R.id.forksTV);
            starsTV = v.findViewById(R.id.starsTV);
            watchersTV = v.findViewById(R.id.watchersTV);
        }
    }
}
