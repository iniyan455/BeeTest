package com.iniyan.krazybee.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.iniyan.krazybee.Fragment.DynamicFragment;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentPagerAdapter {


    private int mNumOfTabs;
    private ArrayList<String> tabTitle;


    public FragmentAdapter(FragmentManager fm, int NumOfTabs, ArrayList<String> tabTitle) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.tabTitle = tabTitle;
    }


    @Override
    public int getCount() {

          return mNumOfTabs;
    }


    @Override
    public Fragment getItem(int position) {

        return DynamicFragment.newInstance(position);


    }

}