package com.example.contactsmanagerapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.contactsmanagerapp.Contact;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void addContact(Contact contact);

    @Query("SELECT * FROM contact_table")
    List<Contact> getAllContact();

    @Delete
    void delete(Contact contact);
}
