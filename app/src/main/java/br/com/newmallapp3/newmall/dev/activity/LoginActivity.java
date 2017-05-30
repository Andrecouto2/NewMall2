package br.com.newmallapp3.newmall.dev.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import br.com.newmallapp3.newmall.R;
import br.com.newmallapp3.newmall.dev.api.APIClient;
import br.com.newmallapp3.newmall.dev.api.ServiceGenerator;
import br.com.newmallapp3.newmall.dev.entity.AccessToken;
import br.com.newmallapp3.newmall.dev.util.AlertUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.tilLogin)
    public TextInputLayout txtInputLogin;
    @BindView(R.id.tilPassword)
    public TextInputLayout txtInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    public void setOnRetrofitEvent(String login, String senha) {
        APIClient apiClient = ServiceGenerator.createService(APIClient.class);
        Call<AccessToken> accessTokenCall = apiClient.getNewAccessToken(login,senha,"password");

        accessTokenCall.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.body() != null) {
                    Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                    startActivity(intent);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        AlertUtil.createAlertDialog(LoginActivity.this, "Login", jObjError.getString("error_description")).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.e("",""+call.toString());
            }
        });
    }

    @OnClick(R.id.btnLogin)
    public void login(View v) {
        setOnRetrofitEvent(txtInputLogin.getEditText().getText().toString(), txtInputPassword.getEditText().getText().toString());
    }


}
