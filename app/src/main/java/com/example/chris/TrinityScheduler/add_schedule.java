package com.example.chris.TrinityScheduler;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class add_schedule extends BaseActivity {
    String date;
    String shift;
    String msg;
    TextView shiftInfo;
    TimePicker msgTimeP;
    TimePicker alarmTimeP;
    ArrayList<String> phoneNums = new ArrayList<String>();


    public static final int CONTACTS_REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule);

        Intent intent = getIntent();
        shift = intent.getStringExtra("shift");
        date = intent.getStringExtra("date");

        shiftInfo = (TextView) findViewById(R.id.shiftInfo);
        shiftInfo.setText(shiftInfo.getText() + " " + date + "  /  " + shift);

        msgTimeP = (TimePicker) findViewById(R.id.timePickerMsg);
        msgTimeP.setIs24HourView(false);

        alarmTimeP = (TimePicker) findViewById(R.id.timePickerAlarm);
        alarmTimeP.setIs24HourView(false);
    }

    public void addEntry(View v) {
        if (checkRequirements()) {
            int hourMsg = msgTimeP.getHour();
            int minuteMsg = msgTimeP.getMinute();
            int Msg_AM_PM = amPm(hourMsg);
            String msgTime = intoDate(hourMsg, minuteMsg, Msg_AM_PM);

            int hourAlarm = alarmTimeP.getHour();
            int minuteAlarm = alarmTimeP.getMinute();
            int Alarm_AM_PM = amPm(hourAlarm);
            String alarmTime = intoDate(hourAlarm, minuteAlarm, Alarm_AM_PM);
            msg = ((EditText) findViewById(R.id.messageTxt)).getText().toString();

            Schedule schedule = new Schedule(date, shift, phoneNums, msgTime, alarmTime, msg);
            homepage.schedules.add(schedule);

            Intent intent = new Intent();
            intent.putExtra(add_roster.SHOW_BUTTON, true);
            setResult(RESULT_OK, intent);
            finish();
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "Required fields missing!";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public int amPm(int hour) {
        return (hour < 12) ? 1 : 0; // 1 = AM, 0 = PM
    }

    public int monthToInt(String month) {
        int intMonth = 0;
        switch(month) {
            case "Jan":
                intMonth = 1;
                break;
            case "Feb":
                intMonth = 2;
                break;
            case "Mar":
                intMonth = 3;
                break;
            case "Apr":
                intMonth = 4;
                break;
            case "May":
                intMonth = 5;
                break;
            case "Jun":
                intMonth = 6;
                break;
            case "Jul":
                intMonth = 7;
                break;
            case "Aug":
                intMonth = 8;
                break;
            case "Sep":
                intMonth = 9;
                break;
            case "Oct":
                intMonth = 10;
                break;
            case "Nov":
                intMonth = 11;
                break;
            case "Dec":
                intMonth = 12;
                break;
        }
        return intMonth;
    }

    public String intoDate(int hour, int minute, int amPm) {

        DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(date.substring(7,11)));
        cal.set(Calendar.MONTH, monthToInt(date.substring(3,6))-1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(date.substring(0,2)));
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.AM_PM, amPm);

        return sdf.format(cal.getTime());
    }

    public boolean checkRequirements() {
        EditText phoneTxt = (EditText) findViewById(R.id.phoneTxt);
        EditText messageTxt = (EditText) findViewById(R.id.messageTxt);
        if (phoneTxt.length() != 0 && messageTxt.length() != 0) {
            return true;
        }
        return false;
    }

    public void findContact(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        startActivityForResult(intent,CONTACTS_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case CONTACTS_REQ_CODE:
                if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                    EditText phNum = (EditText) findViewById(R.id.phoneTxt);

                    Uri contactData = data.getData();
                    Cursor cursor = getContentResolver().query(contactData, null, null, null, null);
                    cursor.moveToFirst();
                    String number = cursor.getString(cursor.getColumnIndexOrThrow
                            (ContactsContract.CommonDataKinds.Phone.NUMBER));
                    phNum.append(number + ", ");
                    phoneNums.add(number);
                }
        }
    }
}
