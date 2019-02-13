package com.example.aniru.a3;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class PointsFragment extends ListFragment {

    private static final String TAG = "TitlesFragment";
    private ListSelectionListener mListener = null;
    private int mCurrIdx = -1;


    public interface ListSelectionListener { // Nested interface class that the sfo Activity calls when a point is selected
        public void onListSelection(int index); // the implementation of this calls the function in WebFragment class that loads the corresponding web page
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        Log.i(TAG, getClass().getSimpleName() + ":onAttach()");
        try {

            // Form of communication to sfo class
            mListener = (ListSelectionListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, getClass().getSimpleName() + ":onCreate()");
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        Log.i(TAG, getClass().getSimpleName() + ": onActivityCreated()");

        // Set the list choice mode to allow only one selection at a time
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Set the list adapter for the ListView

        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.points_fragment, sfo.pointsArray));

        // if item is selected, its state is changed
        if (-1 != mCurrIdx) {
            getListView().setItemChecked(mCurrIdx, true);


            mListener.onListSelection(mCurrIdx);
        }
    }

    // Called when the user selects an item from the List
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        if (mCurrIdx != pos) {
            mCurrIdx = pos;

            // Communication with sfo class, to call the method in the interface it has implemented
            mListener.onListSelection(pos);
        }
        // changing the selected item to "checked state"
        l.setItemChecked(mCurrIdx, true);
    }

    public void onUncheckItem() // unchecking an item for backward navigation
    {
        getListView().setItemChecked(mCurrIdx,false);
        mCurrIdx = -1;
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