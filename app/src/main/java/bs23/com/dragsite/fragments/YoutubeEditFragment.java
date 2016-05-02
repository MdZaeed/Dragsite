package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import bs23.com.dragsite.MainActivity;
import bs23.com.dragsite.R;
import bs23.com.dragsite.widgets.YoutubeWidget;

/**
 * Created by BS-86 on 4/28/2016.
 */
public class YoutubeEditFragment extends BaseEditFragment {

    EditText urlEditText;
    private YoutubeWidget youtubeWidget;
    private Button advanced;
    private Button spacing;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_youtube, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        urlEditText=(EditText) view.findViewById(R.id.et_youttube_video_url);
        advanced=(Button) view.findViewById(R.id.btn_youtube_advanced);
        spacing=(Button) view.findViewById(R.id.btn_youtube_spacing);

        urlEditText.setText(youtubeWidget.getVideoURL());
        urlEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        urlEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                getYoutubeWidget().setVideoIDfromURL(urlEditText.getText().toString());
                return false;
            }
        });

        advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoutubeEditAdvancedFragment youtubeEditAdvancedFragment=YoutubeEditAdvancedFragment.newInstance();
                youtubeEditAdvancedFragment.setFragmentManager1(getFragmentManager1());
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0,android.R.anim.slide_in_left,0)
                        .replace(((MainActivity)getActivity()).getBottomPaneLinearLayout().getId(), youtubeEditAdvancedFragment)
                        .addToBackStack("null")
                        .commit();
            }
        });

        spacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoutubeEditSpacingFragment youtubeEditSpacingFragment=YoutubeEditSpacingFragment.newInstance();
                youtubeEditSpacingFragment.setFragmentManager1(getFragmentManager1());
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0,android.R.anim.slide_in_left,0)
                        .replace(((MainActivity)getActivity()).getBottomPaneLinearLayout().getId(), youtubeEditSpacingFragment)
                        .addToBackStack("null")
                        .commit();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        urlEditText.setText(youtubeWidget.getVideoURL());
    }

    public static YoutubeEditFragment newInstance() {
        return new YoutubeEditFragment();
    }

    public YoutubeWidget getYoutubeWidget() {
        return youtubeWidget;
    }

    public void setYoutubeWidget(YoutubeWidget youtubeWidget) {
        this.youtubeWidget = youtubeWidget;
    }

    public Button getAdvanced() {
        return advanced;
    }

    public void setAdvanced(Button advanced) {
        this.advanced = advanced;
    }
}
