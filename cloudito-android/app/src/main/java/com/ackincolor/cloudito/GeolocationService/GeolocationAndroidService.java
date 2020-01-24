package com.ackincolor.cloudito.GeolocationService;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.net.wifi.rtt.WifiRttManager;
import android.util.Log;

import com.ackincolor.cloudito.GeolocationService.GeolocationCache.GeolocationManager;
import com.ackincolor.cloudito.GeolocationService.GeolocationInterface.GeolocationRetrofitController;
import com.ackincolor.cloudito.entities.AccessPoint;
import com.ackincolor.cloudito.entities.Location;
import com.ackincolor.cloudito.ui.scope1.GeolocationViewModel;

import java.util.ArrayList;
import java.util.List;

public class GeolocationAndroidService {

    private Context context;
    // Managers
    private WifiRttManager wifiRttManager;
    private WifiManager wifiManager;

    // Controller
    private GeolocationRetrofitController geolocationRetrofitController;

    // ACCESS POINTS RESULTS
    private List<ScanResult> mScanResults;

    public GeolocationAndroidService(Context context) {
        this.context = context;
    }

    // GET FROM RETROFIT THEN -> call back
    public void insertAccessPoint(){
        GeolocationRetrofitController retrofitController = new GeolocationRetrofitController(context,this);
        retrofitController.insertAccessPoints();
    }

    // CALL BACK RETROFIT CONTROLLER -> INSERT INTO SQLITE
    public void callBackInsertAccessPoint(ArrayList<AccessPoint> accessPointArrayList){
        GeolocationManager db = new GeolocationManager(context);
        db.open();
        db.insertAccessPoints(accessPointArrayList);
        db.close();
    }

    // GET ACCESS POITS FROM SQLITE
    public ArrayList<AccessPoint> getAccessPointsFromSQLite(){
        GeolocationManager db = new GeolocationManager(context);
        db.open();
        ArrayList<AccessPoint> results =  db.getAccessPoints();
        db.close();
        return results;
    }

    // START SCANNING AND GETTING CLOSEST ACCESSPOINTS
    private void recordLocation(){

        Log.d("DEBUG GEOLOCATION","STARTING.");
        Log.d("DEBUG GEOLOCATION","STARTING..");
        Log.d("DEBUG GEOLOCATION","STARTING...");

        // WIFI MANAGE SCAN
        this.wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);

        Log.d("DEBUG GEOLOCATION","SCAN is available : "+wifiManager.isScanAlwaysAvailable());

        // SETTING A RECEIVER WORKING AFTER SCANNING
        BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent intent) {
                boolean success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false);
                if (success) {
                    mScanResults = wifiManager.getScanResults();

                    if(mScanResults.isEmpty()){
                        // LOG
                        Log.d("DEBUG GEOLOCATION","SCANS IS EMPTY : " + mScanResults.isEmpty());
                    }else{
                        // LOG
                        Log.d("DEBUG GEOLOCATION","LEVEL :"+mScanResults.get(0).level);
                        Log.d("DEBUG GEOLOCATION","BSSID :"+mScanResults.get(0).BSSID);
                        Log.d("DEBUG GEOLOCATION","SSID :"+mScanResults.get(0).SSID);
                        Log.d("DEBUG GEOLOCATION","FREQUENCY : "+mScanResults.get(0).frequency);

                        // get distance of each
                        for(ScanResult scan : mScanResults){
                            //
                            Log.d("DEBUG GEOLOCATION","SSID :"+mScanResults.get(0).SSID);
                            Log.d("DEBUG GEOLOCATION","BSSID :"+mScanResults.get(0).BSSID);
                            Log.d("DEBUG GEOLOCATION","DISTANCE : "+calculateDistance(scan.level,scan.frequency));
                        }
                    }
                } else {
                    Log.d("DEBUG GEOLOCATION","SCAN RECEIVED FAILURE");
                }
            }
        };
        context.registerReceiver(wifiScanReceiver, intentFilter);
        // LAUNCH THE SCAN
        boolean scanSuccess = wifiManager.startScan();
        if(!scanSuccess){
            Log.d("DEBUG GEOLOCATION","SCAN FAILURE");
        }


    }

    // GET 3 POWERFUL SIGNAL KNOWN IN OUR DB
    private List<ScanResult> getThreeMaxPowerSignal(List<ScanResult> mScanResults){
        ArrayList<ScanResult> scanResultsInBd = new ArrayList<ScanResult>();

        // GET THE KNOWN ACCESS POINT FOM BD
        ArrayList<AccessPoint> accessPoints = this.getAccessPointsFromSQLite();

        for(ScanResult scan : mScanResults){

        }
        return null;

    }

    // CALCULATE DISTANCE WITH FREQUENCY AND SIGNAL IN DB
    // USE FREE SPACE PATH LOSS (FSPL)
    private double calculateDistance(double signalLevelInDb, double freqInMHz) {
        double exp = (27.55 - (20 * Math.log10(freqInMHz)) + Math.abs(signalLevelInDb)) / 20.0;
        return Math.pow(10.0, exp);
    }

    // INSERT INTO SQLITE
    private void insertCustomerLocation(){

    }

    // GET FROM SQLITE LOCATION
    private Location getCustomerLocation(){
        return null;
    }

    // ENVOIE VERS LE BACKGROUND
    private void sendCustomerLocation(){

    }

}
