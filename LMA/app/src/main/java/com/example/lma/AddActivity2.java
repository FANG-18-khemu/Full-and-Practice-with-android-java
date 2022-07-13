package com.example.lma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.example.lma.databinding.ActivityAdd2Binding;
import com.example.lma.model.Course;

public class AddActivity2 extends AppCompatActivity {

    private Course course;
    public static final String COURSE_NAME = "coursename";
    public static final String COURSE_ID = "courseid";
    public static final String COURSE_PRICE = "courseprice";


    //databinding
    private ActivityAdd2Binding activityAdd2Binding;
    private AddAndClickHandler clickhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);

        course = new Course();
        activityAdd2Binding = DataBindingUtil.setContentView(this, R.layout.activity_add2);
        activityAdd2Binding.setCourse(course);

        clickhandler = new AddAndClickHandler(this);
        activityAdd2Binding.setClickhandler(clickhandler);

        Intent i = getIntent();
        if(i.hasExtra(COURSE_ID))
        {
            setTitle("Edit Course");
            course.setCourse_name(i.getStringExtra(COURSE_NAME));
            course.setCourse_price(i.getStringExtra(COURSE_PRICE));

        }
        else
        {
            setTitle("Create New Course");
        }
    }


    public class AddAndClickHandler {
        Context context;

        public AddAndClickHandler(Context context) {
            this.context = context;
        }

        public void courseClickHandler(View view) {
            if (course.getCourse_name() == null) {
                Toast.makeText(getApplicationContext(), "Please Enter the data", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent();
                i.putExtra(COURSE_NAME, course.getCourse_name());
                i.putExtra(COURSE_PRICE, course.getCourse_price());
                setResult(RESULT_OK, i);
                finish();
            }
        }
    }

}