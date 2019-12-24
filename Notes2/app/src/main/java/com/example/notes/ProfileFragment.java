package com.example.notes;
/*****************************************************************************************************************************************************************************************************
                                                     THIS FRAGMENT CONTAINS THE DETAILS OF THE USER'S PROFILE AND ALSO HANDLES THE LOGOUT FEATURE
 ****************************************************************************************************************************************************************************************************/

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("My Profile");

        Button logOut = (Button) view.findViewById(R.id.logout_btn);

        //Handles the logout feature
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance().signOut(getActivity());

            }
        });

        return view;
    }
}
