package com.example.shutdown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_shutdown;

    public static final String ACTION_REQUEST_SHUTDOWN = "android.intent.action.ACTION_REQUEST_SHUTDOWN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init(){
        btn_shutdown = findViewById(R.id.main_btn_shutdown);
        btn_shutdown.setOnClickListener(this);
    }

    private void shutDown(){
        debug("开始任务...");
        Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
        intent.putExtra("android.intent.extra.KEY_CONFIRM", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        debug("准备完成，开始关机");
        startActivity(intent);
    }

    public void shutdown(){
        try{
        //Process proc =Runtime.getRuntime().exec(new String[]{"su","-c","shutdown"}); //关机
        Process proc =Runtime.getRuntime().exec(new String[]{"su","-c","reboot -p"}); //关机
        proc.waitFor();
        }catch(Exception e){
            debug("发生了错误");
            Toast.makeText(this,"发生了错误",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void debug(String msg){
        System.out.println("Logout:"+msg);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.main_btn_shutdown:
                Toast.makeText(this,"关机",Toast.LENGTH_SHORT).show();
                debug("选择关机");
                shutdown();
                break;
        }
    }
}
