package bs23.com.dragsite.api;

import bs23.com.dragsite.model.LocationResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by BS-86 on 4/15/2016.
 */
public interface MapApi {

    @GET("/maps/api/geocode/json")
    Call<LocationResponse> getLatAndLng(@Query("address") String address);


    @GET("/maps/api/geocode/json")
    Call<LocationResponse> getAddress(@Query("latlng") String address);
}
