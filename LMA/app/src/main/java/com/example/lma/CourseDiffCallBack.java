package com.example.lma;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.lma.model.Course;

import java.util.ArrayList;

public class CourseDiffCallBack extends DiffUtil.Callback {

    private ArrayList<Course> oldCourses;
    private  ArrayList<Course> newCourses;

    public CourseDiffCallBack(ArrayList<Course> oldCourses, ArrayList<Course> newCourses) {
        this.oldCourses = oldCourses;
        this.newCourses = newCourses;
    }


    @Override
    public int getOldListSize() {
        return oldCourses==null?0:oldCourses.size();
    }

    @Override
    public int getNewListSize() {
        return newCourses==null?0:newCourses.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldCourses.get(oldItemPosition).getCourse_id() == newCourses.get(newItemPosition).getCourse_id();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldCourses.get(oldItemPosition).equals(newCourses.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
