package com.example.cameraintent_fall2022;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

//code adapted from https://www.geeksforgeeks.org/how-to-open-camera-through-intent-and-display-captured-image-in-android

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button camera_open_id;
    ImageView click_image_id;
    private int REQUEST_CODE_PERMISSIONS = 101;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera_open_id= findViewById(R.id.camera_button);
        click_image_id = findViewById(R.id.click_image);

        camera_open_id.setOnClickListener(this);

    }

    ActivityResultLauncher<Intent> resultlauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Bitmap photo = (Bitmap) result.getData().getExtras().get("data");
                    click_image_id.setImageBitmap(photo);
                }
            });

    private boolean allPermissionsGranted(){

        for(String permission : REQUIRED_PERMISSIONS){

            if(ContextCompat.checkSelfPermission(getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(allPermissionsGranted()){
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            resultlauncher.launch(camera_intent);
        } else{
            Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    public void onClick(View view) {
        if(allPermissionsGranted()){
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            resultlauncher.launch(camera_intent);
        } else{
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }

    }

}