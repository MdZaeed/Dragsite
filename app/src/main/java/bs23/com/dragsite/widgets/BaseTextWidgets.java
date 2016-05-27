package bs23.com.dragsite.widgets;

import android.content.Context;

/**
 * Created by BS-86 on 5/27/2016.
 */
public abstract class BaseTextWidgets extends BaseLinearLayout {

    private String titleText="";
    private String textSize=textSizes[1];
    public static String[] textSizes={"Small","Medium","Large"};

    public BaseTextWidgets(Context context) {
        super(context);
    }

    @Override
    public abstract void addContents();

    public String getTitleText() {
        return titleText;
    }

    public void setTitleTextAndUI(String titleText) {
        this.setTitleText(titleText);

        setTextOnWidget(titleText);
    }

    protected abstract void setTextOnWidget(String titleText);

    public String getTextSize() {
        return textSize;
    }

    public void setTextSize(String textSize) {
        this.textSize = textSize;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }
}
