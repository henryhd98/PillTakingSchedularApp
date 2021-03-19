package com.Henry.poppinsmarter.reminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.Henry.poppinsmarter.LoginStartup.ForgetPassword;
import com.Henry.poppinsmarter.LoginStartup.Login;
import com.Henry.poppinsmarter.LoginStartup.SignUp;
import com.Henry.poppinsmarter.MainActivity;
import com.Henry.poppinsmarter.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowPopUp extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window);
        Button yes = (Button) findViewById(R.id.yes);
        Button no = (Button) findViewById(R.id.no);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new VisualReminder().taken();
                new VisualReminder().turnLEDoff();
                Intent returnBtn = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(returnBtn);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new VisualReminder().notTaken();
                new VisualReminder().turnLEDoff();
                Intent returnBtn = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(returnBtn);
            }
        });


    }


}