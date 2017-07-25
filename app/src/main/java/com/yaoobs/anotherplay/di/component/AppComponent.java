package com.yaoobs.anotherplay.di.component;

import com.yaoobs.anotherplay.data.http.ApiService;
import com.yaoobs.anotherplay.di.module.AppModule;
import com.yaoobs.anotherplay.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    public ApiService getApiService();
}
