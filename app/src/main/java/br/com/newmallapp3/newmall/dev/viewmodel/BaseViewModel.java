package br.com.newmallapp3.newmall.dev.viewmodel;

import android.os.Handler;

abstract class BaseViewModel {

    protected Handler mUiThreadHandler;

    public void onCreate(Handler handler) {
        mUiThreadHandler = handler;
    }

    public void onDestroy() {
        mUiThreadHandler = null;
    }
}