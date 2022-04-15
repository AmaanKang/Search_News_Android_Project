package ca.mohawk.kaur;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * ImageAsyncTask class downloads an image in the background and displays it in imageView of NewsActivity
 */
public class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private String TAG = "==ImageAsyncTask==";
    public static int HTTP_OK = 200;

    /**
     * Downloads image in background
     * @param urls image urls
     * @return Bitmap image
     */
    protected Bitmap doInBackground(String... urls) {
        Bitmap bmp = null;
        int statusCode = -1;
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            statusCode = conn.getResponseCode();
            if (statusCode == HTTP_OK) {
                bmp = BitmapFactory.decodeStream(conn.getInputStream());
            }
        } catch (IOException e) {
            Log.d(TAG,e.toString());
        }
        return bmp;
    }

    /**
     * After doInBackground method runs, onPostExecute method displays image
     * @param result downloaded Bitmap image
     */
    protected void onPostExecute(Bitmap result) {
        if (result != null) {
            Activity main = NewsActivity.getCurrentActivity();
            ImageView imageView = main.findViewById(R.id.news_image);
            imageView.setImageBitmap(result);
        }
    }
}
