package com.example.projectmanager.Detail;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TrackDB extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "project_task_name_track";
    public static final String ACTUALSTARTTIME = "actual_start_time";
    public static final String ACTUALFINISHTIME = "actual_finish_time";
    public static final String ID = "_id";

    public TrackDB(@Nullable Context context, String project_task_name_track) {
        super(context,project_task_name_track,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "("
                + ID + " integer primary key autoincrement,"
                + ACTUALSTARTTIME + " text not null,"
                + ACTUALFINISHTIME + " text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
