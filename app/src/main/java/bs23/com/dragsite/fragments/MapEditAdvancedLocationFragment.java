package bs23.com.dragsite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

import bs23.com.dragsite.R;
import bs23.com.dragsite.widgets.MapsWidget;

/**
 * Created by BS-86 on 4/26/2016.
 */
public class MapEditAdvancedLocationFragment extends BaseSecondLevelEditFragment implements MapsWidget.OnResultReceived {

    EditText longitudeEditText;
    EditText latitudeEditText;
    MapEditFragment mapEditFragment;

    public static MapEditAdvancedLocationFragment newInstance() {
        return new MapEditAdvancedLocationFragment();
    }

    public MapEditAdvancedLocationFragment getInstance()
    {
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map_edit_advanced_location, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapEditFragment=(MapEditFragment)getFragmentManager1().findFragmentByTag(BaseEditFragment.FRAGMENT_NAME);

        longitudeEditText=(EditText) view.findViewById(R.id.et_map_longitude);
        latitudeEditText=(EditText) view.findViewById(R.id.et_map_latitude);

        longitudeEditText.setText(mapEditFragment.getMapsWidget().getLongitude()+"");
        latitudeEditText.setText(mapEditFragment.getMapsWidget().getLatitude()+"");
    }

    @Override
    protected void backButtonClick() {
        mapEditFragment.getMapsWidget().setLongitude(Double.parseDouble(longitudeEditText.getText().toString()));
        mapEditFragment.getMapsWidget().setLatitude(Double.parseDouble(latitudeEditText.getText().toString()));

        mapEditFragment.getMapsWidget().getAddressByPosition(this);

/*
        super.backButtonClick();
*/
    }

    @Override
    public void onLocationResponseReceived() {
        super.backButtonClick();
    }
}
