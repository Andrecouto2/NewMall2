package br.com.newmallapp3.newmall.dev.api;


import br.com.newmallapp3.newmall.dev.entity.AccessToken;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIClient {

    @FormUrlEncoded
    @POST("/Token")
    Call<AccessToken> getNewAccessToken(
            @Field("username") String username,
            @Field("password") String password,
            @Field("grant_type") String grantType);
}
