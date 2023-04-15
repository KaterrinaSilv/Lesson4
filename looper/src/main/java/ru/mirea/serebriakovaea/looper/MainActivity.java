package ru.mirea.serebriakovaea.looper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;


import ru.mirea.serebriakovaea.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        Handler mainHandlerThread = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.d(MainActivity.class.getSimpleName(), "Task execute. This is result " + msg.getData().getString("result"));
            }
        };
        MyLooper myLooper = new MyLooper(mainHandlerThread);
        myLooper.start();


        activityMainBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("KEY", "19");
                msg.setData(bundle);
                myLooper.mHandler.sendMessage(msg);
            }
        });
    }

}