package com.entagesoft.a3fragments;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogService extends Service {

    Intent intent;
    ExecutorService executorService;
    public LogService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("MY_TAG", "Servise onCreate");
        executorService = Executors.newFixedThreadPool(1);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String string = intent.getStringExtra("string");
        boolean turnOff = false;
        runLogging run = new runLogging(string, turnOff, startId);
        //run.run();
        executorService.execute(run);

        //Log.d("MY_TAG", "Servise onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MY_TAG", "Servise onDestroy");
    }

    public class runLogging implements Runnable{

        String string;
        boolean turnOff;
        int startId;

        public runLogging(String string, boolean turnOff, int startId){

            this.string = string;
            this.turnOff = turnOff;
            this.startId = startId;
        }

        @Override
        public void run() {

            Log.d("MY_TAG", string);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("MY_TAG", "Servise onBind");
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
