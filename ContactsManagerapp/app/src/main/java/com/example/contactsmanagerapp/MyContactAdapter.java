package com.example.contactsmanagerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsmanagerapp.databinding.ContactListViewBinding;

import java.util.ArrayList;

public class MyContactAdapter extends RecyclerView.Adapter<MyContactAdapter.ViewHolder> {
    private ArrayList<Contact> contacts;

    public MyContactAdapter(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
      //  public TextView name;
      //  public TextView email;
        private ContactListViewBinding contactListViewBinding;
        public ViewHolder(@NonNull ContactListViewBinding contactListViewBinding) {
           // super(itemView);
           // this.name = itemView.findViewById(R.id.tvname);
           // this.email = itemView.findViewById(R.id.tvemail);
            super(contactListViewBinding.getRoot());
            this.contactListViewBinding = contactListViewBinding;

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      //  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_view,parent,false);
        //return new ViewHolder(view);
        ContactListViewBinding contactListViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.contact_list_view,parent,false);
        return new ViewHolder(contactListViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Contact contact = contacts.get(position);

     //   holder.name.setText(contact.getName());
       // holder.email.setText(contact.getEmail());
        holder.contactListViewBinding.setContact(contact);

    }

    @Override
    public int getItemCount() {
        if(contacts != null)
            return contacts.size();
        else
            return 0;
    }

    public void setContacts(ArrayList<Contact> contacts)
    {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

}
