package com.example.chapter02_project.view_layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chapter02_project.R;

public class CustomViewExampleActivity extends AppCompatActivity implements View.OnClickListener {

    private MyCustomView indicator;
    private Button updateIndicatorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_example);
        initView();
    }

    private void initView() {
        indicator = findViewById(R.id.indicator);
        updateIndicatorButton = findViewById(R.id.btn_update_indicator);
        updateIndicatorButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update_indicator:
                int selected = indicator.getSelected();
                if (selected == 2) {
                    selected = 0;
                } else {
                    selected++;
                }
                indicator.setSelected(selected);
                break;
        }
    }
}
