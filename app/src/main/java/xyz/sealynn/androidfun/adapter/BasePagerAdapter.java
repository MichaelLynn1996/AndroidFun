package xyz.sealynn.androidfun.adapter;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * @author Michael Lynn
 * @title: BasePagerAdapter
 * @projectName AndroidFun
 * @description: TODO
 * @date 2019/4/172:44
 */
public class BasePagerAdapter<T> extends FragmentStatePagerAdapter {

    private List<T> Ts;

    public BasePagerAdapter(FragmentManager fm, List<T> Ts) {
        super(fm);
        this.Ts = Ts;
    }

    @Override
    public Fragment getItem(int position) {
            return null;
    }

    @Override
    public int getCount() {
        return Ts.size();
    }
}
