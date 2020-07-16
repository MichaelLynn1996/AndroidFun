/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.model.wan
 * @ClassName: HotKey
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/13 16:12
 */
package cn.mlynn.androidfun.model.wan;

import androidx.room.Entity;

import cn.mlynn.androidfun.model.BaseEntity;

@Entity(primaryKeys = {"id", "key"})
public class HotKey extends BaseEntity {

    private int id;
    private String link;
    private String name;
    private int order;
    private int visible;


    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }


    public void setLink(String link) {
        this.link = link;
    }
    public String getLink() {
        return link;
    }


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


    public void setOrder(int order) {
        this.order = order;
    }
    public int getOrder() {
        return order;
    }


    public void setVisible(int visible) {
        this.visible = visible;
    }
    public int getVisible() {
        return visible;
    }
}
