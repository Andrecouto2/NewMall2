package br.com.newmallapp3.newmall.dev.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import br.com.newmallapp3.newmall.R;
import br.com.newmallapp3.newmall.dev.api.APIClient;
import br.com.newmallapp3.newmall.dev.api.ServiceGenerator;
import br.com.newmallapp3.newmall.dev.application.AppApplication;
import br.com.newmallapp3.newmall.dev.entity.AccessToken;
import br.com.newmallapp3.newmall.dev.util.AlertUtil;
import br.com.newmallapp3.newmall.dev.viewmodel.LoginViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends ViewModelActivity implements LoginViewModel.Listener {

    private static final String TAG = LoginActivity.class.toString();
    @Inject
    LoginViewModel mLoginViewModel;
    @BindView(R.id.tilLogin)
    public TextInputLayout txtInputLogin;
    @BindView(R.id.tilPassword)
    public TextInputLayout txtInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((AppApplication) getApplicationContext()).getComponent().inject(LoginActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void createViewModel() {
        mViewModel = mLoginViewModel;
        mLoginViewModel.setListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLoginViewModel.setListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLoginViewModel.clearListener();
    }

    @OnClick(R.id.btnLogin)
    public void login(View v) {
       mLoginViewModel.getToken(txtInputLogin.getEditText().getText().toString(), txtInputPassword.getEditText().getText().toString(), "password");
    }

    @Override
    public void onError(String header, String message) {
        AlertUtil.createAlertDialog(LoginActivity.this, header, message).show();
    }

    @Override
    public void onAccessToken(AccessToken accessToken) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
