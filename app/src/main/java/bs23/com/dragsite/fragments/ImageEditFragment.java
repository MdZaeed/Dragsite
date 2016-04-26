package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import bs23.com.dragsite.R;

/**
 * Created by BrainStation on 4/15/16.
 */
public class ImageEditFragment extends BaseEditFragment {

    private android.support.v4.app.FragmentManager fragmentManager1;


    public static ImageEditFragment newInstance() {
        return new ImageEditFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_widget, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public android.support.v4.app.FragmentManager getFragmentManager1() {
        return fragmentManager1;
    }

    public void setFragmentManager1(android.support.v4.app.FragmentManager fragmentManager1) {
        this.fragmentManager1 = fragmentManager1;
    }
}
