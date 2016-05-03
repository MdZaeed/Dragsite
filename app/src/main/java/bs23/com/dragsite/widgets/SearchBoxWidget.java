package bs23.com.dragsite.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import bs23.com.dragsite.R;


public class SearchBoxWidget extends BaseLinearLayoutWithSpacingNeeds {

    Context context;
    private String placeholderText="Search";

    public SearchBoxWidget(Context context) {
        super(context);
        this.context = context;
    }

    public void addContents()
    {
        super.addView(LayoutInflater.from(context).inflate(R.layout.widget_search_box,null),new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        addBottomVIew(context);

        spacingAbove = dpToPx(0);
        spacingBelow = dpToPx(0);
        setMainView(this.findViewById(R.id.ll_search_widget));
    }

    public String getPlaceholderText() {
        return placeholderText;
    }

    public void setPlaceholderText(String placeholderText) {
        this.placeholderText = placeholderText;

        ((TextView)(this.findViewById(R.id.tv_search_placeholder))).setText(placeholderText);
    }
}
