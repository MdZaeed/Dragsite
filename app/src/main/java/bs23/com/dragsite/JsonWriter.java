package bs23.com.dragsite;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import Services.AsyncFileWriter;
import bs23.com.dragsite.utils.JsonKeys;
import bs23.com.dragsite.widgets.ImageViewWidget;

/**
 * Created by BS-86 on 5/17/2016.
 */
public class JsonWriter {

    static JsonWriter jsonWriter;
    static Context context;
    private static JSONArray jsonArray;
    private static JSONObject jsonObject;
    private static List<Integer> widgetSequence;

    public static JsonWriter getInstance(Context context) {
        if (jsonWriter == null) {
            jsonWriter = new JsonWriter(context);
        }
        return jsonWriter;
    }

    private JsonWriter(Context context) {
        this.context = context;

        jsonArray = new JSONArray();
        jsonObject = new JSONObject();
    }

    public void writeToFile(int id, String key, String value) {
/*
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
        Log.d("Path: " , file.getAbsolutePath());
*/

        try {
            JSONObject tempJsonObject = jsonObject.getJSONObject(String.valueOf(id));
            tempJsonObject.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new AsyncFileWriter().execute(String.valueOf(jsonObject));

        printArray();

/*        FileOutputStream outputStream;

        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(message.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public void writeToFile(int id, String key, int value) {
        try {
            JSONObject tempJsonObject = jsonObject.getJSONObject(String.valueOf(id));
            tempJsonObject.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(int id, String key, Double value) {
        try {
            JSONObject tempJsonObject = jsonObject.getJSONObject(String.valueOf(id));
            tempJsonObject.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(int id, String key, Float value) {
        try {
            JSONObject tempJsonObject = jsonObject.getJSONObject(String.valueOf(id));
            tempJsonObject.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void createImageWidgetObject(ImageViewWidget imageViewWidget) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JsonKeys.IMAGE_WIDGET_ALTERNATIVE_TEXT, imageViewWidget.getAlternateText());
            jsonObject.put(JsonKeys.IMAGE_WIDGET_CAPTION, imageViewWidget.getCaptionString());

            this.jsonObject.put(String.valueOf(imageViewWidget.getId()), jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new AsyncFileWriter().execute(String.valueOf(jsonObject));

/*
        jsonArray.put(jsonObject);
*/
    }

    private void printArray() {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject obj = jsonArray.getJSONObject(i);
                Log.i("Json Object", obj.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Integer> getWidgetSequence() {
        return widgetSequence;
    }

    public void setWidgetSequence(List<Integer> widgetSequence) {
        this.widgetSequence = widgetSequence;
        jsonArray=new JSONArray();

        for(Integer integer: widgetSequence)
        {
/*
            Log.i("Sequnce element", integer + "");
*/
            jsonArray.put(integer);
        }

        try {
            jsonObject.put(JsonKeys.WIDGET_IDS,jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("Json" , String.valueOf(jsonObject));

        new AsyncFileWriter().execute(String.valueOf(jsonObject));
    }
}
