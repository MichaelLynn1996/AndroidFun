/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.base2
 * @ClassName: BaseEvent
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/10 13:24
 */
package cn.mlynn.androidfun.base;

public class BaseEvent {
    private final int action;

    public BaseEvent(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }
}
