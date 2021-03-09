package com.example.projectmanager.CanNotLogin;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.projectmanager.R;

public class CanNotLoginDialog extends Dialog implements View.OnClickListener {

    private TextView mTvTitle,mTvContent,mTvCancle,mTvConfirm;
    private String title,content,cancel,confirm;
    private MyOnCancelListener CancelListener;
    private MyOnConfirmListener ConfirmListener;


    public CanNotLoginDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cannotlogin_dialog);
        SetWindowSize();
        FindViewById();
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(content)) {
            mTvContent.setText(content);
        }
        if (!TextUtils.isEmpty(cancel)) {
            mTvCancle.setText(cancel);
        }
        if (!TextUtils.isEmpty(confirm)) {
            mTvConfirm.setText(confirm);
        }
        SetOnClickListener();
    }

    //将dialog放在一个指定的位置
    private void SetWindowSize(){
        //设置宽度
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        Point size = new Point();
        d.getSize(size);
        p.width = (int)(size.x * 0.8);//设置dialog的宽度为当前手机屏幕的宽度*0.8
        getWindow().setAttributes(p);
    }

    private void FindViewById(){
        mTvTitle = findViewById(R.id.tv_title);
        mTvContent = findViewById(R.id.tv_content);
        mTvCancle = findViewById(R.id.tv_cancel);
        mTvConfirm = findViewById(R.id.tv_confirm);
    }

    private void SetOnClickListener(){
        mTvCancle.setOnClickListener(CanNotLoginDialog.this);
        mTvConfirm.setOnClickListener(CanNotLoginDialog.this);
    }

    //设置属性
    public CanNotLoginDialog setTitle(String title) {
        this.title = title;
        return CanNotLoginDialog.this;
    }

    public CanNotLoginDialog setContent(String content) {
        this.content = content;
        return CanNotLoginDialog.this;
    }

    public CanNotLoginDialog setCancel(String cancel,MyOnCancelListener listener) {
        this.cancel = cancel;
        this.CancelListener = listener;
        return CanNotLoginDialog.this;
    }

    public CanNotLoginDialog setConfirm(String confirm,MyOnConfirmListener listener) {
        this.confirm = confirm;
        this.ConfirmListener = listener;
        return CanNotLoginDialog.this;
    }

    //事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel:
                if(CancelListener != null){
                    CancelListener.onCancel(CanNotLoginDialog.this);
                }
                dismiss();
                break;
            case R.id.tv_confirm:
                if(ConfirmListener != null){
                    ConfirmListener.onConfirm(CanNotLoginDialog.this);
                }
                dismiss();
                break;
        }
    }

    //点击其他地方不会消失
    public CanNotLoginDialog MySetCancelable(boolean flag){
        setCancelable(flag);
        return CanNotLoginDialog.this;
    }

    //回调接口
    public interface MyOnCancelListener{
        void onCancel(CanNotLoginDialog dialog);
    }

    public interface MyOnConfirmListener{
        void onConfirm(CanNotLoginDialog dialog);
    }

}
