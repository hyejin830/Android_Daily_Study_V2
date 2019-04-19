package com.example.chapter02_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chapter02_project.activity.SaveRestoreActivity;
import com.example.chapter02_project.view_layout.CustomViewExampleActivity;
import com.example.chapter02_project.view_layout.LayoutExampleActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startSaveRestoreActivityButton;
    private Button startLayoutExampleActivityButton;
    private Button startCustomViewActivityButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        startSaveRestoreActivityButton = findViewById(R.id.btn_start_save_restore_activity);
        startLayoutExampleActivityButton = findViewById(R.id.btn_start_layout_activity);
        startCustomViewActivityButton = findViewById(R.id.btn_start_custom_view_activity);

        startSaveRestoreActivityButton.setOnClickListener(this);
        startLayoutExampleActivityButton.setOnClickListener(this);
        startCustomViewActivityButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_save_restore_activity:
                Intent startSaveRestoreActivityIntent = new Intent(this, SaveRestoreActivity.class);
                startActivity(startSaveRestoreActivityIntent);
                break;
            case R.id.btn_start_layout_activity:
                Intent startLayoutActivityIntent = new Intent(this, LayoutExampleActivity.class);
                startActivity(startLayoutActivityIntent);
                break;
            case R.id.btn_start_custom_view_activity:
                Intent startCustomViewActivityIntent = new Intent(this, CustomViewExampleActivity.class);
                startActivity(startCustomViewActivityIntent);
                break;
        }
    }
}
