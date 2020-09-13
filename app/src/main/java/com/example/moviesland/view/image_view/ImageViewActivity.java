package com.example.moviesland.view.image_view;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.moviesland.R;
import com.example.moviesland.databinding.ActivityImageViewBinding;
import com.example.moviesland.utils.DownloadImage;

public class ImageViewActivity extends AppCompatActivity {

    private ActivityImageViewBinding imageViewBinding;
    private DownloadImage downloadImage;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageViewBinding = DataBindingUtil.setContentView(ImageViewActivity.this,
                R.layout.activity_image_view);

        getIntentExtras();

        imageViewBinding.btnBack.setOnClickListener(view -> super.onBackPressed());

        downloadImage = new DownloadImage();

        imageViewBinding.btnDownload.setOnClickListener(view -> {
            if (imagePath != null) {
                downloadImage.downloadImage(ImageViewActivity.this, imagePath);
                IntentFilter intentFilter = new IntentFilter(
                        DownloadManager.ACTION_DOWNLOAD_COMPLETE);
                registerReceiver(myBroadcastReciever, intentFilter);
            }
        });

    }

    private void getIntentExtras() {

        if (getIntent().getSerializableExtra(getString(R.string.image_path)) != null) {
            imagePath = (String) getIntent().getSerializableExtra(getString(R.string.image_path));
            imageViewBinding.setImagePath(imagePath);
        }

    }

    //    To show a toast message if the image is downloaded successfully
    private BroadcastReceiver myBroadcastReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, getString(R.string.image_downloaded_success)
                    , Toast.LENGTH_SHORT).show();
        }
    };

    //    retry download on permission granted
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            permission granted
            downloadImage.downloadImage(ImageViewActivity.this, imagePath);
        }
    }

}
