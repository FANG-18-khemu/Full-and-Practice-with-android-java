package com.example.contactsmanagerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Delete;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.util.Half;
import android.view.View;

import com.example.contactsmanagerapp.databinding.ActivityMainBinding;
import com.example.contactsmanagerapp.db.ContactDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ContactDatabase contactDatabase;
    private MyContactAdapter adapter;
    private ArrayList<Contact> contacts;

    private ActivityMainBinding activityMainBinding;
    private ClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //data binding and adding clickhandler
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        clickHandler = new ClickHandler(this);
        activityMainBinding.setClickHandler(clickHandler);

        //recycler view
     //   RecyclerView recyclerView = findViewById(R.id.Recyclerview1);
        RecyclerView recyclerView = activityMainBinding.Recyclerview1;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //adapter
        adapter = new MyContactAdapter(contacts);
        recyclerView.setAdapter(adapter);

        contactDatabase = Room.databaseBuilder(getApplicationContext(),ContactDatabase.class,"contact_db").build();

        loader();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contact contact = contacts.get(viewHolder.getAdapterPosition());
                DeleteContact(contact);
            }
        }).attachToRecyclerView(recyclerView);

        //floating action buttton


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==1 && resultCode == RESULT_OK)
        {
            String name = data.getStringExtra("NAME");
            String email = data.getStringExtra("EMAIL");

            Contact contact = new Contact(name,email,0);

            ADDNEWCONTACT(contact);
        }
    }

    public void ADDNEWCONTACT(Contact contact)
    {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.myLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDatabase.getContactDao().addContact(contact);
                contacts.add(contact);
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    public void DeleteContact(Contact contact)
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.myLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
               contactDatabase.getContactDao().delete(contact);
                contacts.remove(contact);
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
    public void loader()
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.myLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contacts = (ArrayList<Contact>) contactDatabase.getContactDao().getAllContact();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setContacts(contacts);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
    public class ClickHandler
    {
        private Context context;

        public ClickHandler(Context context)
        {
            this.context = context;
        }

        public void onFabButtonClick(View view)
        {
            Intent i = new Intent(MainActivity.this,AddContactActivity.class);
            startActivityForResult(i,1);
        }
    }
}