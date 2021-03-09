package com.example.projectmanager.AllInformation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.projectmanager.BaseAppCompatActivity;
import com.example.projectmanager.R;

public class AllInformationActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private FrameLayout mFl;
    private InformationFragment informationFragment;
    private Button mBtnAddPriject;

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
        return R.layout.activity_all_information;
    }

    private void SetToolBar(){
        getToolbarTitle().setText("项目列表");
        getSubTitle().setText("");
    }

    private void FindViewById(){
        mFl = findViewById(R.id.fl_information);
        mBtnAddPriject = findViewById(R.id.btn_tianjiaxiangmu);
    }

    private void SetClickListener(){
        mFl.setOnClickListener(this);
        mBtnAddPriject.setOnClickListener(this);
    }

    //实例化fragment，并add
    private void AddFragment(){
        informationFragment = new InformationFragment();
        getFragmentManager().beginTransaction().add(R.id.fl_information,informationFragment).commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn_tianjiaxiangmu:
                intent = new Intent(AllInformationActivity.this,AddProjectActivity.class);
                startActivity(intent);
                break;
        }
    }

}
