package com.example.projectmanager.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectmanager.BaseAppCompatActivity;
import com.example.projectmanager.CanNotLogin.CanNotLoginDialog;
import com.example.projectmanager.Login.LoginActivity;
import com.example.projectmanager.R;

public class RegisterActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private Button mBtnRegister;
    private EditText mEtUser,mEtPassword,mEtPhone;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetToolBar();
        FindViewById();
        SetOnClickListener();
        Init_mSharedPreferences();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    private void SetToolBar(){
        getToolbarTitle().setText("注册");
        getSubTitle().setText("");
    }

    private void FindViewById(){
        mBtnRegister = findViewById(R.id.btn_zhuce);
        mEtUser = findViewById(R.id.et_user);
        mEtPassword = findViewById(R.id.et_password);
        mEtPhone = findViewById(R.id.et_phone);
    }

    private void SetOnClickListener(){
        mBtnRegister.setOnClickListener(RegisterActivity.this);
        mEtUser.setOnClickListener(RegisterActivity.this);
        mEtPassword.setOnClickListener(RegisterActivity.this);
        mEtPhone.setOnClickListener(RegisterActivity.this);
    }

    //初始化sharepreference
    private void Init_mSharedPreferences(){
        if(mSharedPreferences == null){
            mSharedPreferences = getSharedPreferences("User_Login_Information",MODE_PRIVATE);
            mEditor = mSharedPreferences.edit();
        }
    }

    //保存信息
    private void SaveLoginInformation(){
        mEditor.putString(mEtUser.getText().toString(),mEtPassword.getText().toString() + mEtPhone.getText().toString());
        mEditor.apply();
    }

    private void MyDialog(String title,String content,String cancel,String confirm){
        CanNotLoginDialog canNotLoginDialog = new CanNotLoginDialog(RegisterActivity.this);
        canNotLoginDialog.setTitle(title).setContent(content)
                .setCancel(cancel, new CanNotLoginDialog.MyOnCancelListener() {
                    @Override
                    public void onCancel(CanNotLoginDialog dialog) {
                        Toast.makeText(RegisterActivity.this,"取消",Toast.LENGTH_SHORT).show();
                    }
                }).setConfirm(confirm, new CanNotLoginDialog.MyOnConfirmListener() {
            @Override
            public void onConfirm(CanNotLoginDialog dialog) {
                Toast.makeText(RegisterActivity.this,"确认",Toast.LENGTH_SHORT).show();
            }
        }).MySetCancelable(false).show();
    }

    private boolean checkInformation(){
        if(!TextUtils.isEmpty(mSharedPreferences.getString(mEtUser.getText().toString(),""))){
            MyDialog("","昵称重复，请重新输入","","");
            return false;
        }
        String str = mEtPassword.getText().toString() + mEtPhone.getText().toString();
        if(TextUtils.isEmpty(str)){
            MyDialog("","密码和手机号都不允许为空哦！","","");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn_zhuce:
                if(checkInformation()){
                    SaveLoginInformation();
                    setResult(Activity.RESULT_OK);
                    intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}
