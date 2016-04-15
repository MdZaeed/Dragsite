package bs23.com.dragsite;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by BrainStation on 4/15/16.
 */
public class ImageEditFragment extends Fragment {

    private OnViewReady mCallback;

    public static ImageEditFragment newInstance() {
        return new ImageEditFragment();
    }

    public ImageEditFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnViewReady) getActivity();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.test_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mCallback != null) {
            mCallback.onReady(view);
        }
    }

    public interface OnViewReady {
        void onReady(View view);
    }
}
