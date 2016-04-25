package bs23.com.dragsite.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import bs23.com.dragsite.api.GeoCodingRestAdapter;
import bs23.com.dragsite.R;
import bs23.com.dragsite.model.LocationResponse;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by BS-86 on 4/12/2016.
 */
public class MapsWidget extends BaseLinearLayout {
    static Context context;
    private GoogleMap googleMap;

    public MapsWidget(Context context) {
        super(context);
        this.context=context;
    }

    public void addContents()
    {
        addView(LayoutInflater.from(context).inflate(R.layout.maps_layout, null), new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        addBottomVIew(context);
    }

    public void initialSetup()
    {
        LatLng Dhaka=new LatLng(23.7000,90.3667);
        CameraPosition cameraPosition=new CameraPosition.Builder()
                .zoom(10)
                .target(Dhaka)
                .build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        MarkerOptions endMarkerOption = new MarkerOptions().position(Dhaka)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.addMarker(endMarkerOption);
        googleMap.getUiSettings().setAllGesturesEnabled(false);
/*        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.d("Map Clicked", "True");
            }
        });*/
        googleMap.setOnMarkerClickListener(null);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }

    public float getZoom()
    {
        return googleMap.getCameraPosition().zoom;
    }

    public void setMarkerShowing(boolean show)
    {
        if(show==true)
        {
            googleMap.clear();
            CameraPosition cameraPosition=googleMap.getCameraPosition();
            MarkerOptions endMarkerOption = new MarkerOptions().position(cameraPosition.target)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            googleMap.addMarker(endMarkerOption);
        }else
        {
            googleMap.clear();
        }
    }

    public void setZoomControlEnabled(boolean enabled)
    {
        googleMap.getUiSettings().setZoomControlsEnabled(enabled);
    }

    public void setZoom(float zoom)
    {
/*        CameraPosition cameraPosition=googleMap.getCameraPosition();
        cameraPosition.zoom(zoom);
        googleMap.moveCamera(CameraUpdateFactory);*/
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(zoom));
    }

    public Double getLatitude()
    {
        return googleMap.getCameraPosition().target.latitude;
    }

    public Double getLongitude()
    {
        return googleMap.getCameraPosition().target.longitude;
    }

    public void setLatitude(Double newLatitude)
    {
        LatLng latlng=new LatLng(newLatitude,googleMap.getCameraPosition().target.longitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));

        MarkerOptions endMarkerOption = new MarkerOptions().position(latlng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.clear();
        googleMap.addMarker(endMarkerOption);
    }

    public void setLongitude(Double newLongitude)
    {
        LatLng latlng=new LatLng(googleMap.getCameraPosition().target.latitude,newLongitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));

        MarkerOptions endMarkerOption = new MarkerOptions().position(latlng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.clear();
        googleMap.addMarker(endMarkerOption);
    }

    public void setLatitudeAndLongitude(Float newLatitude,Float newLongitude)
    {
        LatLng latlng=new LatLng(newLatitude,newLongitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));

        MarkerOptions endMarkerOption = new MarkerOptions().position(latlng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.clear();
        googleMap.addMarker(endMarkerOption);
    }

    public void setPositionByAddress(String setAddress)
    {
        GeoCodingRestAdapter geoCodingRestAdapter=new GeoCodingRestAdapter();
        Call<LocationResponse> call=geoCodingRestAdapter.mapApi.getLatAndLng(setAddress, context.getResources().getString(R.string.google_maps_key));
        call.enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Response<LocationResponse> response, Retrofit retrofit) {
                if(!response.body().getStatus().equals("ZERO_RESULTS")) {
                    setLatitudeAndLongitude(response.body().getResults().get(0).getGeometry().getLocation().getLat(), response.body().getResults().get(0).getGeometry().getLocation().getLng());
                }
                return;
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public GoogleMap getGoogleMap() {
        return googleMap;
    }

    public void setGoogleMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }
}
