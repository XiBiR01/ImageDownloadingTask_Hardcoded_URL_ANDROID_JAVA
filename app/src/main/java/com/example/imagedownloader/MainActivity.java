package com.example.imagedownloader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Bitmap> {

//    ConnectivityManager & NetworkInfo
    ConnectivityManager connMgr;
    NetworkInfo info;

//  Views
    Button btn;
    ImageView imageView;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);
        imageView=findViewById(R.id.imageView);
        textView=findViewById(R.id.textView);
        connMgr=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        info=connMgr.getActiveNetworkInfo();

    }

    public void downloadImage(View view) {
        if (info!=null && info.isConnected()){/*Checking whether Connection is Available*/
//            If connection Available Load the Task with id - 0
        LoaderManager.getInstance(this).initLoader(0,null,this);
    }
    else {
            Toast.makeText(this, "No Internet, Restart App", Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public Loader<Bitmap> onCreateLoader(int id, @Nullable Bundle args) {
//        Create Instance of the class to Run the task and return the Image Bitmap
        return new DownloadImageTask(this);


    }

    @Override
    public void onLoadFinished(@NonNull Loader<Bitmap> loader, Bitmap data) {
//        Set the image Bitmap to ImageView
        imageView.setImageBitmap(data);
        textView.setText("Working");

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Bitmap> loader) {

    }
}