package bs23.com.dragsite.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import bs23.com.dragsite.R;
import bs23.com.dragsite.api.YoutubeRestAdapter;
import bs23.com.dragsite.model.YoutubeResponse;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class YoutubeWidget extends BaseLinearLayoutWithSpacingNeeds {

    Context context;
    private String videoURL="https://www.youtube.com/watch?v=S176AKQhcCk";
    private String videoId="S176AKQhcCk";
    private char quality=5;
/*    private int spacingAbove;
    private int spacingBelow;*/

 /*   private String spacingAbove;
    private String spacingBelow;*/

    public YoutubeWidget(Context context) {
        super(context);
        this.context = context;
    }

    public void addContents()
    {
        super.addView(LayoutInflater.from(context).inflate(R.layout.widget_youtube,null),new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        addBottomVIew(context);

        spacingAbove = dpToPx(0);
        spacingBelow = dpToPx(0);
        setMainView(this.findViewById(R.id.ll_youtube_widget));
    }

    private void getYoutubeVideo() {
        YoutubeRestAdapter geoCodingRestAdapter=new YoutubeRestAdapter();
        Call<YoutubeResponse> call=geoCodingRestAdapter.youtubeApi.getYoutubeVideoInfo(getVideoId(),context.getResources().getString(R.string.google_maps_key));
        call.enqueue(new Callback<YoutubeResponse>() {
            @Override
            public void onResponse(Response<YoutubeResponse> response, Retrofit retrofit) {
/*
                Log.d("Found",response.body().getItems().get(0).getSnippet().getTitle());
*/
                setViews(response.body());
                return;
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void setViews(YoutubeResponse youtubeResponse) {
        ImageView videoImageView=(ImageView) this.findViewById(R.id.iv_youtube_video);
        Picasso.with(context).load(youtubeResponse.getItems().get(0).getSnippet().getThumbnails().getHigh().getUrl()).into(videoImageView);

        TextView textView=(TextView) this.findViewById(R.id.tv_video_title);
        textView.setText(youtubeResponse.getItems().get(0).getSnippet().getTitle());
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public void setVideoURLThroughID(String videoId)
    {
        this.videoURL = "https://www.youtube.com/watch?v=" + videoId;

        this.setVideoId(videoId);

        getYoutubeVideo();
    }

    public void setVideoIDfromURL(String videoURL)
    {
        this.videoURL=videoURL;

        String delims = "v=";
        String[] tokens = videoURL.split(delims);

        delims="&";
        tokens=tokens[1].split(delims);

        setVideoId(tokens[0]);

        getYoutubeVideo();
    }

/*    public String getSpacingAbove() {
        return spacingAbove;
    }

    public void setSpacingAboveAndUi(String spacingAbove) {
        this.spacingAbove = spacingAbove;
    }

    public String getSpacingBelow() {
        return spacingBelow;
    }

    public void setSpacingBelowAndUi(String spacingBelow) {
        this.spacingBelow = spacingBelow;
    }*/

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public char getQuality() {
        return quality;
    }

    public void setQuality(char quality) {
        this.quality = quality;
    }

/*
    public int getSpacingAbove() {
        return changePxToDp(spacingAbove);
    }

    public void setSpacingAboveAndUi(int spacingAbove) {
        this.spacingAbove = dpToPx(spacingAbove);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.getLayoutParams();
        layoutParams.setMargins(0, this.spacingAbove, 0, spacingBelow);
        this.setLayoutParams(layoutParams);
    }

    public int getSpacingBelow() {
        return changePxToDp(spacingBelow);
    }

    public void setSpacingBelowAndUi(int spacingBelow) {
        this.spacingBelow = dpToPx(spacingBelow);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.getLayoutParams();
        layoutParams.setMargins(0, spacingAbove, 0, this.spacingBelow);
        this.setLayoutParams(layoutParams);
    }

    private int changePxToDp(int px) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
*/


}
