package com.example.hpand.mp3player;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
   static MediaPlayer mp;
    AlertDialog.Builder adb;
    static SeekBar sb,sb1;
    static int dual;
    static int i = 0, j = 0;
    TextView start,end;
    static int duration,min,sec;
    static String s1;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();;
    AudioManager am;
    ArrayAdapter<ListView> adapter;
    ListView lv;
    static String s[]=new String[]{
            "JAI JAIKARA","IK VARI","SUIT SUIT"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView) findViewById(R.id.listView);
        sb1=(SeekBar)findViewById(R.id.seekBar);
        start=(TextView)findViewById(R.id.progress);
        end=(TextView)findViewById(R.id.fullsize);
        //volume control seek bar
        sb=(SeekBar)findViewById(R.id.seekBar2) ;
        am=(AudioManager)getSystemService(AUDIO_SERVICE);
        int max=am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int current=am.getStreamVolume(AudioManager.STREAM_MUSIC);
        sb.setProgress(current);
        sb.setMax(max);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                am.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //volume control over
        adapter=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,s);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:{
                        if (mp==null)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.a);
                            mp.start();

                        }
                        else
                        {
                            mp.stop();
                            mp=null;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.a);
                            mp.start();

                        }
                        break;

                    }
                    case 1:{
                        if (mp==null)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.b);
                            mp.start();
                        }
                        else
                        {
                            mp.stop();
                            mp=null;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.b);
                            mp.start();
                        }
                        break;

                    }
                    case 2:
                    {
                        if (mp==null)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.c);
                            mp.start();

                        }
                        else
                        {
                            mp.stop();
                            mp=null;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.c);
                            mp.start();
                        }
                        break;
                    }
                }
                duration=mp.getDuration();
                sec=(duration/1000)%60;
                min=(duration/1000)/60;
                s1=min+":"+sec;
                end.setText(s1);
                sb1.setMax(duration);
                startTime = mp.getCurrentPosition();
                start.setText(String.format("%d:%d",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                        toMinutes((long) startTime)))
                );
                //sb.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime, 100);
            }
        });
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    if (mp != null) {
                        mp.seekTo(progress);

                        // start.setText(mp.getCurrentPosition());
                    }
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        final Thread th = new Thread() {
            public void run() {
                while (true) {
                    if (mp != null) {
                        sb1.setProgress(mp.getCurrentPosition());
                    } else {
                        sb1.setProgress(0);
                    }
                }
            }
        };
        th.start();
    }
    public void playMusic(View v){
        if(mp==null)
        {
            mp=MediaPlayer.create(this,R.raw.a);
            mp.start();
            sb1.setProgress(mp.getCurrentPosition());
        }
        else {
            mp.start();
            sb1.setProgress(mp.getCurrentPosition());
        }
        duration=mp.getDuration();
        sec=(duration/1000)%60;
        min=(duration/1000)/60;
        s1=min+":"+sec;
        end.setText(s1);
        sb1.setMax(duration);
        startTime = mp.getCurrentPosition();
        start.setText(String.format("%d:%d",
                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                toMinutes((long) startTime)))
        );

        myHandler.postDelayed(UpdateSongTime, 100);

        /*NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder nb = new Notification.Builder(this);
        nb.setTicker("My mp3 player");
        nb.setContentTitle("MP3 NOTIFICATION");
        Intent i0 = new Intent(this, MyService.class);
        PendingIntent pi = PendingIntent.getActivity(this, 1, i0, 0);
        nb.setContentIntent(pi);


        Intent i1 = new Intent(this, MyService.class);
        i1.setAction("prev");
        i1.putExtra("k", j);
        i1.putExtra("kl", s);
        PendingIntent pi1 = PendingIntent.getService(this, (int) System.currentTimeMillis(), i1, 0);
        nb.addAction(android.R.drawable.ic_media_previous, "", pi1);



        Intent i2 = new Intent(this, MyService.class);
        i2.setAction("pause");
        i2.putExtra("k", j);
        i2.putExtra("kl", s);
        PendingIntent pi2 = PendingIntent.getService(this, (int) System.currentTimeMillis(), i2, 0);
        nb.addAction(android.R.drawable.ic_media_pause, "", pi2);


        Intent i3 = new Intent(this, MyService.class);
        getIntent().putExtra("l", i);
        i3.setAction("next");
        i3.putExtra("k", j);
        i3.putExtra("kl", s);
        PendingIntent pi3 = PendingIntent.getService(this, (int) System.currentTimeMillis(), i3, 0);
        nb.addAction(android.R.drawable.ic_media_next, "", pi3);


        Notification n = nb.build();
        //nm.notify((int) System.currentTimeMillis(), n);
        nm.notify(1, n);
        nb.setAutoCancel(true);


        //sm.unregisterListener(this)*/

    }
    /*protected void onResume() {
       super.onResume();
        duration = mp.getDuration();
        sec = (duration / 100) % 60;
        min = (duration / 1000) / 60;
        s1 = min + ":" + sec;
        //end.setText(s);
        sb.setProgress(mp.getCurrentPosition());
        sb.setMax(duration);
    }*/
    public void pauseMusic(View v)
    {
        if(mp!=null && mp.isPlaying())
        {
            mp.pause();
        }
    }
    public void stopMusic(View v)
    {
        if(mp!=null)
        {
            mp.stop();
            mp=null;
            end.setText("0:0");
            start.setText("0:0");
        }
    }
    public void next(View v){
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
                    mp = MediaPlayer.create(MainActivity.this,R.raw.a);
                    Toast.makeText(this, "BAHUBALI", Toast.LENGTH_SHORT).show();
                    mp.start();
                    duration=mp.getDuration();
                    sec=(duration/1000)%60;
                    min=(duration/1000)/60;
                    s1=min+":"+sec;
                    end.setText(s1);
                    break;


                case 1:
                    mp.stop();
                    mp = null;
                    mp = MediaPlayer.create(MainActivity.this, R.raw.b);
                    Toast.makeText(this, "RAABTA", Toast.LENGTH_SHORT).show();
                    mp.start();
                    duration=mp.getDuration();
                    sec=(duration/1000)%60;
                    min=(duration/1000)/60;
                    s1=min+":"+sec;
                    end.setText(s1);
                    break;


                case 2:
                    mp.stop();
                    mp = null;
                    mp = MediaPlayer.create(MainActivity.this, R.raw.c);
                    Toast.makeText(this, "SUIT SUIT", Toast.LENGTH_SHORT).show();
                    mp.start();
                    duration=mp.getDuration();
                    sec=(duration/1000)%60;
                    min=(duration/1000)/60;
                    s1=min+":"+sec;
                    end.setText(s1);
                    break;
            }
        }





    }
    public void prev(View v){
        if(i!=0)
        {
            j=i-1;
        }
        j--;
        if(j==-1)
        {
            j=2;
        }
        if(mp!=null)
        {
            i=0;
            switch (j)
            {
                case 0:
                    mp.stop();
                    mp=null;
                    mp=MediaPlayer.create(MainActivity.this,R.raw.a);
                    Toast.makeText(this, "BAHUBALI", Toast.LENGTH_SHORT).show();
                    mp.start();
                    duration=mp.getDuration();
                    sec=(duration/1000)%60;
                    min=(duration/1000)/60;
                    s1=min+":"+sec;
                    end.setText(s1);
                    break;
                case 1:
                    mp.stop();
                    mp=null;
                    mp=MediaPlayer.create(MainActivity.this,R.raw.b);
                    Toast.makeText(this, "RAABTA", Toast.LENGTH_SHORT).show();
                    mp.start();
                    duration=mp.getDuration();
                    sec=(duration/1000)%60;
                    min=(duration/1000)/60;
                    s1=min+":"+sec;
                    end.setText(s1);
                    break;
                case 2:
                    mp.stop();
                    mp=null;
                    mp=MediaPlayer.create(MainActivity.this,R.raw.c);
                    Toast.makeText(this, "SUIT SUIT", Toast.LENGTH_SHORT).show();
                    mp.start();
                    duration=mp.getDuration();
                    sec=(duration/1000)%60;
                    min=(duration/1000)/60;
                    s1=min+":"+sec;
                    end.setText(s1);
                    break;

            }
        }

    }
    public void showDialog(View v)
    {
        adb=new  AlertDialog.Builder(this);
        adb.setTitle("EXIT");
        adb.setMessage("Are U Sure???");
        adb.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(mp!=null)
                mp.stop();
                finish();

            }
        });
        adb.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        adb.show();
    }
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            if (mp != null) {
                startTime = mp.getCurrentPosition();
                start.setText(String.format("%d:%d",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                        toMinutes((long) startTime)))
                );
                sb1.setProgress((int) startTime);
                myHandler.postDelayed(this, 100);
            }
        }
    };

}
