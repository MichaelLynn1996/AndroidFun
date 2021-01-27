/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.model
 * @ClassName: NavigationRoot
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/10 17:52
 */
package cn.mlynn.androidfun.model.wan;

import java.util.List;

import cn.mlynn.androidfun.model.BaseEntity;

//@Entity(primaryKeys = {"cid", "key"})
public class NavigationRoot extends BaseEntity {

//    @TypeConverters(ArticleListTypeConverter.class)
    private List<Article> articles;
    private int cid;
    private String name;


    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
    public List<Article> getArticles() {
        return articles;
    }


    public void setCid(int cid) {
        this.cid = cid;
    }
    public int getCid() {
        return cid;
    }


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
