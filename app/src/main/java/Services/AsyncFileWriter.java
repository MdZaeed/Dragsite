package Services;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by BS-86 on 5/19/2016.
 */
public class AsyncFileWriter extends AsyncTask<String, String, String> {

    File file;
    public static final String filename = "myfile";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        file = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)), filename);
    }

    @Override
    protected String doInBackground(String... params) {

        FileOutputStream outputStream;

        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(params[0].getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
