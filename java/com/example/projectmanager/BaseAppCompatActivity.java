package com.example.projectmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public abstract class BaseAppCompatActivity extends AppCompatActivity {
    private static final String TAG = BaseAppCompatActivity.class.getSimpleName();
    private TextView mToolbarTitle;
    private TextView mToolbarSubTitle;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        changeStatusBarTextColor(true);
        FindViewById();
        Init_ToolBar();
    }

    //设置系统状态栏颜色
    private void changeStatusBarTextColor(boolean isBlack) {
        if (isBlack) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏黑色字体
        }else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//恢复状态栏白色字体
        }
    }

    private void FindViewById(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarSubTitle = (TextView) findViewById(R.id.toolbar_subtitle);
    }

    private void Init_ToolBar(){
        if (mToolbar != null) {
            //将Toolbar显示到界面
            setSupportActionBar(mToolbar);
        }
        if (mToolbarTitle != null) {
            //getTitle()的值是activity的android:lable属性值
            mToolbarTitle.setText(getTitle());
            //设置默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //判断是否有Toolbar,并默认显示返回按钮
        if(null != getToolbar() && isShowBacking()){
            showBack();
        }
    }

    //获取标题
    public TextView getToolbarTitle(){
        return mToolbarTitle;
    }

    //获取副标题
    public TextView getSubTitle(){
        return mToolbarSubTitle;
    }

    public Toolbar getToolbar() {
        return findViewById(R.id.toolbar);
    }

    private void showBack(){
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        getToolbar().setNavigationIcon(R.drawable.back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    //是否显示回退按钮
    protected boolean isShowBacking(){
        return true;
    }

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy...");


    }
}

