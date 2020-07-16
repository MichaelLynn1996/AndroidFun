package cn.mlynn.androidfun.net;

/**
 * Created by SeaLynn0 on 2019/3/5 0:30
 * <p>
 * Email：sealynndev@gmail.com
 */
public interface OnHttpCallBackListener {
    void onResponse(Object t, int what);

    void onErrors(Throwable e, int what);

    void onCompletes(int what);
}
