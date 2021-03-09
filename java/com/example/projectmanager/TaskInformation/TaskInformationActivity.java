package com.example.projectmanager.TaskInformation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectmanager.AllInformation.InformationFragment;
import com.example.projectmanager.BaseAppCompatActivity;
import com.example.projectmanager.R;

public class TaskInformationActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private FrameLayout mFl;
    private TaskInformationFragment taskInformationFragment;
    private Button mBtnAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetToolBar();
        FindViewById();
        SetClickListener();
        AddFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_information;
    }

    private void SetToolBar(){
        getToolbarTitle().setText("任务列表");
        getSubTitle().setText("");
    }

    private void FindViewById(){
        mFl = findViewById(R.id.fl_information);
        mBtnAddTask = findViewById(R.id.btn_tianjiarenwu);
    }

    private void SetClickListener(){
        mFl.setOnClickListener(this);
        mBtnAddTask.setOnClickListener(this);
    }

    private void AddFragment(){
        taskInformationFragment = new TaskInformationFragment();
        getFragmentManager().beginTransaction().add(R.id.fl_information,taskInformationFragment).commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn_tianjiarenwu:
                intent = new Intent(TaskInformationActivity.this,AddTaskActivity.class);
                intent.putExtra("project_name",getIntent().getStringExtra("project_name"));
                startActivity(intent);
                break;
        }
    }



}
