package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import bs23.com.dragsite.MainActivity;
import bs23.com.dragsite.widgets.MapsWidget;
import bs23.com.dragsite.R;

/**
 * Created by BrainStation on 4/15/16.
 */
public class MapEditFragment extends BaseEditFragment {

    public EditText adressEditText;
    public Button mockButton;
    private MapsWidget mapsWidget;
    private Spinner zoomSpinner;
    private Button positionButton;
    private Button advancedPositionButton;

    public static MapEditFragment newInstance() {
        return new MapEditFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map_edit, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView)view.findViewById(R.id.tv_tittle_add_dialog)).setText("Map");
        view.findViewById(R.id.btn_cancel_add_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).hideBottomOptionMenu();
            }
        });

        zoomSpinner=(Spinner) view.findViewById(R.id.sp_map_zoom);
        adressEditText=(EditText) view.findViewById(R.id.et_adress_map);
        mockButton=(Button) view.findViewById(R.id.btn_mock_click);
        positionButton=(Button) view.findViewById(R.id.btn_map_position);
        advancedPositionButton=(Button) view.findViewById(R.id.btn_map_advanced_location);

        adressEditText.setText(mapsWidget.getAddress());
        adressEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        positionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapEditPositionFragment mapEditPositionFragment=MapEditPositionFragment.newInstance();
                mapEditPositionFragment.setFragmentManager1(getFragmentManager1());
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0,android.R.anim.slide_in_left,0)
                        .replace(((MainActivity)getActivity()).getBottomPaneLinearLayout().getId(), mapEditPositionFragment)
                        .addToBackStack("null")
                        .commit();
            }
        });

        mockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                mapsWidget.setPositionByAddress(adressEditText.getText().toString());
*/
            }
        });
        adressEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                getMapsWidget().setPositionByAddress(adressEditText.getText().toString());
                return false;
            }
        });

        advancedPositionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapEditAdvancedLocationFragment mapEditAdvancedPossitionFragment= MapEditAdvancedLocationFragment.newInstance();
                mapEditAdvancedPossitionFragment.setFragmentManager1(getFragmentManager1());
                getFragmentManager1().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, 0,android.R.anim.slide_in_left,0)
                        .replace(((MainActivity)getActivity()).getBottomPaneLinearLayout().getId(), mapEditAdvancedPossitionFragment)
                        .addToBackStack("null")
                        .commit();
            }
        });

/*        hideDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).hideBottomOptionMenu();

            }
        });*/

        setUpZoomListener();
    }

    private void setUpZoomListener()
    {
        zoomSpinner.setSelection((int) getMapsWidget().getZoom(),true);
        zoomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getMapsWidget().setZoom(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public MapsWidget getMapsWidget() {
        return mapsWidget;
    }

    public void setMapsWidget(MapsWidget mapsWidget) {
        this.mapsWidget = mapsWidget;
    }

    @Override
    public void onResume() {
        super.onResume();
        adressEditText.setText(mapsWidget.getAddress());
    }
}
