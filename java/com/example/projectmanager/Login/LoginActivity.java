package com.example.projectmanager.Login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectmanager.AllInformation.AllInformationActivity;
import com.example.projectmanager.BaseAppCompatActivity;
import com.example.projectmanager.CanNotLogin.CanNotLoginDialog;
import com.example.projectmanager.R;
import com.example.projectmanager.Register.RegisterActivity;

public class LoginActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private Button mBtnRegister,mBtnLogin,mBtnCanNotLogin;
    private EditText mEtUser,mEtPassword,mEtPhone;
    private SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetToolBar();
        FindViewById();
        SetOnClickListener();
    }

    //获取layout
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    //设置回退键是否出现
    @Override
    protected boolean isShowBacking() {
        return false;
    }

    //设置toolbar文字
    private void SetToolBar(){
        getToolbarTitle().setText("登录");
        getSubTitle().setText("");
    }

    //寻找实例
    private void FindViewById(){
        mBtnRegister = findViewById(R.id.btn_xinyonghu);
        mBtnLogin = findViewById(R.id.btn_denglu);
        mBtnCanNotLogin = findViewById(R.id.btn_wufadenglu);
        mEtUser = findViewById(R.id.et_user);
        mEtPassword = findViewById(R.id.et_password);
        mEtPhone = findViewById(R.id.et_phone);
    }

    //设置监听
    private void SetOnClickListener(){
        mBtnRegister.setOnClickListener(LoginActivity.this);
        mBtnCanNotLogin.setOnClickListener(LoginActivity.this);
        mBtnLogin.setOnClickListener(LoginActivity.this);
        mEtUser.setOnClickListener(LoginActivity.this);
        mEtPassword.setOnClickListener(LoginActivity.this);
        mEtPhone.setOnClickListener(LoginActivity.this);
    }

    //检查登录信息
    private boolean checkInformation(){
        mSharedPreferences = getSharedPreferences("User_Login_Information",MODE_PRIVATE);
        String str = mEtPassword.getText().toString() + mEtPhone.getText().toString();
        if(mSharedPreferences.getString(mEtUser.getText().toString(),"").equals(str) && !TextUtils.isEmpty(str)){
            return true;
        }
        return false;
    }

    //自定义dialog
    private void MyDialog(String title,String content,String cancel,String confirm){
        CanNotLoginDialog canNotLoginDialog = new CanNotLoginDialog(LoginActivity.this);
        canNotLoginDialog.setTitle(title).setContent(content)
                .setCancel(cancel, new CanNotLoginDialog.MyOnCancelListener() {
                    @Override
                    public void onCancel(CanNotLoginDialog dialog) {
                        Toast.makeText(LoginActivity.this,"取消",Toast.LENGTH_SHORT).show();
                    }
                }).setConfirm(confirm, new CanNotLoginDialog.MyOnConfirmListener() {
            @Override
            public void onConfirm(CanNotLoginDialog dialog) {
                Toast.makeText(LoginActivity.this,"确认",Toast.LENGTH_SHORT).show();
            }
        }).MySetCancelable(false).show();
    }

    //点击事件
    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn_denglu:
                if(checkInformation()){
                    intent = new Intent(LoginActivity.this, AllInformationActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    MyDialog("","昵称或密码不对，请重新输入","","");
                }
                break;
            case R.id.btn_wufadenglu:
                MyDialog("","","","");
                break;
            case R.id.btn_xinyonghu:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent,0);
                break;
        }
    }

    //setResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            finish();
        }
    }
}
