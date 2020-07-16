package cn.mlynn.androidfun.net;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import cn.mlynn.androidfun.model.wan.Children;
import cn.mlynn.androidfun.model.wan.HotKey;
import cn.mlynn.androidfun.model.wan.NavigationRoot;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.model.wan.ArticleCollection;
import cn.mlynn.androidfun.model.wan.ArticleRoot;
import cn.mlynn.androidfun.model.wan.Banner;
import cn.mlynn.androidfun.model.wan.Result;
import cn.mlynn.androidfun.model.wan.User;
import cn.mlynn.androidfun.model.wan.WebCollection;

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
    Observable<Result<String>> logout();

    /**
     * 获取首页banner的接口
     */
    @GET("banner/json")
    Observable<Result<List<Banner>>> getBanner();

    /**
     * 获取首页置顶文章的接口
     */
    @GET("article/top/json")
    Observable<Result<List<Article>>> getTopArticle();

    /**
     * 获取首页文章的接口
     *
     * @param page 页数
     */
    @GET("article/list/{page}/json")
    ListenableFuture<Result<ArticleRoot<Article>>> getHomeArticles(@Path("page") int page);

    /**
     *
     * @param page 页数
     * @return 最新项目tab
     */
    @GET("article/listproject/{page}/json")
    ListenableFuture<Result<ArticleRoot<Article>>> getLatestProject(@Path("page") int page);

    /**
     * @return 公众号作者
     */
    @GET("wxarticle/chapters/json")
    Observable<Result<ArticleRoot<Article>>> getWXChapter();

    /**
     * @param id   公众号id
     * @param page 页数
     * @return 某个公众号历史数据
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<Result<Article>> getChapterArticles(@Path("id") int id, @Path("page") int page);

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

    /**
     *
     * @param page 页码
     * @return 问答列表
     */
    @GET("wenda/list/{page}/json")
    ListenableFuture<Result<ArticleRoot<Article>>> getWenda(@Path("page") int page);

    /**
     *
     * @return 导航数据
     */
    @GET("navi/json")
    Observable<Result<List<NavigationRoot>>> getNavigation();

    /**
     *
     * @return  体系数据
     */
    @GET("tree/json")
    Observable<Result<List<Children>>> getTree();

    /**
     *
     * @param page 页码
     * @return 广场列表数据
     */
    @GET("user_article/list/{page}/json")
    ListenableFuture<Result<ArticleRoot<Article>>> getSquare(@Path("page") int page);

    /**
     *
     * @return  热词
     */
    @GET("hotkey/json")
    Observable<Result<List<HotKey>>> getHotKey();

    /**
     *
     * @param page 页码：拼接在链接上，从0开始。
     * @param k k ： 搜索关键词
     * @return 搜索
     */
    @FormUrlEncoded
    @POST("article/query/{page}/json")
    ListenableFuture<Result<ArticleRoot<Article>>> getSearchResult(@Path("page") int page
            , @Field("k") String k);
}
