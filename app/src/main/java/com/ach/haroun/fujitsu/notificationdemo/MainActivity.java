package com.ach.haroun.fujitsu.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 120;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void notifyMe(View view) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(largeIcon(this))
                .setContentTitle("Notification")
                .setContentText("You received new notification!")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        "You received new notification!"
                ))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(createPendingIntent(this))
                .setAutoCancel(true);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        }
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,notificationBuilder.build());
    }
    private static PendingIntent createPendingIntent(Context context){
        Intent startMainActivityIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(startMainActivityIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        return resultPendingIntent;
    }
    private static Bitmap largeIcon(Context context){
        Resources resources = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher);
        return largeIcon;
    }



}
