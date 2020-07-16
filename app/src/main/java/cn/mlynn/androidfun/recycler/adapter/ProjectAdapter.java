/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.recycler.adapter
 * @ClassName: ProjectAdapter
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/6 9:42
 */
package cn.mlynn.androidfun.recycler.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;

import cn.mlynn.androidfun.databinding.ItemLatestProjectBinding;
import cn.mlynn.androidfun.diffutil.ArticleDiffUtilCallBack;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.recycler.viewholder.ProjectViewHolder;

public class ProjectAdapter extends PagingDataAdapter<Article, ProjectViewHolder> {
    public ProjectAdapter() {
        super(new ArticleDiffUtilCallBack());
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProjectViewHolder(ItemLatestProjectBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
