package ru.mirea.serebriakovaea.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;



public class MyLooper extends Thread {
    public Handler mHandler;
    private Handler mainHandler;

    public MyLooper(Handler mainThreadHandler) {
        mainHandler = mainThreadHandler;
    }

    public void run() {
        Log.d("My Looper", "run");
        Looper.prepare();
        mHandler = new Handler(Looper.myLooper()) {

            public void handleMessage(Message msg) {
                String data = msg.getData().getString("KEY");
                Log.d("MyLooperMassage:", data);
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("result",String.format("Мой возраст: %s, Я работаю: репетитором", data));
                mainHandler.sendMessage(message);
            }
        };
        Looper.loop();
    }

}
