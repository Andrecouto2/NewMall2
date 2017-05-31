package br.com.newmallapp3.newmall.dev.viewmodel;


import android.app.Activity;

import br.com.newmallapp3.newmall.dev.api.CallBack;
import br.com.newmallapp3.newmall.dev.api.TokenService;
import br.com.newmallapp3.newmall.dev.entity.AccessToken;
import br.com.newmallapp3.newmall.dev.listeners.BaseListener;

public class LoginViewModel extends BaseViewModel implements IViewModel{
    private static final String TAG = LoginViewModel.class.toString();
    private Listener mListener;
    private TokenService tokenService;

    public LoginViewModel(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public void clearListener() {
        mListener = null;
    }

    public void getToken(String username, String password, String grantType) {

        tokenService.getToken(username, password, grantType, new CallBack<AccessToken>() {
            @Override
            public void onSuccess(AccessToken response) {
                   mListener.onAccessToken(response);
            }

            @Override
            public void onError(String header, String message) {
                if (mListener != null) mListener.onError(header, message);
            }
        });

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void setCurrentActivity(Activity value) {

    }

    public interface Listener extends BaseListener {
          void onAccessToken(AccessToken accessToken);
    }
}
