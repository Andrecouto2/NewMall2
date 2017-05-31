package br.com.newmallapp3.newmall.dev.viewmodel;

import android.app.Activity;
import android.os.Handler;

public interface IViewModel {
    void onCreate(Handler handler);

    void onResume();

    void onPause();

    void onDestroy();

    void setCurrentActivity(Activity value);
}
