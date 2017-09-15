package com.alexkaz.myrepos.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserEntity implements Parcelable {

    @SerializedName("login")
    private String login;

    @SerializedName("id")
    private int id;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private String location;

    @SerializedName("email")
    private String email;

    @SerializedName("bio")
    private String bio;

    @SerializedName("blog")
    private String blog;

    @SerializedName("public_repos")
    private int publicRepos;

    @SerializedName("public_gists")
    private int publicGists;

    @SerializedName("followers")
    private int followers;

    @SerializedName("following")
    private int following;

    @SerializedName("company")
    private String company;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
    }

    public int getPublicGists() {
        return publicGists;
    }

    public void setPublicGists(int publicGists) {
        this.publicGists = publicGists;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    private UserEntity(Parcel in) {
        login = in.readString();
        id = in.readInt();
        avatarUrl = in.readString();
        name = in.readString();
        location = in.readString();
        email = in.readString();
        bio = in.readString();
        blog = in.readString();
        publicRepos = in.readInt();
        publicGists = in.readInt();
        followers = in.readInt();
        following = in.readInt();
        company = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeInt(id);
        dest.writeString(avatarUrl);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(email);
        dest.writeString(bio);
        dest.writeString(blog);
        dest.writeInt(publicRepos);
        dest.writeInt(publicGists);
        dest.writeInt(followers);
        dest.writeInt(following);
        dest.writeString(company);
    }

    public static final Parcelable.Creator<UserEntity> CREATOR = new Parcelable.Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel in) {
            return new UserEntity(in);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };
}