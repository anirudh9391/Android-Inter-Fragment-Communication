
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

public class sfo extends AppCompatActivity implements
        PointsFragment.ListSelectionListener {

    public static String[] pointsArray;
    public static String[] linkArray;
    private PointsFragment Points;
    private WebFragment Link;


    static int flag = 0;
    int select = 0;
    private static final String TAG = "SFO";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        // Get the string arrays with the titles and qutoes
        pointsArray = getResources().getStringArray(R.array.points);
        linkArray = getResources().getStringArray(R.array.website);

        setContentView(R.layout.activity_sfo);

        Toolbar myToolbar=(Toolbar)findViewById(R.id.action_bar);
        setSupportActionBar(myToolbar);

        if(savedInstanceState!=null)
        {
            flag = savedInstanceState.getInt("flag");
        }

        if(findViewById(R.id.layout_default)!=null) // Portrait orientation
        {
            if(flag == 1) // if device was rotated and configuration was changed
            {
                //show only the web pages in a case when a selection was made and screen was flipped
                System.out.println("Comes in to portrait");
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().show(manager.findFragmentById(R.id.links_fragment))
                        .hide(manager.findFragmentById(R.id.points_fragment)
                        ).commit()
                ;

            }
            else
            {
                // if screen rotation was not done, showing only the link
                System.out.println("Comes in to portrait");
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().hide(manager.findFragmentById(R.id.links_fragment))
                        .show(manager.findFragmentById(R.id.points_fragment)
                        ).commit()
                ;

            }
        }

        if(findViewById(R.id.layout_land)!=null) // Layout orientation
        {

            if(flag == 1)
            {
                // showing the web views and points side by side
                System.out.println("saved stats "+flag);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().show(manager.findFragmentById(R.id.links_fragment))
                        .show(manager.findFragmentById(R.id.points_fragment)
                        )
                        .commit();
            }
            else
            {
                // showing only the points and not webviews
                System.out.println("saved stats "+flag);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().hide(manager.findFragmentById(R.id.links_fragment))
                        .show(manager.findFragmentById(R.id.points_fragment)
                        ).commit();
            }
        }

        Points = (PointsFragment) getFragmentManager()
                .findFragmentById(R.id.points_fragment); // checking existing fragments


        Link = (WebFragment) getFragmentManager()
                .findFragmentById(R.id.links_fragment); // checking existing fragments


    }


    @Override
    public void onListSelection(int index)
    {

        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().show(manager.findFragmentById(R.id.points_fragment))
                .hide(manager.findFragmentById(R.id.links_fragment)).addToBackStack(null).commit(); // adding the state to backstack


        if(findViewById(R.id.layout_default)!=null) // checking orientation of screen
        {

            System.out.println("Comes in to portrait");

            manager.beginTransaction().hide(manager.findFragmentById(R.id.points_fragment))
                    .show(manager.findFragmentById(R.id.links_fragment)
                    ).commit() // on selected, showing only the web views
            ;
        }

        if(findViewById(R.id.layout_land)!=null) // landscape mode
        {
            System.out.println("Comes in to portrait");

            manager.beginTransaction().show(manager.findFragmentById(R.id.points_fragment))
                    .show(manager.findFragmentById(R.id.links_fragment)
                    ).commit() // showing both the points and web views
            ;
        }

        Link.showLinkAtIndex(index); // To display the Website of the point clicked
        flag = 1;

    }

    public void onSaveInstanceState(Bundle savedInstance)
    {
        super.onSaveInstanceState(savedInstance);
        System.out.println("savedInstancestate");
        savedInstance.putInt("flag",flag);
        savedInstance.putInt("select",select);

    }

    public void onBackPressed() // handling a back button press
    {


        super.onBackPressed();

        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().show(manager.findFragmentById(R.id.points_fragment))
                .hide(manager.findFragmentById(R.id.links_fragment)
                ).commit() // design a layout by showing only the points and not the web views
        ;
        Points.onUncheckItem(); // unchecking(to unhighlight)
        flag = 0; // flag that indicates if a selection was already made and then screen was rotated
    }
    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

}


