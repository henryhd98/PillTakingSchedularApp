package com.Henry.poppinsmarter.LoginStartup;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.Henry.poppinsmarter.LocationOwner.RetailerDashboard;
import com.Henry.poppinsmarter.User.UserDashboard;
import com.Henry.poppinsmarter.data.SessionManager;
import com.Henry.poppinsmarter.data.UserHelperClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;
import com.Henry.poppinsmarter.HelperClasses.CheckInternet;
import com.Henry.poppinsmarter.R;

public class SignUp3rdClass extends AppCompatActivity {


    //Variables
    ScrollView scrollView;
    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;
    RelativeLayout progressbar;
    Button next;
    String fullName, phoneNo, email, username, password, date, gender, whatToDO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up3rd_class);

        //Hooks

        scrollView = findViewById(R.id.signup_3rd_screen_scroll_view);
        countryCodePicker = findViewById(R.id.country_code_picker);
        phoneNumber = findViewById(R.id.signup_phone_number);
        progressbar = findViewById(R.id.check_progress_bar);
        next = findViewById(R.id.signup_next_button);
        fullName = getIntent().getStringExtra("fullName");
        email = getIntent().getStringExtra("email");
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        date = getIntent().getStringExtra("date");
        gender = getIntent().getStringExtra("gender");
        whatToDO = getIntent().getStringExtra("whatToDO");

    }

    public void VerifyNumberCheck(View view){


        //Check Internet Connection
        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)) {
            showCustomDialog();
            return;
        }
        //Validate fields
        if (!validatePhoneNumber()) {
            return;
        }//Validation succeeded and now move to next screen to verify phone number and save data



        progressbar.setVisibility(View.VISIBLE);

        Toast.makeText(SignUp3rdClass.this, "GOT THIS FAR", Toast.LENGTH_SHORT).show();
        //Get values from fields
        String _getUserEnteredPhoneNumber = phoneNumber.getEditText().getText().toString().trim(); //Get Phone Number
        if (_getUserEnteredPhoneNumber.charAt(0) == '0') {
            _getUserEnteredPhoneNumber = _getUserEnteredPhoneNumber.substring(1);
        } //remove 0 at the start if entered by the user
        final String _phoneNo = "+" + countryCodePicker.getFullNumber() + _getUserEnteredPhoneNumber;


        //Check weather User exists or not in database
        Query checkUser = FirebaseDatabase.getInstance("https://poppinsmarter-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users").orderByChild("phoneNo").equalTo(_phoneNo);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //If Phone Number exists then get password
                if (dataSnapshot.exists()) {
                    phoneNumber.setError("Phone Number Already Registered!");
                    progressbar.setVisibility(View.GONE);
                    return;
                } else {

                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);
                    progressbar.setVisibility(View.GONE);
                    storeNewUsersData(_phoneNo);
                    Toast.makeText(SignUp3rdClass.this, "Phone number Valid! press next", Toast.LENGTH_SHORT).show();

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(com.Henry.poppinsmarter.LoginStartup.SignUp3rdClass.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                progressbar.setVisibility(View.GONE);
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
                         Conditions To Update
                         OR Create New User
                            */
        private void storeNewUsersData(String No) {


            Toast.makeText(SignUp3rdClass.this, "GOT THIS FAR", Toast.LENGTH_SHORT).show();


       if (whatToDO.equals("updateData")) {
            Intent intent = new Intent(getApplicationContext(), SetNewPassword.class);
            intent.putExtra("phoneNo", No);
            startActivity(intent);
            finish();
        } else if (whatToDO.equals("createNewUser")) {

            FirebaseDatabase rootNode = FirebaseDatabase.getInstance("https://poppinsmarter-default-rtdb.europe-west1.firebasedatabase.app/");
            DatabaseReference reference = rootNode.getReference("Users");

            //Create helperclass reference and store data using firebase
            UserHelperClass addNewUser = new UserHelperClass(fullName, username, email, No, password, date, gender);
            reference.child(No).setValue(addNewUser);

            //Create a Session
            SessionManager sessionManager = new SessionManager(SignUp3rdClass.this, SessionManager.SESSION_USERSESSION);
            sessionManager.createLoginSession(fullName, username, email, No, password, date, gender);

            startActivity(new Intent(getApplicationContext(), UserDashboard.class));
            Toast.makeText(SignUp3rdClass.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {

            Toast.makeText(SignUp3rdClass.this, "Not Completed! Try again.", Toast.LENGTH_SHORT).show();
        }


    }



    /*
   Validation function
    */
    private boolean validatePhoneNumber() {
        String val = phoneNumber.getEditText().getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";
        if (val.isEmpty()) {
            phoneNumber.setError("Enter valid phone number");
            return false;
        } else if (!val.matches(checkspaces)) {
            phoneNumber.setError("No White spaces are allowed!");
            return false;
        } else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }

    }

    public void goToHome(View view) {
        startActivity(new Intent(getApplicationContext(), StartupScreen.class));

        finish();
    }


}


