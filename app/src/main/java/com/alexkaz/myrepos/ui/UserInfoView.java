package com.alexkaz.myrepos.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alexkaz.myrepos.R;
import com.alexkaz.myrepos.model.entities.UserEntity;
import com.squareup.picasso.Picasso;

public class UserInfoView extends LinearLayout {

    private ImageView userPhotoIV;
    private TextView userNameTV;
    private LinearLayout userInfoItemDiv;
    private TextView userFollowersCountTV;
    private TextView userReposCountTV;

    private UserEntity user;

    public UserInfoView(Context context) {
        super(context);
        init(context);
    }

    public UserInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        setVisibility(INVISIBLE);
        setOrientation(VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.user_info_layout, this, true);

        userPhotoIV = findViewById(R.id.userPhotoIV);
        userNameTV = findViewById(R.id.userNameTV);
        userInfoItemDiv = findViewById(R.id.userInfoItemDiv);
        userFollowersCountTV = findViewById(R.id.userFollowersCountTV);
        userReposCountTV = findViewById(R.id.userReposCountTV);
    }

    public void setValues(UserEntity user){
        this.user = user;
        Picasso.with(getContext()).load(user.getAvatarUrl()).transform(new CircleTransform()).into(userPhotoIV);

        if (user.getName() != null && !(user.getName().equals(""))){
            userNameTV.setText(user.getName());
        } else {
            userNameTV.setText(user.getLogin());
        }

        userInfoItemDiv.removeAllViews();

        if (user.getBio() != null && !(user.getBio().equals(""))){
            userInfoItemDiv.addView(new UserInfoItemView(getContext(), UserInfoItemView.BIO, user.getBio()));
        }
        if (user.getLocation() != null && !(user.getLocation().equals(""))){
            userInfoItemDiv.addView(new UserInfoItemView(getContext(), UserInfoItemView.LOCATION, user.getLocation()));
        }
        if (user.getBlog() != null && !(user.getBlog().equals(""))){
            userInfoItemDiv.addView(new UserInfoItemView(getContext(), UserInfoItemView.LINK, user.getBlog()));
        }
        if (user.getCompany() != null && !(user.getCompany().equals(""))){
            userInfoItemDiv.addView(new UserInfoItemView(getContext(), UserInfoItemView.COMPANY, user.getCompany()));
        }
        if (user.getEmail() != null && !(user.getEmail().equals(""))){
            userInfoItemDiv.addView(new UserInfoItemView(getContext(), UserInfoItemView.EMAIL, user.getEmail()));
        }

        userFollowersCountTV.setText(String.valueOf(user.getFollowers()));
        userReposCountTV.setText(String.valueOf(user.getPublicRepos()));
    }

    public UserEntity getValues(){
        return user;
    }

    public void show(){
        this.setVisibility(VISIBLE);
    }

}
