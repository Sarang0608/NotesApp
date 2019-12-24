package com.example.notes;
/****************************************************************************************************************************************************************************************************-
                                                 THIS FRAGMENT IS THE HOME SCREEN FOR THE APP. IT SHOWS RECENT NOTES AND THE IMPORTANT NOTES SEPARATELY.
 ****************************************************************************************************************************************************************************************************/

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);


        FloatingActionButton addFab = (FloatingActionButton) view.findViewById(R.id.add_note_fab);

        //Handles the Floating Action Button that takes the user to AddNotesFragment
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new AddNoteFragment());
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        return view;
    }
}
