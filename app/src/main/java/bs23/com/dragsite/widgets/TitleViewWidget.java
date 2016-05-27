package bs23.com.dragsite.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bs23.com.dragsite.Interfaces.IStyleChanger;
import bs23.com.dragsite.JsonWriter;
import bs23.com.dragsite.R;
import bs23.com.dragsite.utils.JsonKeys;


public class TitleViewWidget extends BaseTextWidgets implements IStyleChanger {

    Context context;
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

        if(!getTitleText().equals("")) {
            setTextOnWidget(getTitleText());
        }
        JsonWriter.getInstance(context).createTitleWidget(this);
    }

    @Override
    protected void setTextOnWidget(String titleText) {
        ((TextView)this.findViewById(R.id.tv_title)).setText(titleText);
        JsonWriter.getInstance(context).writeToFile(getId(), JsonKeys.TITLE_WIDGET_TEXT,titleText);
    }

    @Override
    public void applyStyle(String attributeName, String attributeValue) {

        if(attributeName.equals(JsonKeys.TITLE_WIDGET_TEXT))
        {
            setTitleTextAndUI(attributeValue);
        }
    }
}
