package cn.mlynn.androidfun.module.wx;

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
import cn.mlynn.androidfun.net.RequestApi;
import retrofit2.HttpException;

/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.wx
 * @ClassName: WxSearchPagingSource
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/29 1:34
 */
public class WxSearchPagingSource extends ListenableFuturePagingSource<Integer, Article> {

    @NonNull
    private Executor mBgExecutor = Executors.newSingleThreadExecutor();

    private Integer nextPageNumber;

    private String k;

    private int id;

    private RequestApi requestApi;

    public WxSearchPagingSource(RequestApi requestApi, String k, int id) {
        this.k = k;
        this.id = id;
        this.requestApi = requestApi;
    }

    @NotNull
    @Override
    public ListenableFuture<LoadResult<Integer, Article>> loadFuture(@NotNull LoadParams<Integer> loadParams) {
        // Start refresh at page 0 if undefined.
        nextPageNumber = loadParams.getKey();
        if (nextPageNumber == null) {
            nextPageNumber = 1;
        }

        ListenableFuture<LoadResult<Integer, Article>> pageFuture = Futures
                .transform(requestApi.searchWxArticles(id, nextPageNumber, k)
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
