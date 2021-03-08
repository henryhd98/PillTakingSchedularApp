package com.Henry.poppinsmarter.LoginStartup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.Henry.poppinsmarter.R;

public class MakeSelection extends AppCompatActivity {

    //Variables
    private TextView title, description;
    private RelativeLayout smsBox, mailBox;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // To Remove Status Bar
        setContentView(R.layout.activity_make_selection);

        //Hooks
        title = findViewById(R.id.make_selection_title);
        description = findViewById(R.id.make_selection_description);
        smsBox = findViewById(R.id.make_selection_sms_box);
        mailBox = findViewById(R.id.make_selection_mail_box);

        //Animation Hook
        animation = AnimationUtils.loadAnimation(this,R.anim.slide_animation);

        //Set animation to all the elements
        title.setAnimation(animation);
        description.setAnimation(animation);
        smsBox.setAnimation(animation);
        mailBox.setAnimation(animation);

    }

    //call Next Screen
    public void callOTPScreenFromMakeSelection(View view){
        startActivity(new Intent(getApplicationContext(), StartupScreen.class));
        finish();
    }

    //call Previous Screen on Back arrow click
    public void callBackScreenFromMakeSelection(View view){
        super.onBackPressed();
        finish();
    }
}
