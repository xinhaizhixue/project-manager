package com.example.projectmanager.AllInformation;

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
import com.example.projectmanager.Detail.CommentActivity;
import com.example.projectmanager.Detail.DetailActivity;
import com.example.projectmanager.R;

public class AddProjectActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private Button mBtnFinish;
    private EditText mEtProjectName,mEtProjectStartTime,mEtProjectFinishTime,mEtProjectPersonNumber,mEtProjectRecordTime;
    private ProjectDB projectDB;
    private SQLiteDatabase dbWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetToolBar();
        Init_ProjectDB();
        FindViewById();
        SetOnClickListener();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_project;
    }

    private void SetToolBar(){
        getToolbarTitle().setText("添加项目");
        getSubTitle().setText("");
    }

    private void Init_ProjectDB(){
        projectDB = new ProjectDB(this);
        dbWriter = projectDB.getWritableDatabase();
    }

    private void FindViewById(){
        mBtnFinish = findViewById(R.id.btn_tianjiawancheng);
        mEtProjectName = findViewById(R.id.et_xiangmumingcheng);
        mEtProjectStartTime = findViewById(R.id.et_jihuakaishishijian);
        mEtProjectFinishTime = findViewById(R.id.et_jihuajieshushijian);
        mEtProjectPersonNumber = findViewById(R.id.et_xiangmurenshu);
        mEtProjectRecordTime = findViewById(R.id.et_lurushijian);
    }

    private void SetOnClickListener(){
        mBtnFinish.setOnClickListener(this);
    }

    //添加数据
    private void addDB(){
        ContentValues cv = new ContentValues();
        cv.put(ProjectDB.PROJECT_NAME,mEtProjectName.getText().toString());
        cv.put(ProjectDB.PROJECT_START_TIME,mEtProjectStartTime.getText().toString());
        cv.put(ProjectDB.PROJECT_FINISH_TIME,mEtProjectFinishTime.getText().toString());
        cv.put(ProjectDB.PROJECT_PERSON_NUMBER,mEtProjectPersonNumber.getText().toString());
        cv.put(ProjectDB.PROJECT_RECORD_TIME,mEtProjectRecordTime.getText().toString());
        dbWriter.insert(ProjectDB.TABLE_NAME,null,cv);
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
