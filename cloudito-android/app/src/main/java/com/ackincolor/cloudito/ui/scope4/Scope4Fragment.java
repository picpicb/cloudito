package com.ackincolor.cloudito.ui.scope4;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
import com.ackincolor.cloudito.ui.components.Map3D;

import java.util.ArrayList;
import java.util.UUID;

import static android.content.Context.SENSOR_SERVICE;

public class Scope4Fragment extends Fragment implements CourseService<ArrayList<CourseNode>>, SensorEventListener {

    private com.ackincolor.cloudito.ui.scope4.Scope4ViewModel scope4ViewModel;
    private CourseRetrofitController courseRetrofitController;


    // PERMISSION CODE
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 0;
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_FINE_LOCATION = 0;
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_WIFI_STATE = 0;
    private static final int PERMISSIONS_REQUEST_CODE_CHANGE_WIFI_STATE = 0;

    private Map3D mapComponent;
    private SensorManager mSensorManager;
    private Sensor mMagnetometer;
    private Sensor mAccelerometer;
    float[] mGravity;
    float[] mGeomagnetic;
    private boolean followNorth = true;


    public void start(Map3D mapComponent){
        CourseService<ArrayList<CourseNode>> courseService = this;
        Thread t = new Thread(){
            public void run() {
                //test boussole

                GeolocationAndroidService gas = new GeolocationAndroidService(getContext());
                gas.insertAccessPoint();
                gas.recordLocation();
                //gas.startSendLocation();
                //while(true){
                //    AsyncTaskGetCustomerLocation atgcl = new AsyncTaskGetCustomerLocation();
                //}
                /*while(gas.getCustomerLocation() == null){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
                int counter = 0;
                Integer course[] = {
                        1277,
                        1276,
                        1275,
                        1338,
                        1337,
                        1336,
                        1335,
                        1331,
                        1327,
                        1325,
                        1326,
                        2454,
                        2453,
                        2449,
                        2344,
                        2524,
                        2343,
                        2326,
                        2301,
                        2520,
                        2525,
                        2531,
                        2530,
                        2598,
                        3085
                };
                while (true) {
                    CourseNode cs = gas.getCustomerLocation();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(cs);
                    int mockServicegeo = course[counter];
                    counter = (counter+1)%course.length;
                    //courseRetrofitController.getCourseNodesBtwAandB(mapComponent, Integer.parseInt(gas.getCustomerLocation().getId().toString()), 1710, courseService);
                    courseRetrofitController.getCourseNodesBtwAandB(mapComponent, mockServicegeo, 3085, courseService);
                }
            }
        };
        t.start();
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scope4ViewModel = ViewModelProviders.of(this).get(com.ackincolor.cloudito.ui.scope4.Scope4ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_scope_4, container, false);
        this.mapComponent = root.findViewById(R.id.custView);
        //example
        this.courseRetrofitController = new CourseRetrofitController(new CourseManager(getContext()));
        this.courseRetrofitController.getStoresMap(mapComponent);
        mSensorManager = (SensorManager)getContext().getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, mMagnetometer, SensorManager.SENSOR_DELAY_UI);



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
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
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
        //System.out.println(response.get(0));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {  }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity = event.values;
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = event.values;
        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                float azimut = orientation[0]; // orientation contains: azimut, pitch and roll
                float azimuthInDegress = (float)(Math.toDegrees(azimut)+360)%360;
                Log.d("DEBUG Orientation","Orientation = "+azimuthInDegress);
                if(mapComponent!=null && (azimuthInDegress%5)!=0){
                    if(followNorth)
                        mapComponent.setNorthAngle(azimuthInDegress);
                    else
                        mapComponent.setUserRotation(azimuthInDegress);
                }
            }
        }
    }
}