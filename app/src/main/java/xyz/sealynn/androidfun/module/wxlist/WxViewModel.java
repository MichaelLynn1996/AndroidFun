package xyz.sealynn.androidfun.module.wxlist;

import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import androidx.paging.RxPagedListBuilder;

import java.util.List;

import io.reactivex.Observable;
import xyz.sealynn.androidfun.dao.WxDao;
import xyz.sealynn.androidfun.model.wxarticle.WxActicle;

/**
 * Created by MichaelLynn on 2020/2/4 17:02
 * <p>
 * Email: sealynndev@gamil.com
 */
public class WxViewModel extends ViewModel {
    private WxDao wxDao;
    public final Observable<PagedList<WxActicle>> wxActicle;

    public WxViewModel(WxDao wxDao) {
        this.wxDao = wxDao;

        wxActicle = new RxPagedListBuilder<Integer, WxActicle>(wxDao.queryWxActicleByPage(),1).buildObservable();
    }
}
