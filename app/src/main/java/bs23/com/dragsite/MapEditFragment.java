package bs23.com.dragsite;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by BrainStation on 4/15/16.
 */
public class MapEditFragment extends BaseEditFragment {

    public EditText adressEditText;
    public Button mockButton;
    private MapsWidget mapsWidget;
    private Spinner zoomSpinner;

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
        mockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                mapsWidget.setPositionByAddress(adressEditText.getText().toString());
*/
            }
        });
        adressEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mapsWidget.setPositionByAddress(adressEditText.getText().toString());
            }
        });

        setUpZoomListener();
    }

    private void setUpZoomListener()
    {
        zoomSpinner.setSelection((int) mapsWidget.getZoom(),true);
        zoomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mapsWidget.setZoom(position);
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
}
