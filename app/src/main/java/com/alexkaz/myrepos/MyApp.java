package com.alexkaz.myrepos;

import android.app.Application;

import com.alexkaz.myrepos.di.components.DaggerMyComponent;
import com.alexkaz.myrepos.di.components.MyComponent;

public class MyApp extends Application {

    private MyComponent myComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        myComponent = DaggerMyComponent.builder().build();
    }

    public MyComponent getMyComponent(){
        return myComponent;
    }
}
