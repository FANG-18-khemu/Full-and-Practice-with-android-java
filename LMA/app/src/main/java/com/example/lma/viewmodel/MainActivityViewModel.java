package com.example.lma.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lma.model.Categrory;
import com.example.lma.model.Course;
import com.example.lma.model.CoursesShopRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    //Repository k object
    private CoursesShopRepository repository ;

    //live data
    private LiveData<List<Course>> courses;
    private LiveData<List<Categrory>> categrory;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        repository = new CoursesShopRepository(application);
    }

    public LiveData<List<Categrory>> getCategrory()
    {
        categrory = repository.getCategrory();
        return categrory;
    }

    public LiveData<List<Course>> getCourses(int id)
    {
        courses = repository.getCourses(id);
        return courses;
    }

    public void addNewCourse(Course course)
    {
        repository.insetCourse(course);

    }
    public void updateCourse(Course course)
    {
        repository.UpdateCourse(course);
    }

    public void deleteCourse(Course course)
    {
        repository.DeleteCourse(course);
    }
}
