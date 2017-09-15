package com.alexkaz.myrepos.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alexkaz.myrepos.R;

public class UserInfoItemView extends LinearLayout {

    public static final int BIO = 0;
    public static final int LOCATION = 1;
    public static final int LINK = 2;
    public static final int COMPANY = 3;
    public static final int EMAIL = 4;


    private ImageView imageView;
    private TextView textView;

    public UserInfoItemView(Context context, int type, String text) {
        super(context);
        setOrientation(HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.user_info_item, this, true);
        imageView = findViewById(R.id.userItemIV);
        textView = findViewById(R.id.userItemTV);
        setValue(type, text);
    }

    public void setValue(int type, String text){
        switch (type){
            case BIO:
                imageView.setImageResource(R.drawable.ic_bio);
                break;
            case LOCATION:
                imageView.setImageResource(R.drawable.ic_location);
                break;
            case LINK:
                imageView.setImageResource(R.drawable.ic_links);
                break;
            case COMPANY:
                imageView.setImageResource(R.drawable.ic_company);
                break;
            case EMAIL:
                imageView.setImageResource(R.drawable.ic_letter);
                break;
            default:
                throw new IllegalArgumentException();
        }
        textView.setText(text);
    }

}
