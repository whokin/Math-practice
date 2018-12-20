package com.trainer.math.mathtrainer;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    List<Button> buttonList = new ArrayList<>();
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Lets populate the list with all button attributes
        populateList();

        for(int i = 0; i < buttonList.size(); i++)
        {
            final int selected = i;
            buttonList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CharSequence buttonText = buttonList.get(selected).getText();
                    Toast.makeText(SettingsActivity.this, "You've selected "+ buttonText, Toast.LENGTH_SHORT).show();
                }
            });
        }


        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void populateList() {

        // Generating the References for each button
        Button x1 = (Button) findViewById(R.id.btn_mul1);
        Button x2 = (Button) findViewById(R.id.btn_mul2);
        Button x3 = (Button) findViewById(R.id.btn_mul3);
        Button x4 = (Button) findViewById(R.id.btn_mul4);
        Button x5 = (Button) findViewById(R.id.btn_mul5);
        Button x6 = (Button) findViewById(R.id.btn_mul6);
        Button x7 = (Button) findViewById(R.id.btn_mul7);
        Button x8 = (Button) findViewById(R.id.btn_mul8);
        Button x9 = (Button) findViewById(R.id.btn_mul9);
        Button x10 = (Button) findViewById(R.id.btn_mul10);
        Button x11 = (Button) findViewById(R.id.btn_mul11);
        Button x12 = (Button) findViewById(R.id.btn_mul12);

        // Adding the buttons to the list
        buttonList.add(x1);
        buttonList.add(x2);
        buttonList.add(x3);
        buttonList.add(x4);
        buttonList.add(x5);
        buttonList.add(x6);
        buttonList.add(x7);
        buttonList.add(x8);
        buttonList.add(x9);
        buttonList.add(x10);
        buttonList.add(x11);
        buttonList.add(x12);
    }
}
