package com.example.projectmanager.AllInformation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ProjectDB extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "project";
    public static final String PROJECT_NAME = "project_name";
    public static final String PROJECT_START_TIME = "project_start_time";
    public static final String PROJECT_FINISH_TIME = "project_finish_time";
    public static final String PROJECT_PERSON_NUMBER = "project_person_number";
    public static final String PROJECT_RECORD_TIME = "project_record_time";
    public static final String ID = "_id";

    public ProjectDB(@Nullable Context context) {
        super(context,"project",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "("
                + ID + " integer primary key autoincrement,"
                + PROJECT_NAME + " text not null,"
                + PROJECT_START_TIME + " text not null,"
                + PROJECT_FINISH_TIME + " text not null,"
                + PROJECT_PERSON_NUMBER + " text not null,"
                + PROJECT_RECORD_TIME + " text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
