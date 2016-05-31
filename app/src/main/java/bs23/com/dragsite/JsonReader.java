package bs23.com.dragsite;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Services.AsyncFileWriter;
import bs23.com.dragsite.utils.JsonKeys;
import bs23.com.dragsite.widgets.BaseLinearLayout;
import bs23.com.dragsite.widgets.ImageViewWidget;
import bs23.com.dragsite.widgets.TitleViewWidget;

/**
 * Created by BS-86 on 5/17/2016.
 */
public class JsonReader {

    static JsonReader jsonReader;
    static Context context;
    File file;
    JSONObject jsonObject;
    JSONArray keyArray;
    JSONArray sequencedObjectArray;
    List<BaseLinearLayout> baseLinearLayouts;

    public static JsonReader getInstance(Context context) {
        if (jsonReader == null) {
            jsonReader = new JsonReader(context);
        }
        return jsonReader;
    }

    private JsonReader(Context context) {
        this.context = context;
        sequencedObjectArray=new JSONArray();
        baseLinearLayouts=new ArrayList<>();
    }

    public List<BaseLinearLayout> readFromFile()
    {
        file = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)), AsyncFileWriter.filename);

        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();

/*
            Log.i("Read",text.toString());
*/
            jsonObject=new JSONObject(text.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        createTheSequentialFile();

        return baseLinearLayouts;
    }

    private void createTheSequentialFile() {
        try {
            keyArray=jsonObject.getJSONArray(JsonKeys.WIDGET_IDS);

            for (int i=0;i<keyArray.length();i++)
            {
                Integer integer=keyArray.getInt(i);
/*
                sequencedObjectArray.put(jsonObject.get(String.valueOf(integer)));
*/
                JSONObject tempJsonObject=jsonObject.getJSONObject(String.valueOf(integer));

                if(tempJsonObject.get(JsonKeys.WIDGET_TYPE).equals(ImageViewWidget.TYPE))
                {
                    ImageViewWidget imageViewWidget=new ImageViewWidget(context.getApplicationContext());
/*
                    imageViewWidget.setCaptionStringAndUI(JsonKeys.IMAGE_WIDGET_CAPTION);
*/
                    imageViewWidget.setCaptionString(tempJsonObject.getString(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_CAPTION));
                    imageViewWidget.setSpacingAbove(tempJsonObject.getInt(JsonKeys.CommonKeys.IMAGE_WIDGET_SPACING_ABOVE));
                    imageViewWidget.setSpacingBelow(tempJsonObject.getInt(JsonKeys.CommonKeys.IMAGE_WIDGET_SPACING_BELOW));
                    imageViewWidget.setSpacingLeft(tempJsonObject.getInt(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_LEFT));
                    imageViewWidget.setSpacingRight(tempJsonObject.getInt(JsonKeys.ImageWidgetKeys.IMAGE_WIDGET_SPACING_RIGHT));
                    baseLinearLayouts.add(imageViewWidget);
                }else if(tempJsonObject.get(JsonKeys.WIDGET_TYPE).equals(TitleViewWidget.TYPE))
                {
                    TitleViewWidget titleViewWidget=new TitleViewWidget(context.getApplicationContext());
                    titleViewWidget.setTitleText(tempJsonObject.getString(JsonKeys.TITLE_WIDGET_TEXT));
                    baseLinearLayouts.add(titleViewWidget);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
