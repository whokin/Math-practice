package com.trainer.math.mathtrainer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import org.checkerframework.checker.formatter.FormatUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.appcompat.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity {

    AppDatabase db;
    List<CheckBox> checkBoxList = new ArrayList<>();
    Button btnBack;
    Button btnClear;


    Map<Button,Settings> listSettingsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Hooking up the Database
        db = AppDatabase.getAppDatabase(this);
        //final Settings settings = new Settings();
        // Lets populate the list with all button attributes
        populateList();


        // Gets the current Settings on the database
        getDataFromDatabase();


//        for (int i = 0; i < checkBoxList.size(); i++) {
//            final int selected = i;
//            checkBoxList.get(i).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Button thisButton = checkBoxList.get(selected);
//                    CharSequence buttonText = thisButton.getText();
//                    Toast.makeText(SettingsActivity.this, "You've selected " + buttonText, Toast.LENGTH_SHORT).show();
//                    thisButton.setBackgroundColor(Color.GREEN);
//
//                    //settings.setPressed(true);
//                    settings.setSettingName(buttonText.toString());
//                    settings.setPosition(selected);
//
//                    SettingsClass.populateWithData(db, settings);
//                }
//            });
//        }


        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingsActivity.this,"Your Settings is saved!", Toast.LENGTH_LONG).show();
                for (CheckBox level: checkBoxList){
                    if(level.isChecked()) {
                        Settings settings = new Settings();
                        settings.setPressed(true);
                        settings.setSettingName(level.getText().toString());
                        SettingsClass.populateWithData(db, settings);
                    }
                    else if (!level.isChecked()){
                        Settings settings = new Settings();
                        settings.setPressed(false);
                        settings.setSettingName(level.getText().toString());
                        SettingsClass.populateWithData(db, settings);
                    }

                }


                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnClear = (Button) findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clean the Database
                SettingsClass.deleteAll(db);
            }
        });



    }


    private void getDataFromDatabase() {

        // TODO: Fix the bug on coloring all the levels from Database

        String TAG = SettingsActivity.class.getName();
        List<Settings> dataSaved;
        dataSaved = SettingsClass.getAllSettings(db);

        if(dataSaved.size() == 0)
        {
            return;
        }
        else {

            for(int i = 0; i < dataSaved.size(); i++) {
                Settings currentSetting = dataSaved.get(i);
                Log.d(TAG, "Rows count "+ dataSaved.size());
                Log.d(TAG, "Data is: " + dataSaved.get(0).getPosition());

                for(int j = 0; j < checkBoxList.size(); j++)
                {
                    if(dataSaved.get(i).getSettingName() == checkBoxList.get(j).getText());
                    {
                        if(dataSaved.get(i).getPressed())
                        {
                            checkBoxList.get(j).setBackgroundColor(Color.GREEN);
                        }

                    }
                }
           }
        }
    }
    private void populateList() {

        // Generating the References for each button
        CheckBox x1 = (CheckBox) findViewById(R.id.btn_mul1);
        CheckBox x2 = (CheckBox) findViewById(R.id.btn_mul2);
        CheckBox x3 = (CheckBox) findViewById(R.id.btn_mul3);
        CheckBox x4 = (CheckBox) findViewById(R.id.btn_mul4);
        CheckBox x5 = (CheckBox) findViewById(R.id.btn_mul5);
        CheckBox x6 = (CheckBox) findViewById(R.id.btn_mul6);
        CheckBox x7 = (CheckBox) findViewById(R.id.btn_mul7);
        CheckBox x8 = (CheckBox) findViewById(R.id.btn_mul8);
        CheckBox x9 = (CheckBox) findViewById(R.id.btn_mul9);
        CheckBox x10 = (CheckBox) findViewById(R.id.btn_mul10);
        CheckBox x11 = (CheckBox) findViewById(R.id.btn_mul11);
        CheckBox x12 = (CheckBox) findViewById(R.id.btn_mul12);

        // Adding the buttons to the list
        checkBoxList.add(x1);
        checkBoxList.add(x2);
        checkBoxList.add(x3);
        checkBoxList.add(x4);
        checkBoxList.add(x5);
        checkBoxList.add(x6);
        checkBoxList.add(x7);
        checkBoxList.add(x8);
        checkBoxList.add(x9);
        checkBoxList.add(x10);
        checkBoxList.add(x11);
        checkBoxList.add(x12);
    }
}




