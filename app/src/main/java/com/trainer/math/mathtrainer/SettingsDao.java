package com.trainer.math.mathtrainer;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface SettingsDao {

    @Query("SELECT * FROM Settings")
    List<Settings> getAllSettings();


    @Query("SELECT * FROM Settings WHERE status = 1")
    List<Settings> getAlluserSettings();

    @Insert
    void insertAll(Settings... entry);

    @Delete
    void delete(Settings entry);

    @Query("DELETE FROM Settings")
    void deleteAll();


}
