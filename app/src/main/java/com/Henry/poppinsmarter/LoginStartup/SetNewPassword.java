package com.Henry.poppinsmarter.LoginStartup;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.Henry.poppinsmarter.HelperClasses.CheckInternet;
import com.Henry.poppinsmarter.R;

public class SetNewPassword extends AppCompatActivity {
    //Variables
    private ImageView screenIcon;
    private TextView title, description;
    private TextInputLayout newPassword, confirmNewPassword;
    private Button updatePasswordbtn;
    private Animation animation;
    private RelativeLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // To Remove Status Bar
        setContentView(R.layout.activity_set_new_password);

        //Hooks
        screenIcon = findViewById(R.id.set_new_password_icon);
        title = findViewById(R.id.set_new_password_title);
        description = findViewById(R.id.set_new_password_description);
        newPassword = findViewById(R.id.new_password);
        confirmNewPassword = findViewById(R.id.confirm_password);
        updatePasswordbtn = findViewById(R.id.set_new_password_btn);
        progressBar = findViewById(R.id.set_new_password_progress_bar);

        //Animation Hook
        animation = AnimationUtils.loadAnimation(this, R.anim.slide_animation);

        //Set animation to all the elements
        screenIcon.setAnimation(animation);
        title.setAnimation(animation);
        description.setAnimation(animation);
        newPassword.setAnimation(animation);
        confirmNewPassword.setAnimation(animation);
        updatePasswordbtn.setAnimation(animation);

    }


    /*
    Update Users
    Password on Button
    Click
     */
    public void setNewPasswordBtn(View view) {

        //Check Internet Connection
        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)) {
            showCustomDialog();
            return;
        }

        //validate phone Number
        if (!validatePassword() | !validateConfirmPassword()) {
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        //Get data from fields
        String _newPassword = newPassword.getEditText().getText().toString().trim();
        String _phoneNumber = getIntent().getStringExtra("phoneNo");

        //Update Data in Firebase and in Sessions
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(_phoneNumber).child("password").setValue(_newPassword);

        startActivity(new Intent(getApplicationContext(),ForgetPasswordSuccessMessage.class));
        finish();
    }


    /*
    Show
    Internet
    Connection Dialog
     */
    private void showCustomDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please connect to the internet to proceed further")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(), StartupScreen.class));
                        finish();
                    }
                });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    /*
    Fields
    Validations
     */
    private Boolean validatePassword() {
        String val = newPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";


        if (val.isEmpty()) {
            newPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            newPassword.setError("At least 4 letters with no space!");
            return false;
        } else {
            newPassword.setError(null);
            newPassword.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateConfirmPassword() {
        String passwordFromUser = newPassword.getEditText().getText().toString();
        String confirmedPasswordFromUser = confirmNewPassword.getEditText().getText().toString();


        if (confirmedPasswordFromUser.isEmpty()) {
            confirmNewPassword.setError("Field cannot be empty");
            return false;
        } else if (!passwordFromUser.equals(confirmedPasswordFromUser)) {
            confirmNewPassword.setError("Password Does not match! Please try again.");
            return false;
        } else {
            confirmNewPassword.setError(null);
            confirmNewPassword.setErrorEnabled(false);
            return true;
        }
    }


    /*
    Call Previous
    Screen on
    Back arrow click
     */
    public void goToHomeFromSetNewPassword(View view) {
        startActivity(new Intent(getApplicationContext(), StartupScreen.class));
        finish();
    }
}
