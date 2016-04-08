package bs23.com.dragsite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by BS-86 on 4/1/2016.
 */
public class EditOptionsDialog extends LinearLayout {

    Context context;

    public EditOptionsDialog(Context context) {
        super(context);
        this.context=context;
    }

    public void addContents()
    {
        super.addView(LayoutInflater.from(context).inflate(R.layout.dialog_on_click, null), new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
