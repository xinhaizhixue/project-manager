package com.example.projectmanager.TaskInformation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.renderscript.Script;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectmanager.BaseAppCompatActivity;
import com.example.projectmanager.Detail.CommentActivity;
import com.example.projectmanager.Detail.DetailActivity;
import com.example.projectmanager.R;

public class AddTaskActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private Button mBtnFinish;
    private EditText mEtTaskName,mEtTaskPersonInCharge,mEtTaskStartTime,mEtTaskFinishTime,mEtTaskDetail,mEtTaskRecordTime;
    private TaskDB taskDB;
    private SQLiteDatabase dbWriter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetToolBar();
        taskDB = new TaskDB(this,getIntent().getStringExtra("project_name"));
        dbWriter = taskDB.getWritableDatabase();
        FindViewById();
        SetOnClickListener();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_task;
    }

    private void SetToolBar(){
        getToolbarTitle().setText("添加任务");
        getSubTitle().setText("");
    }

    private void FindViewById(){
        mBtnFinish = findViewById(R.id.btn_tianjiawancheng);
        mEtTaskName = findViewById(R.id.et_renwumingcheng);
        mEtTaskPersonInCharge = findViewById(R.id.et_renwuzerenren);
        mEtTaskStartTime = findViewById(R.id.et_renwukaishishijian);
        mEtTaskFinishTime = findViewById(R.id.et_renwujieshushijian);
        mEtTaskDetail = findViewById(R.id.et_renwumiaoshu);
        mEtTaskRecordTime = findViewById(R.id.et_lurushijian);
    }

    private void SetOnClickListener(){
        mBtnFinish.setOnClickListener(this);
    }

    private void addDB(){
        ContentValues cv = new ContentValues();
        cv.put(TaskDB.TASK_NAME,mEtTaskName.getText().toString());
        cv.put(TaskDB.TASK_PERSON_IN_CHARGE,mEtTaskPersonInCharge.getText().toString());
        cv.put(TaskDB.TASK_START_TIME,mEtTaskStartTime.getText().toString());
        cv.put(TaskDB.TASK_FINISH_TIME,mEtTaskFinishTime.getText().toString());
        cv.put(TaskDB.TASK_DETAIL,mEtTaskDetail.getText().toString());
        cv.put(TaskDB.TASK_RECORD_TIME,mEtTaskRecordTime.getText().toString());
        dbWriter.insert(TaskDB.TABLE_NAME,null,cv);
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
