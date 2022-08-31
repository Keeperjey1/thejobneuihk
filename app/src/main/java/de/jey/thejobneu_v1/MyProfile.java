package de.jey.thejobneu_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinBranche, spinJobs;
    ArrayList<String> brancheList = new ArrayList<>();
    ArrayList<String> jobList = new ArrayList<>();
    ArrayAdapter<String> brancheAdapter;
    ArrayAdapter<String> jobsAdapter;
    RequestQueue requestQueue;
  /*  String[] branche = {"Gastronomie", "Medizin", "Informatik"};
    String[] jobs = {"Barman", "Kellner", "Thekenkraft", "Betriebsleiter"};
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        spinBranche = findViewById(R.id.spiBranche);
        spinJobs = findViewById(R.id.spiJob);
        requestQueue = Volley.newRequestQueue(this);
        String brancheUrl = "http://192.168.0.105/buero/dropdownbranche.php";
       // String jobsUrl = "http://192.168.64.150/buero/dropdownjobs.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                brancheUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("branche");
                    for (int i = 0; i<jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String branchenname = jsonObject.optString("branchenname");
                        brancheList.add(branchenname);
                        brancheAdapter = new ArrayAdapter<>(MyProfile.this, R.layout.item_file, brancheList);
                        brancheAdapter.setDropDownViewResource(R.layout.item_file);
                        spinBranche.setAdapter(brancheAdapter);
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
        spinBranche.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.spiBranche) {
            jobList.clear();
            String selectedBranche = adapterView.getSelectedItem().toString();
            String jobsUrl = "http://192.168.0.105/buero/dropdownjobs.php?branchenname="+selectedBranche;
            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    jobsUrl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("jobs");
                        for (int i = 0; i<jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String branchenname = jsonObject.optString("jobname");
                            jobList.add(branchenname);
                            jobsAdapter = new ArrayAdapter<>(MyProfile.this, R.layout.item_file, jobList);
                            jobsAdapter.setDropDownViewResource(R.layout.item_file);
                            spinJobs.setAdapter(jobsAdapter);
                        }

                    }catch (JSONException e) {
                        e.printStackTrace();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(jsonObjectRequest);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /*
        ArrayAdapter<String> adapterBranche = new ArrayAdapter<String>(MyProfile.this, R.layout.item_file, branche);
        adapterBranche.setDropDownViewResource(R.layout.item_file);
        spinBranche.setAdapter(adapterBranche);

        ArrayAdapter<String> adapterJobs = new ArrayAdapter<String>(MyProfile.this, R.layout.item_file, jobs);
        adapterJobs.setDropDownViewResource(R.layout.item_file);
        spinJobs.setAdapter(adapterJobs);*/

     /*   spinBranche.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Toast.makeText(MyProfile.this, value, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinJobs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Toast.makeText(MyProfile.this, value, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
}