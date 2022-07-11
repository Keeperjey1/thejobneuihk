package de.jey.thejobneu_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText pas, usr;
    Button holeDaten;

   // public static final String LOG = MainActivity.class.getSimpleName();

    private List<String> mBerufList;
    private List<String> mBetriebsartList;
    private List<String> mOrtList;
    private List<String> mVerfuegbarkeitList;
    private List<JobOffer> mJobOfferList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        holeDaten = findViewById(R.id.btHoleDaten);

        createJobsList();
        bindAdapterToListView();
    }
    private void createJobsList() {
        String[] beruf = getResources().getStringArray(R.array.beruf);
        String[] betriebsart = getResources().getStringArray(R.array.betriebsart);
        String[] ort = getResources().getStringArray(R.array.ort);
        String[] verfuegbarkeit = getResources().getStringArray(R.array.verfuegbarkeit);
        mBerufList = new ArrayList<>(Arrays.asList(beruf));
        mBetriebsartList = new ArrayList<>(Arrays.asList(betriebsart));
        mOrtList = new ArrayList<>(Arrays.asList(ort));
        mVerfuegbarkeitList = new ArrayList<>(Arrays.asList(verfuegbarkeit));

        JobOffer jobOffer = new JobOffer(beruf[0], betriebsart[0], ort[0], verfuegbarkeit[0]);
        mJobOfferList.add(jobOffer);
        mJobOfferList.add(new JobOffer(beruf[1],  betriebsart[1], ort[1], verfuegbarkeit[1]));
        mJobOfferList.add(new JobOffer(beruf[2],  betriebsart[2], ort[2], verfuegbarkeit[2]));
        mJobOfferList.add(new JobOffer(beruf[3],  betriebsart[3], ort[3], verfuegbarkeit[3]));
        mJobOfferList.add(new JobOffer(beruf[4],  betriebsart[4], ort[4], verfuegbarkeit[4]));
        mJobOfferList.add(new JobOffer(beruf[5],  betriebsart[5], ort[5], verfuegbarkeit[5]));
        mJobOfferList.add(new JobOffer(beruf[6],  betriebsart[6], ort[6], verfuegbarkeit[6]));
    }

    private void bindAdapterToListView() {
        JobOfferArrayAdapter jobOfferArrayAdapter = new JobOfferArrayAdapter(this, mJobOfferList);
        ListView jobOfferListView = (ListView) findViewById(R.id.lvActivityMain);
        jobOfferListView.setAdapter(jobOfferArrayAdapter);
    }
    public void login(View view) {
        pas = findViewById(R.id.edtPassword);
        usr = findViewById(R.id.edtUsername);

        String user = usr.getText().toString();
        String pass = pas.getText().toString();

        UserLogin ul = new UserLogin(this);
        ul.execute(user, pass);
       //startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
    public void holeDaten(View view) {

    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itLogin:
                setContentView(R.layout.login);
            return true;
            case R.id.itLogout:
                finish();
                return true;
            case R.id.itMain:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
