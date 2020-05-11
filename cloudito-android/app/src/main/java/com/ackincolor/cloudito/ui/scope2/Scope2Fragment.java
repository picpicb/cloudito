package com.ackincolor.cloudito.ui.scope2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ackincolor.cloudito.R;
import com.ackincolor.cloudito.ui.authentication.AuthenticationFragment;

public class Scope2Fragment extends Fragment {

    private Scope2ViewModel scope2ViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scope2ViewModel =
                ViewModelProviders.of(this).get(Scope2ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_scope_2, container, false);
        final TextView textView = root.findViewById(R.id.textViewToken);
        Button button = (Button) root.findViewById(R.id.logTokenButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                updateDetail();
            }
        });
        scope2ViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
    public void updateDetail()
    {
        Intent intent = new Intent(getActivity(), AuthenticationFragment.class);
        startActivity(intent);
    }
}