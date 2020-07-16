/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.model
 * @ClassName: BaseEntity
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/13 14:36
 */
package cn.mlynn.androidfun.model;

public abstract class BaseEntity {

    private int key;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
