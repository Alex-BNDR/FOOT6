package com.startup.foot6like

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.startup.foot6like.fragments.Blind
import com.startup.foot6like.fragments.Chat
import com.startup.foot6like.fragments.Home
import com.startup.foot6like.fragments.Support

class ViewPageAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Home()
            1 -> Blind()
            2 -> Chat()
            3 -> Support()
            else -> Home()
        }
    }

    override fun getItemCount(): Int {
        return 4
    }
}
