package bs23.com.dragsite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class SoftKeyboardLsnedRelativeLayout extends RelativeLayout {
    private SoftKeyboardLsner lsner;

    public SoftKeyboardLsnedRelativeLayout(Context context) {
        super(context);
    }

    public SoftKeyboardLsnedRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("NewApi")
    public SoftKeyboardLsnedRelativeLayout(Context context, AttributeSet attrs,
                                           int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addSoftKeyboardLsner(SoftKeyboardLsner lsner) {
        this.lsner = lsner;
    }

    public void removeSoftKeyboardLsner() {
        this.lsner = null;
    }

    public interface SoftKeyboardLsner {
        void onSoftKeyboardHide();
    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            KeyEvent.DispatcherState state = getKeyDispatcherState();
            if (state != null) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getRepeatCount() == 0) {
                    state.startTracking(event, this);
                    return true;
                } else if (event.getAction() == KeyEvent.ACTION_UP
                        && !event.isCanceled() && state.isTracking(event)) {
                    if(lsner!=null) {
                        lsner.onSoftKeyboardHide();
                    }
                    return true;
                }
            }
        }

        return super.dispatchKeyEventPreIme(event);
    }
}