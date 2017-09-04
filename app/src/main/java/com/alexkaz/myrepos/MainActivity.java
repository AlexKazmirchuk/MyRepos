package com.alexkaz.myrepos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alexkaz.myrepos.view.BasicAuthActivity;
import com.alexkaz.myrepos.view.UserReposActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view){
        if (view.getId() == R.id.userReposBtn){
            startActivity(new Intent(this,UserReposActivity.class));
        }
        if (view.getId() == R.id.authBtn){
            startActivity(new Intent(this,BasicAuthActivity.class));
        }
    }
}
