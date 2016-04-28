package bs23.com.dragsite;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Created by BS-86 on 4/27/2016.
 */
public class TweekedEditText extends EditText {
    public TweekedEditText(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        /*return super.onKeyPreIme(keyCode, event);*/
        if (keyCode == KeyEvent.KEYCODE_BACK &&
                event.getAction() == KeyEvent.ACTION_UP) {
            // do your stuff
            Log.d("Back Pressed","Yes");
            return false;
        }
        return true;
    }
}
