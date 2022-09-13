package de.jey.thejobneu_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowJobOffer extends AppCompatActivity {
    TextView showId, showBeruf, showBetriebsart, showOrt, showVerfuegbarkeit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_job_offer);
        getSupportActionBar().hide();
        showId = findViewById(R.id.tvShowId);
        showBeruf = findViewById(R.id.tvShowBeruf);
        showBetriebsart = findViewById(R.id.tvShowBetriebsart);
        showOrt = findViewById(R.id.tvShowOrt);
        showVerfuegbarkeit = findViewById(R.id.tvShowVerfuegbarkeit);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String beruf = intent.getStringExtra("beruf");
        String betriebsart = intent.getStringExtra("betriebsart");
        String ort = intent.getStringExtra("ort");
        String verfuegbarkeit = intent.getStringExtra("verfuegbarkeit");

        showId.setText(id);
        showBeruf.setText(beruf);
        showBetriebsart.setText(betriebsart);
        showOrt.setText(ort);
        showVerfuegbarkeit.setText(verfuegbarkeit);

    }
}