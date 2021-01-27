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
 * @ClassName: ChapterPagingSource
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/29 0:21
 */
public class WxPagingSource extends ListenableFuturePagingSource<Integer, Article> {

    @NonNull
    private Executor mBgExecutor = Executors.newSingleThreadExecutor();

    private Integer nextPageNumber;

    private int id;

    private RequestApi requestApi;

    public WxPagingSource(RequestApi requestApi, int id) {
        this.id = id;
        this.requestApi = requestApi;
    }

    @NotNull
    @Override
    public ListenableFuture<LoadResult<Integer, Article>> loadFuture(@NotNull LoadParams<Integer> loadParams) {
        // Start refresh at page 0 if undefined.
        nextPageNumber = loadParams.getKey();
        if (nextPageNumber == null) {
            nextPageNumber = 0;
        }

        ListenableFuture<LoadResult<Integer, Article>> pageFuture = Futures
                    .transform(requestApi.getWxArticles(id, nextPageNumber)
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
