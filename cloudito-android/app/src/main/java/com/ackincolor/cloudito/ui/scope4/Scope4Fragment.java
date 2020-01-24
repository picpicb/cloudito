package com.ackincolor.cloudito.ui.scope4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ackincolor.cloudito.R;
import com.ackincolor.cloudito.controllers.ParcoursController;

import java.util.UUID;

public class Scope4Fragment extends Fragment {

    private Scope4ViewModel scope4ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scope4ViewModel = ViewModelProviders.of(this).get(Scope4ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_scope_4, container, false);
        final TextView textView = root.findViewById(R.id.text_scope_4);
        scope4ViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}