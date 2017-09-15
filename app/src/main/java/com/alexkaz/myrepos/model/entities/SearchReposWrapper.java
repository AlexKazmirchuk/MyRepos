package com.alexkaz.myrepos.model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchReposWrapper {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<RepoEntity> items = null;

    public SearchReposWrapper() {
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<RepoEntity> getItems() {
        return items;
    }

    public void setItems(List<RepoEntity> items) {
        this.items = items;
    }

}
