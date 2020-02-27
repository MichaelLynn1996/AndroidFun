package xyz.sealynn.androidfun.net;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import xyz.sealynn.androidfun.model.ArticleCollection;
import xyz.sealynn.androidfun.model.Banner;
import xyz.sealynn.androidfun.model.ArticleRoot;
import xyz.sealynn.androidfun.model.User;
import xyz.sealynn.androidfun.model.Result;
import xyz.sealynn.androidfun.model.WebCollection;
import xyz.sealynn.androidfun.model.wxarticle.WxActicle;
import xyz.sealynn.androidfun.model.wxarticle.WxChapter;

/**
 * Created by SeaLynn0 on 2018/12/5 2:36
 * <p>
 * Email：sealynndev@gmail.com
 */
public interface RequestApi {

    String BASE_URL = "https://www.wanandroid.com/";

    int SUCC = 0;
    int LOGIN_EXPIRE = -1001;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<Result<User>> login(
            @Field("username") String username,
            @Field("password") String password);

    /**
     * 注册
     *
     * @param username   用户名
     * @param password   密码
     * @param repassword 重新输入密码
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<Result<User>> register(
            @Field("username") String username,
            @Field("password") String password,
            @Field("repassword") String repassword);

    /**
     * 注销
     */
    @GET("user/logout/json")
    Observable<Result> logout();

    /**
     * 获取首页banner的接口
     */
    @FormUrlEncoded
    @GET("banner/json")
    Observable<Result<List<Banner>>> getBanner();

    /**
     * 获取首页文章的接口
     *
     * @param page 页数
     */
    @FormUrlEncoded
    @GET("article/list/{page}/json")
    Observable<Result<ArticleRoot>> getHomeArticles(@Path("page") int page);

    /**
     * @return 公众号作者
     */
    @GET("wxarticle/chapters/json")
    Observable<Result<List<WxChapter>>> getWeChatChapter();

    /**
     * @param id   公众号id
     * @param page 页数
     * @return 某个公众号历史数据
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<Result<WxActicle>> getChapterArticles(@Path("id") int id, @Path("page") int page);

    /**
     * @return 收藏网站列表
     */
    @GET("lg/collect/usertools/json")
    Observable<Result<List<WebCollection>>> getWebCollection();

    /**
     * @param id 网站id
     * @return 删除结果
     */
    @FormUrlEncoded
    @POST("lg/collect/deletetool/json")
    Observable<Result> deleteTool(@Field("id") int id);

    /**
     * @return 收藏文章列表
     */
    @GET("lg/collect/list/{page}/json")
    Observable<Result<ArticleRoot<List<ArticleCollection>>>> getArticleCollection(@Path("page") int page);
}
