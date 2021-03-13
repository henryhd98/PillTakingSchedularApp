package com.Henry.poppinsmarter;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class AlarmActivity extends AppCompatActivity {
    private EditText notes;
    private ListView alarms;
    private TextView mtextView;
    ArrayList<Map<String, Object>> activityArrayList;
    String dateTime, activity;





   /* final  DatabaseReference timeStamp2= ref.child("Activity");
    final  DatabaseReference timeStamp3= ref.child("Activity");
    final  DatabaseReference timeStamp4= ref.child("Activity");
    final  DatabaseReference timeStamp5= ref.child("Activity");
    final  DatabaseReference timeStamp6= ref.child("Activity");
    final  DatabaseReference timeStamp7= ref.child("Activity");
*/


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_history);

        // initializing variables for listviews.
        alarms = findViewById(R.id.alarms);

        // initializing our array list
        activityArrayList = new ArrayList<Map<String, Object>>();

        // calling a method to get data from
        // Firebase and set data to list view
        initializeListView();
    }

    private void initializeListView() {
        DatabaseReference rootRef = FirebaseDatabase.getInstance("https://poppinsmarter-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        DatabaseReference usersRef = rootRef.child("Activity");


        // creating a new array adapter for our list view.
        final ArrayAdapter<Map<String, Object>> adapter = new ArrayAdapter<Map<String, Object>>(this, android.R.layout.simple_dropdown_item_1line, activityArrayList);


        // below line is used for getting reference
        // of our Firebase Database.

        // in below line we are calling method for add child event
        // listener to get the child of our database.

        usersRef.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                // this method is called when new child is added to
                // our data base and after adding new child
                // we are adding that item inside our array list and
                // notifying our adapter that the data in adapter is changed.

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();

                    activityArrayList.add(map);
                 //   Log.d("TAG", timeDate );
                    adapter.notifyDataSetChanged();
                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // this method is called when we get any
                // error from Firebase with error.
                Log.w("file", "Failed to read value", error.toException());
            }
        });

       /* timeStamp2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // this method is called when new child is added to
                // our data base and after adding new child
                // we are adding that item inside our array list and
                // notifying our adapter that the data in adapter is changed.
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String timeDate = ds.getValue(String.class);
                    activityArrayList.add(timeDate);
                    //   Log.d("TAG", timeDate );
                    adapter.notifyDataSetChanged();}
            }






            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // this method is called when we get any
                // error from Firebase with error.
            }
        });

        timeStamp3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // this method is called when new child is added to
                // our data base and after adding new child
                // we are adding that item inside our array list and
                // notifying our adapter that the data in adapter is changed.
            for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String timeDate = ds.getValue(String.class);
                    activityArrayList.add(timeDate);
                    //   Log.d("TAG", timeDate );
                    adapter.notifyDataSetChanged();}
            }






            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // this method is called when we get any
                // error from Firebase with error.
            }
        });
        timeStamp4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // this method is called when new child is added to
                // our data base and after adding new child
                // we are adding that item inside our array list and
                // notifying our adapter that the data in adapter is changed.

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String timeDate = ds.child("alarm").getValue(String.class);
                    activityArrayList.add(timeDate);
                    //   Log.d("TAG", timeDate );
                    adapter.notifyDataSetChanged();}
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // this method is called when we get any
                // error from Firebase with error.
            }
        });

        timeStamp5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // this method is called when new child is added to
                // our data base and after adding new child
                // we are adding that item inside our array list and
                // notifying our adapter that the data in adapter is changed.
              for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String timeDate = ds.child("alarm").getValue(String.class);
                    activityArrayList.add(timeDate);
                    //   Log.d("TAG", timeDate );
                    adapter.notifyDataSetChanged();}
            }






            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // this method is called when we get any
                // error from Firebase with error.
            }
        });

        timeStamp6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // this method is called when new child is added to
                // our data base and after adding new child
                // we are adding that item inside our array list and
                // notifying our adapter that the data in adapter is changed.
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String timeDate = ds.getValue(String.class);
                    activityArrayList.add(timeDate);
                    //   Log.d("TAG", timeDate );
                    adapter.notifyDataSetChanged();}
            }






            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // this method is called when we get any
                // error from Firebase with error.
            }
        });

        timeStamp6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // this method is called when new child is added to
                // our data base and after adding new child
                // we are adding that item inside our array list and
                // notifying our adapter that the data in adapter is changed.
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String timeDate = ds.getValue(String.class);
                    activityArrayList.add(timeDate);
                    //   Log.d("TAG", timeDate );
                    adapter.notifyDataSetChanged();}
            }






            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // this method is called when we get any
                // error from Firebase with error.
            }
        });

        timeStamp7.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // this method is called when new child is added to
                // our data base and after adding new child
                // we are adding that item inside our array list and
                // notifying our adapter that the data in adapter is changed.
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String timeDate = ds.getValue(String.class);
                    activityArrayList.add(timeDate);
                    //   Log.d("TAG", timeDate );
                    adapter.notifyDataSetChanged();}
            }






            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // this method is called when we get any
                // error from Firebase with error.
            }
        });


        */

        // below line is used for setting
        // an adapter to our list view.
        alarms.setAdapter(adapter);
    }
}