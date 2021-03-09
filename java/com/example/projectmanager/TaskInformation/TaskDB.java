package com.example.projectmanager.TaskInformation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TaskDB extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "project_name";
    public static final String TASK_NAME = "task_name";
    public static final String TASK_PERSON_IN_CHARGE = "task_person_in_charge";
    public static final String TASK_START_TIME = "task_start_time";
    public static final String TASK_FINISH_TIME = "task_finish_time";
    public static final String TASK_DETAIL = "task_detail";
    public static final String TASK_RECORD_TIME = "task_record_time";
    public static final String ID = "_id";

    public TaskDB(@Nullable Context context,String project_name) {
        super(context,project_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "("
                + ID + " integer primary key autoincrement,"
                + TASK_NAME + " text not null,"
                + TASK_PERSON_IN_CHARGE + " text not null,"
                + TASK_START_TIME + " text not null,"
                + TASK_FINISH_TIME + " text not null,"
                + TASK_DETAIL + " text not null,"
                + TASK_RECORD_TIME + " text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
