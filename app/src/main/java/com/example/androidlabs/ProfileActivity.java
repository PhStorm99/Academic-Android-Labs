package com.example.androidlabs;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity{

    EditText enterText;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageButton mImageButton;

    Button goToChatBtn;

    public static final String ACTIVITY_NAME = "PROFILE_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(ACTIVITY_NAME ,"In function:  onCreate");
        setContentView(R.layout.profile_activity);

        enterText = (EditText)findViewById(R.id.editText4);

        // get the intent that got us here
        Intent lastPage = getIntent();
        String previousTypedEmail = lastPage.getStringExtra("EmailFormLastPage");
        //Put the string that was sent from FirstActivity into the edit text
        enterText.setText(previousTypedEmail);


        mImageButton = (ImageButton)findViewById(R.id.picture);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View c) {
                ProfileActivity.this.dispatchTakePictureIntent();
            }
        });

        goToChatBtn = (Button)findViewById(R.id.GoToChatBtn);
        goToChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View c) {

                Intent goToChatPage = new Intent(ProfileActivity.this, ChatRoomActivity.class);

                ProfileActivity.this.startActivityForResult(goToChatPage, 345);

                }});

            Log.d(ACTIVITY_NAME,"In function: OnCreate()");


    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(ACTIVITY_NAME,"In function: onActivityResult");
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageButton.setImageBitmap(imageBitmap);
        }
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
    protected void onPause() {
        super.onPause();
        Log.e(ACTIVITY_NAME ,"In function:  onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(ACTIVITY_NAME ,"In function:  onResume");
    }

}
