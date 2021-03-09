package com.example.projectmanager.Detail;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectmanager.AllInformation.ProjectDB;
import com.example.projectmanager.BaseAppCompatActivity;
import com.example.projectmanager.R;

import org.w3c.dom.Comment;

public class DetailActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private Button mBtnAddTrack,mBtnAddComment;
    private TextView mTvTaskName,mTvTaskPersonInCharge,mTvTaskStartTime,mTvTaskFinishTime,mTvTaskDetail,mTvTaskRecordTime,
            mTvActualStartTime,mTvActualFinishTime,mTvComment;
    private String ProjectTask;
    private TrackDB trackDB;
    private CommentDB commentDB;
    private SQLiteDatabase trackReader,commentReader;
    private Cursor track,comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetToolBar();
        Init_DetailDB();
        FindViewById();
        SetOnClickListener();
        fangcan();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    private void SetToolBar(){
        getToolbarTitle().setText("任务详情");
        getSubTitle().setText("");
    }

    private void Init_DetailDB(){
        ProjectTask = getIntent().getStringExtra("project_task_name");
        trackDB = new TrackDB(this,ProjectTask + "track");
        trackReader = trackDB.getReadableDatabase();
        commentDB = new CommentDB(this,ProjectTask + "comment");
        commentReader = commentDB.getReadableDatabase();
    }

    private void FindViewById(){
        mBtnAddTrack = findViewById(R.id.btn_tianjiagenzongxinxi);
        mBtnAddComment = findViewById(R.id.btn_tianjiapizhu);
        mTvActualStartTime = findViewById(R.id.tv_shijikaishishijian);
        mTvActualFinishTime = findViewById(R.id.tv_shijijieshushijian);
        mTvComment = findViewById(R.id.tv_pizhu);
        mTvTaskName = findViewById(R.id.tv_renwumingcheng);
        mTvTaskPersonInCharge = findViewById(R.id.tv_renwuzerenren);
        mTvTaskStartTime = findViewById(R.id.tv_jihuakaishishijian);
        mTvTaskFinishTime = findViewById(R.id.tv_jihuajieshushijian);
        mTvTaskDetail = findViewById(R.id.tv_renwumiaoshu);
        mTvTaskRecordTime = findViewById(R.id.tv_renwulurushijian);
    }

    private void SetOnClickListener(){
        mBtnAddTrack.setOnClickListener(this);
        mBtnAddComment.setOnClickListener(this);
    }

    private void fangcan(){
        Intent intent_ = getIntent();
        Log.d("aaa","intent_.getStringExtra(\"task_name\")");
        mTvTaskName.setText(intent_.getStringExtra("task_name"));
        mTvTaskPersonInCharge.setText(intent_.getStringExtra("task_person_in_charge"));
        mTvTaskStartTime.setText(intent_.getStringExtra("task_start_time"));
        mTvTaskFinishTime.setText(intent_.getStringExtra("task_finish_time"));
        mTvTaskDetail.setText(intent_.getStringExtra("task_detail"));
        mTvTaskRecordTime.setText(intent_.getStringExtra("task_record_time"));
    }

    private void selectDBT(){
        track = trackReader.query(trackDB.TABLE_NAME,null, null,null,
                null,null,null);
        while(track.moveToNext()){
            mTvActualStartTime.setText(track.getString(track.getColumnIndex("actual_start_time")));
            mTvActualFinishTime.setText(track.getString(track.getColumnIndex("actual_finish_time")));
        }
    }

    private void selectDBC(){
        comment = commentReader.query(commentDB.TABLE_NAME,null,null,null,
                null,null,null);
        while(comment.moveToNext()){
            mTvComment.setText(comment.getString(comment.getColumnIndex("comment")));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectDBT();
        selectDBC();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_tianjiagenzongxinxi:
                intent = new Intent(DetailActivity.this, TrackActivity.class);
                intent.putExtra("project_task_name", ProjectTask);
                startActivity(intent);
                break;
            case R.id.btn_tianjiapizhu:
                intent = new Intent(DetailActivity.this, CommentActivity.class);
                intent.putExtra("project_task_name", ProjectTask);
                intent.putExtra("comment", mTvComment.getText().toString());
                startActivity(intent);
                break;
        }
    }

}
