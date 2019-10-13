package com.example.helloworld;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;



public class HomePage extends AppCompatActivity {
    private ImageButton tempbutton;
    private ImageButton foodbutton;
    private ImageButton queenbutton;
    private ImageButton pollenbutton;
    private ImageButton varroabutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        tempbutton = (ImageButton) findViewById(R.id.tempbutton);
        tempbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTempHumidityPage();
            }
        });

        foodbutton = (ImageButton) findViewById(R.id.foodbutton);
        foodbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLeftFood();
            }
        });

        queenbutton = (ImageButton) findViewById(R.id.queenbutton);
        queenbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQueen();
            }
        });

        pollenbutton = (ImageButton) findViewById(R.id.pollenbutton);
        pollenbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPollen();
            }
        });

        varroabutton = (ImageButton) findViewById(R.id.varroabutton);
        varroabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVarroa();
            }
        });
    }

    public void openTempHumidityPage() {
        Intent intent = new Intent(this, TempHumidityPage.class);
        startActivity(intent);
    }

    public void openLeftFood() {
        Intent intent = new Intent(this, LeftFood.class);
        startActivity(intent);
    }

    public void openQueen() {
        Intent intent = new Intent(this, Queen.class);
        startActivity(intent);
    }

    public void openPollen() {
        Intent intent = new Intent(this, Pollen.class);
        startActivity(intent);
    }

    public void openVarroa() {
        Intent intent = new Intent(this, Varroa.class);
        startActivity(intent);
    }
}



