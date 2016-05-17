package bs23.com.dragsite.widgets;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by BS-86 on 4/4/2016.
 */
public abstract class BaseLinearLayout extends LinearLayout implements View.OnLongClickListener {

    private int aboveId = 0;
    View bottomView;
    boolean isForegroundDrawable = false;
    private boolean isDrawn = false;

    public BaseLinearLayout(Context context) {
        super(context);
        this.setOrientation(VERTICAL);
    }

    public void addBottomVIew(Context context) {
        bottomView = new View(context);
        bottomView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        super.addView(bottomView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 20));

        this.setOnLongClickListener(this);
    }

    public int getAboveId() {
        return aboveId;
    }

    public void setAboveId(int aboveId) {
        this.aboveId = aboveId;
    }

    public void setBottomViewColor(int color) {
        bottomView.setBackgroundColor(getResources().getColor(color));
    }

    public abstract void addContents();

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if (isForegroundDrawable && isDrawn()) {
            Paint strokePaint = new Paint();
            strokePaint.setARGB(0, 0, 0, 0);
            strokePaint.setStyle(Paint.Style.STROKE);
            strokePaint.setStrokeWidth(2);
            Rect r = canvas.getClipBounds();
            Rect outline = new Rect(1, 1, r.right - 1, r.bottom - 1);
            canvas.drawRect(outline, strokePaint);
            setDrawn(false);
        } else if (isForegroundDrawable && !isDrawn()) {
            Paint strokePaint = new Paint();
            strokePaint.setARGB(255, 255, 0, 0);
            strokePaint.setStyle(Paint.Style.STROKE);
            strokePaint.setStrokeWidth(2);
            Rect r = canvas.getClipBounds();
            Rect outline = new Rect(1, 1, r.right - 1, r.bottom - 1);
            canvas.drawRect(outline, strokePaint);
            setDrawn(true);
        }
        isForegroundDrawable = false;
    }

    public void drawForegroundRectangle() {
        isForegroundDrawable = true;
        postInvalidate();
    }

    @Override
    public boolean onLongClick(View v) {
        ClipData data = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
        v.startDrag(data, shadowBuilder, v, 0);
        return true;
    }

    public boolean isDrawn() {
        return isDrawn;
    }

    public void setDrawn(boolean drawn) {
        isDrawn = drawn;
    }
}
