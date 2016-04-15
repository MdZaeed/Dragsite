package bs23.com.dragsite;

import bs23.com.dragsite.model.LocationResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by BS-86 on 4/15/2016.
 */
public interface MapApi {

    @GET("/maps/api/geocode/json")
    Call<LocationResponse> getLatAndLng(@Query("address") String address,
                                      @Query("key") String key);
}
