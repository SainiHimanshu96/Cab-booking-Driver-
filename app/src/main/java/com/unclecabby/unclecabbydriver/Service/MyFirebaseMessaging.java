package com.unclecabby.unclecabbydriver.Service;

import android.content.Intent;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.unclecabby.unclecabbydriver.CustomerCall;

public class MyFirebaseMessaging extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //firebase message will contain lat and lng of rider
        //so convet message in LatLng
        LatLng customer_Location = new Gson().fromJson(remoteMessage.getNotification().getBody(),LatLng.class);

        Intent intent = new Intent(getBaseContext(), CustomerCall.class);
        intent.putExtra("lat",customer_Location.latitude);
        intent.putExtra("lng",customer_Location.longitude);
        intent.putExtra("customer",remoteMessage.getNotification().getTitle());

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
