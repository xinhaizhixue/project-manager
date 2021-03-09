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

import com.example.projectmanager.AllInformation.ProjectDB;
import com.example.projectmanager.BaseAppCompatActivity;
import com.example.projectmanager.R;

public class CommentActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private Button mBtnFinish;
    private EditText mEtComment;
    private String ProjectTask;
    private CommentDB commentDB;
    private SQLiteDatabase dbWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetToolBar();
        Init_CommentDB();
        FindViewById();
        SetOnClickListener();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    private void SetToolBar(){
        getToolbarTitle().setText("添加批注");
        getSubTitle().setText("");
    }

    private void Init_CommentDB(){
        ProjectTask = getIntent().getStringExtra("project_task_name");
        commentDB = new CommentDB(this,ProjectTask + "comment");
        dbWriter = commentDB.getWritableDatabase();
    }

    private void FindViewById(){
        mBtnFinish = findViewById(R.id.btn_tianjiawancheng);
        mEtComment = findViewById(R.id.et_pizhu);
        mEtComment.setText(getIntent().getStringExtra("comment"));
    }

    private void SetOnClickListener(){
        mBtnFinish.setOnClickListener(this);
    }

    private void addDB(){
        ContentValues cv = new ContentValues();
        cv.put(CommentDB.COMMENT,mEtComment.getText().toString());
        dbWriter.insert(CommentDB.TABLE_NAME,null,cv);
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
