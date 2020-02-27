package xyz.sealynn.androidfun.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Created by MichaelLynn on 2020/2/5 1:03
 * <p>
 * Email: sealynndev@gamil.com
 */
public class SwipeRefreshLayoutAtViewPager2 extends SwipeRefreshLayout {

    public SwipeRefreshLayoutAtViewPager2(@NonNull Context context) {
        super(context);
    }

    public SwipeRefreshLayoutAtViewPager2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    private float lastEventX,lastEventY;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = x - lastEventX;
                float dy = y - lastEventY;
                if(dx > 16 * dy){
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            default:
                break;
        }
        lastEventX = x;
        lastEventY = y;
        return super.dispatchTouchEvent(ev);
    }

//    作者：103style
//    链接：https://juejin.im/post/5e08d3aae51d4558096d6eea
//    来源：掘金
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
