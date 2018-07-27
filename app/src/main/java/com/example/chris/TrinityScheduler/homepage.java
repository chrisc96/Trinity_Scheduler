package com.example.chris.TrinityScheduler;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class homepage extends BaseActivity {

    public static ArrayList<Schedule> schedules = new ArrayList<>();

    private static final int REQUEST_PERMS = 1;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.SET_ALARM
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyStoragePermissions(this);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your content_main.xml
        getLayoutInflater().inflate(R.layout.homepage, contentFrameLayout);
    }



    //persmission method.
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        int contactPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS);
        int readAlarm = ActivityCompat.checkSelfPermission(activity, Manifest.permission.SET_ALARM);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED
                || contactPermission != PackageManager.PERMISSION_GRANTED || readAlarm != PackageManager.PERMISSION_GRANTED) {

            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_PERMS
            );
        }
    }

    public void startRoster(View view) {
        Intent intent = new Intent(getApplicationContext(), add_roster.class);
        startActivity(intent);
    }
}