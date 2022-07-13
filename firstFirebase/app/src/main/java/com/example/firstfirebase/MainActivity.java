package com.example.firstfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private DatabaseReference myref;

    ArrayList<Message> messages;

    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myref = database.getReference();

        messages = new ArrayList<>();

        clearAllMessage();

        addDataFromFireBase();
    }

    private void addDataFromFireBase() {

        Query query = myref.child("message");
        query.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                clearAllMessage();

                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Message message = new Message();
                    message.setImageUrl(dataSnapshot.child("image").getValue().toString());
                    message.setName(dataSnapshot.child("name").getValue().toString());
                    messages.add(message);
                }
                adapter = new MyAdapter(getApplicationContext(),messages);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void clearAllMessage() {
        if(messages != null)
        {
            messages.clear();
        }
        if(adapter !=null)
        {
            adapter.notifyDataSetChanged();
        }
        messages = new ArrayList<>();
    }
}