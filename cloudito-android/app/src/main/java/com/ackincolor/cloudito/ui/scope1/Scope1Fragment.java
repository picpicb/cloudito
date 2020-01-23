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
import android.os.Debug;
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

import java.util.List;

public class Scope1Fragment extends Fragment {

    private Scope1ViewModel scope1ViewModel;

    // Managers
    private WifiRttManager wifiRttManager;
    private WifiManager wifiManager;

    // ACCESS POINTS RESULTS
    private List<ScanResult> mScanResults;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        scope1ViewModel =
                ViewModelProviders.of(this).get(Scope1ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_scope_1, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        scope1ViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // WIFI MANAGE SCAN
        this.wifiManager = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
        //wifiManager.startScan();
        mScanResults = this.wifiManager.getScanResults();

        // LOG
        Log.d("DEBUG GEOLOCATION","SCANS IS EMPTY : " + mScanResults.isEmpty());

        //start Scanning with RTT
        scanAccessPointsRTT();

        return root;
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
        //builder.addAccessPoint(ap1ScanResult);
        //builder.addAccessPoint(ap2ScanResult);
        //builder.addAccessPoint(ap3ScanResult);

        RangingRequest request = builder.build();

        if (ContextCompat.checkSelfPermission(this.getActivity(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
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