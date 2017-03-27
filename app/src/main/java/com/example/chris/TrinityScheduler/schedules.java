package com.example.chris.TrinityScheduler;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ListView;

public class schedules extends BaseActivity {

    ListView scheduleList;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your content_main.xml
        getLayoutInflater().inflate(R.layout.schedule, contentFrameLayout);

        adapter = new CustomAdapter(getApplicationContext(), homepage.schedules);
        setupScheduleAdapter();
    }

    public void setupScheduleAdapter() {
        scheduleList = (ListView) findViewById(R.id.schedule_list); // Currently being null
        scheduleList.setAdapter(adapter);
    }
}