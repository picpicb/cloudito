package com.ackincolor.cloudito.ui.scope3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ackincolor.cloudito.CourseService.CourseCache.CourseManager;
import com.ackincolor.cloudito.CourseService.CourseInterface.CourseRetrofitController;
import com.ackincolor.cloudito.R;
import com.ackincolor.cloudito.controllers.ParcoursController;
import com.ackincolor.cloudito.entities.Course;
import com.ackincolor.cloudito.ui.components.Map;

import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class Scope3Fragment extends Fragment {

    private Scope3ViewModel scope3ViewModel;
    private ParcoursController parcoursController;
    private CourseRetrofitController courseRetrofitController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scope3ViewModel =
                ViewModelProviders.of(this).get(Scope3ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_scope_3, container, false);
        final Map mapComponent = root.findViewById(R.id.custView);
        //example
        this.courseRetrofitController = new CourseRetrofitController(new CourseManager(getContext()));
        this.courseRetrofitController.getStoresMap(mapComponent);
        this.courseRetrofitController.getCourseNodesBtwAandB(mapComponent,3085,1710);
        return root;
    }
}