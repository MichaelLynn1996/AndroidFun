package cn.mlynn.androidfun.net;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import cn.mlynn.androidfun.model.wan.Chapter;
import cn.mlynn.androidfun.model.wan.Friend;
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
import retrofit2.http.Query;

/**
 * Created by SeaLynn0 on 2018/12/5 2:36
 * <p>
 * Email：sealynndev@gmail.com
 * <p>
 * 玩Android 开放API
 */
public interface RequestApi {

    String BASE_URL = "https://www.wanandroid.com/";

    int SUCC = 0;
    int LOGIN_EXPIRE = -1001;

    /*
     * 一些更新
     *
     * //TODO:2020-05-08 开放 Google Maven Repo API
     *
     * 2018-10-13 公众号Tab
     * 增加微信公众号 Tab，查看公众号文章，支持搜索，。
     *
     * 2018-10-10 最新项目 Tab
     */

    /**
     * @return 获取公众号列表
     */
    @GET("wxarticle/chapters/json")
    Observable<Result<List<Chapter>>> getWXChapter();

    /**
     * @param id   公众号 ID：拼接在 url 中，eg:405
     * @param page 公众号页码：拼接在url 中，eg:1
     * @return 查看某个公众号历史数据
     */
    @GET("wxarticle/list/{id}/{page}/json")
    ListenableFuture<Result<ArticleRoot<Article>>> getWxArticles(@Path("id") int id
            , @Path("page") int page);

    /**
     * @param k    k : 字符串，eg:Java
     * @param id   公众号 ID：拼接在 url 中，eg:405
     * @param page 公众号页码：拼接在url 中，eg:1
     * @return 在某个公众号中搜索历史文章
     */
    @GET("wxarticle/list/{id}/{page}/json")
    ListenableFuture<Result<ArticleRoot<Article>>> searchWxArticles(@Path("id") int id
            , @Path("page") int page, @Query("k") String k);

    /**
     * @param page 页码，拼接在连接中，从0开始。
     * @return 最新项目tab (首页的第二个tab)
     */
    @GET("article/listproject/{page}/json")
    ListenableFuture<Result<ArticleRoot<Article>>> getLatestProject(@Path("page") int page);

    /*
     * 1.首页相关
     */

    /**
     * @param page 页码，拼接在连接中，从0开始。
     * @return 1.1 首页文章列表
     */
    @GET("article/list/{page}/json")
    ListenableFuture<Result<ArticleRoot<Article>>> getHomeArticles(@Path("page") int page);

    /**
     * @return 1.2 首页banner
     */
    @GET("banner/json")
    Observable<Result<List<Banner>>> getBanner();

    /**
     * @return 1.3 常用网站
     */
    @GET("friend/json")
    Observable<Result<List<Friend>>> getFriend();

    /**
     * @return 1.4 搜索热词
     */
    @GET("hotkey/json")
    Observable<Result<List<HotKey>>> getHotKey();

    /**
     * @return 1.5 置顶文章
     */
    @GET("article/top/json")
    Observable<Result<List<Article>>> getTopArticle();

    /*
     * 2. 体系
     */

    /**
     * @return 2.1 体系数据
     */
    @GET("tree/json")
    Observable<Result<List<Chapter>>> getTree();

    /*
     * 3.1 导航数据
     */

    /**
     * @return 导航数据
     */
    @GET("navi/json")
    Observable<Result<List<NavigationRoot>>> getNavigation();

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
     * @param page 页码
     * @return 问答列表
     */
    @GET("wenda/list/{page}/json")
    ListenableFuture<Result<ArticleRoot<Article>>> getWenda(@Path("page") int page);



    /**
     * @param page 页码
     * @return 广场列表数据
     */
    @GET("user_article/list/{page}/json")
    ListenableFuture<Result<ArticleRoot<Article>>> getSquare(@Path("page") int page);


    /**
     * @param page 页码：拼接在链接上，从0开始。
     * @param k    k ： 搜索关键词
     * @return 搜索
     */
    @FormUrlEncoded
    @POST("article/query/{page}/json")
    ListenableFuture<Result<ArticleRoot<Article>>> getSearchResult(@Path("page") int page
            , @Field("k") String k);
}
