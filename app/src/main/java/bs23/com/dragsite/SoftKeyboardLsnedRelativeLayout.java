package bs23.com.dragsite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
/*        final int newH = MeasureSpec.getSize(heightMeasureSpec);
        if (newH > layoutMaxH) {
            layoutMaxH = newH;
        }
        if (layoutMaxH != 0f) {
            final float sizePercent = newH / layoutMaxH;
            if (!isKeyboardShown && sizePercent <= DETECT_ON_SIZE_PERCENT) {
                isKeyboardShown = true;
                for (final SoftKeyboardLsner lsner : lsners) {
                    lsner.onSoftKeyboardShow();
                }
            } else if (isKeyboardShown && sizePercent > DETECT_ON_SIZE_PERCENT) {
                isKeyboardShown = false;
                for (final SoftKeyboardLsner lsner : lsners) {
                    lsner.onSoftKeyboardHide();
                }
            }
        }*/

        final int proposedheight = MeasureSpec.getSize(heightMeasureSpec);
        final int actualHeight = getHeight();

        if (actualHeight < proposedheight) {

            if(lsner!=null) {
                lsner.onSoftKeyboardHide();
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void addSoftKeyboardLsner(SoftKeyboardLsner lsner) {
        this.lsner = lsner;
    }

    public void removeSoftKeyboardLsner() {
        this.lsner = null;
    }

    // Callback
    public interface SoftKeyboardLsner {
        void onSoftKeyboardHide();
    }
}