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

import com.ackincolor.cloudito.GeolocationService.GeolocationCache.GeolocationCustomerLocationManager;
import com.ackincolor.cloudito.GeolocationService.GeolocationCache.GeolocationManager;
import com.ackincolor.cloudito.GeolocationService.GeolocationInterface.GeolocationRetrofitController;
import com.ackincolor.cloudito.entities.AccessPoint;
import com.ackincolor.cloudito.entities.Location;

import java.util.ArrayList;
import java.util.List;

public class GeolocationAndroidService {

    // CONTEXT
    private Context context;

    // Managers
    private WifiRttManager wifiRttManager;
    private WifiManager wifiManager;

    // Controller
    private GeolocationRetrofitController geolocationRetrofitController;

    // ACCESS POINTS RESULTS
    private List<ScanResult> mScanResults;

    // CONSTRUCTOR
    public GeolocationAndroidService(Context context) {
        this.context = context;
    }

    // CANCEL RECORD AND SEND
    private boolean recordIsCancel = false;

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

                        getThreeMaxPowerSignal(mScanResults);

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
    private void getThreeMaxPowerSignal(List<ScanResult> mScanResults){
        ArrayList<ScanResult> scanResultsInBd = new ArrayList<ScanResult>();

        // GET THE KNOWN ACCESS POINT FOM BD
        ArrayList<AccessPoint> accessPoints = this.getAccessPointsFromSQLite();

        AccessPoint[] finalAccessPoint = {null,null,null};
        ScanResult[] finalScans = {null,null,null};
        int max = -100;
        ScanResult maxObject = null;
        AccessPoint maxAccessPoint = null;
        for(int i=0; i<finalAccessPoint.length; i++){
            for(ScanResult scan : mScanResults){
                for(AccessPoint accessPoint : accessPoints){
                    if(scan.BSSID.equalsIgnoreCase(accessPoint.getMacAdress())){
                        // get Max 3 times and delete the max each time.
                        if(scan.level >= max){
                            max = scan.level;
                            maxObject = scan;
                            maxAccessPoint = accessPoint;
                        };
                    }
                }
            }
            finalAccessPoint[i] = maxAccessPoint;
            finalScans[i]=maxObject;

            // RESET VARIABLES
            max = -100;
            accessPoints.remove(maxAccessPoint);
        }

        double[] finalDistance = {0.0,0.0,0.0};
        for(int i=0;i<finalScans.length;i++){
            if(finalScans[i]==null){
                Log.d("DEBUG GEOLOCATION ANDROID SERVICE","ERROR CANNOT TRIANGULATE");
                return;
            }else{
                try {
                    finalDistance[i]=calculateDistance(finalScans[i].level,finalScans[i].frequency);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }

        calculateLocation(finalAccessPoint,finalDistance);

    }

    // CALCULATE DISTANCE WITH FREQUENCY AND SIGNAL IN DB
    // USE FREE SPACE PATH LOSS (FSPL)
    public double calculateDistance(double signalLevelInDb, double freqInMHz) throws Exception {
        if(signalLevelInDb >= 0){
            Log.d("DEBUG GEOLOCATION ANDROID SERVICE","UNCORRECT SIGNAL");
            throw new Exception("Signal can't be > 0");
        }
        if(freqInMHz <= 0){
            Log.d("DEBUG GEOLOCATION ANDROID SERVICE","UNCORRECT Frequency");
            throw new Exception("Frequency can't be < 0");
        }
        double exp = (27.55 - (20 * Math.log10(freqInMHz)) + Math.abs(signalLevelInDb)) / 20.0;
        return Math.pow(10.0, exp);
    }

    // CALCULATE DISTANCE
    private void calculateLocation(AccessPoint[] arrayAccessPoint,double[] arrayDistance){
        Location location = null;

        insertCustomerLocation(location);
    }

    // INSERT INTO SQLITE
    private void insertCustomerLocation(Location location){
        if(location == null){
            Log.d("DEBUG GEOLOCATION ANDROID SERVICE","CUSTOMER LOCATION IS NULL");
            return;
        }
        GeolocationCustomerLocationManager db = new GeolocationCustomerLocationManager(context);
        db.open();
        db.insertCustomerLocation(location);
        db.close();
    }

    // GET FROM SQLITE LOCATION
    private Location getCustomerLocation(){
        GeolocationCustomerLocationManager db = new GeolocationCustomerLocationManager(context);
        db.open();
        Location location = db.getCustomerLocation();
        db.close();
        return location;
    }

    // ENVOIE VERS LE BACKGROUND -> sendCustomerLocation
    public void sendCustomerLocation(){
        Location location = this.getCustomerLocation();
        GeolocationRetrofitController retrofitController = new GeolocationRetrofitController(context,this);
        retrofitController.sendCustomerLocation(location);
    }

    public void callBackSendCustomerLocation(){
        Log.d("DEBUG GEOLOCATION ANDROID SERVICE","CUSTOMER LOCATION SENT TO BACKEND");
    }

    // STARTING SERVICE TO RECORD LOCATION
    public void startRecordLocation() throws InterruptedException{
        recordIsCancel = false;
        while(!recordIsCancel){
            recordLocation();
            // 30 sec
            Thread.sleep(30000);
        }
    }

    // STARTING SERVICE TO SEND LOCATION TO BACK END
    public void startSendLocation() throws InterruptedException{
        recordIsCancel = false;
        while(!recordIsCancel){
            sendCustomerLocation();
            // 180 sec
            Thread.sleep(180000);
        }
    }

    public void cancelRecording(){
        recordIsCancel = true;
    }

}
