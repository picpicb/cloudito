package com.ackincolor.cloudito.ui.scope4;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.ackincolor.cloudito.ui.components.Map;

import java.util.ArrayList;
import java.util.UUID;

public class Scope4Fragment extends Fragment implements CourseService<ArrayList<CourseNode>> {

    private Scope4ViewModel scope4ViewModel;
    private CourseRetrofitController courseRetrofitController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scope4ViewModel = ViewModelProviders.of(this).get(Scope4ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_scope_4, container, false);
        final Map mapComponent = root.findViewById(R.id.custView);
        //example
        this.courseRetrofitController = new CourseRetrofitController(new CourseManager(getContext()));
        this.courseRetrofitController.getStoresMap(mapComponent);

        GeolocationAndroidService gas = new GeolocationAndroidService(getContext());

            gas.recordLocation();
          //gas.startSendLocation();


        //getCustomerLocation
        this.courseRetrofitController.getCourseNodesBtwAandB(mapComponent,3085,1710,this);

        //mapComponent.mScaleFactor = 10f;
        //System.out.println(mapComponent.courseNode);

        return root;
    }

    @Override
    public void onResponse(ArrayList<CourseNode> response) {
        System.out.println(response.get(0));


    }
}