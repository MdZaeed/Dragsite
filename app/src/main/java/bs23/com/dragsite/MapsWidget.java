package bs23.com.dragsite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import bs23.com.dragsite.model.Location;
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

    public static float getZoom(GoogleMap googleMap)
    {
        return googleMap.getCameraPosition().zoom;
    }

    public static void setMarkerShowing(GoogleMap googleMap,boolean show)
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

    public static void setZoomControlEnabled(GoogleMap googleMap,boolean enabled)
    {
        googleMap.getUiSettings().setZoomControlsEnabled(enabled);
    }

    public static void changeZoom(GoogleMap googleMap, float zoom)
    {
/*        CameraPosition cameraPosition=googleMap.getCameraPosition();
        cameraPosition.zoom(zoom);
        googleMap.moveCamera(CameraUpdateFactory);*/
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(zoom));
    }

    public static Double getLatitude(GoogleMap googleMap)
    {
        return googleMap.getCameraPosition().target.latitude;
    }

    public static Double getLongitude(GoogleMap googleMap)
    {
        return googleMap.getCameraPosition().target.longitude;
    }

    public static void setLatitude(GoogleMap googleMap,Double newLatitude)
    {
        LatLng latlng=new LatLng(newLatitude,googleMap.getCameraPosition().target.longitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));

        MarkerOptions endMarkerOption = new MarkerOptions().position(latlng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.clear();
        googleMap.addMarker(endMarkerOption);
    }

    public static void setLongitude(GoogleMap googleMap,Double newLongitude)
    {
        LatLng latlng=new LatLng(googleMap.getCameraPosition().target.latitude,newLongitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));

        MarkerOptions endMarkerOption = new MarkerOptions().position(latlng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.clear();
        googleMap.addMarker(endMarkerOption);
    }

    public static void setLatitudeAndLongitude(GoogleMap googleMap,Float newLatitude,Float newLongitude)
    {
        LatLng latlng=new LatLng(newLatitude,newLongitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));

        MarkerOptions endMarkerOption = new MarkerOptions().position(latlng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.clear();
        googleMap.addMarker(endMarkerOption);
    }

    public static void setAddress(final GoogleMap googleMap,String setAddress)
    {
        GeoCodingRestAdapter geoCodingRestAdapter=new GeoCodingRestAdapter();
        Call<LocationResponse> call=geoCodingRestAdapter.mapApi.getLatAndLng(setAddress, context.getResources().getString(R.string.google_maps_key));
        call.enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Response<LocationResponse> response, Retrofit retrofit) {
                if(!response.body().getStatus().equals("ZERO_RESULTS")) {
                    setLatitudeAndLongitude(googleMap, response.body().getResults().get(0).getGeometry().getLocation().getLat(), response.body().getResults().get(0).getGeometry().getLocation().getLng());
                }
                return;
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
