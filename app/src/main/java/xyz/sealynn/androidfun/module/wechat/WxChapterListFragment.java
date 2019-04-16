package xyz.sealynn.androidfun.module.wechat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.model.Result;
import xyz.sealynn.androidfun.model.WxActicle;
import xyz.sealynn.androidfun.model.WxChapter;
import xyz.sealynn.androidfun.net.RetrofitManager;

/**
 * @author Michael Lynn
 * @title: WxChapterListFragment
 * @projectName AndroidFun
 * @description: TODO
 * @date 2019/4/923:49
 */
public class WxChapterListFragment extends BaseLazyLoadListFragment {

    private static final String KEY = "chapter";
    private int page = 1;

    private WxChapter chapter;
    private List<WxActicle.DatasBean> datasBeans = new ArrayList<>();

    public WxChapterListFragment() {
    }

    @Override
    protected void loadData() {
        ListAdapter adapter = new ListAdapter();
        this.setListAdapter(adapter);
    }

    @Override
    protected void initData() {
        chapter = new Gson().fromJson(getArguments().getString(KEY), WxChapter.class);

        RetrofitManager
                .getInstance()
                .createReq()
                .getChapterArticles(chapter.getId(), page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<WxActicle>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<WxActicle> wxActicleResult) {
                        datasBeans.addAll(wxActicleResult.getData().getDatas());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Logger.d("onListItemClick");
        super.onListItemClick(l, v, position, id);

    }

    /**
     * fragment静态传值
     */
    public static WxChapterListFragment newInstance(WxChapter chapter) {
        WxChapterListFragment fragment = new WxChapterListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY, new Gson().toJson(chapter));
        fragment.setArguments(bundle);

//        fragment.setEmptyText(str);

        return fragment;
    }

    class ListAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public ListAdapter() {
            mInflater = LayoutInflater.from(getContext());
        }

        @Override
        public int getCount() {
            return datasBeans.size();
        }

        @Override
        public Object getItem(int position) {
            return datasBeans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_chapter, null);  //将布局转换成视图
                holder = new ViewHolder();
                holder.mTitle = convertView.findViewById(R.id.title);
                holder.mDate = convertView.findViewById(R.id.date);
                holder.like = convertView.findViewById(R.id.like);
                convertView.setTag(holder);
            } else {
                //ViewHolder被复用
                holder = (ViewHolder) convertView.getTag();
            }
            holder.mTitle.setText(datasBeans.get(position).getTitle());
            holder.mDate.setText((getString(R.string.time) + ":" + datasBeans.get(position).getNiceDate()));
            return convertView;
        }

        private class ViewHolder {
            AppCompatTextView mTitle;
            AppCompatTextView mDate;
            AppCompatImageButton like;
        }
    }
}
