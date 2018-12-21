package xyz.sealynn.androidfun.adapter;

import android.content.Context;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by SeaLynn0 on 2018/12/6 21:03
 * <p>
 * Email：sealynndev@gmail.com
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private List<Fragment> fragments;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragments) {
        this(fm);
        this.context = context;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
