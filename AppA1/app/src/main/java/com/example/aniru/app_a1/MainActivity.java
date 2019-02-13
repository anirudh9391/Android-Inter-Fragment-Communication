package com.example.aniru.app_a1;

import android.content.Intent;
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
    String CLICKED_BUTTON = "";
    final int REQUEST_CODE=1;
    final String PERMISSION = "edu.uic.cs478.f18.project3";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button sfo = (Button)findViewById(R.id.button); // getting reference to button objects that broadcast intents
        final Button ny = (Button)findViewById(R.id.button2);

        // Broadcast actions "SFO" and "NY"
        sfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CLICKED_BUTTON = "SFO";
                BroadcastWithPermission("SFO");
            }
        });

        ny.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CLICKED_BUTTON = "NY";
                BroadcastWithPermission("NY");

            }
        });
    }
    void BroadcastWithPermission(String s) // checks if the app has permission to broadcast
    {
        if (ContextCompat.checkSelfPermission(MainActivity.this, PERMISSION) == PackageManager.PERMISSION_GRANTED)
        { // if permission is given, go ahead and send an ordered broadcast
            Intent i = new Intent(s);

            sendOrderedBroadcast(i, PERMISSION); // this way, the receiver also needs this permission
        }
        else
        {
            // if permission is not given, request for it
            System.out.println("No permission");
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{PERMISSION}, REQUEST_CODE);

        }

    }

    // Callback for requesting permission
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case REQUEST_CODE:
            {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                { // If permission has been grandted, go ahead and broadcast
                    Intent i = new Intent(CLICKED_BUTTON);

                    sendOrderedBroadcast(i, PERMISSION);
                }
                else
                {
                    // Otherwsie display a toast that broadcasting will not take place
                    Toast.makeText(getApplicationContext(), "As permission is denied, unable to broadcast", Toast.LENGTH_SHORT).show();

                }
            }
        }

    }

}
