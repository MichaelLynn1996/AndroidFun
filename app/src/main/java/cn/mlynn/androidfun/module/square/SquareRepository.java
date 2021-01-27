/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.square
 * @ClassName: SquareRepository
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/23 18:02
 */
package cn.mlynn.androidfun.module.square;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import javax.inject.Inject;

import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.net.RequestApi;

class SquareRepository extends BaseRepository {

    private Pager<Integer, Article> pager;

    @Inject
    public SquareRepository(RequestApi requestApi) {
        super(requestApi);
        pager = new Pager<>(new PagingConfig(20), () -> new SquarePagingSource(getRequestApi()));
    }

    public LiveData<PagingData<Article>> initSquareLiveData(Lifecycle lifecycle) {
        return PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager)
                , lifecycle);
    }
}
