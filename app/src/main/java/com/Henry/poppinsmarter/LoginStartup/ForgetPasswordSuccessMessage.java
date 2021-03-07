package com.Henry.poppinsmarter.LoginStartup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.Henry.poppinsmarter.R;

public class ForgetPasswordSuccessMessage extends AppCompatActivity {
    //Variables
    private ImageView screenIcon;
    private TextView title, description;
    private Button backToLoginBtn;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // To Remove Status Bar
        setContentView(R.layout.activity_forget_password_success_message);

        //Hooks
        screenIcon = findViewById(R.id.success_message_icon);
        title = findViewById(R.id.success_message_title);
        description = findViewById(R.id.success_message_description);
        backToLoginBtn = findViewById(R.id.success_message_btn);
        //Animation Hook
        animation = AnimationUtils.loadAnimation(this,R.anim.slide_animation);

        //Set animation to all the elements
        screenIcon.setAnimation(animation);
        title.setAnimation(animation);
        description.setAnimation(animation);
        backToLoginBtn.setAnimation(animation);

    }

    //call login Screen
    public void backToLogin(View view){
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}
