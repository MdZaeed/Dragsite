package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bs23.com.dragsite.R;

/**
 * Created by BS-86 on 4/28/2016.
 */
public class SearchBoxEditFragment extends BaseEditFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_box, container, false);
    }

    public static SearchBoxEditFragment newInstance() {
        return new SearchBoxEditFragment();
    }
}
