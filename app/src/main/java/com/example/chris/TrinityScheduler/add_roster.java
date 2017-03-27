package com.example.chris.TrinityScheduler;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class add_roster extends BaseActivity {

    private Toolbar toolbar;

    File roster;
    EditText setName;
    String nameToMatch;
    int rowOfWorker;
    public static final String SHOW_BUTTON = "shouldShowButton";

    Button add1,add2,add3,add4,add5,add6,add7;
    Button buttonClicked;

    public static final int EXCEL_REQ_CODE = 2;
    public static final int EDIT_ENTRY = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your content_main.xml
        getLayoutInflater().inflate(R.layout.add_roster, contentFrameLayout);
        setName = (EditText) findViewById(R.id.setName);
    }

    public void setVisibilities() {
        add1.setVisibility(View.INVISIBLE);
        add2.setVisibility(View.INVISIBLE);
        add3.setVisibility(View.INVISIBLE);
        add4.setVisibility(View.INVISIBLE);
        add5.setVisibility(View.INVISIBLE);
        add6.setVisibility(View.INVISIBLE);
        add7.setVisibility(View.INVISIBLE);
    }

    public void uploadOnClick(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        intent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet/*");
        startActivityForResult(intent, EXCEL_REQ_CODE);
    }

    public String getPath(Context context, Uri uri) {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public void add1Click(View v) {
        buttonClicked = add1;
        Intent myIntent = new Intent(this, add_schedule.class);
        myIntent.putExtra("date", ((TextView) findViewById(R.id.day1Date)).getText().toString());
        myIntent.putExtra("shift", ((TextView) findViewById(R.id.day1Shift)).getText().toString());
        startActivityForResult(myIntent,EDIT_ENTRY);
    }

    public void add2Click(View v) {
        buttonClicked = add2;
        Intent myIntent = new Intent(this, add_schedule.class);
        myIntent.putExtra("date", ((TextView) findViewById(R.id.day2Date)).getText().toString());
        myIntent.putExtra("shift", ((TextView) findViewById(R.id.day2Shift)).getText().toString());
        startActivityForResult(myIntent,EDIT_ENTRY);
    }

    public void add3Click(View v) {
        buttonClicked = add3;
        Intent myIntent = new Intent(this, add_schedule.class);
        myIntent.putExtra("date", ((TextView) findViewById(R.id.day3Date)).getText().toString());
        myIntent.putExtra("shift", ((TextView) findViewById(R.id.day3Shift)).getText().toString());
        startActivityForResult(myIntent,EDIT_ENTRY);
    }

    public void add4Click(View v) {
        buttonClicked = add4;
        Intent myIntent = new Intent(this, add_schedule.class);
        myIntent.putExtra("date", ((TextView) findViewById(R.id.day4Date)).getText().toString());
        myIntent.putExtra("shift", ((TextView) findViewById(R.id.day4Shift)).getText().toString());
        startActivityForResult(myIntent,EDIT_ENTRY);
    }

    public void add5Click(View v) {
        buttonClicked = add5;
        Intent myIntent = new Intent(this, add_schedule.class);
        myIntent.putExtra("date", ((TextView) findViewById(R.id.day5Date)).getText().toString());
        myIntent.putExtra("shift", ((TextView) findViewById(R.id.day5Shift)).getText().toString());
        startActivityForResult(myIntent,EDIT_ENTRY);
    }

    public void add6Click(View v) {
        buttonClicked = add6;
        Intent myIntent = new Intent(this, add_schedule.class);
        myIntent.putExtra("date", ((TextView) findViewById(R.id.day6Date)).getText().toString());
        myIntent.putExtra("shift", ((TextView) findViewById(R.id.day6Shift)).getText().toString());
        startActivityForResult(myIntent,EDIT_ENTRY);
    }

    public void add7Click(View v) {
        buttonClicked = add7;
        Intent myIntent = new Intent(this, add_schedule.class);
        myIntent.putExtra("date", ((TextView) findViewById(R.id.day7Date)).getText().toString());
        myIntent.putExtra("shift", ((TextView) findViewById(R.id.day7Shift)).getText().toString());
        startActivityForResult(myIntent,EDIT_ENTRY);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Coming from excel file request
        switch (requestCode) {
            case EXCEL_REQ_CODE:
                if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                    TextView selectedFile = (TextView) findViewById(R.id.selectedFile);

                    Uri uri = data.getData();
                    String path = this.getPath(this, uri);

                    String file = new File(path).getName(); // name of file w/ extension

                    if (path.contains("xlsx")) {
                        selectedFile.setText(file);
                        // Creates a file reference to selected excel file
                        roster = new File(path);
                    } else {
                        selectedFile.setText("Invalid File");
                        roster = null;
                    }
                    nameAndSettings();
                    break;
                }
            case EDIT_ENTRY:
                if (resultCode == RESULT_OK) {
                    buttonClicked.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }

    public void nameAndSettings() {
        TextView nameTxt = (TextView) findViewById(R.id.setNameTxt);
        Button submitOK = (Button) findViewById(R.id.submitOK);
        if (roster == null) {
            setName.setVisibility(View.INVISIBLE);
            nameTxt.setVisibility(View.INVISIBLE);
            submitOK.setVisibility(View.INVISIBLE);
            setVisibilities();
            return;
        } else {
            setName.setVisibility(View.VISIBLE);
            nameTxt.setVisibility(View.VISIBLE);
            submitOK.setVisibility(View.VISIBLE);
        }
    }

    public void inRoster(View v) {
        if (checkValidinFile()) {
            TextView worker = (TextView) findViewById(R.id.workerName);
            nameToMatch = nameToMatch.substring(0,1).toUpperCase() + nameToMatch.substring(1,nameToMatch.length()); // Makes first letter of name uppercase again
            worker.setText(nameToMatch + "'s Roster: ");
            fillRoster();
            System.out.println(homepage.schedules.size());

            // Send to a new page that lists your shifts, what time you want your alarms
            // and what time you want messages to be sent
            // What time do you want to send the messages?
            // What time do you want your alarms to be?
            // Do all alarm and message sending bs
        } else {
            // Display a toast message saying name not found
            // Clear contents of the editbox for the name for user to re enter
            CharSequence text = "Name not found in Roster!";
            setName.setText("");
            nameToMatch = "";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(this, text, duration).show();
        }
    }

    public boolean checkValidinFile() {
        nameToMatch = setName.getText().toString().toLowerCase();

        try {
            InputStream ExcelFileToRead = new FileInputStream(roster);
            XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
            XSSFSheet sheet = wb.getSheetAt(0);

            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    if (nameToMatch.equalsIgnoreCase(currentCell.toString().toLowerCase())) {
                        rowOfWorker = currentRow.getRowNum();
                        return true;
                    }
                }
            }
            wb.close();
            ExcelFileToRead.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        rowOfWorker = -1; // error handling for worker none found
        return false;
    }

    public void fillRoster() {
        nameToMatch = setName.getText().toString().toLowerCase();

        try {
            InputStream ExcelFileToRead = new FileInputStream(roster);
            XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
            XSSFSheet sheet = wb.getSheetAt(0);

            Iterator<Row> iterator = sheet.iterator();
            int dayCount = 1;
            int shiftCount = 1;

            // Storing weekly roster in array for the worker
            String[] weeklyRoster = new String[7];
            XSSFRow row = sheet.getRow(rowOfWorker);
            for (int i = 1; i <= 7; i++) {
                if (row.getCell(i) == null) {
                    weeklyRoster[i-1] = "X";
                }
                else {
                    weeklyRoster[i-1] = row.getCell(i).toString();
                }
            }

            TextView day1 = (TextView) findViewById(R.id.day1Shift);
            day1.setText(weeklyRoster[0]);
            TextView day2 = (TextView) findViewById(R.id.day2Shift);
            day2.setText(weeklyRoster[1]);
            TextView day3 = (TextView) findViewById(R.id.day3Shift);
            day3.setText(weeklyRoster[2]);
            TextView day4 = (TextView) findViewById(R.id.day4Shift);
            day4.setText(weeklyRoster[3]);
            TextView day5 = (TextView) findViewById(R.id.day5Shift);
            day5.setText(weeklyRoster[4]);
            TextView day6 = (TextView) findViewById(R.id.day6Shift);
            day6.setText(weeklyRoster[5]);
            TextView day7 = (TextView) findViewById(R.id.day7Shift);
            day7.setText(weeklyRoster[6]);


            add1 = (Button) findViewById(R.id.day1Add);
            add2 = (Button) findViewById(R.id.day2Add);
            add3 = (Button) findViewById(R.id.day3Add);
            add4 = (Button) findViewById(R.id.day4Add);
            add5 = (Button) findViewById(R.id.day5Add);
            add6 = (Button) findViewById(R.id.day6Add);
            add7 = (Button) findViewById(R.id.day7Add);

            for (int i = 0; i < weeklyRoster.length; i++) {
                if (!weeklyRoster[i].equalsIgnoreCase("X")) {
                    switch(i) {
                        case 0:
                            add1.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            add2.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            add3.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            add4.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            add5.setVisibility(View.VISIBLE);
                            break;
                        case 5:
                            add6.setVisibility(View.VISIBLE);
                            break;
                        case 6:
                            add7.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            }

            for (Schedule sched : homepage.schedules) {
                if (sched.getDateOfShift().contains(((TextView) findViewById(R.id.day1Date)).getText())) {
                    add1.setVisibility(View.INVISIBLE);
                }
                if (sched.getDateOfShift().contains(((TextView) findViewById(R.id.day2Date)).getText())) {
                    add2.setVisibility(View.INVISIBLE);
                }
                if (sched.getDateOfShift().contains(((TextView) findViewById(R.id.day3Date)).getText())) {
                    add3.setVisibility(View.INVISIBLE);
                }
                if (sched.getDateOfShift().contains(((TextView) findViewById(R.id.day4Date)).getText())) {
                    add4.setVisibility(View.INVISIBLE);
                }
                if (sched.getDateOfShift().contains(((TextView) findViewById(R.id.day5Date)).getText())) {
                    add5.setVisibility(View.INVISIBLE);
                }
                if (sched.getDateOfShift().contains(((TextView) findViewById(R.id.day6Date)).getText())) {
                    add6.setVisibility(View.INVISIBLE);
                }
                if (sched.getDateOfShift().contains(((TextView) findViewById(R.id.day7Date)).getText())) {
                    add7.setVisibility(View.INVISIBLE);
                }
            }


            while (iterator.hasNext()) {
                if (rowOfWorker == -1) break; // Double checking name was found

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    if (currentRow.getRowNum() == 0 && DateUtil.isCellDateFormatted(currentCell)) {
                        switch (dayCount) {
                            case 1:
                                TextView day1Date = (TextView) findViewById(R.id.day1Date);
                                day1Date.setText(currentCell.toString());
                                dayCount++;
                                break;
                            case 2:
                                TextView day2Date = (TextView) findViewById(R.id.day2Date);
                                day2Date.setText(currentCell.toString());
                                dayCount++;
                                break;
                            case 3:
                                TextView day3Date = (TextView) findViewById(R.id.day3Date);
                                day3Date.setText(currentCell.toString());
                                dayCount++;
                                break;
                            case 4:
                                TextView day4Date = (TextView) findViewById(R.id.day4Date);
                                day4Date.setText(currentCell.toString());
                                dayCount++;
                                break;
                            case 5:
                                TextView day5Date = (TextView) findViewById(R.id.day5Date);
                                day5Date.setText(currentCell.toString());
                                dayCount++;
                                break;
                            case 6:
                                TextView day6Date = (TextView) findViewById(R.id.day6Date);
                                day6Date.setText(currentCell.toString());
                                dayCount++;
                                break;
                            case 7:
                                TextView day7Date = (TextView) findViewById(R.id.day7Date);
                                day7Date.setText(currentCell.toString());
                                dayCount++;
                                break;
                        }
                    }
                }
            }
            wb.close();
            ExcelFileToRead.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}