package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.*;

public class LeftFood extends AppCompatActivity {
    Parser p;
    float foodLvl;

    public LeftFood(){
        System.out.println("HERE");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        p = new Parser("http://192.168.88.161:1337/disconnect/");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_food);
        TextView foodLevelText = (TextView)findViewById(R.id.textViewFood);

        System.out.println(p.getSucroseLevel());

        foodLvl = Float.valueOf(p.getSucroseLevel());

        System.out.println(foodLvl);

        if (foodLvl >= 50.0) {
            foodLevelText.setText("High");
        } else {
            foodLevelText.setText("Low");
        }



        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        //String url = p.getImage();
        Picasso.with(this)
                .load(p.getImage())
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .into(imageView);






    }


}
