package com.startup.foot6like.test;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.startup.foot6like.fragments.Blind;
import com.startup.foot6like.fragments.Home;
import com.startup.foot6like.fragments.Support;

public class ViewPageAdapterJava extends FragmentStateAdapter {
    public ViewPageAdapterJava(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Home();
            case 1:
                return new Blind();
            case 2:
                return new Support();
            default:
                return new Home();
        }


    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
