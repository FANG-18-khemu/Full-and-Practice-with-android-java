package com.example.lma.model;


import android.app.Application;

import android.os.Handler;
import android.os.Looper;


import androidx.lifecycle.LiveData;

import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CoursesShopRepository {

    //dao variables

    private CategroryDao categroryDao;
    private CourseDao courseDao;

    private LiveData<List<Categrory>> categrory;

    private LiveData<List<Course>> courses;

    public CoursesShopRepository(Application application)
    {
        CourseDatabase courseDatabase = CourseDatabase.getInstance(application);
        categroryDao = courseDatabase.getCategroryDao();
        courseDao = courseDatabase.getCourseDao();
    }

    public LiveData<List<Categrory>> getCategrory() {
        return categroryDao.getAllCategrory();
    }

    public LiveData<List<Course>> getCourses(int id) {
        return courseDao.getAllCourse(id);
    }

    public void insertCategrory(Categrory categrory)
    {
        ExecutorService excutore = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        excutore.execute(new Runnable() {
            @Override
            public void run() {
                categroryDao.AddCategrory(categrory);
            }
        });

    }

    public void insetCourse(Course course)
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                courseDao.insertCourse(course);
            }
        });
    }

    public  void DeleteCategrory(Categrory categrory)
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                categroryDao.deletecategrory(categrory);
            }
        });
    }

    public void DeleteCourse(Course course)
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                courseDao.deletCourse(course);
            }
        });
    }

    public  void UpdateCategrory(Categrory categrory)
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                categroryDao.updateCategrory(categrory);
            }
        });
    }

    public void UpdateCourse(Course course)
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                courseDao.updateCourse(course);
            }
        });
    }
}
