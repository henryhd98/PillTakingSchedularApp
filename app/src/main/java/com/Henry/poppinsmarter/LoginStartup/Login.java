package com.Henry.poppinsmarter.LoginStartup;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.Henry.poppinsmarter.MainActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;
import com.Henry.poppinsmarter.data.SessionManager;
import com.Henry.poppinsmarter.HelperClasses.CheckInternet;
import com.Henry.poppinsmarter.R;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    //Variables
    CountryCodePicker countryCodePicker;
    TextInputLayout phoneNumber, password;
    RelativeLayout progressbar;
    CheckBox rememberMe;
    TextInputEditText phoneNumberEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_login);

        //hooks
        countryCodePicker = findViewById(R.id.login_country_code_picker);
        phoneNumber = findViewById(R.id.login_phone_number);
        password = findViewById(R.id.login_password);
        progressbar = findViewById(R.id.login_progress_bar);
        rememberMe = findViewById(R.id.remember_me);
        phoneNumberEditText = findViewById(R.id.login_phone_number_editText);
        passwordEditText = findViewById(R.id.login_password_editText);


        //Check weather phone number and password is already saved in Shared Preferences or not
        SessionManager sessionManager = new SessionManager(com.Henry.poppinsmarter.LoginStartup.Login.this, SessionManager.SESSION_REMEMMBERME);
        if (sessionManager.checkRememberMe()) {
            HashMap<String, String> rememberMeDetails = sessionManager.getRemeberMeDetailsFromSession();
            phoneNumberEditText.setText(rememberMeDetails.get(SessionManager.KEY_SESSIONPHONENUMBER));
            passwordEditText.setText(rememberMeDetails.get(SessionManager.KEY_SESSIONPASSWORD));
        }

    }


    /*
    Login the
    user in
    app!
     */
    public void letTheUserLoggedIn(View view) {

        //Check Internet Connection
        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)) {
            showCustomDialog();
            return;
        }


        //validate phone Number and Password
        if (!validateFields()) {
            return;
        }
        progressbar.setVisibility(View.VISIBLE);


        //Get values from fields
        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        final String _password = password.getEditText().getText().toString().trim();
        if (_phoneNumber.charAt(0) == '0') {
            _phoneNumber = _phoneNumber.substring(1);
        } //remove 0 at the start if entered by the user
        final String _completePhoneNumber = "+" + countryCodePicker.getFullNumber() + _phoneNumber;


        //Check Remember Me Button to create it's session
        if (rememberMe.isChecked()) {
            SessionManager sessionManager = new SessionManager(com.Henry.poppinsmarter.LoginStartup.Login.this, SessionManager.SESSION_REMEMMBERME);
            sessionManager.createRememberMeSession(_phoneNumber, _password);
        }

        //Check weather User exists or not in database
        Query checkUser = FirebaseDatabase.getInstance("https://poppinsmarter-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users").orderByChild("phoneNo").equalTo(_completePhoneNumber);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //If Phone Number exists then get password
                if (dataSnapshot.exists()) {
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);
                    String systemPassword = dataSnapshot.child(_completePhoneNumber).child("password").getValue(String.class);
                    //if password exists and matches with users password then get other fields from database
                    if (systemPassword.equals(_password)) {
                        password.setError(null);
                        password.setErrorEnabled(false);

                        //Get users data from firebase database
                        String _fullname = dataSnapshot.child(_completePhoneNumber).child("fullName").getValue(String.class);
                        String _username = dataSnapshot.child(_completePhoneNumber).child("username").getValue(String.class);
                        String _email = dataSnapshot.child(_completePhoneNumber).child("email").getValue(String.class);
                        String _phoneNo = dataSnapshot.child(_completePhoneNumber).child("phoneNo").getValue(String.class);
                        String _password = dataSnapshot.child(_completePhoneNumber).child("password").getValue(String.class);
                        String _dateOfBirth = dataSnapshot.child(_completePhoneNumber).child("date").getValue(String.class);
                        String _gender = dataSnapshot.child(_completePhoneNumber).child("gender").getValue(String.class);

                        //Create a Session
                        SessionManager sessionManager = new SessionManager(com.Henry.poppinsmarter.LoginStartup.Login.this, SessionManager.SESSION_USERSESSION);
                        sessionManager.createLoginSession(_fullname, _username, _email, _phoneNo, _password, _dateOfBirth, _gender);

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        progressbar.setVisibility(View.GONE);

                    } else {
                        progressbar.setVisibility(View.GONE);
                        password.setError("Password does not match!");
                    }
                } else {
                    progressbar.setVisibility(View.GONE);
                    phoneNumber.setError("No such user exist!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressbar.setVisibility(View.GONE);
                Toast.makeText(com.Henry.poppinsmarter.LoginStartup.Login.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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
    private boolean validateFields() {

        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _password = password.getEditText().getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";

        if (_phoneNumber.isEmpty()) {
            phoneNumber.setError("Phone number can not be empty");
            phoneNumber.requestFocus();
            return false;
        } else if (_password.isEmpty()) {
            password.setError("Password can not be empty");
            password.requestFocus();
            return false;
        } else if (!_phoneNumber.matches(checkspaces)) {
            phoneNumber.setError("No White spaces are allowed!");
            return false;
        } else {
            phoneNumber.setError(null);
            password.setError(null);
            phoneNumber.setErrorEnabled(false);
            password.setErrorEnabled(false);
            return true;
        }
    }


    /*
    Function to call
    the Forget Password
    Screen
     */
    public void callForgetPassword(View view) {
        startActivity(new Intent(getApplicationContext(), ForgetPassword.class));
    }


    public void callSignUpFromLogin(View view) {
        startActivity(new Intent(getApplicationContext(), SignUp.class));
        finish();
    }
}
