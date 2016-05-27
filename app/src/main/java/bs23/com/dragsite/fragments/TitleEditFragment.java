package bs23.com.dragsite.fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.Arrays;

import bs23.com.dragsite.MainActivity;
import bs23.com.dragsite.R;
import bs23.com.dragsite.SoftKeyboardLsnedRelativeLayout;
import bs23.com.dragsite.widgets.TitleViewWidget;

/**
 * Created by BS-86 on 4/28/2016.
 */
public class TitleEditFragment extends BaseEditFragment {

    private TitleViewWidget titleViewWidget;
    EditText focusedEditText;
    TextView focusedTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_title_edit, container, false);
    }

    public static TitleEditFragment newInstance() {
        return new TitleEditFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner spinner = (Spinner) view.findViewById(R.id.sp_title_size);
        spinner.setSelection(Arrays.asList(TitleViewWidget.textSizes).indexOf(titleViewWidget.getTextSize()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                titleViewWidget.setTextSize(Arrays.asList(TitleViewWidget.textSizes).get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        titleViewWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) titleViewWidget.findViewById(R.id.et_title);
                TextView textView = (TextView) titleViewWidget.findViewById(R.id.tv_title);
                setEditTextEdit(editText, textView, titleViewWidget);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setEditTextEdit(EditText editText, TextView textView, final TitleViewWidget titleViewWidget) {

        focusedEditText = editText;
        focusedTextView = textView;

        textView.setVisibility(View.GONE);
        editText.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                editText.setBackground(getResources().getDrawable(R.drawable.dark_blue_border_transparent_background, getContext().getTheme()));
            } else {
                editText.setBackground(getResources().getDrawable(R.drawable.dark_blue_border_transparent_background));
            }
        } else {
            editText.setBackgroundDrawable(getResources().getDrawable(R.drawable.dark_blue_border_transparent_background));
        }

        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        ((MainActivity) getActivity()).slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_FORCED);

        ((MainActivity) getActivity()).mainRelativeLayout.addSoftKeyboardLsner(new SoftKeyboardLsnedRelativeLayout.SoftKeyboardListenner() {

            @Override
            public void onSoftKeyboardHide() {
                hideSOftKeyBoard(titleViewWidget);
            }
        });
    }

    private void hideSOftKeyBoard(TitleViewWidget titleViewWidget) {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(focusedEditText.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);

        focusedTextView.setText(focusedEditText.getText().toString());
        focusedTextView.setVisibility(View.VISIBLE);
        focusedEditText.setVisibility(View.GONE);
        ((MainActivity) getActivity()).mainRelativeLayout.requestFocus();

        ((MainActivity) getActivity()).mainRelativeLayout.removeSoftKeyboardLsner();

        titleViewWidget.setTitleTextAndUI(focusedTextView.getText().toString());

        ((MainActivity) getActivity()).mainScrollView.post(
                new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity) getActivity()).slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                    }
                }
        );
    }

    public TitleViewWidget getTitleViewWidget() {
        return titleViewWidget;
    }

    public void setTitleViewWidget(TitleViewWidget titleViewWidget) {
        this.titleViewWidget = titleViewWidget;
    }

    @Override
    public void onCloseButtonPressed() {
        titleViewWidget.setOnClickListener(((MainActivity) getActivity()).new WidgetTouchHandler(titleViewWidget));
        super.onCloseButtonPressed();
    }
}
