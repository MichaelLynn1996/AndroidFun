package xyz.sealynn.androidfun.adapter;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import xyz.sealynn.androidfun.model.WxChapter;
import xyz.sealynn.androidfun.module.wechat.WxChapterListFragment;

/**
 * @author Michael Lynn
 * @title: WeChatChapterPagerAdapter
 * @projectName AndroidFun
 * @description: TODO
 * @date 2019/4/923:58
 */
public class WeChatChapterPagerAdapter extends FragmentStatePagerAdapter {

    private List<WxChapter> chapters = new ArrayList<>();

    public WeChatChapterPagerAdapter(FragmentManager fm, List<WxChapter> chapters) {
        super(fm);
        this.chapters.addAll(chapters);
    }

    @Override
    public Fragment getItem(int position) {
        Logger.d(position);
        return WxChapterListFragment.newInstance(chapters.get(position));
    }

    @Override
    public int getCount() {
        return chapters.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return chapters.get(position).getName();
    }
}
