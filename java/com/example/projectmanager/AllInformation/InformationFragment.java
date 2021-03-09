package com.example.projectmanager.AllInformation;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.projectmanager.R;
import com.example.projectmanager.TaskInformation.TaskInformationActivity;

import java.util.ArrayList;

public class InformationFragment extends Fragment {

    private ListView mLvInformation;
    private Context context;
    private ProjectDB projectDB;
    private SQLiteDatabase dbReader;
    private Cursor cursor;
    private LayoutInflater layoutInflater;
    private EditText mEtSearch;
    private ListAdapter listAdapter;
    private ArrayList<MyProject> OrginalList = new ArrayList<MyProject>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information,container,false);
        GetContext();
        Init_ProjectDB();
        FindViewById(view);
        setListeners();
        return view;
    }

    //获取context
    private void GetContext(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context = getContext();
        }
    }

    //初始化项目DB，实例化，获得读权限
    private void Init_ProjectDB(){
        projectDB = new ProjectDB(context);
        dbReader = projectDB.getReadableDatabase();
    }

    private void FindViewById(View view){
        mEtSearch = view.findViewById(R.id.et_search);
        mLvInformation = view.findViewById(R.id.lv_information);
        mLvInformation.setTextFilterEnabled(true);
    }

    //对展示的list有点击事件
    protected void setItemClick(final ArrayList<MyProject> DisplayProject){
        mLvInformation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, TaskInformationActivity.class);
                intent.putExtra("project_name",DisplayProject.get(position).ProjectName);
                startActivity(intent);
            }
        });
    }

    //设置监听，并对搜索加入文字改变监听和相应的事件
    private void setListeners(){
        //设置原数据的点击事件
        setItemClick(OrginalList);

        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(listAdapter != null){
                    //过滤文字
                    listAdapter.getFilter().filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //获取数据
    private void selectDB(){
        OrginalList.clear();
        //读取所有
        cursor = dbReader.query(ProjectDB.TABLE_NAME,null,null,null,
                null,null,null);
        //将每一条插入OrginalList作为原始数据
        while (cursor.moveToNext()){
            MyProject myProject = new MyProject();
            myProject.ProjectName = cursor.getString(cursor.getColumnIndex("project_name"));
            myProject.ProjectStartTime = cursor.getString(cursor.getColumnIndex("project_start_time"));
            myProject.ProjectFinishTime = cursor.getString(cursor.getColumnIndex("project_finish_time"));
            myProject.ProjectPersonNumber = cursor.getString(cursor.getColumnIndex("project_person_number"));
            myProject.ProjectRecordTime = cursor.getString(cursor.getColumnIndex("project_record_time"));
            OrginalList.add(myProject);
        }
        //实例化adapter，并设置点击事件
        listAdapter = new ListAdapter(context, OrginalList, new ListAdapter.FilterListener() {
            @Override
            public void getFilterData(ArrayList<MyProject> DisplayProject) {
                setItemClick(DisplayProject);
            }
        });
        mLvInformation.setAdapter(listAdapter);
    }

    //更新界面
    @Override
    public void onResume() {
        super.onResume();
        //每次进入都会消除搜索栏文字
        if(mEtSearch.getText() != null){
            mEtSearch.setText("");
        }
        selectDB();
    }

    //module，用于过滤
    public static class MyProject{
        String ProjectName,ProjectStartTime,ProjectFinishTime,
                ProjectPersonNumber,ProjectRecordTime;
    }
}
