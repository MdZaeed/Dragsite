package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import bs23.com.dragsite.R;

/**
 * Created by BS-86 on 4/25/2016.
 */
public class YoutubeEditAdvancedFragment extends BaseSecondLevelEditFragment {

    YoutubeEditFragment youtubeEditFragment;
    EditText idEditText;
    Spinner videoQualitySpinner;

    public static YoutubeEditAdvancedFragment newInstance() {
        return new YoutubeEditAdvancedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_youtube_advanced, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        youtubeEditFragment=(YoutubeEditFragment)getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        idEditText=(EditText) view.findViewById(R.id.et_youttube_video_id);
        videoQualitySpinner=(Spinner) view.findViewById(R.id.sp_youtube_video_quality);

        idEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        idEditText.setText(youtubeEditFragment.getYoutubeWidget().getVideoId());

        idEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                onNewIdEntered();
                return false;
            }
        });

        videoQualitySpinner.setSelection(youtubeEditFragment.getYoutubeWidget().getQuality());
        videoQualitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                youtubeEditFragment.getYoutubeWidget().setQuality((char) position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void onNewIdEntered() {
        youtubeEditFragment.getYoutubeWidget().setVideoURLThroughID(idEditText.getText().toString());
    }
}
