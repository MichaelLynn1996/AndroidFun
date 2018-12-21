package xyz.sealynn.androidfun.net;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import xyz.sealynn.androidfun.model.LoginData;
import xyz.sealynn.androidfun.model.Result;

/**
 * Created by SeaLynn0 on 2018/12/5 2:36
 * <p>
 * Email：sealynndev@gmail.com
 */
public interface AccountApi {

    @FormUrlEncoded
    @POST("/user/login")
    Observable<Result<LoginData>> login(@Path("username") String username, @Field("password") String password);

}
