/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.base2
 * @ClassName: BaseEventAction
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/10 13:24
 */
package cn.mlynn.androidfun.base;

public class BaseEventAction extends BaseEvent {

    public boolean isCurrentAction(ACTION action) {
        return action.ordinal() == getAction();
    }

    public enum ACTION {
        SHOW_LOADING, DISMISS_LOADING, SHOW_TOAST
    }

    private String message;

    public BaseEventAction(int action) {
        super(action);
    }

    public BaseEventAction(int action, String msg) {
        super(action);
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
