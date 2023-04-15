package ru.mirea.serebriakovaea.serviceapp;

import static android.Manifest.permission.FOREGROUND_SERVICE;
import static android.Manifest.permission.POST_NOTIFICATIONS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.security.Permission;

import ru.mirea.serebriakovaea.serviceapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding mainBinding;
    private int permission_code = 200;
    public final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);

        if(ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "Разрешение полученно");
        }else{
            Log.d(TAG, "Разрешения нет");
            ActivityCompat.requestPermissions(this, new String[] {POST_NOTIFICATIONS, FOREGROUND_SERVICE}, permission_code);
        }

        mainBinding.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(MainActivity.this, PlayerService.class);
                ContextCompat.startForegroundService(MainActivity.this, serviceIntent);
            }
        });

        mainBinding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, PlayerService.class));
            }
        });
    }
}