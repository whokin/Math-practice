package com.trainer.math.mathtrainer;

import android.os.AsyncTask;

import java.util.List;

import androidx.annotation.NonNull;

public class SettingsClass {



    public static void populateAsync(@NonNull final AppDatabase db, Settings setting){
        PopulateDbAsync task = new PopulateDbAsync(db, setting);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db, Settings setting){
        populateWithData(db,setting);
    }

    public static void deleteAll(final AppDatabase db)
    {
        db.settingsInterface().deleteAll();
    }

    public static List<Settings> getAllSettings(final AppDatabase db)
    {
        return db.settingsInterface().getAllSettings();
    }

    private static Settings addSetting(final AppDatabase db, Settings settings){
        db.settingsInterface().insertAll(settings);
        return settings;
    }

    public static void populateWithData(AppDatabase db, Settings settings){
        addSetting(db,settings);
    }



    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>
    {
        private final AppDatabase mDb;
        private final Settings setting;

        PopulateDbAsync(AppDatabase db, Settings setting){
            this.mDb = db;
            this.setting = setting;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            populateWithData(mDb,setting);
            return null;
        }
    }
}
