package com.example.projectmanager.TaskInformation;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.projectmanager.Detail.DetailActivity;
import com.example.projectmanager.R;

import java.util.ArrayList;

public class TaskInformationFragment extends Fragment {

    private ListView mLvTaskInformation;
    private Context context;
    private TaskDB taskDB;
    private SQLiteDatabase dbReader;
    private Cursor cursor;
    private Activity activity;
    private EditText mEtSearch;
    private TaskListAdapter taskListAdapter;
    private ArrayList<MyTask> OrginalTask = new ArrayList<MyTask>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_information,container,false);
        GetContext();
        Init_TaskDB();
        FindViewById(view);
        setListeners();
        return view;
    }

    private void GetContext(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context = getContext();
            activity = getActivity();
        }
    }

    private void Init_TaskDB(){
        taskDB = new TaskDB(context,activity.getIntent().getStringExtra("project_name"));
        dbReader = taskDB.getReadableDatabase();
    }

    private void FindViewById(View view){
        mLvTaskInformation = view.findViewById(R.id.lv_task_information);
        mEtSearch = view.findViewById(R.id.et_search);
    }

    private void selectDB(){
        OrginalTask.clear();
        cursor = dbReader.query(taskDB.TABLE_NAME,
                null,null,null,
                null,null,null);
        while (cursor.moveToNext()){
            MyTask myTask = new MyTask();
            myTask.TaskName = cursor.getString(cursor.getColumnIndex("task_name"));
            myTask.TaskPersonInCharge = cursor.getString(cursor.getColumnIndex("task_person_in_charge"));
            myTask.TaskStartTime = cursor.getString(cursor.getColumnIndex("task_start_time"));
            myTask.TaskFinishTime = cursor.getString(cursor.getColumnIndex("task_finish_time"));
            myTask.TaskDetail = cursor.getString(cursor.getColumnIndex("task_detail"));
            myTask.TaskRecordTime = cursor.getString(cursor.getColumnIndex("task_record_time"));
            OrginalTask.add(myTask);
        }
        taskListAdapter = new TaskListAdapter(context, OrginalTask, new TaskListAdapter.FilterListener() {
            @Override
            public void getFilterData(ArrayList<MyTask> DisplayTask) {
                setItemClick(DisplayTask);
            }
        });
        mLvTaskInformation.setAdapter(taskListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mEtSearch.getText() != null){
            mEtSearch.setText("");
        }
        selectDB();
    }

    protected void setItemClick(final ArrayList<MyTask> DisplayTask){
        mLvTaskInformation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,"position",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailActivity.class);
                String ProjectTask;
                ProjectTask = activity.getIntent().getStringExtra("project_name") +
                        DisplayTask.get(position).TaskName;
                intent.putExtra("project_task_name",ProjectTask);
                chuancan(view,intent);
                startActivity(intent);
            }
        });
    }

    private void setListeners(){
        setItemClick(OrginalTask);

        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(taskListAdapter != null){
                    taskListAdapter.getFilter().filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    //传递参数
    private void chuancan(View view,Intent intent){
        TextView task_name,task_person_in_charge,task_start_time,task_finish_time,task_detail,task_record_time;
        task_name = view.findViewById(R.id.tv_renwumingcheng);
        task_person_in_charge = view.findViewById(R.id.tv_renwuzerenren);
        task_start_time = view.findViewById(R.id.tv_jihuakaishishijian);
        task_finish_time = view.findViewById(R.id.tv_jihuajieshushijian);
        task_detail = view.findViewById(R.id.tv_renwumiaoshu);
        task_record_time = view.findViewById(R.id.tv_renwulurushijian);
        Bundle bundle = new Bundle();
        intent.putExtra("task_name",task_name.getText().toString());
        intent.putExtra("task_person_in_charge",task_person_in_charge.getText().toString());
        intent.putExtra("task_start_time",task_start_time.getText().toString());
        intent.putExtra("task_finish_time",task_finish_time.getText().toString());
        intent.putExtra("task_detail",task_detail.getText().toString());
        intent.putExtra("task_record_time",task_record_time.getText().toString());
    }

    public static class MyTask{
        String TaskName,TaskPersonInCharge,TaskStartTime,TaskFinishTime,
                TaskDetail,TaskRecordTime;
    }

}
