package com.example.plasma.Adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.plasma.Fragments.HomeFragments.WhereFragment;
import com.example.plasma.Fragments.HomeFragments.HowFragment;
import com.example.plasma.Fragments.HomeFragments.WhyFragment;
import com.example.plasma.Fragments.HomeFragments.WhoFragment;

public class ViewPagerAdapterForHomePage extends FragmentPagerAdapter {
    public ViewPagerAdapterForHomePage(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.d("ViewPager" , "called");
        if (position == 0){
            return new WhoFragment();
        }
        else if (position == 1){
            return new HowFragment();
        }
        else if (position == 2){
            return new WhyFragment();
        }
        return new WhereFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }
}
