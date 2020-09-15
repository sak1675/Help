package com.example.help;

import android.location.Location;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class HomeFragment extends Fragment {
    ImageButton eme_Btn;
    TextView message;
    String location;
    String Message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        eme_Btn = view.findViewById(R.id.emergencyBTN);
        message = view.findViewById(R.id.Welcome_Text);

        eme_Btn.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View V){
                       getLocation();
                       message.setText(location);
                        sendmessage();
            }
        }
        );



        return view;
    }
    public void getLocation(){
        try {
            locationTraker locationtrack = new locationTraker(getActivity());
            if (locationtrack.cangetlocation()) {
                String longitude = String.valueOf(locationtrack.getLongitude());
                String latitude = String.valueOf(locationtrack.getLatitude());
                location = "I am at" + "Longitude: " + longitude + "Latitude: " + latitude;
                Message = "Latitude: " + latitude + "Longitude: " + longitude ;

            }else{
               locationtrack.showSettingsAlert();
            }
        } catch ( Exception e){
            Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();;
        }
    }

    public void sendmessage(){
        String PhoneNO = " 9849562586";
        Message = "Help Here!! " + Message;

        SmsManager smsmanager = SmsManager.getDefault();
        smsmanager.sendTextMessage(PhoneNO,null, Message,null,null);
        Toast.makeText(getActivity(), "SMS Sent", Toast.LENGTH_SHORT).show();

    }
}