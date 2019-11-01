package com.ackincolor.cloudito.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.ackincolor.cloudito.ui.plan.PlanFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TriangulationController {
    private WifiManager wifiManager;
    private List<ScanResult> bornesWifi;
    private PlanFragment fragmentView;
    public TriangulationController(PlanFragment view) {
        this.fragmentView = view;
        Context context = this.fragmentView.getContext();
        this.bornesWifi = new ArrayList<>();
        this.wifiManager = (WifiManager)
                context.getSystemService(Context.WIFI_SERVICE);
        Log.d("DEBUG","start manager");
        BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent intent) {
                Log.d("DEBUG","start recieving");
                boolean success = intent.getBooleanExtra(
                        WifiManager.EXTRA_RESULTS_UPDATED, false);
                if (success) {
                    scanSuccess();
                } else {
                    // scan failure handling
                    scanFailure();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        context.registerReceiver(wifiScanReceiver, intentFilter);
        //this.wifiManager.startScan();
    }

    private void scanSuccess() {
        Log.d("DEBUG","scan: success");
        List<ScanResult> results = wifiManager.getScanResults();
        for (ScanResult s : results)
        {
           Log.d("DEBUG", s.SSID +" force :"+s.level);
        }
        Collections.sort(results, new Comparator<ScanResult>() {
            @Override
            public int compare(ScanResult o1, ScanResult o2) {
                return o2.level - o1.level;
            }
        });

        this.bornesWifi = results;
        ScanResult res;
        if(!this.bornesWifi.isEmpty()){
            res = this.bornesWifi.get(0);
            //set Text
            this.fragmentView.setNearestBorne( "emplacement de la borne :"+res.SSID+" MAC :"+res.BSSID);
            //envoi de la position pour pub ciblées.

        }else{
            //set Text
            this.fragmentView.setNearestBorne( "not even in the center");
        }
    }
    public String getLocation() {
        //recuperation du wifi le plu proche
        this.wifiManager.startScan();
        return "Scanning...";
    }

    private void scanFailure() {
        // handle failure: new scan did NOT succeed
        // consider using old scan results: these are the OLD results!
        List<ScanResult> results = wifiManager.getScanResults();
    }

}
