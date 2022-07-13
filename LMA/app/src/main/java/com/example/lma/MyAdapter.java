package com.example.lma;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lma.databinding.ContentListItemBinding;
import com.example.lma.model.Course;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private onItemClickListener listener;

    private ArrayList<Course> courses = new ArrayList<>();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContentListItemBinding contentListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.content_list_item,
                parent,
                false
        );
        return new MyViewHolder(contentListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.binding.setCourse(course);
    }

    @Override
    public int getItemCount() {
       if(courses !=null)
       {
           return courses.size();
       }
       else
       {
           return 0;
       }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ContentListItemBinding binding;

        public MyViewHolder(ContentListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickPosition = getAdapterPosition();
                    if (listener != null && clickPosition != RecyclerView.NO_POSITION) {
                        listener.onItemClick(courses.get(clickPosition));
                    }
                }
            });
        }


    }

    public interface onItemClickListener {

        void onItemClick(Course course);
    }

    public void setListener(onItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

    public void setCourses(ArrayList<Course> newcourses)
    {
      //  this.courses = courses;
        //notifyDataSetChanged();
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new CourseDiffCallBack(courses,newcourses),false);

        courses = newcourses;
        result.dispatchUpdatesTo(MyAdapter.this);

    }
}
