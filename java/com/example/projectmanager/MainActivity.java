package com.example.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.projectmanager.Login.LoginActivity;

public class MainActivity extends Activity {
    private ImageView mIvStrartImageAnother;
    private Button mBtnJump;
    private Runnable runnable;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindViewById();
        handler();
        SetOnClickListener();
        Jump();
        FadeOut();

    }

    //找到对象
    private void FindViewById(){
        mIvStrartImageAnother = findViewById(R.id.iv_start_image_another);
        mBtnJump = findViewById(R.id.btn_jump);
    }

    //利用hander延迟做跳转
    private void handler(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };
        handler.postDelayed(runnable,5000);
    }

    //设置监听
    private void SetOnClickListener(){
        mBtnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                handler.removeCallbacks(runnable);
                finish();
            }
        });
    }

    //跳过，倒计时部分
    private void Jump(){
        CountDownTimer countDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String value = "跳过 (" + String.valueOf((int) (millisUntilFinished / 1000)) + ")";
                mBtnJump.setText(value);
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();
    }

    //渐出效果
    private void FadeOut(){
        mIvStrartImageAnother.setAlpha(0f);
        mIvStrartImageAnother.animate().alpha(1f).setDuration(4000).start();
    }

}
