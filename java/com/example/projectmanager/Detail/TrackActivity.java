package com.example.projectmanager.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectmanager.BaseAppCompatActivity;
import com.example.projectmanager.R;

public class TrackActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private Button mBtnFinish;
    private String ProjectTask;
    private EditText mEtActualStartTime,mEtActualFinishTime;
    private TrackDB trackDB;
    private SQLiteDatabase dbWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetToolBar();
        Init_TrackDB();
        FindViewById();
        SetOnClickListener();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_track;
    }

    private void SetToolBar(){
        getToolbarTitle().setText("添加跟踪");
        getSubTitle().setText("");
    }

    private void Init_TrackDB(){
        ProjectTask = getIntent().getStringExtra("project_task_name");
        trackDB = new TrackDB(this, ProjectTask + "track");
        dbWriter = trackDB.getWritableDatabase();
    }

    private void FindViewById(){
        mBtnFinish = findViewById(R.id.btn_tianjiawancheng);
        mEtActualFinishTime = findViewById(R.id.et_renwujieshushijian);
        mEtActualStartTime = findViewById(R.id.et_renwukaishishijian);
    }

    private void SetOnClickListener(){
        mBtnFinish.setOnClickListener(this);
    }

    private void addDB(){
        ContentValues cv = new ContentValues();
        cv.put(TrackDB.ACTUALSTARTTIME,mEtActualStartTime.getText().toString());
        cv.put(TrackDB.ACTUALFINISHTIME,mEtActualFinishTime.getText().toString());
        dbWriter.insert(TrackDB.TABLE_NAME,null,cv);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn_tianjiawancheng:
                addDB();
                finish();
                break;
        }
    }
}
