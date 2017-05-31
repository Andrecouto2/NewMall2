package br.com.newmallapp3.newmall.dev.application;

import javax.inject.Singleton;

import br.com.newmallapp3.newmall.dev.activity.LoginActivity;
import dagger.Component;

/**
 * Defines injections at the application level
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(LoginActivity activity);
}