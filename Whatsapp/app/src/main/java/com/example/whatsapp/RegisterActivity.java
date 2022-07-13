package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    //all widgets
    EditText userName,password,email;
    Button btnRegist;

    //Firebase
    FirebaseAuth auth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initializes the widgets

        userName = findViewById(R.id.Username_reg);
        password = findViewById(R.id.Password_reg);
        email = findViewById(R.id.Email_reg);

        btnRegist = findViewById(R.id.registerButton);



        auth = FirebaseAuth.getInstance();

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_txt = userName.getText().toString();
                String email_txt = email.getText().toString();
                String password_txt = password.getText().toString();

                if(TextUtils.isEmpty(username_txt) || TextUtils.isEmpty(email_txt) || TextUtils.isEmpty(password_txt))
                {
                    Toast.makeText(RegisterActivity.this, "Please enter all the data", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    RegisterNow(username_txt,password_txt, email_txt);
                }


            }
        });
    }

    private void RegisterNow(final String usern, String pass, String eml)
    {
        auth.createUserWithEmailAndPassword(eml,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            FirebaseUser user = auth.getCurrentUser();
                            String userId = user.getUid();
                            databaseReference = FirebaseDatabase.getInstance().getReference("MyUsers")
                                    .child(userId);

                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("id",userId);
                            hashMap.put("username",usern);
                            hashMap.put("image","default");

                            //going back to main activity
                            databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                        startActivity(i);
                                        finish();
                                    }
                                }
                            });


                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}