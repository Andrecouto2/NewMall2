package br.com.newmallapp3.newmall.dev.application;


import android.app.Application;

public class AppApplication extends Application {
    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }
}