package utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import com.truytimkhachno.ttkn.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import entities.Client;

/**
 * Created by Rypon on 10/31/2016.
 */

public class ImageLoader extends AsyncTask<Client, Void, Bitmap> {

    private View view;
    private String url;

    public ImageLoader(View view) {
        this.view = view;
    }

    @Override
    protected Bitmap doInBackground(Client... clients) {
        Bitmap bm = null;
        if (clients[0].getImages().size() > 0) {
            this.url = clients[0].getImages().get(0).getPath();
            try {
                bm = BitmapFactory.decodeStream(new URL(url).openConnection().getInputStream());
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bm;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        System.out.println("=== url: " + url + " = result: " + result);
        if (result != null) {
            ((ImageView)view).setImageBitmap(result);
        }
    }

}
