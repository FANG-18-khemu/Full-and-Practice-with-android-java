package com.example.contactsmanagerapp;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contact extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    int contact_id;

    private String name;
    private String email;

    @Ignore
    public Contact(){}

    public Contact(String name, String email, int contact_id) {
        this.name = name;
        this.email = email;
        this.contact_id = contact_id;
    }

    @Bindable
    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmail() {
        return email;

    }


    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }
}
