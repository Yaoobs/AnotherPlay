package com.yaoobs.anotherplay.ui.bean;

import android.support.v4.app.Fragment;

/**
 * Created by yaoobs on 2017/4/20.
 */

public class FragmentInfo {
    private String title;

    private Class fragment;

    public FragmentInfo(String title, Class fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }
}
