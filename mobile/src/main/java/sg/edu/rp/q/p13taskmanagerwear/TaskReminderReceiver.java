package sg.edu.rp.q.p13taskmanagerwear;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Action.Builder;
import android.support.v4.app.RemoteInput;

public class TaskReminderReceiver extends BroadcastReceiver {

    int notifReqCode = 123;

	@Override
	public void onReceive(Context context, Intent i) {

		int id = i.getIntExtra("id", -1);
		String name = i.getStringExtra("name");
		String desc = i.getStringExtra("desc");

		NotificationManager notificationManager = (NotificationManager)
				context.getSystemService(Context.NOTIFICATION_SERVICE);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationChannel channel = new
					NotificationChannel("default", "Default Channel",
					NotificationManager.IMPORTANCE_DEFAULT);

			channel.setDescription("This is for default notification");
			notificationManager.createNotificationChannel(channel);
		}

		Intent intent = new Intent(context, MainActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(context, notifReqCode,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);



        // build notification
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");
		builder.setContentTitle("Task Manager Reminder");
		builder.setContentText(name);
		builder.setSmallIcon(android.R.drawable.ic_dialog_info);
		builder.setContentIntent(pIntent);
		builder.setAutoCancel(true);

		Notification n = builder.build();

        NotificationCompat.Action action = new
                NotificationCompat.Action.Builder(
                R.mipmap.ic_launcher,
                "Reply",
                pIntent).build();


        Intent intentreply = new Intent(context,
                ReplyActivity.class);
        PendingIntent pendingIntentReply = PendingIntent.getActivity
                (context, 0, intentreply,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteInput ri = new RemoteInput.Builder("status")
                .setLabel("Status report")
                .setChoices(new String [] {"Speak now", "Draw Emoji"})
                .build();


        NotificationCompat.WearableExtender extender = new
                NotificationCompat.WearableExtender();
        extender.addAction(action);


        notificationManager.notify(notifReqCode, n);
	}

}
