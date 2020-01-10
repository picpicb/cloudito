package com.ackincolor.cloudito.ui.scope1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ackincolor.cloudito.R;

public class Scope1Fragment extends Fragment {

    private Scope1ViewModel scope1ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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
        return root;
    }
}