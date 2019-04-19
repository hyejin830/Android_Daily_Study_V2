package com.example.chapter02_project.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chapter02_project.R;

public class SaveRestoreActivity extends AppCompatActivity implements View.OnClickListener {

    private String saveText;
    private EditText inputEditText;
    private TextView viewTextView;
    private Button saveTextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_restore);

        initView();
    }

    private void initView() {
        inputEditText = findViewById(R.id.et_input_save_text);
        viewTextView = findViewById(R.id.tv_save_text);
        saveTextButton = findViewById(R.id.btn_save_text);
        saveTextButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (saveText != null) {
            viewTextView.setText(saveText);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveText = inputEditText.getText().toString();
        outState.putString("EDIT_TEXT", saveText);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        saveText = savedInstanceState.getString("EDIT_TEXT");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_text:
                saveText = inputEditText.getText().toString();
                viewTextView.setText(saveText);
                break;
        }
    }
}
