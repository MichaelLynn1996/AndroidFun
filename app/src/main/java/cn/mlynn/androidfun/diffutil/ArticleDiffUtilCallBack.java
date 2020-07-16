/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun
 * @ClassName: ArticleDiffUtilCallBack
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/6 9:46
 */
package cn.mlynn.androidfun.diffutil;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import cn.mlynn.androidfun.model.wan.Article;

public class ArticleDiffUtilCallBack extends DiffUtil.ItemCallback<Article> {

    @Override
    public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
        return oldItem.equals(newItem);
    }
}
