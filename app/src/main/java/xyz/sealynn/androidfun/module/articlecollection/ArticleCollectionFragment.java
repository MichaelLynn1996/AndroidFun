package xyz.sealynn.androidfun.module.articlecollection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseFragment;
import xyz.sealynn.androidfun.databinding.FragmentArticleCollectionBinding;
import xyz.sealynn.androidfun.databinding.LayoutArticleBinding;
import xyz.sealynn.androidfun.model.ArticleCollection;

/**
 * Created by MichaelLynn on 2019/4/19 16:33
 * <p>
 * Email: sealynndev@gamil.com
 */
public class ArticleCollectionFragment extends BaseFragment<ArticleCollectionContract.Presenter
        , FragmentArticleCollectionBinding> implements ArticleCollectionContract.View {

//    @BindView(R.id.recycler_view)
//    RecyclerView recyclerView;
//    @BindView(R.id.refresh)
//    SwipeRefreshLayout refreshLayout;
//    @BindView(R.id.status_view)
//    MultipleStatusView statusView;

    private int page = 0;
    private List<ArticleCollection> list = new ArrayList<>();

    @Override
    protected FragmentArticleCollectionBinding initBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentArticleCollectionBinding.inflate(inflater, container, false);
    }

    @Override
    protected ArticleCollectionContract.Presenter createPresenter() {
        return new ArticleCollectionPresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initEvent() {
        getPresenter().getArticleCollectionList(page);
        getBinding().refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getArticleCollectionList(page);
            }
        });
    }

    @Override
    public void refreshList(List<ArticleCollection> data) {
        list.addAll(data);
        Objects.requireNonNull(getBinding().recyclerView.getAdapter()).notifyDataSetChanged();
        page++;
    }
}
