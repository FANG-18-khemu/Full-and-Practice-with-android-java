package com.example.lma.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.lma.BR;

import java.util.Objects;


@Entity(tableName = "course_table",foreignKeys = @ForeignKey(entity = Categrory.class,parentColumns = "categrory_id",childColumns = "categrory_Id",onDelete = CASCADE))
public class Course  extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "course_id")
    private int course_id;

    @ColumnInfo(name = "course_name")
    private String course_name;

    @ColumnInfo(name = "course_price")
    private String course_price;


    @ColumnInfo(name = "categrory_Id",index = true)
    private int categroryId;

    @Ignore
    public Course(){}

    public Course(int course_id, String course_name, String course_price, int categroryId) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_price = course_price;
        this.categroryId = categroryId;
    }

    @Bindable
    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
       notifyPropertyChanged(BR.course_id);
    }

    @Bindable
    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
        notifyPropertyChanged(BR.course_name);
    }

    @Bindable
    public String getCourse_price() {
        return course_price;
    }

    public void setCourse_price(String course_price) {
        this.course_price = course_price;
        notifyPropertyChanged(BR.course_price);
    }

    @Bindable
    public int getCategroryId() {
        return categroryId;
    }

    public void setCategroryId(int categroryId) {
        this.categroryId = categroryId;
       notifyPropertyChanged(BR.categrory_id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return course_id == course.course_id && categroryId == course.categroryId && course_name.equals(course.course_name) && course_price.equals(course.course_price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course_id, course_name, course_price, categroryId);
    }
}
