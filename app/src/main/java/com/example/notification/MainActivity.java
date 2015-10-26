package com.example.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("ALL")
public class MainActivity extends Activity implements View.OnClickListener {
    private Button b;
    String put;
    static String formattedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(this);
    }

 //   protected void onStop()
   /* {
        super.onStop();

        //finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    } */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   // @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {

        Intent intent = new Intent();
        // PendingIntent pIntent = PendingIntent.getActivity(this,0,intent,0);
       // TaskStackBuilder stackBuilder = null;
        Intent resultIntent = new Intent(this,SocialActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        //stackBuilder.addParentStack(SocialActivity.class);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
      /*  Notification notif =new Notification();
        notif.ledARGB= Color.BLUE;
        notif.flags |=Notification.FLAG_SHOW_LIGHTS;
        notif.ledOnMS=1000;
        notif.ledOffMS=300;*/

       //  put=  formattedDate = df.format(c.getTime());

        Notification noti = new Notification.Builder(this)
                // NotificationCompat.Builder noti = new NotificationCompat.Builder(
                //       this)
                // NotificationCompat.Builder builder = new NotificationCompat.Builder(
                //  this);
                // notification = builder.setContentIntent(contentIntent)
                .setTicker("Social Situation")
                .setContentTitle("Social Situation")
                .setContentText("Describe your social situation.")
                .setSmallIcon(R.drawable.social_situations_1_challenge)
                .setContentIntent(resultPendingIntent).getNotification();


       // Date date;
      /* Date StartTime = null;
      Date date=StartTime;

       Intent launch = new Intent(MainActivity.this, SocialActivity.class);
        Bundle extras = new Bundle();
        extras.putLong("start_time", date.getTime());
        launch.putExtras(extras);*/



      /*  Intent time_intent = new Intent(this, SocialActivity.class);
        intent.putExtra("date",df);
        startActivity(intent);*/


        Vibrator vibr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibr.vibrate(500); // Vibrate for half a second (500 milli seconds)

        long[] pattern = {400, 200};
        vibr.vibrate(pattern, 0); // 400ms pause, 200ms vibration. Start repeating at index 0.









        //   noti.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // mNM.notify(NOTIFICATION, notification)
       // notificationManager.notify(123,notif);
        notificationManager.notify(023, noti);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time =&gt; " + c.getTime());
        String time= String.valueOf(c.getTime());
        System.out.println("Current time =&gt; " + time);


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formattedDate = df.format(c.getTime());
// Now formattedDate have current date/time
        Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();

        Long tsLong = System.currentTimeMillis()/1000;
        formattedDate +="split"+tsLong.toString();

        // Log.d("timeval",formattedDate);

       // System.exit(0);
        //android.os.Process.killProcess(android.os.Process.myPid());
              // finish();
       /* Intent time_intent = new Intent(getApplicationContext(),SocialActivity.class);
        time_intent.putExtra("date", formattedDate);
        startActivity(time_intent);*/
    }

    }


