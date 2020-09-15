package com.example.help;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    String Message, location;
    ImageButton HomeBtn, AnalysisBtn, TipsBtn;

    int KeyCode;
    private static final int PERMISSION_REQUEST_SMS = 1;
    private static final int PERMISSION_REQUEST_INTERNET = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeBtn = findViewById(R.id.Home);
        AnalysisBtn = findViewById(R.id.Analysis);
        TipsBtn = findViewById(R.id.Tips);



        HomeBtn.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        // Create a new Fragment to be placed in the activity layout
                        Fragment fragment = new HomeFragment();
                        // Add the fragment to the 'fragment_container' Layout present in Activity
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
        );
        AnalysisBtn.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        // Create a new Fragment to be placed in the activity layout
                        Fragment fragment = new AnalysisFragment();
                        // Add the fragment to the 'fragment_container' Layout present in Activity
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
        );
        TipsBtn.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        // Create a new Fragment to be placed in the activity layout
                        Fragment fragment = new TipsFragment();
                        // Add the fragment to the 'fragment_container' Layout present in Activity
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
        );



        //Checking for enabled permissions.

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)) {
            }
            else { ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},PERMISSION_REQUEST_SMS);
            }
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) this, Manifest.permission.INTERNET)) {
            } else {
                ActivityCompat.requestPermissions((Activity) this, new String[]{Manifest.permission.INTERNET}, PERMISSION_REQUEST_INTERNET);
            }
        }

        getLocation();

    }

    // to enable permission if they are not
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            case PERMISSION_REQUEST_INTERNET: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
    }

    public void getLocation(){
        try {
            locationTraker locationtrack = new locationTraker(MainActivity.this);
            if (locationtrack.cangetlocation()) {
            String longitude = String.valueOf(locationtrack.getLongitude());
            String latitude = String.valueOf(locationtrack.getLatitude());
            location = "I am at " + "Longitude: " + longitude+ "Latitude: " + latitude ;

                           }else{
                                locationtrack.showSettingsAlert();
                            }


        } catch ( Exception ex){
            ex.printStackTrace();

        }
    }

    public void keyEvent(){
        if (KeyCode == KeyEvent.KEYCODE_VOLUME_UP){

            sendmessage();
        }
    }

    public void sendmessage(){
        String PhoneNO = " 9849562586";
        Message = "Help Here!! " + location;

        SmsManager smsmanager = SmsManager.getDefault();
        smsmanager.sendTextMessage(PhoneNO,null, Message,null,null);
        Toast.makeText(getApplicationContext(),"SMS Sent.",Toast.LENGTH_SHORT).show();
    }

}