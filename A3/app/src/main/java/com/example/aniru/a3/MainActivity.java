package com.example.aniru.a3;

import android.Manifest;
import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    final int REQUEST_CODE = 1;
    int REG = 0;

    final String PERMISSION = "edu.uic.cs478.f18.project3";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar myToolbar=(Toolbar)findViewById(R.id.action_bar);
        setSupportActionBar(myToolbar);



        if (ContextCompat.checkSelfPermission(MainActivity.this, PERMISSION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{PERMISSION}, REQUEST_CODE);
        }
        else
        {
            System.out.println("Registered -- permission is present");
            Toast toast = Toast.makeText(getApplicationContext(), "Points for NY are under construction", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_CODE:
            {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {

                    System.out.println("Registered -- permission has been granted");

                    Toast toast = Toast.makeText(getApplicationContext(), "Points for NY are under construction", Toast.LENGTH_SHORT);
                    toast.show();


                } else
                {
                    Toast.makeText(getApplicationContext(), "As permission is denied, app will not function as intended", Toast.LENGTH_SHORT).show();

                }

            }

        }
    }
}