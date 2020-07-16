/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.pagingsource
 * @ClassName: HomePagingSource
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/11 15:37
 */
package cn.mlynn.androidfun.pagingsource;

import androidx.annotation.NonNull;
import androidx.paging.ListenableFuturePagingSource;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.model.wan.ArticleRoot;
import cn.mlynn.androidfun.model.wan.Result;
import cn.mlynn.androidfun.net.RetrofitManager;
import retrofit2.HttpException;

public class HomePagingSource extends ListenableFuturePagingSource<Integer, Article> {

    @NonNull
    private Executor mBgExecutor = Executors.newSingleThreadExecutor();

    Integer nextPageNumber;

    @NotNull
    @Override
    public ListenableFuture<LoadResult<Integer, Article>> loadFuture(@NotNull LoadParams<Integer> loadParams) {
        // Start refresh at page 0 if undefined.
        nextPageNumber = loadParams.getKey();
        if (nextPageNumber == null) {
            nextPageNumber = 0;
        }

        ListenableFuture<LoadResult<Integer, Article>> pageFuture = Futures
                .transform(RetrofitManager.getInstance().createReq().getHomeArticles(nextPageNumber)
                        , this::toLoadResult
                        , mBgExecutor);

        ListenableFuture<LoadResult<Integer, Article>> partialLoadResultFuture = Futures.catching(
                pageFuture, HttpException.class,
                LoadResult.Error::new, mBgExecutor);

        return Futures.catching(partialLoadResultFuture,
                IOException.class, LoadResult.Error::new, mBgExecutor);
    }

    private LoadResult<Integer, Article> toLoadResult(
            @NonNull Result<ArticleRoot<Article>> response) {
        return new LoadResult.Page<>(
                response.getData().getDatas(),
                null, // Only paging forward.
                response.getData().getCurPage() == response.getData().getTotal() ? null : ++nextPageNumber,
                LoadResult.Page.COUNT_UNDEFINED,
                LoadResult.Page.COUNT_UNDEFINED);
    }
}
