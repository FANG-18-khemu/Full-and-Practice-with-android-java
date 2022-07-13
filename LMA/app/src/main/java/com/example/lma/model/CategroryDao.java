package com.example.lma.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface CategroryDao {

    @Insert
    void AddCategrory(Categrory categrory);

    @Update
    void updateCategrory(Categrory categrory);

    @Delete
    void deletecategrory(Categrory categrory);

    @Query("SELECT * FROM categrory")
    LiveData<List<Categrory>> getAllCategrory();
}
