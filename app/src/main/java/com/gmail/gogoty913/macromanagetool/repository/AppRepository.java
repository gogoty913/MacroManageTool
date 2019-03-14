package com.gmail.gogoty913.macromanagetool.repository;

import android.content.Context;

import com.gmail.gogoty913.macromanagetool.database.AppDatabase;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public class AppRepository {
    private static final AppRepository ourInstance = new AppRepository();
    private AppDatabase db;

    public static AppRepository getInstance(Context context) {
        if(ourInstance.getDb() == null) {
            ourInstance.setDb(context);
        }
        return ourInstance;
    }

    private AppRepository() {
    }

    private void setDb(Context context){
        db = Room.databaseBuilder(context,AppDatabase.class,"macro_manage_tool_db").build();
    }

    public AppDatabase getDb(){
        return db;
    }
}
