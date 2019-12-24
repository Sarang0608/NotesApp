package com.example.notes;

/***************************************************************************************************************************************************************************************************
                                                                THIS FRAGMENT HANDLES ADDING THE NEW NOTES INTO THE FIREBASE DATABASE
***************************************************************************************************************************************************************************************************/

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class AddNoteFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view;
        view = inflater.inflate(R.layout.fragment_add_note,container,false);

        int whiteColorValue = Color.WHITE;

        final EditText header = (EditText) view.findViewById(R.id.note_header_et);
        final EditText body = (EditText) view.findViewById(R.id.note_content_et);
        ImageView mic = (ImageView) view.findViewById(R.id.mic_img);
        Button saveBtn = (Button) view.findViewById(R.id.save_btn);

        //Adding the custom toolbar(different from the Main Activity)
        Toolbar addNotesToolbar =  (Toolbar) view.findViewById(R.id.add_notes_toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();//Hide the toolbar from Main Activity. Absence of this method will result in two different toolbars being present at the same time
        ((AppCompatActivity)getActivity()).setSupportActionBar(addNotesToolbar);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        /* Code to test the Firebase Firestrore functionality. Please do not alter this comment.
        Map<String,Object> map = new HashMap<>();
        map.put("Header","Third Note");
        map.put("Body","This is the third note added.");
        map.put("Time of Creation",new Timestamp(new Date()));
        map.put("User Created",FirebaseAuth.getInstance().getCurrentUser().getEmail());

        firestore.collection("notes")
                .add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(getActivity(), "Note Added!", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getActivity(), "Task Failed!!", Toast.LENGTH_SHORT).show();

                    }
                });


        FirebaseFirestore.getInstance()
                .collection("notes")
                .whereEqualTo("Header","Third Note")
                .whereEqualTo("User Created",FirebaseAuth.getInstance().getCurrentUser().getEmail())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot snapshot:snapshotList) {

                            header.setText(snapshot.getString("Header"));
                            body.setText(snapshot.getString("Body"));
                        }

                    }

                });

                DocumentReference documentReference = FirebaseFirestore.getInstance()
                        .collection("notes")
                        .document("id");
                Map<String,Object> map = new HashMap<>();
                map.put("Header","Second Note");

                documentReference.update(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity(), "Updated the Doc", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Cannot Update the Doc", Toast.LENGTH_SHORT).show();
                            }
                        });*/





        //Handles the Speech-to-Text conversion of the App
        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSpeechInput(view);
            }
        });

        //Handles the Add Note to Firebase feature of App
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"This will save the note into Firebase Database",Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }


    //Method that recognizes the Speech given by user
    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        startActivityForResult(intent,10);

    }

    //Method that converts the recognized speech to text and stores it in the Edit Text
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EditText content = (EditText) getView().findViewById(R.id.note_content_et);

        switch (requestCode) {
            case 10:
                if(resultCode==RESULT_OK && data!=null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    content.setText(result.get(0));
                }
                break;
        }

    }





}
