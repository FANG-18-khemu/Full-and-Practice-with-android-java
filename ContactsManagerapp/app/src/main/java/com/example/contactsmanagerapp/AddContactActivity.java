package com.example.contactsmanagerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.contactsmanagerapp.databinding.ActivityAddContactBinding;

public class AddContactActivity extends AppCompatActivity {

    private ActivityAddContactBinding activityAddContactBinding;
    Contact contact;
    private NewOnClickHandler newOnClickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        contact = new Contact();

        activityAddContactBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_contact);
        activityAddContactBinding.setContact(contact);
        newOnClickHandler = new NewOnClickHandler(this);
        activityAddContactBinding.setClickHandler(newOnClickHandler);



    }

    public class NewOnClickHandler
    {
        Context context;

        public NewOnClickHandler(Context context) {
            this.context = context;
        }

        public void onSubmitClickListener(View view)
        {
            if(contact.getName() == null)
            {
                Toast.makeText(getApplicationContext(), "Field can not be null", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Intent i = new Intent();
                i.putExtra("NAME",contact.getName());
                i.putExtra("EMAIL",contact.getEmail());

                setResult(RESULT_OK,i);
                finish();

            }
        }
    }
}