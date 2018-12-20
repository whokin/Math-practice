package com.trainer.math.mathtrainer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Settings {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "multiplication_table")
    private String settingName;


    @ColumnInfo(name = "status")
    private Boolean isPressed;

    @ColumnInfo(name = "position")
    private int position;


    public int getPosition(){
        return position;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public Boolean getPressed() {
        return isPressed;
    }

    public void setPressed(Boolean pressed) {
        isPressed = pressed;
    }
}
