package xyz.sealynn.androidfun.net;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
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
        Response response = chain.proceed(chain.request());
        try {
            ResponseBody rspBody = response.body();
            JSONObject jsonObject = null;
            if (rspBody != null) {
                jsonObject = new JSONObject(InterceptorUtils.getRspData(rspBody));
            }
            int status = 0;
            if (jsonObject != null) {
                status = jsonObject.getInt("status");
            }
            if (status != 0) {
                throw new IOException(jsonObject.getString("msg"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException("parase data error");
        } catch (Exception e) {
            if (e instanceof IOException) {
                throw (IOException) e;
            } else e.printStackTrace();
        }

        return response;
    }
}
