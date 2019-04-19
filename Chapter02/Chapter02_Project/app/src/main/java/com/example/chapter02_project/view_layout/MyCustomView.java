package com.example.chapter02_project.view_layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.chapter02_project.R;

public class MyCustomView extends LinearLayout {

    private ImageView star1;
    private ImageView star2;
    private ImageView star3;

    private int selectedNumber = 0;

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context, attrs);
    }

    private void initializeViews(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.three_stars_indicator, this);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCustomView);
            selectedNumber = typedArray.getInteger(0, 0);
            typedArray.recycle();

        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        star1 = findViewById(R.id.iv_star_1);
        star2 = findViewById(R.id.iv_star_2);
        star3 = findViewById(R.id.iv_star_3);

        setSelected(selectedNumber, true);
    }

    public void setSelected(int select) {
        setSelected(select, false);
    }

    private void setSelected(int select, boolean force) {
        if (force || selectedNumber != select) {
            if (2 > selectedNumber && selectedNumber < 0) {
                return;
            }
            selectedNumber = select;
            if (selectedNumber == 0) {
                star1.setImageResource(R.drawable.star);
                star2.setImageResource(R.drawable.empty_star_);
                star3.setImageResource(R.drawable.empty_star_);
            } else if (selectedNumber == 1) {
                star1.setImageResource(R.drawable.empty_star_);
                star2.setImageResource(R.drawable.star);
                star3.setImageResource(R.drawable.empty_star_);
            } else if (selectedNumber == 2) {
                star1.setImageResource(R.drawable.empty_star_);
                star2.setImageResource(R.drawable.empty_star_);
                star3.setImageResource(R.drawable.star);
            }
        }
    }

    public int getSelected() {
        return selectedNumber;
    }
}
