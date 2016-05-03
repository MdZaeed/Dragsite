package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import bs23.com.dragsite.R;
import bs23.com.dragsite.widgets.SearchBoxWidget;

/**
 * Created by BS-86 on 4/28/2016.
 */
public class SearchBoxEditFragment extends BaseSpacingFragmentForFirstLevel {

    private SearchBoxWidget searchBoxWidget;
    private EditText placeholderTextEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_box, container, false);
    }

    public static SearchBoxEditFragment newInstance() {
        return new SearchBoxEditFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setPerCategoryDifference(10);
        ownWidget= getSearchBoxWidget();

        super.onViewCreated(view, savedInstanceState);

        placeholderTextEditText=(EditText) view.findViewById(R.id.et_search_placeholder);

        placeholderTextEditText.setText(searchBoxWidget.getPlaceholderText());
        placeholderTextEditText.setInputType(EditorInfo.IME_ACTION_DONE);

        placeholderTextEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                searchBoxWidget.setPlaceholderText(placeholderTextEditText.getText().toString());
                return false;
            }
        });
    }

    public SearchBoxWidget getSearchBoxWidget() {
        return searchBoxWidget;
    }

    public void setSearchBoxWidget(SearchBoxWidget searchBoxWidget) {
        this.searchBoxWidget = searchBoxWidget;
    }
}
