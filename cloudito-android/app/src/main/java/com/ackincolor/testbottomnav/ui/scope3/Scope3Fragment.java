package com.ackincolor.testbottomnav.ui.scope3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ackincolor.testbottomnav.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class Scope3Fragment extends Fragment {

    private Scope3ViewModel scope3ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scope3ViewModel =
                ViewModelProviders.of(this).get(Scope3ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_scope_3, container, false);
        final TextView textView = root.findViewById(R.id.text_scope_3);
        scope3ViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}