package com.iniyan.krazybee.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.iniyan.krazybee.Fragment.DynamicFragment;
import com.iniyan.krazybee.Model.Album;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {


    private int mNumOfTabs;
    private ArrayList<String> tabTitle;

    private List<Album> albumslist;

    public FragmentAdapter(FragmentManager fm, int NumOfTabs, ArrayList<String> tabTitle, List<Album> albumslist) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.tabTitle = tabTitle;
        this.albumslist=albumslist;
    }


    @Override
    public int getCount() {

          return mNumOfTabs;
    }


    @Override
    public Fragment getItem(int position) {

        return DynamicFragment.newInstance(position,albumslist);


    }

}