package bs23.com.dragsite;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by BrainStation on 4/15/16.
 */
public class MapEditFragment extends BaseEditFragment {

    public EditText adressEditText;
    public Button mockButton;
    private MapsWidget mapsWidget;

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
        adressEditText=(EditText) view.findViewById(R.id.et_adress_map);
        mockButton=(Button) view.findViewById(R.id.btn_mock_click);
        mockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapsWidget.setPositionByAddress(adressEditText.getText().toString());
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
