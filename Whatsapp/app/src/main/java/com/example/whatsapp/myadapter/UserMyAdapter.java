package com.example.whatsapp.myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whatsapp.R;
import com.example.whatsapp.model.Users;

import java.util.ArrayList;
import java.util.List;

public class UserMyAdapter extends RecyclerView.Adapter<UserMyAdapter.MyUserViewHolder> {

    private List<Users> users;
    private Context context;

    public UserMyAdapter(List<Users> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public MyUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new UserMyAdapter.MyUserViewHolder(LayoutInflater.from(context).inflate(R.layout.useri_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyUserViewHolder holder, int position) {
        Users user = users.get(position);
        holder.userText.setText(user.getUsername());

        if(user.getImage().equals("default"))
        {
            holder.imageView.setImageResource(R.mipmap.ic_launcher);
        }
        else{
                Glide.with(context).load(user.getImage()).into(holder.imageView);
            }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class MyUserViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView userText;

        public MyUserViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ufimageView);
            userText = itemView.findViewById(R.id.ufusername);
        }
    }
}
