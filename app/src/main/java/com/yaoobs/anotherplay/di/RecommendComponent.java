package com.yaoobs.anotherplay.di;


import com.yaoobs.anotherplay.ui.fragment.RecommendFragment;

import dagger.Component;

@Component(modules = RecommendModule.class)
public interface RecommendComponent {
    void inject(RecommendFragment fragment);
}
