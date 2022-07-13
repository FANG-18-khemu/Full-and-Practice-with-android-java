package com.example.lma.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Categrory.class,Course.class},version = 1,exportSchema = false)
public abstract class CourseDatabase extends RoomDatabase {

    public abstract CourseDao getCourseDao();

    public abstract CategroryDao getCategroryDao();

    //singelton pattern

    private static CourseDatabase instance;

    public static synchronized  CourseDatabase getInstance(Context context)
    {
        if(instance ==null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),CourseDatabase.class,"course_db").fallbackToDestructiveMigration()
                    .addCallback(roomCallback).build();
        }
        return instance;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //when the database is created add some predefined data to it


            initialized();

        }
    };

    private static void initialized()
    {
        CourseDao courseDao = instance.getCourseDao();
        CategroryDao categroryDao = instance.getCategroryDao();

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                Categrory ob1 = new Categrory();
                ob1.setCategroryName("Front End");
                ob1.setCategoryDescription("web development using ");

                Categrory ob2 = new Categrory();
                ob2.setCategroryName("Back End");
                ob2.setCategoryDescription("backend with");

                categroryDao.AddCategrory(ob1);
                categroryDao.AddCategrory(ob2);


                //course
                Course c1 = new Course();
                c1.setCourse_name("HTML");
                c1.setCourse_price("$140");
                c1.setCategroryId(1);

                Course c2 = new Course();
                c2.setCourse_name("CSS");
                c2.setCourse_price("$190");
                c2.setCategroryId(1);

                Course c3 = new Course();
                c3.setCourse_name("React");
                c3.setCourse_price("$240");
                c3.setCategroryId(1);

                Course c4 = new Course();
                c4.setCourse_name("Sql");
                c4.setCourse_price("$340");
                c4.setCategroryId(2);

                Course c5 = new Course();
                c5.setCourse_name("MySql");
                c5.setCourse_price("$540");
                c5.setCategroryId(2);

                courseDao.insertCourse(c1);
                courseDao.insertCourse(c2);
                courseDao.insertCourse(c3);
                courseDao.insertCourse(c4);
                courseDao.insertCourse(c5);
            }
        });

    }
}
