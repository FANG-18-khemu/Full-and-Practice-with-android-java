package com.example.lma.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
//import androidx.databinding.library.baseAdapters.BR;
//import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.example.lma.BR;



@Entity(tableName = "categrory")
public class Categrory extends BaseObservable {

    @PrimaryKey(autoGenerate = true)

    private int categrory_id;

    @ColumnInfo(name = "categrory_Name")
    private String categroryName;

    @ColumnInfo(name = "categrory_description")
    private String categoryDescription;

    @Ignore
    public Categrory() {}

    public Categrory(int categrory_id, String categroryName, String categoryDescription) {
        this.categrory_id = categrory_id;
        this.categroryName = categroryName;
        this.categoryDescription = categoryDescription;
    }



    @Bindable
    public int getCategrory_id() {
        return categrory_id;
    }

    public void setCategrory_id(int categrory_id) {
        this.categrory_id = categrory_id;
      notifyPropertyChanged(BR.categrory_id);
    }

    @Bindable
    public String getCategroryName() {
        return categroryName;
    }

    public void setCategroryName(String categroryName) {
        this.categroryName = categroryName;
      notifyPropertyChanged(BR.categroryName);
    }

    @Bindable
    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
        notifyPropertyChanged(BR.categoryDescription);
    }

    @Override
    public String toString()
    {
        return this.categroryName;
    }
}
