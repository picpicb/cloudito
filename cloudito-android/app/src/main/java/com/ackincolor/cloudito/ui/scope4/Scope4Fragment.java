package com.ackincolor.cloudito.ui.scope4;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ackincolor.cloudito.CourseService.CourseCache.CourseManager;
import com.ackincolor.cloudito.CourseService.CourseInterface.CourseRetrofitController;
import com.ackincolor.cloudito.CourseService.CourseInterface.CourseService;
import com.ackincolor.cloudito.GeolocationService.GeolocationAndroidService;
import com.ackincolor.cloudito.R;
import com.ackincolor.cloudito.controllers.ParcoursController;
import com.ackincolor.cloudito.entities.CourseNode;
import com.ackincolor.cloudito.entities.Location;
import com.ackincolor.cloudito.services.AsyncTaskGetCustomerLocation;
import com.ackincolor.cloudito.ui.components.Map;

import java.util.ArrayList;
import java.util.UUID;

public class Scope4Fragment extends Fragment implements CourseService<ArrayList<CourseNode>> {

    private Scope4ViewModel scope4ViewModel;
    private CourseRetrofitController courseRetrofitController;


    // PERMISSION CODE
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 0;
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_FINE_LOCATION = 0;
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_WIFI_STATE = 0;
    private static final int PERMISSIONS_REQUEST_CODE_CHANGE_WIFI_STATE = 0;


    public void start(Map mapComponent){
        GeolocationAndroidService gas = new GeolocationAndroidService(getContext());

        gas.insertAccessPoint();
        gas.recordLocation();

        //gas.startSendLocation();
        //while(true){
        //    AsyncTaskGetCustomerLocation atgcl = new AsyncTaskGetCustomerLocation();
        //}
        while(gas.getCustomerLocation() == null){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        CourseNode cs = gas.getCustomerLocation();
        System.out.println(cs);
        this.courseRetrofitController.getCourseNodesBtwAandB(mapComponent,Integer.parseInt(""+gas.getCustomerLocation().getId()),1710,this);

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scope4ViewModel = ViewModelProviders.of(this).get(Scope4ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_scope_4, container, false);
        final Map mapComponent = root.findViewById(R.id.custView);
        //example
        this.courseRetrofitController = new CourseRetrofitController(new CourseManager(getContext()));
        this.courseRetrofitController.getStoresMap(mapComponent);



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
                        start(mapComponent);
                    }
                }
            }
        }

        //mapComponent.mScaleFactor = 10f;
        //System.out.println(mapComponent.courseNode);



        return root;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults,Map mapComponent) {
        if (requestCode == PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if(ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
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
            start(mapComponent);
        }
    }

    @Override
    public void onResponse(ArrayList<CourseNode> response) {

        System.out.println(response.get(0));


    }
}