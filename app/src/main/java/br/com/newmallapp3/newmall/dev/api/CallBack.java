package br.com.newmallapp3.newmall.dev.api;

public interface CallBack<T> {
    void onSuccess(T response);
    void onError(String header, String message);
}
