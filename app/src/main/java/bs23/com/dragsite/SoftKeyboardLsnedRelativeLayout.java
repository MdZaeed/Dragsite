package bs23.com.dragsite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

public class SoftKeyboardLsnedRelativeLayout extends RelativeLayout {
    private SoftKeyboardListenner listener;

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

    public void addSoftKeyboardLsner(SoftKeyboardListenner lsner) {
        this.listener = lsner;
    }

    public void removeSoftKeyboardLsner() {
        this.listener = null;
    }

    public interface SoftKeyboardListenner {
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
                    if(listener !=null) {
                        listener.onSoftKeyboardHide();
                    }
                    return true;
                }
            }
        }

        return super.dispatchKeyEventPreIme(event);
    }
}