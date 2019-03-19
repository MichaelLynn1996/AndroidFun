package xyz.sealynn.androidfun.net.interceptor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import xyz.sealynn.androidfun.utils.InterceptorUtils;

/**
 * Created by SeaLynn0 on 2018/12/5 2:46
 * <p>
 * Email：sealynndev@gmail.com
 */
public class RspCheckInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request().newBuilder()

                .addHeader("Connection", "close").build();

        return chain.proceed(request);
    }
}
