package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by HQ on 6/12/2017.
 */
public class WordPagerAdapter extends FragmentStatePagerAdapter {
    public WordPagerAdapter(FragmentManager _fm) {
        super(_fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case (0):
                fragment = new PhrasesFragment();
                break;
            case (1):
                fragment = new ColorsFragment();
                break;
            case (2):
                fragment = new FamilyMemberFragment();
                break;
            case (3):
                fragment = new NumberFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String _name = "no title";
        switch (position) {
            case (0):
                _name= "phrases";
                break;
            case (1):
                _name = "Colors";
                break;
            case (2):
                _name = "Family Members";
                break;
            case (3):
                _name = "Numbers";
                break;
        }
        return _name;
    }
}