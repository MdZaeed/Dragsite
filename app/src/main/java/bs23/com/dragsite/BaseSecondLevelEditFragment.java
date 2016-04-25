package bs23.com.dragsite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by BrainStation on 4/15/16.
 */
public abstract class BaseSecondLevelEditFragment extends BaseEditFragment {

    private android.support.v4.app.FragmentManager fragmentManager1;
    Button backButton;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backButton=(Button) view.findViewById(R.id.btn_cancel_add_dialog);
        backButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                fragmentManager1.beginTransaction().setCustomAnimations(R.anim.slide_in_right, 0,android.R.anim.slide_in_left,0).replace(((MainActivity)getActivity()).getBottomPaneLinearLayout().getId(), ).addToBackStack(null).commit();
*/
                getFragmentManager1().popBackStack();
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
