package br.com.newmallapp3.newmall.dev.application;

import javax.inject.Singleton;

import br.com.newmallapp3.newmall.dev.api.ServiceGenerator;
import br.com.newmallapp3.newmall.dev.api.TokenService;
import br.com.newmallapp3.newmall.dev.viewmodel.LoginViewModel;
import constants.RestApi;
import dagger.Module;
import dagger.Provides;

/**
 * Things that live for the duration of the application and will be injected into whatever the
 * component defines
 */
@Module
public class ApplicationModule {
    public ApplicationModule() {
    }

    @Provides
    @Singleton
    LoginViewModel provideLoginViewModel(TokenService tokenService) {
        return new LoginViewModel(tokenService);
    }

    @Provides
    @Singleton
    ServiceGenerator provideRestService() {
        return new ServiceGenerator(RestApi.BASE_URL);
    }

    @Provides
    @Singleton
    TokenService provideTokenService(ServiceGenerator restClient) {
        return new TokenService(restClient);
    }
}
