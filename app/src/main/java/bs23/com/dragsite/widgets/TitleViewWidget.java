package bs23.com.dragsite.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import bs23.com.dragsite.JsonWriter;
import bs23.com.dragsite.R;
import bs23.com.dragsite.utils.JsonKeys;


public class TitleViewWidget extends BaseLinearLayout {

    Context context;
    boolean isButtonPressedHere=false;
    private String titleText="";
    public static final String TYPE="title";

    public TitleViewWidget(Context context) {
        super(context);
        this.context = context;
    }
    public void addContents()
    {
        super.addView(LayoutInflater.from(context).inflate(R.layout.widget_title_view,null),new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        addBottomVIew(context);

        JsonWriter.getInstance(context).createTitleWidget(this);
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
        JsonWriter.getInstance(context).writeToFile(getId(), JsonKeys.TITLE_WIDGET_TEXT,titleText);
    }
}
