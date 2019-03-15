package xyz.sealynn.androidfun.net;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import xyz.sealynn.androidfun.model.Banner;
import xyz.sealynn.androidfun.model.HomeArticle;
import xyz.sealynn.androidfun.model.Login;
import xyz.sealynn.androidfun.model.Result;

/**
 * Created by SeaLynn0 on 2018/12/5 2:36
 * <p>
 * Email：sealynndev@gmail.com
 */
public interface RequestApi {

    String BASE_URL = "https://www.wanandroid.com/";

    /**
     * 登录接口
     *
     * @param username 用户名
     * @param password 密码
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<Result<Login>> login(@Field("username") String username, @Field("password") String password);

    /**
     * 获取首页banner的接口
     */
    @FormUrlEncoded
    @GET("banner/json")
    Observable<Result<List<Banner>>> getBanner();

    /**
     * 获取首页文章的接口
     * @param page 页数
     */
    @FormUrlEncoded
    @GET("article/list/{page}/json")
    Observable<Result<HomeArticle>> getHomeArticles(@Path("page") int page);

}
