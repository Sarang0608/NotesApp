package com.example.notes;

/****************************************************************************************************************************************************************************************************
                            THIS IS THE LOGIN SCREEN OF THE APP. IT CONTAINS THE CODE FOR THE FIREBASE AUTHENTICATION UI THAT CONTAINS THE VARIOUS LOGIN METHODS
 ***************************************************************************************************************************************************************************************************/

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

public class LoginActivity extends AppCompatActivity {


    int AUTHUI_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Checks if the user is already logged on or not. If he is, it takes him to the MainActivity.
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(this,MainActivity.class));
            this.finish();
        }



    }
    //Method that handles the Login processes.
    public void handleLoginRegister(View view) {

        //This list contains the various ways a user can login.
        List<AuthUI.IdpConfig> provider = Arrays.asList(
          new AuthUI.IdpConfig.EmailBuilder().build(),
                  new AuthUI.IdpConfig.GoogleBuilder().build()

        );

        //Builds the Firebase Authentication (LOGIN) UI
        Intent intent = AuthUI.getInstance()
                .createSignInIntentBuilder()//Creates the Sign in builders that build the sign in methods
                .setAvailableProviders(provider)//Sets the available login methods to the ones mentioned in the above list
                .setTosAndPrivacyPolicyUrls("https://firebase@notes.example.com","https://firebase@notes.example.com")//Handle the PRIVACY POLICY and TOS hyperlinks
                .setLogo(R.drawable.app_icon)//Sets the logo that appears on the top
                .setAlwaysShowSignInMethodScreen(true)//Method to always show the login screen, whenever the user is logged out.
                .build();

        startActivityForResult(intent,AUTHUI_REQUEST_CODE);
    }


    //Handles the login processes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        //Check if the sign in is successful.
        if(requestCode == AUTHUI_REQUEST_CODE) {
            //Check if the user is a new user or a returning one.
            if(resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user.getMetadata().getCreationTimestamp()==user.getMetadata().getLastSignInTimestamp()) {
                    Toast.makeText(this,"Welcome New User!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "Welcome back Returning User!!", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                this.finish();
            }
            else {
                Toast.makeText(this, "Login/Registration Failed", Toast.LENGTH_SHORT).show();

            }
        }
    }


}
