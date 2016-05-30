package bs23.com.dragsite.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import bs23.com.dragsite.MainActivity;
import bs23.com.dragsite.R;
import bs23.com.dragsite.model.Style;
import bs23.com.dragsite.model.StyleChange;

/**
 * Created by BrainStation on 4/15/16.
 */
public abstract class BaseEditFragment extends Fragment {

    private android.support.v4.app.FragmentManager fragmentManager1;
    public static String FRAGMENT_NAME="common_name";

    protected OnViewReady mCallback;
    Button backButton;
    protected OnStyleChanged mStyleChanged;

    public BaseEditFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnViewReady) getActivity();
            mStyleChanged=(OnStyleChanged) getActivity();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mCallback != null) {
            mCallback.onReady(view);
        }

        backButton=(Button) view.findViewById(R.id.btn_cancel_add_dialog);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCloseButtonPressed();
            }
        });
    }

    public void onCloseButtonPressed(){
        ((MainActivity)getActivity()).hideBottomOptionMenu();
    }


    public interface OnViewReady {
        void onReady(View view);
    }

    public interface OnStyleChanged{
        void changeStyle(StyleChange... styleArgs);
    }

    public android.support.v4.app.FragmentManager getFragmentManager1() {
        return fragmentManager1;
    }

    public void setFragmentManager1(android.support.v4.app.FragmentManager fragmentManager1) {
        this.fragmentManager1 = fragmentManager1;
    }
}
