package com.example.imagedownloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImageTask extends AsyncTaskLoader<Bitmap> {


    public DownloadImageTask(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public Bitmap loadInBackground() {
        try {
            URL url=new URL("https://img.freepik.com/free-vector/donkey-with-face-expression-white-background_1308-80934.jpg");
//            creating Connection to URL-->
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setReadTimeout(10000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
//            Getting the Data back and Storing in an InputStream object-->
            InputStream stream=conn.getInputStream();
//            Convert the InputStreamObject to bitmap and return the Bitmap
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deliverResult(@Nullable Bitmap data) {
        super.deliverResult(data);
    }
}
