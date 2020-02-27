package xyz.sealynn.androidfun.module.wx;

import java.util.List;

import xyz.sealynn.androidfun.base.BasePresenter;
import xyz.sealynn.androidfun.base.BaseView;
import xyz.sealynn.androidfun.model.wxarticle.WxChapter;

/**
 * Created by SeaLynn0 on 2018/12/10 19:38
 * <p>
 * Email：sealynndev@gmail.com
 */
public interface WxContract {

    interface View extends BaseView {
        void setLoading(Boolean isLoading);
        void setTags(List<WxChapter> chapters);
    }

    interface Presenter extends BasePresenter<View> {
        void getWxChapter();
    }
}
