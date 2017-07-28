package com.yaoobs.anotherplay.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yaoobs.anotherplay.ui.bean.FragmentInfo;
import com.yaoobs.anotherplay.ui.fragment.CategoryFragment;
import com.yaoobs.anotherplay.ui.fragment.GamesFragment;
import com.yaoobs.anotherplay.ui.fragment.RankingFragment;
import com.yaoobs.anotherplay.ui.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<FragmentInfo> mFragments = new ArrayList<>(4);

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void  initFragments(){
        mFragments.add(new FragmentInfo("推荐",RecommendFragment.class));
        mFragments.add(new FragmentInfo ("排行", RankingFragment.class));
        mFragments.add(new FragmentInfo ("游戏", GamesFragment.class));
        mFragments.add(new FragmentInfo ("分类", CategoryFragment.class));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) mFragments.get(position).getFragment().newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return  null;
//        Fragment fragment = null;
//        switch (position){
//            case 0:
//                fragment = new RecommendFragment();
//                break;
//            case 1:
//                fragment = new RankingFragment();
//                break;
//            case 2:
//                fragment = new GamesFragment();
//                break;
//            case 3:
//                fragment = new CategoryFragment();
//                break;
//        }
//        return mFragments.get(0);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
