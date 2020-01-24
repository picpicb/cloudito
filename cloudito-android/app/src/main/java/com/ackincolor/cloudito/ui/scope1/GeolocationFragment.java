package com.ackincolor.cloudito.ui.scope1;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.MacAddress;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.net.wifi.rtt.RangingRequest;
import android.net.wifi.rtt.RangingResult;
import android.net.wifi.rtt.RangingResultCallback;
import android.net.wifi.rtt.WifiRttManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ackincolor.cloudito.R;
import com.ackincolor.cloudito.entities.AccessPoint;

import java.util.ArrayList;
import java.util.List;

public class GeolocationFragment extends Fragment {

    private GeolocationViewModel geolocationViewModel;

    // Managers
    private WifiRttManager wifiRttManager;
    private WifiManager wifiManager;

    // ACCESS POINTS RESULTS
    private List<ScanResult> mScanResults;

    // PERMISSION CODE
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 0;
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_FINE_LOCATION = 0;
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_WIFI_STATE = 0;
    private static final int PERMISSIONS_REQUEST_CODE_CHANGE_WIFI_STATE = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        geolocationViewModel =
                ViewModelProviders.of(this).get(GeolocationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_scope_1, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        geolocationViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // CHECKING FOR PERMISSIONS TO USE LOCATION AND WI FI SCANNING
        if(ContextCompat.checkSelfPermission(this.getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION);
        }else{
            if(ContextCompat.checkSelfPermission(this.getActivity(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSIONS_REQUEST_CODE_ACCESS_FINE_LOCATION);
            }else{
                if(ContextCompat.checkSelfPermission(this.getActivity(),Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]{Manifest.permission.ACCESS_WIFI_STATE},PERMISSIONS_REQUEST_CODE_ACCESS_WIFI_STATE);
                }else{
                    if(ContextCompat.checkSelfPermission(this.getActivity(),Manifest.permission.CHANGE_WIFI_STATE) != PackageManager.PERMISSION_GRANTED){
                        requestPermissions(new String[]{Manifest.permission.ACCESS_WIFI_STATE},PERMISSIONS_REQUEST_CODE_CHANGE_WIFI_STATE);
                    }else{
                        start();
                    }
                }
            }
        }

        return root;
    }

