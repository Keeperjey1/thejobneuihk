package de.jey.thejobneu_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ProfilVorschau extends AppCompatActivity {
    EditText u_name, u_surname, u_age, u_username, u_password;
    String method;
    String name, surname, age, username, password;
    String profilname, jobAuswahl1;
    TextView vorProfilname, vorJobAus1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_vorschau);
        vorProfilname = findViewById(R.id.tvVorProfilname);
        vorJobAus1 = findViewById(R.id.tvVorJobAus);

        Bundle pickupData = getIntent().getExtras();
        profilname = pickupData.getString("vorProfilname");
        jobAuswahl1 = pickupData.getString("firstJob");

        vorProfilname.setText(profilname);
        vorJobAus1.setText(jobAuswahl1);
    }

    public void safeProfile(View view) {
        u_name = findViewById(R.id.edtName);
        u_surname = findViewById(R.id.edtSurname);
        u_age = findViewById(R.id.edtAge);
        u_username = findViewById(R.id.edtUsername);
        u_password = findViewById(R.id.edtPassword);

        name = u_name.getText().toString();
        surname = u_surname.getText().toString();
        age = u_age.getText().toString();
        username = u_username.getText().toString();
        password = u_password.getText().toString();


        method = "safeProflile";
        SafeProfile safe = new SafeProfile(this);
        safe.execute(new Runnable() {
            @Override
            public void run() {
                safe.doInBackground(method, name, surname, age, username, password);
            }
        });
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}