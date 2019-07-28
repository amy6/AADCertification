package com.example.customfancontroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import static com.example.customfancontroller.DialView.DEFAULT_SELECTION_COUNT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        DialView dialView = findViewById(R.id.dialView);

        switch (item.getItemId()) {
            case R.id.selection_3:
                dialView.setSelectionIndicators(3);
                return true;
            case R.id.selection_4:
                dialView.setSelectionIndicators(4);
                return true;
            case R.id.selection_5:
                dialView.setSelectionIndicators(5);
                return true;
            case R.id.selection_6:
                dialView.setSelectionIndicators(6);
                return true;
            case R.id.selection_7:
                dialView.setSelectionIndicators(7);
                return true;
            case R.id.selection_8:
                dialView.setSelectionIndicators(8);
                return true;
            default:
                dialView.setSelectionIndicators(DEFAULT_SELECTION_COUNT);
                return true;
        }
    }
}
