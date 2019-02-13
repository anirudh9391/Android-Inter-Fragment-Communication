package com.example.aniru.a2;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
{
    BroadcastReceiver receiver_ny = null;
    BroadcastReceiver receiver_sfo = null;

    Context mContext = this;
    IntentFilter filter1 = new IntentFilter("NY");
    IntentFilter filter2 = new IntentFilter("SFO");



    final int REQUEST_CODE = 1;
    int REG = 0;
    final String PERMISSION = "edu.uic.cs478.f18.project3";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button check = (Button)findViewById(R.id.button);

        filter1.setPriority(20); // as A2 always receivs before A3
        filter2.setPriority(20);

       // setting up the button that waits on permissions
       check.setOnClickListener(new View.OnClickListener()
        {
                @Override
                public void onClick(View view)
                {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, PERMISSION) != PackageManager.PERMISSION_GRANTED)
                    {
                        // Permission is not granted
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{PERMISSION}, REQUEST_CODE);

                    }
                    else
                    {
                        //if permission exists, go ahead and register receiver
                        System.out.println("Registered -- permission is present");
                        Receiver();

                    }
                 }
        });
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    // when permission is given, perform regintration of receivers
                    System.out.println("Registered -- permission has been granted");
                    Receiver();

                } else
                {
                    Toast.makeText(getApplicationContext(), "As permission is denied, unable to recieve broadcast", Toast.LENGTH_SHORT).show();

                }

            }

        }
    }
    void Receiver()
    {
        BroadcastReceiver br = new BroadcastReceiver()
        {
            public void onReceive(Context context, Intent i)
            {
                String str = i.getAction() + " Recieved by A2"+"\n";

                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
            }
        };


        this.registerReceiver(br, filter1); // registering
        this.registerReceiver(br, filter2);

        }
}


