package com.example.mahima.customviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customView = findViewById(R.id.custom_view);

        Button swapColor = findViewById(R.id.swap_color);
        Button increasePadding = findViewById(R.id.padding_increase);
        Button decreasePadding = findViewById(R.id.padding_decrease);

        swapColor.setOnClickListener(this);
        increasePadding.setOnClickListener(this);
        decreasePadding.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.swap_color:
                customView.swapColor();
                break;
            case R.id.padding_increase:
                customView.paddingIncrease(30);
                break;
            case R.id.padding_decrease:
                customView.paddingDecrease(30);
                break;
        }
    }
}
