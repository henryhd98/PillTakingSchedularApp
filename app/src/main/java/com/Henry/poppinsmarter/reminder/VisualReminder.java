package com.Henry.poppinsmarter.reminder;

import android.app.PendingIntent;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.HttpCookie;
import java.util.Calendar;

public class VisualReminder {



    private DatabaseReference mDatabase;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://poppinsmarter-default-rtdb.europe-west1.firebasedatabase.app/");

    DatabaseReference myRef = database.getReference();

    final  DatabaseReference ledstatus1= myRef.child("LED1");
    final  DatabaseReference ledstatus2= myRef.child("LED2");
    final  DatabaseReference ledstatus3= myRef.child("LED3");
    final  DatabaseReference ledstatus4= myRef.child("LED4");
    final  DatabaseReference ledstatus5= myRef.child("LED5");
    final  DatabaseReference ledstatus6= myRef.child("LED6");
    final  DatabaseReference ledstatus7= myRef.child("LED7");
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


        // PendingIntent operation = ReminderAlarmService.on(dayInt); // Passing the 3 parameters into an object of the pending intent


        switch (day) {
            case 1:
                ledstatus7.setValue("ON");
                break;
            case 2:
                ledstatus1.setValue("ON");
                break;
            case 3:
                ledstatus2.setValue("ON");
                break;
            case 4:
                ledstatus3.setValue("ON");
                break;
            case 5:
                ledstatus4.setValue("ON");
                break;
            case 6:
                ledstatus5.setValue("ON");
                break;
            case 7:
                ledstatus6.setValue("ON");
                break;
            default:
                ledstatus1.setValue("OFF");
                ledstatus2.setValue("OFF");
                ledstatus3.setValue("OFF");
                ledstatus4.setValue("OFF");
                ledstatus5.setValue("OFF");
                ledstatus6.setValue("OFF");
                ledstatus7.setValue("OFF");


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
}
