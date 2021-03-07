package com.Henry.poppinsmarter.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/*
   Schedule a reminder alarm at the specified time for the given task.
    context Local application or activity context
     reminderTask Uri referencing the task in the content provider
    */

public class AlarmScheduler {




    public void setAlarm(Context context, long alarmTime, Uri reminderTask, int dayInt) {

        AlarmManager manager = AlarmManagerProvider.getAlarmManager(context); //injects the alarm manager which gives the alarm service

        PendingIntent operation = ReminderAlarmService.getReminderPendingIntent(context, reminderTask); // Passing the 3 parameters into an object of the pending intent

        if (Build.VERSION.SDK_INT >= 23) { //These are for different android sdks so  they all function the same

            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmTime, operation);


        } else if (Build.VERSION.SDK_INT >= 19) {

            manager.setExact(AlarmManager.RTC_WAKEUP, alarmTime, operation);


        } else {

            manager.set(AlarmManager.RTC_WAKEUP, alarmTime, operation);

        }

    }

    public void setRepeatAlarm(Context context, long alarmTime, Uri reminderTask, long RepeatTime, int dayInt) {
        AlarmManager manager = AlarmManagerProvider.getAlarmManager(context);

        PendingIntent operation =
                ReminderAlarmService.getReminderPendingIntent(context, reminderTask);

        manager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime, RepeatTime, operation);


    }

    public void cancelAlarm(Context context, Uri reminderTask, int dayInt) {
        AlarmManager manager = AlarmManagerProvider.getAlarmManager(context);

        PendingIntent operation =
                ReminderAlarmService.getReminderPendingIntent(context, reminderTask);

        manager.cancel(operation);

    }

}