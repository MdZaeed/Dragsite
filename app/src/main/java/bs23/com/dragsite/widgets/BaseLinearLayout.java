package bs23.com.dragsite.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by BS-86 on 4/4/2016.
 */
public abstract class BaseLinearLayout extends LinearLayout {

    private int aboveId=0;
    View bottomView;

    public BaseLinearLayout(Context context) {
        super(context);
        this.setOrientation(VERTICAL);
    }

    public void addBottomVIew(Context context)
    {
        bottomView=new View(context);
        bottomView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        super.addView(bottomView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 20));
    }

    public int getAboveId() {
        return aboveId;
    }

    public void setAboveId(int aboveId) {
        this.aboveId = aboveId;
    }

    public void setBottomViewColor(int color)
    {
        bottomView.setBackgroundColor(getResources().getColor(color));
    }

    public abstract void addContents();
/*
    @Override
    protected void dispa(Canvas canvas) {
        this.canvas=canvas;
        super.onDraw(canvas);
        drawRect(canvas);
    }*/
/*
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        drawRect(canvas);
    }

    public void drawRect(Canvas canvas)
    {
        canvas.drawColor(Color.rgb(0, 0, 0));
        Paint myPaint = new Paint();
        myPaint.setColor(Color.rgb(0, 0, 0));
        myPaint.setStrokeWidth(10);
        canvas.drawRect(this.getX(),this.getY(), this.getX()+this.getWidth(), this.getY()+this.getHeight(), myPaint);
    }*/

}
