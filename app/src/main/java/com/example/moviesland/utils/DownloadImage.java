package com.example.moviesland.utils;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.moviesland.R;

import java.io.File;

public class DownloadImage {

    private Context context;
    private String imageName;

    public void downloadImage(Context context, String imagePath) {

        this.context = context;
        imageName = imagePath.substring(1, imagePath.indexOf("." + context.getResources().getString(R.string.jpg)));

        if (!createFile().exists()) {
            if (permissionGranted()) {
                DownloadManager manager
                        = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(context.getResources().getString(R.string.image_base_url) +
                        imagePath);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setDestinationInExternalPublicDir(context.getResources()
                                .getString(R.string.download_dir_path),
                        imageName + "." + context.getResources().getString(R.string.jpg));
                assert manager != null;
                manager.enqueue(request);
            }
        } else
            Toast.makeText(context, context.getResources().getString(R.string.image_downloaded), Toast.LENGTH_SHORT).show();

    }

    private File createFile() {
        return new File(Environment.getExternalStoragePublicDirectory(
                context.getResources().getString(R.string.download_dir_path)) + "/" + imageName + "." + context.getResources().getString(R.string.jpg));
    }

    private boolean permissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions((Activity) context
                        , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {
            return true;
        }
    }

}
