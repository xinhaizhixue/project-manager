package com.example.projectmanager.AllInformation;

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

public class ListAdapter extends BaseAdapter implements Filterable {
    private Context mConText;
    private LayoutInflater mLayoutInflater;
    private ArrayList<InformationFragment.MyProject> OrginalProject = new ArrayList<InformationFragment.MyProject>();
    private ArrayList<InformationFragment.MyProject> DisplayProject = new ArrayList<InformationFragment.MyProject>();
    private FilterListener listener;

    //构造方法，初始化数据，和实现接口函数
    ListAdapter(Context context, ArrayList<InformationFragment.MyProject> OrginalList,FilterListener listener){
        mConText = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.OrginalProject = OrginalList;
        this.DisplayProject = OrginalList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return DisplayProject.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //创建viewholder类，便于复用
    static class ViewHolder{
        private TextView mTvXiangMuMingCheng;
        private TextView mTvXiangMuJiHuaKaiShiShiJian;
        private TextView mTvXiangMuJiHuaJieShuShiJian;
        private TextView mTvXiangMuRenShu;
        private TextView mTvLuRuShiJian;

        public void FindViewById(View convertView){
            mTvXiangMuMingCheng = convertView.findViewById(R.id.tv_xiangmumingcheng);
            mTvXiangMuJiHuaKaiShiShiJian = convertView.findViewById(R.id.tv_jihuakaishishijian);
            mTvXiangMuJiHuaJieShuShiJian = convertView.findViewById(R.id.tv_jihuajieshushijian);
            mTvXiangMuRenShu = convertView.findViewById(R.id.tv_xiangmurenshu);
            mTvLuRuShiJian = convertView.findViewById(R.id.tv_xiangmulurushijian);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.layout_list_adapter,null);
            holder = new ViewHolder();
            //实例化
            holder.FindViewById(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //赋值
        String ProjectName,ProjectStartTime,ProjectFinishTime,ProjectPersonNumber,ProjectRecordTime;
        ProjectName = DisplayProject.get(position).ProjectName;
        ProjectStartTime = DisplayProject.get(position).ProjectStartTime;
        ProjectFinishTime = DisplayProject.get(position).ProjectFinishTime;
        ProjectPersonNumber = DisplayProject.get(position).ProjectPersonNumber;
        ProjectRecordTime = DisplayProject.get(position).ProjectRecordTime;
        holder.mTvXiangMuMingCheng.setText(ProjectName);
        holder.mTvXiangMuJiHuaKaiShiShiJian.setText(ProjectStartTime);
        holder.mTvXiangMuJiHuaJieShuShiJian.setText(ProjectFinishTime);
        holder.mTvXiangMuRenShu.setText(ProjectPersonNumber);
        holder.mTvLuRuShiJian.setText(ProjectRecordTime);

        return convertView;
    }

    //过滤
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<InformationFragment.MyProject> FilteredArrList = new ArrayList<InformationFragment.MyProject>();
                if(OrginalProject == null){
                    OrginalProject = new ArrayList<InformationFragment.MyProject>(DisplayProject);
                }

                if(constraint == null || constraint.length() == 0){
                    results.count = OrginalProject.size();
                    results.values = OrginalProject;
                }else{
                    constraint = constraint.toString().toLowerCase();
                    for(int i = 0;i < OrginalProject.size();i++){
                        //对名称和时间字符串进行对比
                        String data = OrginalProject.get(i).ProjectName + OrginalProject.get(i).ProjectRecordTime;
                        if(data.toLowerCase().contains(constraint.toString())){
                            InformationFragment.MyProject myProject = new InformationFragment.MyProject();
                            myProject.ProjectName = OrginalProject.get(i).ProjectName;
                            myProject.ProjectStartTime = OrginalProject.get(i).ProjectStartTime;
                            myProject.ProjectFinishTime = OrginalProject.get(i).ProjectFinishTime;
                            myProject.ProjectPersonNumber = OrginalProject.get(i).ProjectPersonNumber;
                            myProject.ProjectRecordTime = OrginalProject.get(i).ProjectRecordTime;
                            FilteredArrList.add(myProject);
                        }
                    }
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                DisplayProject = (ArrayList<InformationFragment.MyProject>)results.values;
                //这里对过滤后的数据设置点击事件
                if(listener != null){
                    listener.getFilterData(DisplayProject);
                }
                //刷新数据显示
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public interface FilterListener{
        void getFilterData(ArrayList<InformationFragment.MyProject> DisplayProject);
    }

}
