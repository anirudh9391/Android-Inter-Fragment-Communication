package com.example.aniru.a3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent i)
    {
        String str = i.getAction() + " Recieved by A3"+"\n";
        Intent intent = null;
        switch(i.getAction())
        {
            case "SFO": intent = new Intent(context, sfo.class);break;
            case "NY": intent = new Intent(context, MainActivity.class);break;
        }

        if(intent!=null)
        {
            context.startActivity(intent);
        }

        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }
}
