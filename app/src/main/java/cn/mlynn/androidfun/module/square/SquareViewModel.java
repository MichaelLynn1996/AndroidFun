/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.square
 * @ClassName: SquareViewModel
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/10 21:34
 */
package cn.mlynn.androidfun.module.square;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import cn.mlynn.androidfun.base.BaseViewModel;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.pagingsource.SquarePagingSource;

public class SquareViewModel extends BaseViewModel {

    private LiveData<PagingData<Article>> squareLiveData;

    private Pager<Integer, Article> pager = new Pager<>(new PagingConfig(20), SquarePagingSource::new);

    public LiveData<PagingData<Article>> getSquareLiveData() {
        if (squareLiveData == null){
            throw new RuntimeException("squareLiveData not been initialized");
        }
        return squareLiveData;
    }

    public void initSquareLiveData(Lifecycle lifecycle) {
        squareLiveData = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager)
                , lifecycle);
    }
}
