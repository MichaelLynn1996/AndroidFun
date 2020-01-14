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

//import com.classic.common.MultipleStatusView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseFragment;
import xyz.sealynn.androidfun.model.ArticleCollection;

/**
 * Created by MichaelLynn on 2019/4/19 16:33
 * <p>
 * Email: sealynndev@gamil.com
 */
public class ArticleCollectionFragment extends BaseFragment<ArticleCollectionContract.Presenter> implements ArticleCollectionContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;
//    @BindView(R.id.status_view)
//    MultipleStatusView statusView;

    private int page = 0;
    private List<ArticleCollection> list = new ArrayList<>();

    @Override
    protected ArticleCollectionContract.Presenter createPresenter() {
        return new ArticleCollectionPresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_article_collection;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(View rootView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RecyclerView.Adapter() {

            class ViewHolder extends RecyclerView.ViewHolder {

                @BindView(R.id.collect)
                AppCompatImageView collect;
                @BindView(R.id.title)
                AppCompatTextView title;

                public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    ButterKnife.bind(this,rootView);
                }
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_article, parent, false);
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ArticleCollection collection = list.get(position);
                ViewHolder viewHolder = (ViewHolder)holder;
                viewHolder.title.setText(collection.getTitle());
            }

            @Override
            public int getItemCount() {
                return list.size();
            }
        });
    }

    @Override
    protected void initEvent() {
        getPresenter().getArticleCollectionList(page);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getArticleCollectionList(page);
            }
        });
    }

    @Override
    public void refreshList(List<ArticleCollection> data) {
        list.addAll(data);
        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
        page++;
    }
}
