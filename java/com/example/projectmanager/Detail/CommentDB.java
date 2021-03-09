package com.example.projectmanager.Detail;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CommentDB extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "project_task_name_comment";
    public static final String COMMENT = "comment";
    public static final String ID = "_id";
    public Context context;

    public CommentDB(@Nullable Context context, String project_task_name_comment) {
        super(context,project_task_name_comment,null,1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "("
                + ID + " integer primary key autoincrement,"
                + COMMENT + " text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
