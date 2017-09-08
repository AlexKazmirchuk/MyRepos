package com.alexkaz.myrepos;

import android.app.Application;

import com.alexkaz.myrepos.di.components.DaggerMyComponent;
import com.alexkaz.myrepos.di.components.MyComponent;
import com.alexkaz.myrepos.di.modules.AppModule;

public class MyApp extends Application {

    private MyComponent myComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        myComponent = DaggerMyComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public void recreateMyComponent(){
        myComponent = DaggerMyComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public MyComponent getMyComponent(){
        return myComponent;
    }
}
