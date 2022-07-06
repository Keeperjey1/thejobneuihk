package de.jey.thejobneu_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String LOG = MainActivity.class.getSimpleName();


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
       /* ArrayAdapter<JobOffer> jobOfferArrayAdapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.listviewlayout,
                        R.id.tvOrt,
                        mJobOfferList);

        ListView jobOfferListView = (ListView) findViewById(R.id.lvActivityMain);
        jobOfferListView.setAdapter(jobOfferArrayAdapter);*/
        JobOfferArrayAdapter jobOfferArrayAdapter = new JobOfferArrayAdapter(this, mJobOfferList);
        ListView jobOfferListView = (ListView) findViewById(R.id.lvActivityMain);
        jobOfferListView.setAdapter(jobOfferArrayAdapter);
    }}