package com.example.notes;

/*****************************************************************************************************************************************************************************************************
                                                                THIS FRAGMENT WILL DISPLAY ALL THE NOTES STORED BY THE USER IN A RECYCLER VIEW
 ****************************************************************************************************************************************************************************************************/

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AllNotesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_notes,container,false);
    }
}
