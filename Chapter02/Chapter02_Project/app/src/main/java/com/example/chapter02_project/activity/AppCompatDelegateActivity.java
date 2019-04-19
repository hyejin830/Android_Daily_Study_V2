package com.example.chapter02_project.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;

import com.example.chapter02_project.R;

public class AppCompatDelegateActivity extends Activity {

    private AppCompatDelegate appCompatDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //appCompatDelegate = new AppCompatDelegate(this);
        appCompatDelegate.setContentView(R.layout.activity_app_compat_delegate);
    }
}