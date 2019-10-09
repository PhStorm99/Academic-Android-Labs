package com.example.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
 //   protected Button mybutton;

    SharedPreferences sp;
    EditText typeField;
    public static final String ACTIVITY_NAME = "MainACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        typeField =(EditText)findViewById(R.id.editText1);
        sp = getSharedPreferences("UserEmail", Context.MODE_PRIVATE);

        String savedEmail = sp.getString("EmailEntered","");
        typeField.setText(savedEmail);

        Button loginButton = (Button)findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View c) {

                Intent profilePage = new Intent(MainActivity.this, ProfileActivity.class);
                //Give directions to go from this page, to ProfileActivity page

                profilePage.putExtra("EmailFormLastPage", typeField.getText().toString());

                //Now make the transition:
                MainActivity.this.startActivityForResult(profilePage,345);
            }
        });

    }


    @Override
    protected void onPause()
    {
        super.onPause();
        //object editor
        SharedPreferences.Editor editor = sp.edit();

        //saving Email address on EmailEntered
        String email = typeField.getText().toString();
        editor.putString("EmailEntered",email);

        //to save the changes
        editor.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(ACTIVITY_NAME ,"In function:  onStop");    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(ACTIVITY_NAME ,"In function:  onDestroy");    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(ACTIVITY_NAME ,"In function:  onStart");    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e(ACTIVITY_NAME ,"In function:  onResume");
    }


}
