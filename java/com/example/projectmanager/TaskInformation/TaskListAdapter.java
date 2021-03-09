package com.example.projectmanager.TaskInformation;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.projectmanager.AllInformation.InformationFragment;
import com.example.projectmanager.R;

import java.util.ArrayList;

public class TaskListAdapter extends BaseAdapter implements Filterable {
    private Context mConText;
    private LayoutInflater mLayoutInflater;
    private Cursor cursor;
    private ArrayList<TaskInformationFragment.MyTask> OrginalTask = new ArrayList<TaskInformationFragment.MyTask>();
    private ArrayList<TaskInformationFragment.MyTask> DisplayTask = new ArrayList<TaskInformationFragment.MyTask>();
    private FilterListener listener;

    TaskListAdapter(Context context, ArrayList<TaskInformationFragment.MyTask> OrginalTask,FilterListener listener){
        mConText = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.OrginalTask = OrginalTask;
        this.DisplayTask = OrginalTask;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return DisplayTask.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        private TextView mTvRenWuMingCheng;
        private TextView mTvRenWuZeRenRen;
        private TextView mTvRenWuJiHuaKaiShiShiJian;
        private TextView mTvRenWuJiHuaJieShuShiJian;
        private TextView mTvRenWuMiaoShu;
        private TextView mTvLuRuShiJian;

        public void FindViewById(View convertView){
            mTvRenWuMingCheng = convertView.findViewById(R.id.tv_renwumingcheng);
            mTvRenWuZeRenRen = convertView.findViewById(R.id.tv_renwuzerenren);
            mTvRenWuJiHuaKaiShiShiJian = convertView.findViewById(R.id.tv_jihuakaishishijian);
            mTvRenWuJiHuaJieShuShiJian = convertView.findViewById(R.id.tv_jihuajieshushijian);
            mTvRenWuMiaoShu = convertView.findViewById(R.id.tv_renwumiaoshu);
            mTvLuRuShiJian = convertView.findViewById(R.id.tv_renwulurushijian);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.layout_task_list_adapter,null);
            holder = new ViewHolder();
            holder.FindViewById(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //赋值
        String TaskName,TaskPersonInCharge,TaskStartTime,TaskFinishTime,TaskDetail,TaskRecordTime;
        TaskName = DisplayTask.get(position).TaskName;
        TaskPersonInCharge = DisplayTask.get(position).TaskPersonInCharge;
        TaskStartTime = DisplayTask.get(position).TaskStartTime;
        TaskFinishTime = DisplayTask.get(position).TaskFinishTime;
        TaskDetail = DisplayTask.get(position).TaskDetail;
        TaskRecordTime = DisplayTask.get(position).TaskRecordTime;
        holder.mTvRenWuMingCheng.setText(TaskName);
        holder.mTvRenWuZeRenRen.setText(TaskPersonInCharge);
        holder.mTvRenWuJiHuaKaiShiShiJian.setText(TaskStartTime);
        holder.mTvRenWuJiHuaJieShuShiJian.setText(TaskFinishTime);
        holder.mTvRenWuMiaoShu.setText(TaskDetail);
        holder.mTvLuRuShiJian.setText(TaskRecordTime);
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<TaskInformationFragment.MyTask> FilteredArrList = new ArrayList<TaskInformationFragment.MyTask>();
                if(OrginalTask == null){
                    OrginalTask = new ArrayList<TaskInformationFragment.MyTask>(DisplayTask);
                }

                if(constraint == null || constraint.length() == 0){
                    results.count = OrginalTask.size();
                    results.values = OrginalTask;
                }else{
                    constraint = constraint.toString().toLowerCase();
                    for(int i = 0;i < OrginalTask.size();i++){
                        String data = OrginalTask.get(i).TaskPersonInCharge + OrginalTask.get(i).TaskRecordTime;
                        if(data.toLowerCase().contains(constraint.toString())){
                            TaskInformationFragment.MyTask myTask = new TaskInformationFragment.MyTask();
                            myTask.TaskName = OrginalTask.get(i).TaskName;
                            myTask.TaskPersonInCharge = OrginalTask.get(i).TaskPersonInCharge;
                            myTask.TaskStartTime = OrginalTask.get(i).TaskStartTime;
                            myTask.TaskFinishTime = OrginalTask.get(i).TaskFinishTime;
                            myTask.TaskDetail = OrginalTask.get(i).TaskDetail;
                            myTask.TaskRecordTime = OrginalTask.get(i).TaskRecordTime;
                            FilteredArrList.add(myTask);
                        }
                    }
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                DisplayTask = (ArrayList<TaskInformationFragment.MyTask>)results.values;

                if(listener != null){
                    listener.getFilterData(DisplayTask);
                }

                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public interface FilterListener{
        void getFilterData(ArrayList<TaskInformationFragment.MyTask> DisplayTask);
    }
}
