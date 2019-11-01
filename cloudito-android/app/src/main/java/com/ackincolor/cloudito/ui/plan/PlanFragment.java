package com.ackincolor.cloudito.ui.plan;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
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
import com.ackincolor.cloudito.controller.TriangulationController;

import java.util.Timer;
import java.util.TimerTask;

public class PlanFragment extends Fragment {

    private PlanViewModel planViewModel;
    private TimerTask timerTask;
    private Timer timer;
    private static final String[] LOCATION_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int INITIAL_REQUEST=1337;
    private static final int LOCATION_REQUEST=INITIAL_REQUEST+3;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        planViewModel =
                ViewModelProviders.of(this).get(PlanViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plan, container, false);
        final TextView textView = root.findViewById(R.id.text_share);
        planViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        //test wifi triangulation
        if(!canAccessLocation())
            requestPermissions(LOCATION_PERMS, LOCATION_REQUEST);
        final TriangulationController tc = new TriangulationController(this);


        final Handler handler = new Handler();
        this.timer = new Timer();
        this.timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @SuppressWarnings("unchecked")
                    public void run() {
                        try {
                            textView.setText(tc.getLocation());
                        }
                        catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        return root;
    }
    private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }
    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED== ContextCompat.checkSelfPermission(this.getContext(),perm));
    }

    public void setNearestBorne(String borne){
        this.planViewModel.setBorne(borne);
    }
    @Override
    public void onResume() {
        //Log.d("DEBUG", "RESUME Fragment");
        this.timer = new Timer();
        timer.schedule(this.timerTask, 0, 30000);
        super.onResume();
    }

    @Override
    public void onPause() {
        //Log.d("DEBUG", "PAUSE Fragment");
        this.timer.cancel();
        super.onPause();
    }

    @Override
    public void onStop() {
        //Log.d("DEBUG", "STOP Fragment");
        this.timer.cancel();
        this.timerTask.cancel();
        super.onStop();
    }
}