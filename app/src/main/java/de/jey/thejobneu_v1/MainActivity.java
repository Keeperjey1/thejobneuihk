package de.jey.thejobneu_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText pas, usr;

   // public static final String LOG = MainActivity.class.getSimpleName();

    private List<String> mBerufList;
    private List<JobOffer> mJobOfferList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createJobsList();
        bindAdapterToListView();
    }
    private void createJobsList() {
        String[] beruf = getResources().getStringArray(R.array.beruf);
        mBerufList = new ArrayList<>(Arrays.asList(beruf));

        JobOffer jobOffer = new JobOffer(beruf[0], "Restaurant", "Deutschland, 50937 Koeln/Suelz", "Aushilfe");
        mJobOfferList.add(jobOffer);
        mJobOfferList.add(new JobOffer(beruf[1], "Bar", "Deutschland", "3 Tage"));
        mJobOfferList.add(new JobOffer(beruf[2], "Kneipe", "Deutschland", "3 Tage"));
        mJobOfferList.add(new JobOffer(beruf[3], "Bar-Restaurant", "Deutschland", "3 Tage"));
        mJobOfferList.add(new JobOffer(beruf[4], "Bistro", "Deutschland", "6 Monate"));
        mJobOfferList.add(new JobOffer(beruf[5], "Bar", "Deutschland", "Teilzeit"));
        mJobOfferList.add(new JobOffer(beruf[6], "Bar", "Deutschland", "Festanstellung"));

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
       startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }
h
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