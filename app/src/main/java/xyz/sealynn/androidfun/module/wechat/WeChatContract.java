package xyz.sealynn.androidfun.module.wechat;

import java.util.List;

import xyz.sealynn.androidfun.base.BasePresenter;
import xyz.sealynn.androidfun.base.BaseView;
import xyz.sealynn.androidfun.model.WxChapter;

/**
 * Created by SeaLynn0 on 2018/12/10 19:38
 * <p>
 * Email：sealynndev@gmail.com
 */
public interface WeChatContract {

    interface View extends BaseView {
        void setLoading(Boolean isLoading);
        void setTags(List<WxChapter> chapters);
    }

    interface Presenter extends BasePresenter<View> {
        void getWeChatChapter();
    }
}
