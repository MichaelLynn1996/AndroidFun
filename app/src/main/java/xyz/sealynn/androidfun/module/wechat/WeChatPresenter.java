package xyz.sealynn.androidfun.module.wechat;

import com.orhanobut.logger.Logger;

import java.util.List;

import xyz.sealynn.androidfun.base.BasePresenterImpl;
import xyz.sealynn.androidfun.model.Result;
import xyz.sealynn.androidfun.model.WxChapter;
import xyz.sealynn.androidfun.net.RetrofitManager;
import xyz.sealynn.androidfun.utils.ToastUtils;

/**
 * Created by SeaLynn0 on 2018/12/10 19:40
 * <p>
 * Email：sealynndev@gmail.com
 */
public class WeChatPresenter extends BasePresenterImpl<WeChatContract.View> implements WeChatContract.Presenter {

    private static final int GET_WEHCAT_CHAPTER = 0;

//    private MutableLiveData<List<WxChapter>> mWeChatChapters = new MutableLiveData<>();

    public WeChatPresenter(WeChatContract.View view) {
        super(view);
    }

    @Override
    public void getWeChatChapter() {
        getView().setLoading(true);
        domine(RetrofitManager.getInstance().createReq().getWeChatChapter(), GET_WEHCAT_CHAPTER);
    }

    @Override
    public void onResponse(Object t, int what) {
        switch (what) {
            case GET_WEHCAT_CHAPTER:
                Result<List<WxChapter>> requestBody = Result.cast(t);
                Logger.d(requestBody);
                if (requestBody.getErrorCode() == 0) {
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
                break;
        }
    }

//    public MutableLiveData<List<WxChapter>> getmWeChatChapters() {
//        return mWeChatChapters;
//    }
}
