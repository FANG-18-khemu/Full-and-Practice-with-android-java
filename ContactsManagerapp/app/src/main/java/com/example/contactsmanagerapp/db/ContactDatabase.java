package com.example.contactsmanagerapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.contactsmanagerapp.Contact;

@Database(entities = {Contact.class},version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    public abstract ContactDao getContactDao();
}
