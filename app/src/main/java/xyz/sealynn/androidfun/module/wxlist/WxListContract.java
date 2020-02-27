package xyz.sealynn.androidfun.module.wxlist;

import java.util.List;

import xyz.sealynn.androidfun.base.BasePresenter;
import xyz.sealynn.androidfun.base.BaseView;
import xyz.sealynn.androidfun.model.wxarticle.WxActicle;

/**
 * Created by MichaelLynn on 2020/2/4 0:11
 * <p>
 * Email: sealynndev@gamil.com
 */
public interface WxListContract {

    interface View extends BaseView {
        boolean isLoading();
        void setLoading(Boolean isLoading);

        void loadDatas(List<WxActicle.DatasBean> datas);
    }

    interface Presenter extends BasePresenter<View> {
        void ChapterArticles(int id, int page);
    }
}
