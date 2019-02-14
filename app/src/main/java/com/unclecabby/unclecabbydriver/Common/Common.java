package com.unclecabby.unclecabbydriver.Common;

import android.location.Location;

import com.unclecabby.unclecabbydriver.Model.User;
import com.unclecabby.unclecabbydriver.Remote.FCMClient;
import com.unclecabby.unclecabbydriver.Remote.IFCMService;
import com.unclecabby.unclecabbydriver.Remote.IGoogleAPI;
import com.unclecabby.unclecabbydriver.Remote.RetrofitClient;

public class Common {

    public static final  String driver_tbl = "Drivers";
    public static final  String user_driver_tbl = "DriversInformation";
    public static final  String user_rider_tbl = "RidersInformation";
    public static final  String pickup_request_tbl = "PickupRequest";
    public static final  String token_tbl = "Tokens";

    public static final int PICK_IMAGE_REQUEST = 9999;

    public static User currentUser;

    public static Location mLastLocation = null;

    public static final String baseURL = "https://maps.googleapis.com";
    public static final String fcmURL = "https://fcm.googleapis.com/";
    public static final String user_field= "usr";
    public static final String pwd_field= "pwd";

    public   static double base_fare=0;
    private static double time_rate=1.2;
    private static  double distance_rate=8.5;

    public  static double getPrice(double km, int min)
    {
        km*=2;
        if(km<100)
        {
            distance_rate=14;
            km/=2;
        }
        else if(km>=50&&km<150) {
            km -= 40;
            if(km<0)
            {
                km=0;
            }
            base_fare = 170 * 4;
        }
        else if(km>=150)
        {
            distance_rate=8.5;
        }

        return  (base_fare+(time_rate*min*0)+(distance_rate*km));
    }

    public  static IGoogleAPI getGoogleAPI()
    {
        return RetrofitClient.getClient(baseURL).create(IGoogleAPI.class);
    }

    public  static IFCMService getFCMService()
    {
        return FCMClient.getClient(fcmURL).create(IFCMService.class);
    }
}
