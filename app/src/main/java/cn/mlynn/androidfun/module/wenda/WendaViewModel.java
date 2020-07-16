/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.wenda
 * @ClassName: WendaViewModel
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/9 21:32
 */
package cn.mlynn.androidfun.module.wenda;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import cn.mlynn.androidfun.base.BaseViewModel;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.pagingsource.WendaPagingSource;

public class WendaViewModel extends BaseViewModel {

    private LiveData<PagingData<Article>> wendaLiveData;

    private Pager<Integer, Article> pager = new Pager<>(new PagingConfig(20), WendaPagingSource::new);

    public LiveData<PagingData<Article>> getWendaLiveData() {
        return wendaLiveData;
    }

    public void initWendaLiveData(Lifecycle lifecycle) {
        wendaLiveData = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager)
                , lifecycle);
    }
}
