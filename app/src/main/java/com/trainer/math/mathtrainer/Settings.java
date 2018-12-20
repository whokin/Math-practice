package com.trainer.math.mathtrainer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Settings {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "multiplication_table")
    private String settingName;


    @ColumnInfo(name = "status")
    private Boolean isPressed;
}
