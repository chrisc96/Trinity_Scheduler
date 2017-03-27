package com.example.chris.TrinityScheduler;

import java.util.ArrayList;

public class Schedule {

    String dateOfShift;
    String shiftType;
    public ArrayList<String> phoneNumbers;
    String alarmTime;
    String msgTime;
    String msg;

    public Schedule(String date, String shiftType, ArrayList<String> phNums, String alarmTime, String msgSendTime, String msg) {
        this.dateOfShift = date;
        this.shiftType = shiftType;
        this.phoneNumbers = phNums;
        this.alarmTime = alarmTime;
        this.msgTime = msgSendTime;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDateOfShift() {
        return dateOfShift;
    }

    public void setDateOfShift(String dateOfShift) {
        this.dateOfShift = dateOfShift;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getAlarmTime() {return alarmTime;}

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {this.msgTime = msgTime;}

    public String toString() {
        return "Your shift is on " + this.dateOfShift + ". You're in at " + shiftType + ". Your alarm will go off on " + alarmTime + " and your message will send on the " + msgTime;
    }
}