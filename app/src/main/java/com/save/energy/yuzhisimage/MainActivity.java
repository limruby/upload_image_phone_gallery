package com.save.energy.yuzhisimage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final int SELECT_PIC = 100;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image_Camera);
    }

    public void openCamera(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/'");
        startActivityForResult(intent, SELECT_PIC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PIC) {
            Uri selectImg = data.getData();
            InputStream inputStream = null;
            try{
                assert selectImg != null;
                inputStream = getContentResolver().openInputStream(selectImg);


            } catch (FileNotFoundException e){
                e.printStackTrace();
            }

            BitmapFactory.decodeStream(inputStream);
            imageView.setImageURI(selectImg);
            Toast.makeText(MainActivity.this, "Changed successfully!", Toast.LENGTH_SHORT).show();

        }
    }
}