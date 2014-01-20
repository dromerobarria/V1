/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.geon.services;


import com.geon.InspectoresAndroid.MainActivity;
import com.geon.InspectoresAndroid.R;

import com.google.android.gms.gcm.GoogleCloudMessaging;


import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * IntentService responsible for handling GCM messages.
 */
public class GCMIntentService extends IntentService {

	public static final int NOTIFICATION_ID = 1;

	Notification.Builder builder;

	private NotificationManager mNotificationManager;

	public GCMIntentService() {
		super("GcmIntentService");
		Log.d("INIT", "init");

	}

	public static final String TAG = "GCM Demo";

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		
		String messageType = gcm.getMessageType(intent);

		if (!extras.isEmpty()) { 

			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR
					.equals(messageType)) {
				sendNotification("Send error: " + extras.toString());
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED
					.equals(messageType)) {
				sendNotification("Deleted messages on server: "
						+ extras.toString());

			} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE
					.equals(messageType)) {

				for (int i = 0; i < 5; i++) {
					Log.i(TAG,
							"Working... " + (i + 1) + "/5 @ "
									+ SystemClock.elapsedRealtime());
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
					}
				}
				Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());
				//displayMessage(getApplicationContext(), extras.toString());
				sendNotification("Received: " + extras.getString("price"));
				Log.i(TAG, "Received: " + extras.toString());
			}
		}

		GcmBroadcastReceiver.completeWakefulIntent(intent);
	}

	private void sendNotification(String msg) {
		mNotificationManager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		Intent notification = new Intent(getApplicationContext(),
				MainActivity.class);

		PendingIntent pIntent = PendingIntent.getActivity(this, 0,
				notification, 0);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this);

		builder.setSmallIcon(R.drawable.ic_launcher2)
				.setContentTitle("Geon app")
				.setContentText("mensaje desde servidor " + msg)
				.setContentIntent(pIntent)
				.setAutoCancel(true)
				.setOnlyAlertOnce(true)
				.setSound(
						RingtoneManager
								.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		mNotificationManager.notify(0, builder.build());
	}

}
