package com.task.experiment.activity;

import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.task.experiment.R;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    public final String LANGUAGE_QUERY = "LANGUAGE_QUERY";

    void activateToolbar(boolean enableHome) {
        Log.i(TAG, "activateToolbar: starts");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
//            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//
//            if (toolbar != null) {
//                setSupportActionBar(toolbar);
//                actionBar = getSupportActionBar();
//            }
//        }
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(enableHome);
        }
    }
}
