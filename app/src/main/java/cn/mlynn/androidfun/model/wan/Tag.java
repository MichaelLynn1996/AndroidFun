/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.model
 * @ClassName: Tag
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/9 16:45
 */
package cn.mlynn.androidfun.model.wan;

public class Tag {

    public static final String WE_CHAT = "公众号";
    public static final String OWN_PUBLISH = "本站发布";
    public static final String WEN_DA = "问答";

    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
