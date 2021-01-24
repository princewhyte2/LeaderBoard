package com.example.leaderboard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm);
        this.mNumOfTabs = behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new LearningLeadersFragment();
            case 1 : return new SkillIQLeadersFragment();
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
