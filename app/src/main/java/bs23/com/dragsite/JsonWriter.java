package bs23.com.dragsite;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;

import bs23.com.dragsite.widgets.ImageViewWidget;

/**
 * Created by BS-86 on 5/17/2016.
 */
public class JsonWriter {

    static JsonWriter jsonWriter;
    static Context context;
    File file;
    String filename = "myfile";
    JSONArray jsonArray;

    public static JsonWriter getInstance(Context context) {
        if (jsonWriter == null) {
            jsonWriter = new JsonWriter(context);
        }
        return jsonWriter;
    }

    private JsonWriter(Context context)
    {
        this.context=context;

        file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES),filename);

        jsonArray=new JSONArray();
    }

    public void writeToFile(ImageViewWidget imageViewWidget)
    {
/*
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
        Log.d("Path: " , file.getAbsolutePath());
*/

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("alternateText",imageViewWidget.getAlternateText());
            jsonObject.put("spacingAbove",imageViewWidget.getSpacingAbove());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        jsonArray.put(jsonObject);

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

    private void printArray() {
        for (int i=0; i < jsonArray.length(); i++) {
            try {
                JSONObject obj = jsonArray.getJSONObject(i);
                Log.i("Json Object",obj.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
