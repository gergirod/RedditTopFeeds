package germangirod.devigettest.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.MediaStore;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by germangirod on 12/10/15.
 */
public class ImageSaverTask extends AsyncTask<String, Void, String> {

    private Context context;

    public ImageSaverTask(Context context){
        this.context=context;
    }

    protected String doInBackground(String... urls) {

        URL imageurl = null;
        try {
            imageurl = new URL(urls[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(imageurl.openConnection().getInputStream());
            MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "", "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return urls[0];
    }

    protected void onPostExecute(String image) {
    }
}
