package com.yaoobs.anotherplay.di.component;


import com.yaoobs.anotherplay.di.FragmentScope;
import com.yaoobs.anotherplay.di.module.AppModule;
import com.yaoobs.anotherplay.di.module.RecommendModule;
import com.yaoobs.anotherplay.ui.fragment.RecommendFragment;

import dagger.Component;

@FragmentScope
@Component(modules = RecommendModule.class,dependencies = AppComponent.class)
public interface RecommendComponent {
    void inject(RecommendFragment fragment);
}
