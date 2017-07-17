package com.downloader.app.downloader;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Date;

import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    final String url = "http://cdn3.nflximg.net/images/3093/2043093.jpg";
    private BroadcastReceiver mDLCompleteReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        DownloadManager.Request request = null;

        try {
            request = new DownloadManager.Request(Uri.parse(url));
        } catch (IllegalArgumentException e) {
            Log.d("Slider Demo","Error" + e.getMessage());
        }

          /* allow mobile and WiFi downloads */
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setTitle("DM Example");
        request.setDescription("Downloading file");

         /* we let the user see the download in a notification */
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                /* Try to determine the file extension from the url. Only allow image types. You
                 * can skip this check if you only plan to handle the downloaded file manually and
                 * don't care about file managers not recognizing the file as a known type */
        String[] allowedTypes = {"png", "jpg", "jpeg", "gif", "webp"};
        String suffix = url.substring(url.lastIndexOf(".") + 1).toLowerCase();

        if (!Arrays.asList(allowedTypes).contains(suffix)) {
            Log.d("Invalid file extension"," Allowed types: \\n");

            for (String s : allowedTypes) {
                Log.d("\n","."+s);
            }
        }

          /* set the destination path for this download */
//        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS +File.separator + "2043093", name + "." + suffix);

        File direct = new File(Environment.getExternalStorageDirectory()
                + "/dhaval_files");

        if (!direct.exists()) {
            direct.mkdirs();
        }

        final DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                /* this is our unique download id */
        final long DL_ID = dm.enqueue(request);



        setContentView(R.layout.activity_main);




    }
}
