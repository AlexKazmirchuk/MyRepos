package com.alexkaz.myrepos.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alexkaz.myrepos.R;

public class CustomToast extends Toast {

    private TextView textView;

    public CustomToast(Context context) {
        super(context);

        View view = LayoutInflater.from(context).inflate(R.layout.custom_toast_layout, new FrameLayout(context));
        textView = view.findViewById(R.id.customToastTV);
        this.setView(view);
        this.setDuration(Toast.LENGTH_SHORT);
    }

    public void showMessage(String message){
        textView.setText(message);
        this.show();
    }

}
