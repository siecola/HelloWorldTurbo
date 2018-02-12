package com.siecolasystems.helloworldturbo.gcm;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import com.siecolasystems.helloworldturbo.MainActivity;
import com.siecolasystems.helloworldturbo.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.Gson;
import com.siecolasystems.helloworldturbo.models.ProductInfo;


/**
 * Created by siecola on 6/4/16.
 */
public class GcmBroadcastReceiver extends BroadcastReceiver {

    private Context context;
    private NotificationManager mNotificationManager;
    public static final int NOTIFICATION_ID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
        this.context = context;
        String messageType = gcm.getMessageType(intent);

        if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
            Bundle extras = intent.getExtras();

            Gson gson = new Gson();

            if (extras.containsKey("product")) {
                String productStr = extras.getString("product");
                if (productStr != null) {
                    ProductInfo productInfo = gson.fromJson(productStr,
                            ProductInfo.class);
                    sendNotification(productInfo);
                }

            }
        }
        setResultCode(Activity.RESULT_OK);
    }

    private void sendNotification(ProductInfo productInfo) {
        mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("productInfo", productInfo);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder mBuilder = new Notification.Builder(
                context)
                //FIXME - change for a proper icon
                .setSmallIcon(R.drawable.ic_warning_black_24dp)
                .setAutoCancel(true)
                .setContentTitle("Siecola Vendas")
                .setStyle(new Notification.BigTextStyle().bigText("Produto:"
                        + productInfo.getProductID() + " - "
                        + productInfo.getName()))
                .setContentText(
                        "Produto:" + productInfo.getProductID() + " - "
                                + productInfo.getName());

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}











