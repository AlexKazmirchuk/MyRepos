package com.alexkaz.myrepos.model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchReposWrapper {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<SearchRepo> items = null;

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

    public List<SearchRepo> getItems() {
        return items;
    }

    public void setItems(List<SearchRepo> items) {
        this.items = items;
    }

}
