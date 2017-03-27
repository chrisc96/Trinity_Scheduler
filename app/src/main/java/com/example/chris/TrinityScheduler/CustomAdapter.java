package com.example.chris.TrinityScheduler;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Schedule> {

    ArrayList<String> phNums = new ArrayList();
    public static Button buttonClicked;

    public CustomAdapter(Context context, ArrayList<Schedule> schedules) {
        super(context, R.layout.schedule_row, schedules);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View customView = convertView;
        if (customView == null) {
            LayoutInflater scheduleInflater = LayoutInflater.from(getContext());
            customView = scheduleInflater.inflate(R.layout.schedule_row, parent, false);
        }

        phNums = getItem(position).getPhoneNumbers();
        final String dateOfShift = getItem(position).getDateOfShift();
        final String shiftType = getItem(position).getShiftType();
        final String alarmTime = getItem(position).getAlarmTime();
        final String msgTime = getItem(position).getMsgTime();
        final String msg = getItem(position).getMsg();

        final TextView date = (TextView) customView.findViewById(R.id.listViewDate);
        final TextView alarm = (TextView) customView.findViewById(R.id.listViewAlarm);
        final TextView txtMsg = (TextView) customView.findViewById(R.id.listViewMsg);
        final TextView shift = (TextView) customView.findViewById(R.id.listViewShift);

        date.setTextColor(Color.BLACK);
        alarm.setTextColor(Color.BLACK);
        txtMsg.setTextColor(Color.BLACK);
        shift.setTextColor(Color.BLACK);

        date.setText(dateOfShift);
        alarm.append(alarmTime);
        txtMsg.append((msg) + " @" + msgTime);
        shift.append(shiftType);

        return customView;
    }
}