    // START SCANNING AND GETTING CLOSEST ACCESSPOINTS
    private void start(){

        Log.d("DEBUG GEOLOCATION","STARTING.");
        Log.d("DEBUG GEOLOCATION","STARTING..");
        Log.d("DEBUG GEOLOCATION","STARTING...");

        // WIFI MANAGE SCAN
        this.wifiManager = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);

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
                        //start Scanning with RTT
                        //scanAccessPointsRTT();

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
        getContext().registerReceiver(wifiScanReceiver, intentFilter);
        // LAUNCH THE SCAN
        boolean scanSuccess = wifiManager.startScan();
        if(!scanSuccess){
            Log.d("DEBUG GEOLOCATION","SCAN FAILURE");
        }


    }

    // GET 3 POWERFUL SIGNAL KNOWN IN OUR DB
    private List<ScanResult> getThreeMaxPowerSignal(List<ScanResult> mScanResults){
        ArrayList<ScanResult> results = new ArrayList<ScanResult>();
        ArrayList<AccessPoint> accessPoints = new ArrayList<AccessPoint>();
        // GET THE KNOWN ACCESS POINT FOM BD

        for(ScanResult scan : mScanResults){

        }
        return results;

    }

    // CALCULATE DISTANCE WITH FREQUENCY AND SIGNAL IN DB
    // USE FREE SPACE PATH LOSS (FSPL)
    private double calculateDistance(double signalLevelInDb, double freqInMHz) {
        double exp = (27.55 - (20 * Math.log10(freqInMHz)) + Math.abs(signalLevelInDb)) / 20.0;
        return Math.pow(10.0, exp);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if(ContextCompat.checkSelfPermission(this.getActivity(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSIONS_REQUEST_CODE_ACCESS_FINE_LOCATION);
            }
        }else if(requestCode == PERMISSIONS_REQUEST_CODE_ACCESS_FINE_LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if(ContextCompat.checkSelfPermission(this.getActivity(),Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.ACCESS_WIFI_STATE},PERMISSIONS_REQUEST_CODE_ACCESS_WIFI_STATE);
            }
        }else if(requestCode == PERMISSIONS_REQUEST_CODE_ACCESS_WIFI_STATE && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getActivity(),Manifest.permission.CHANGE_WIFI_STATE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.CHANGE_WIFI_STATE},PERMISSIONS_REQUEST_CODE_CHANGE_WIFI_STATE);
            }
        }else if (requestCode == PERMISSIONS_REQUEST_CODE_CHANGE_WIFI_STATE && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            start();
        }
    }

    // CHECK IF RTT IS WORKING ON THIS DEVICE THEN -> rttManagerAvailable()
    private void scanAccessPointsRTT() {

        boolean rttActive = getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_WIFI_RTT);
        if (rttActive) {
            Log.d("DEBUG GEOLOCATION", "RTT IS WORKING");
            rttManagerAvailable();
        } else {
            Log.d("DEBUG GEOLOCATION", "RTT IS NOT WORKING");

        }
    }

    // CHECK IF RTT IS AVAILABLE THEN -> sendRequest()
    private void rttManagerAvailable() {
        this.wifiRttManager = (WifiRttManager) getContext().getSystemService(Context.WIFI_RTT_RANGING_SERVICE);

        IntentFilter filter = new IntentFilter(WifiRttManager.ACTION_WIFI_RTT_STATE_CHANGED);
        BroadcastReceiver myReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("DEBUG GEOLOCATION", " RTT RECEIVED");
                if (wifiRttManager.isAvailable()) {
                    Log.d("DEBUG GEOLOCATION", "RTT IS AVAILABLE");
                    sendRequest();
                } else {
                    Log.d("DEBUG GEOLOCATION", "RTT IS UNAVAILABLE ");
                }
            }
        };
        getContext().registerReceiver(myReceiver, filter);
    }

    // SEND REQUEST WITH ACCESS POINT PREVIOUSLY GOT TO GET RANGING LOCATION
    private void sendRequest() {
        // BUILD REQUEST TO GET RANGING OF ACCESS POINTS

        RangingRequest.Builder builder = new RangingRequest.Builder();
        //builder.addWifiAwarePeer(macAddress);
        //builder.addAccessPoint(ap2ScanResult);
        //builder.addAccessPoint(ap3ScanResult);

        RangingRequest request = builder.build();

        if (ContextCompat.checkSelfPermission(this.getActivity(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("DEBUG GEOLOCATION","REQUEST DENIED : ACCESS_FINE_LOCATION NOT GRANTED");
            return;
        }

        if (ContextCompat.checkSelfPermission(this.getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("DEBUG GEOLOCATION","REQUEST DENIED : ACCESS_COARSE_LOCATION NOT GRANTED");
            return;
        }

        // GETTING RANGING WITH RTT
        wifiRttManager.startRanging(request, getContext().getMainExecutor(), new RangingResultCallback() {

            @Override
            public void onRangingFailure(int code) {
                Log.d("DEBUG GEOLOCATION","RTT RANGING FAILURE");
            }

            @Override
            public void onRangingResults(List<RangingResult> results) {
                Log.d("DEBUG GEOLOCATION","RTT RANGING RESULTS");
                for(RangingResult result : results){
                    // MAC
                    MacAddress mac  = result.getMacAddress();

                    // distance VALIDE SI status = STATUS_SUCCESS
                    int distance = result.getDistanceMm();

                    // status DISTANCE CALCULEE OK OU KO
                    int status = result.getStatus();

                    // rssi (PUISSANCE SIGNAL)
                    int rssi = result.getRssi();
                }
            }
        });
    }

}