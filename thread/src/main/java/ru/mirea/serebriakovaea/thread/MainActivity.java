package ru.mirea.serebriakovaea.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

import ru.mirea.serebriakovaea.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        Thread mainTread = Thread.currentThread();
        activityMainBinding.textView.setText("Имя текущегоо потока " + mainTread.getName());
        mainTread.setName("МОЙ НОМЕР ГРУППЫ: БСБО-06-21, МОЙ НОМЕР ПО СПИСКУ: 22, МОЙ ЛЮБИМЫЙ ФИЛЬМ: НАЧАЛО");
        activityMainBinding.textView.append("\n\n Новое имя потока: " + mainTread.getName());
        Log.d(MainActivity.class.getSimpleName(), "Stack" + Arrays.toString(mainTread.getStackTrace()));

        activityMainBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {

                    public void run() {
                        int numberThread = counter++;
                        Log.d("ThreadProject", String.format("Запущен поток №%d студентом группы №%s номер по списку №%d", numberThread, "БСБО-06-21", 22));
                        long endTime = System.currentTimeMillis() + 20 * 1000;
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject", "Выполнен поток №" + numberThread);
                        }
                    }
                }).start();
            }
        });
    }
}