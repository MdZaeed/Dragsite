package bs23.com.dragsite;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by BrainStation on 4/15/16.
 */
public class ImageEditFragment extends BaseEditFragment {

    private android.support.v4.app.FragmentManager fragmentManager1;
    Button backButton;


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
        backButton=(Button) view.findViewById(R.id.btn_cancel_add_dialog);
        backButton.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.ic_dialog_info));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                fragmentManager1.beginTransaction().setCustomAnimations(R.anim.slide_in_right, 0,android.R.anim.slide_in_left,0).replace(((MainActivity)getActivity()).getBottomPaneLinearLayout().getId(), ).addToBackStack(null).commit();
*/
                fragmentManager1.popBackStack();
            }
        });
    }

    public android.support.v4.app.FragmentManager getFragmentManager1() {
        return fragmentManager1;
    }

    public void setFragmentManager1(android.support.v4.app.FragmentManager fragmentManager1) {
        this.fragmentManager1 = fragmentManager1;
    }
}
