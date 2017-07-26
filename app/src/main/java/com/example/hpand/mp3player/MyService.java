package com.example.hpand.mp3player;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.hpand.mp3player.*;

import static android.R.attr.duration;

import static com.example.hpand.mp3player.MainActivity.*;
import static com.example.hpand.mp3player.MainActivity.mp;

public class MyService extends Service {

    public int onStartCommand(Intent intent, int flags, int startId) {
        String a = intent.getAction();
        if (a.equals("prev")) {
            if (i != 0) {
                j = i - 1;
            }
            j--;
            if (j == -1) {
                j = 2;
            }
            if (mp != null) {
                i = 0;
                switch (j) {
                    case 0:
                        mp.stop();
                        mp = null;
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a);
                        Toast.makeText(this, "ed sheeron", Toast.LENGTH_SHORT).show();
                        mp.start();
                        //duration = mp.getDuration();
                        sec = (duration / 100) % 60;
                        min = (duration / 1000) / 60;
                        s1 = min + ":" + sec;

                        sb.setMax(duration);
                        break;
                    case 1:
                        mp.stop();
                        mp = null;
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.b);
                        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
                        mp.start();
                        //duration = mp.getDuration();
                        sec = (duration / 100) % 60;
                        min = (duration / 1000) / 60;
                        s1= min + ":" + sec;

                        sb.setMax(duration);
                        break;
                    case 2:
                        mp.stop();
                        mp = null;
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.c);
                        Toast.makeText(this, "one republic", Toast.LENGTH_SHORT).show();
                        mp.start();
                        //duration = mp.getDuration();
                        sec = (duration / 100) % 60;
                        min = (duration / 1000) / 60;
                        s1 = min + ":" + sec;

                        sb.setMax(duration);
                        break;

                }
            }
        } else if (a.equals("pause")) {
            if (dual == 0) {
                dual = 1;
                if (mp != null && mp.isPlaying()) {
                    mp.pause();
                }
            } else if (dual == 1) {
                dual = 0;
                mp.start();
            }
        } else if (a.equals("next")) {
            if (i != 0) {
                j = i - 1;
            }
            j++;
            if (j == 3) {
                j = 0;
            }
            if (mp != null) {

                i = 0;
                switch (j) {

                    case 0:
                        mp.stop();
                        mp = null;
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a);
                        Toast.makeText(this, "BAHUBALI", Toast.LENGTH_SHORT).show();
                        mp.start();
                        //duration = mp.getDuration();
                        sec = (duration / 100) % 60;
                        min = (duration / 1000) / 60;
                        s1 = min + ":" + sec;
                        //end.setText(s);
                        sb.setMax(duration);
                        break;


                    case 1:
                        mp.stop();
                        mp = null;
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.b);
                        Toast.makeText(this, "IK VAARI", Toast.LENGTH_SHORT).show();
                        mp.start();
                        //duration = mp.getDuration();
                        sec = (duration / 100) % 60;
                        min = (duration / 1000) / 60;
                        s1 = min + ":" + sec;
                        //end.setText(s);
                        sb.setMax(duration);
                        break;


                    case 2:
                        mp.stop();
                        mp = null;
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.c);
                        Toast.makeText(this, "SUIT SUIT", Toast.LENGTH_SHORT).show();
                        mp.start();
                        //duration = mp.getDuration();
                        sec = (duration / 100) % 60;
                        min = (duration / 1000) / 60;
                        s1 = min + ":" + sec;
                        //end.setText(s);
                        sb.setMax(duration);
                        break;
                }
            }
        }
        return super.onStartCommand(intent,flags,startId);

    }

    @Nullable
    @Override

    public IBinder onBind(Intent intent) {
        return null;
    }
}
