package bs23.com.dragsite.api;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by BS-86 on 1/28/2016.
 */
public class YoutubeRestAdapter {
    public YoutubeApi youtubeApi;
    Retrofit retrofit;
    public static String BASE_URL="https://www.googleapis.com";

    public YoutubeRestAdapter()
    {
        OkHttpClient okHttpClient=new OkHttpClient();
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.interceptors().add(httpLoggingInterceptor);
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        youtubeApi=retrofit.create(YoutubeApi.class);
    }
}
