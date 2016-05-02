package bs23.com.dragsite.api;

import bs23.com.dragsite.model.LocationResponse;
import bs23.com.dragsite.model.YoutubeResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by BS-86 on 4/15/2016.
 */
public interface YoutubeApi {

    @GET("/youtube/v3/videos?part=snippet")
    Call<YoutubeResponse> getYoutubeVideoInfo(@Query("id") String id,
                                              @Query("key") String key);
}
