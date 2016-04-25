package bs23.com.dragsite;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import bs23.com.dragsite.widgets.BaseLinearLayout;

/**
 * Created by BS-86 on 3/31/2016.
 */
public class CustomLayout extends BaseLinearLayout {
    public CustomLayout(Context context) {
        super(context);

        Button button=new Button(context);
        button.setText("HAhahahahahh");
        TextView textView=new TextView(context);
        textView.setText("NOOOOOO");
        super.addView(textView);
        super.addView(button);

        addBottomVIew(context);
    }

    @Override
    public void addContents() {

    }
}
