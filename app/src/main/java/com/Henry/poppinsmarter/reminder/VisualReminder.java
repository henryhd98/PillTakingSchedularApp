package com.Henry.poppinsmarter.reminder;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.HttpCookie;
import java.util.Calendar;

import static android.content.Intent.getIntent;

public class VisualReminder {



    private DatabaseReference mDatabase;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://poppinsmarter-default-rtdb.europe-west1.firebasedatabase.app/");

    DatabaseReference myRef = database.getReference();

    final  DatabaseReference ledstatus1= myRef.child("LEDS/LED1/L1");
    final  DatabaseReference ledstatus2= myRef.child("LEDS/LED2/L2");
    final  DatabaseReference ledstatus3= myRef.child("LEDS/LED3/L3");
    final  DatabaseReference ledstatus4= myRef.child("LEDS/LED4/L4");
    final  DatabaseReference ledstatus5= myRef.child("LEDS/LED5/L5");
    final  DatabaseReference ledstatus6= myRef.child("LEDS/LED6/L6");
    final  DatabaseReference ledstatus7= myRef.child("LEDS/LED7/L7");
    final  DatabaseReference timeStamp1= myRef.child("Activity/mon/alarm");
    final  DatabaseReference timeStamp2= myRef.child("Activity/tues/alarm");
    final  DatabaseReference timeStamp3= myRef.child("Activity/wed/alarm");
    final  DatabaseReference timeStamp4= myRef.child("Activity/thurs/alarm");
    final  DatabaseReference timeStamp5= myRef.child("Activity/fri/alarm");
    final  DatabaseReference timeStamp6= myRef.child("Activity/sat/alarm");
    final  DatabaseReference timeStamp7= myRef.child("Activity/sun/alarm");
    final  DatabaseReference mon= myRef.child("Activity/mon/Taken");
    final  DatabaseReference tue= myRef.child("Activity/tue/Taken");
    final  DatabaseReference wed= myRef.child("Activity/wed/Taken");
    final  DatabaseReference thurs= myRef.child("Activity/thurs/Taken");
    final  DatabaseReference fri= myRef.child("Activity/fri/Taken");
    final  DatabaseReference sat= myRef.child("Activity/sat/Taken");
    final  DatabaseReference sun= myRef.child("Activity/sun/Taken");
    private Calendar mCalendar;
    private int mYear, mMonth, mHour, mMinute, mDay, dayInt;
    private String mTime;
    private String mDate;


    public int setLedStatus() {
        mCalendar = Calendar.getInstance(); //Initialising the Calender
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH) + 1;
        mDay = mCalendar.get(Calendar.DATE);

        mDate = mDay + "/" + mMonth + "/" + mYear;
        mTime = mHour + ":" + mMinute;

        int day = mCalendar.get(Calendar.DAY_OF_WEEK);
        String dateTime= "Alarm Info: " + mDate+ " @" + mTime;







        switch (day) {
            case 1:
                ledstatus7.setValue("ON");
                timeStamp7.setValue(dateTime);
                break;
            case 2:
                ledstatus1.setValue("ON");
                timeStamp1.setValue(dateTime);
                break;
            case 3:
                ledstatus2.setValue("ON");
                timeStamp2.setValue(dateTime);
                break;
            case 4:
                ledstatus3.setValue("ON");
                timeStamp3.setValue(dateTime);
                break;
            case 5:
                ledstatus4.setValue("ON");
                timeStamp4.setValue(dateTime);
                break;
            case 6:
                ledstatus5.setValue("ON");
                timeStamp5.setValue(dateTime);
                break;
            case 7:
                ledstatus6.setValue("ON");
                timeStamp6.setValue(dateTime);
                break;
            default:

                timeStamp1.setValue("Monday, no alarms set");
                timeStamp2.setValue("Monday, no alarms set");
                timeStamp3.setValue("Monday, no alarms set");
                timeStamp4.setValue("Monday, no alarms set");
                timeStamp5.setValue("Monday, no alarms set");
                timeStamp6.setValue("Monday, no alarms set");
                timeStamp7.setValue("Monday, no alarms set");


        }


        ledstatus1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //this method is called once with the initial value and again whenever data at this location is updated

                String value = dataSnapshot.getValue(String.class);
                Log.d("file", "Value is : " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                //Failed to read value
                Log.w("file", "Failed to read value", error.toException());
            }

        });

        ledstatus2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //this method is called once with the initial value and again whenever data at this location is updated

                String value = dataSnapshot.getValue(String.class);
                Log.d("file", "Value is : " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                //Failed to read value
                Log.w("file", "Failed to read value", error.toException());
            }

        });

        ledstatus3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //this method is called once with the initial value and again whenever data at this location is updated

                String value = dataSnapshot.getValue(String.class);
                Log.d("file", "Value is : " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                //Failed to read value
                Log.w("file", "Failed to read value", error.toException());
            }

        });

        ledstatus4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //this method is called once with the initial value and again whenever data at this location is updated

                String value = dataSnapshot.getValue(String.class);
                Log.d("file", "Value is : " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                //Failed to read value
                Log.w("file", "Failed to read value", error.toException());
            }

        });

        ledstatus5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //this method is called once with the initial value and again whenever data at this location is updated

                String value = dataSnapshot.getValue(String.class);
                Log.d("file", "Value is : " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                //Failed to read value
                Log.w("file", "Failed to read value", error.toException());
            }

        });

        ledstatus6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //this method is called once with the initial value and again whenever data at this location is updated

                String value = dataSnapshot.getValue(String.class);
                Log.d("file", "Value is : " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                //Failed to read value
                Log.w("file", "Failed to read value", error.toException());
            }

        });


        ledstatus7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //this method is called once with the initial value and again whenever data at this location is updated

                String value = dataSnapshot.getValue(String.class);
                Log.d("file", "Value is : " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                //Failed to read value
                Log.w("file", "Failed to read value", error.toException());
            }

        });
        return day;

    }

    public void taken(){
        mCalendar = Calendar.getInstance(); //Initialising the Calender
        int day = mCalendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case 1:
                sun.setValue("yes");

                break;
            case 2:
                mon.setValue("yes");
                break;
            case 3:
                tue.setValue("yes");
                break;
            case 4:
                wed.setValue("yes");
                break;
            case 5:
                thurs.setValue("yes");
                break;
            case 6:
                fri.setValue("yes");
                break;
            case 7:
                sat.setValue("yes");
                break;
            default:



        }
    }

    public void notTaken(){
        mCalendar = Calendar.getInstance(); //Initialising the Calender
        int day = mCalendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case 1:
                sun.setValue("no");

                break;
            case 2:
                mon.setValue("no");
                break;
            case 3:
                tue.setValue("no");
                break;
            case 4:
                wed.setValue("no");
                break;
            case 5:
                thurs.setValue("no");
                break;
            case 6:
                fri.setValue("no");
                break;
            case 7:
                sat.setValue("no");
                break;
            default:



        }
    }
    public void turnLEDoff(){
        ledstatus1.setValue("OFF");
        ledstatus2.setValue("OFF");
        ledstatus3.setValue("OFF");
        ledstatus4.setValue("OFF");
        ledstatus5.setValue("OFF");
        ledstatus6.setValue("OFF");
        ledstatus7.setValue("OFF");


        }
}


