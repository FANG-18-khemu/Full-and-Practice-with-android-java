package com.example.lma.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseDao {


    @Insert
    void insertCourse(Course course);

    @Update
    void updateCourse(Course course);

    @Delete
    void deletCourse(Course course);

    @Query("SELECT * FROM COURSE_TABLE")
    LiveData<List<Course>> getCourse();

   @Query("SELECT * FROM course_table WHERE categrory_Id ==:id")
    LiveData<List<Course>> getAllCourse(int id);

}
