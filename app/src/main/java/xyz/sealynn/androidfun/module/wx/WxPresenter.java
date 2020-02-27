package xyz.sealynn.androidfun.module.wx;

import com.orhanobut.logger.Logger;

import java.util.List;

import xyz.sealynn.androidfun.base.BasePresenterImpl;
import xyz.sealynn.androidfun.model.Result;
import xyz.sealynn.androidfun.model.wxarticle.WxChapter;
import xyz.sealynn.androidfun.net.RequestApi;
import xyz.sealynn.androidfun.net.RetrofitManager;
import xyz.sealynn.androidfun.utils.ToastUtils;

/**
 * Created by SeaLynn0 on 2018/12/10 19:40
 * <p>
 * Email：sealynndev@gmail.com
 */
public class WxPresenter extends BasePresenterImpl<WxContract.View> implements WxContract.Presenter {

    private static final int GET_WECHAT_CHAPTER = 0;

//    private MutableLiveData<List<WxChapter>> mWeChatChapters = new MutableLiveData<>();

    WxPresenter(WxContract.View view) {
        super(view);
    }

    @Override
    public void getWxChapter() {
        getView().setLoading(true);
        domine(RetrofitManager.getInstance().createReq().getWeChatChapter(), GET_WECHAT_CHAPTER);
    }

    @Override
    public void onResponse(Object t, int what) {
        if (what == GET_WECHAT_CHAPTER) {
            Result<List<WxChapter>> requestBody = Result.cast(t);
            Logger.d(requestBody);
            if (requestBody.getErrorCode() == RequestApi.SUCC) {
//                    ToastUtils.shortToast(getView().getContext(), "登陆成功");
//                    Result.putResultBean(getView().getContext(), "login", requestBody);
//                    Activity activity = (Activity) getView().getContext();
//                    activity.finish();
//                    mWeChatChapters.postValue(requestBody.getData());
                getView().setTags(requestBody.getData());
            } else {
                ToastUtils.shortToast(getView().getContext(), requestBody.getErrorMsg());
            }
            getView().setLoading(false);
        }
    }

//    public MutableLiveData<List<WxChapter>> getmWeChatChapters() {
//        return mWeChatChapters;
//    }
}
