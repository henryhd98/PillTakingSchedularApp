package com.Henry.poppinsmarter;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.Henry.poppinsmarter.data.AlarmReminderContract;
import com.Henry.poppinsmarter.reminder.ActivityCursorAdapter;

public class ProfileActivity  extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    TextView result;
    Button schedular;
    ActivityCursorAdapter mCursorAdapter1;
    ListView reminderListView1;
    TextView reminderText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profo);
        reminderListView1 = (ListView) findViewById(R.id.list1);
        View emptyView = findViewById(R.id.empty_view1);
        reminderListView1.setEmptyView(emptyView);
        reminderText1 = (TextView) findViewById(R.id.reminderText1);
        mCursorAdapter1 = new ActivityCursorAdapter(this, null);
        reminderListView1.setAdapter(mCursorAdapter1);

        schedular = findViewById(R.id.schedular1);

        reminderListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(ProfileActivity.this, AddReminderActivity.class);

                Uri currentVehicleUri = ContentUris.withAppendedId(AlarmReminderContract.AlarmReminderEntry.CONTENT_URI, id); //This will call the content URI when the reminder is clicked on, (pulls the id of the chosen alarm)

                // Set the URI on the data field of the intent
                intent.setData(currentVehicleUri);

                startActivity(intent);

            }
        });


        schedular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //getLoaderManager().initLoader(VEHICLE_LOADER, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {  //What is displayed in the list view, calling all columns
        String[] projection = {
                AlarmReminderContract.AlarmReminderEntry._ID,
                AlarmReminderContract.AlarmReminderEntry.KEY_TITLE,
                AlarmReminderContract.AlarmReminderEntry.KEY_DATE,
                AlarmReminderContract.AlarmReminderEntry.KEY_TIME,
                AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT,
                AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_NO,
                AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_TYPE,
                AlarmReminderContract.AlarmReminderEntry.KEY_ACTIVE

        };

        return new CursorLoader(this,   // Parent activity context
                AlarmReminderContract.AlarmReminderEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) { //Swap the cursor from the cursor adapter and pass the DB query
        mCursorAdapter1.swapCursor(cursor);
        if (cursor.getCount() > 0){
            reminderText1.setVisibility(View.VISIBLE);
        }else{
            reminderText1.setVisibility(View.INVISIBLE);
        }
        //If the cursor is greater than 0, the reminder is shown


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) { //Reset the loader by passing null
        mCursorAdapter1.swapCursor(null);

    }

}