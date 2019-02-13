package com.example.aniru.a3;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

//Several Activity and Fragment lifecycle methods are instrumented to emit LogCat output
//so you can follow the class' lifecycle
public class WebFragment extends Fragment {

    private WebView mWebView = null;
    private int mCurrIdx = -1;
    private int mArrayLen = 0;

    private static final String TAG = "WebFragment";

    public int getShownIndex() {
        return mCurrIdx;
    }

    // Show the Webview of the link clicked
    public void showLinkAtIndex(int newIndex)
    {
        System.out.println("Clicked item is "+newIndex);
        if (newIndex < 0 || newIndex >= mArrayLen)
            return;

        mCurrIdx = newIndex;
        mWebView.loadUrl(sfo.linkArray[mCurrIdx]);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        mWebView.setWebViewClient(new WebViewClient());

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":onCreate()");
        super.onCreate(savedInstanceState);

        // Retain this Fragment across Activity reconfigurations
        setRetainInstance(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":onCreateView()");

        //Inflate the layout parameter for the web view display. web_fragment contains webview display
        return inflater.inflate(R.layout.web_fragment, container, false);
    }

    // Set up some information about the mWebView TextView
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":onActivityCreated()");
        super.onActivityCreated(savedInstanceState);

        mWebView = (WebView) getActivity().findViewById(R.id.web_view); // get the UI object of the web view
        mArrayLen = sfo.pointsArray.length; // calculating number of elements
        showLinkAtIndex(mCurrIdx); // calling the function with position of item clicked
    }

    @Override
    public void onAttach(Context activity) {
        Log.i(TAG, getClass().getSimpleName() + ":onAttach()");
        super.onAttach(activity);
    }



    @Override
    public void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, getClass().getSimpleName() + ":onDestroyView()");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, getClass().getSimpleName() + ":onDetach()");
        super.onDetach();
    }

    @Override
    public void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":onPause()");
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":onResume()");
        super.onResume();
    }

    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":onStart()");
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":onStop()");
        super.onStop();
    }
}
