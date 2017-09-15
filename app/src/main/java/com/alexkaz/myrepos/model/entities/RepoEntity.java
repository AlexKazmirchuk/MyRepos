package com.alexkaz.myrepos.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RepoEntity implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("description")
    private String description;

    @SerializedName("size")
    private int size;

    @SerializedName("stargazers_count")
    private int stargazersCount;

    @SerializedName("watchers_count")
    private int watchersCount;

    @SerializedName("forks_count")
    private int forksCount;

    @SerializedName("language")
    private String language;

    public RepoEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public int getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(int watchersCount) {
        this.watchersCount = watchersCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    private RepoEntity(Parcel in) {
        id = in.readInt();
        name = in.readString();
        fullName = in.readString();
        description = in.readString();
        size = in.readInt();
        stargazersCount = in.readInt();
        watchersCount = in.readInt();
        forksCount = in.readInt();
        language = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(fullName);
        dest.writeString(description);
        dest.writeInt(size);
        dest.writeInt(stargazersCount);
        dest.writeInt(watchersCount);
        dest.writeInt(forksCount);
        dest.writeString(language);
    }

    public static final Parcelable.Creator<RepoEntity> CREATOR = new Parcelable.Creator<RepoEntity>() {
        @Override
        public RepoEntity createFromParcel(Parcel in) {
            return new RepoEntity(in);
        }

        @Override
        public RepoEntity[] newArray(int size) {
            return new RepoEntity[size];
        }
    };
}
