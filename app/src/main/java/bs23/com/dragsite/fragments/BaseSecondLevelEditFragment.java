package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import bs23.com.dragsite.R;

/**
 * Created by BrainStation on 4/15/16.
 */
public abstract class BaseSecondLevelEditFragment extends BaseEditFragment {

    Button backButton;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backButton=(Button) view.findViewById(R.id.btn_cancel_add_dialog);
        backButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonClick();
            }
        });
    }

    protected void backButtonClick()
    {
        /*getFragmentManager1().popBackStack();*/

        if(iFragManagement!=null) {
            iFragManagement.onFragmentDestroy(getArguments());
        }
    }

}
