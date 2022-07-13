package com.example.lma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.lma.databinding.ActivityMainBinding;
import com.example.lma.model.Categrory;
import com.example.lma.model.Course;
import com.example.lma.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel ;
    private ArrayList<Categrory> categrories;
    private ArrayList<Course> courses;
    private ActivityMainBinding activityMainBinding;
    private ClickHandler clickHandler;
    private Categrory categrory;

    public int selectedCourseId;

    //recycler view
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private static final int Add_Course_Request_Code = 1;
    public static final int Edit_Course_Request_code = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        clickHandler = new ClickHandler();
        activityMainBinding.setClickhandler(clickHandler);



        viewModel.getCategrory().observe(this, new Observer<List<Categrory>>() {
            @Override
            public void onChanged(List<Categrory> categrorie) {

                categrories = (ArrayList<Categrory>) categrorie;

                for(Categrory c:categrorie)
                {
                    Log.v("categories",c.getCategroryName());
                }

                showOnSpinnerAdapter();
            }
        });

        viewModel.getCourses(1).observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                for(Course c : courses)
                {
                    Log.v("course",c.getCourse_name());
                }
            }
        });
    }

    public void loadCourseAdapter(int id)
    {
        viewModel.getCourses(id).observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> course) {
               courses = (ArrayList<Course>) course;
               loadRecyclerView();
            }
        });
    }

    private void loadRecyclerView()
    {
        recyclerView = activityMainBinding.secondaryLayout.recyclerView1;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new MyAdapter();
        adapter.setCourses(courses);
        recyclerView.setAdapter(adapter);

        //edit course

        adapter.setListener(new MyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Course course) {
                selectedCourseId = course.getCourse_id();
                Intent i = new Intent(MainActivity.this,AddActivity2.class);
                i.putExtra(AddActivity2.COURSE_ID,selectedCourseId);
                i.putExtra(AddActivity2.COURSE_NAME,course.getCourse_name());
                i.putExtra(AddActivity2.COURSE_PRICE,course.getCourse_price());

                startActivityForResult(i,Edit_Course_Request_code);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Course course = courses.get(viewHolder.getAdapterPosition());
                viewModel.deleteCourse(course);
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void showOnSpinnerAdapter() {
        ArrayAdapter<Categrory> adapter = new ArrayAdapter<>(this,R.layout.spinner_item,categrories);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(adapter);
    }

    public  class ClickHandler {

        public void onFabClick(View view)
        {
            Intent i = new Intent(MainActivity.this,AddActivity2.class);
            startActivityForResult(i,Add_Course_Request_Code);
        }

        public void onSelectItem(AdapterView<?> parent,View view,int pos,long id)
        {
            categrory = (Categrory) parent.getItemAtPosition(pos);

            String msg = "id is " + categrory.getCategrory_id()+"\n"+"categrory name is :"+ categrory.getCategroryName();

            Toast.makeText(parent.getContext(), msg, Toast.LENGTH_SHORT).show();

            loadCourseAdapter(categrory.getCategrory_id());

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int selectedCate = categrory.getCategrory_id();

        if(requestCode ==Add_Course_Request_Code && resultCode==RESULT_OK)
        {
            Course course = new Course();
            course.setCategroryId(selectedCate);
            course.setCourse_name(data.getStringExtra(AddActivity2.COURSE_NAME));
            course.setCourse_price(data.getStringExtra(AddActivity2.COURSE_PRICE));

            viewModel.addNewCourse(course);
        }else if(requestCode ==Edit_Course_Request_code && resultCode==RESULT_OK)
        {
            Course course = new Course();
            course.setCategroryId(selectedCate);
            course.setCourse_name(data.getStringExtra(AddActivity2.COURSE_NAME));
            course.setCourse_price(data.getStringExtra(AddActivity2.COURSE_PRICE));

            course.setCourse_id(selectedCourseId);
            viewModel.updateCourse(course);
        }

    }
}