package com.example.wear;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.example.alish.coastlinesandpeople.WeatherUpdate;

public class MainActivity extends WearableActivity {

    private TextView mTextView;
    private static final int MY_PERMISSIONS_REQUEST_CALL=1;
    Button makeCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        //makecall=(Button)findViewById(R.id.makecall);

        mTextView = (TextView) findViewById( R.id.text );
        //mTextView.setText( WeatherUpdate.getCurrentTemp().get(0));
        mTextView.setText( "Call getCurrentTemp here");

        makeCall=(Button)findViewById(R.id.makecall);

        makeCall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                ;
                Intent call=new Intent(Intent.ACTION_CALL,Uri.parse("tel:+911234567890"));
                makePhoneCall();
            }
        });




        // Enables Always-on
        setAmbientEnabled();
    }

    private void makePhoneCall(){
        if (ActivityCompat.checkSelfPermission( MainActivity.this, Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL);

        } else{
            startActivity(new Intent( Intent.ACTION_CALL, Uri.parse( "tel:+18138954757" ) ));
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    makePhoneCall();
                }
            }
        }
    }
}
