package clothesup.turningpoint.clothesup;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Hanbyeol on 2016-05-18.
 */
/*public class ImageLoader {
    private final String serverUrl = "http://52.196.54.163:1337";

    public ImageLoader() {
        new ThreadPolicy();
    }

    public Bitmap getBitmapImg(String img) {
        Bitmap bitmapImg = null;

        try {
            URL url = new URL(serverUrl + URLEncoder.encode(img, "utf-8"));

            HttpURLConnection httpURLConnectionion = (HttpURLConnection) url.openConnection();
            httpURLConnectionion.setDoInput(true);
            httpURLConnectionion.connect();

            InputStream is = httpURLConnectionion.getInputStream();
            bitmapImg = BitmapFactory.decodeStream(is);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return bitmapImg;
    }
}
*/