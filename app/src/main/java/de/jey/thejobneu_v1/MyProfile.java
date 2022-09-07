package de.jey.thejobneu_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

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
    Spinner spinBranche;
    Spinner spinJobs;
    Spinner spinJobs2;
    Spinner spinJobs3;
    Spinner spinErfBra;
    private View view;
    private int i;
    private long l;
    Spinner spiErfJob;
    Spinner spiErfJob2;
    Spinner spiErfJob3;
    Button waehleJob2;
    Button waehleJob3;
    LinearLayout layoutJob2;
    LinearLayout layoutJob3;
    ImageView imvFaKePlus;
    ImageView imvFaKeMinus;
    LinearLayout linLayFaehigKenntn;
    ArrayList<String> brancheList = new ArrayList<>();
    ArrayList<String> jobList = new ArrayList<>();
    ArrayList<String> erfList = new ArrayList<>();
    ArrayAdapter<String> brancheAdapter;
    ArrayAdapter<String> jobsAdapter;
    ArrayAdapter<String> erfAdapter;
    RequestQueue requestQueue;

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return super.onContextItemSelected(item);
    }



    /*  String[] branche = {"Gastronomie", "Medizin", "Informatik"};
          String[] jobs = {"Barman", "Kellner", "Thekenkraft", "Betriebsleiter"};
      */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        spinBranche = findViewById(R.id.spiBranche);
        spinJobs = findViewById(R.id.spiJob);
        spinJobs2 = findViewById(R.id.spiJob2);
        spinJobs3 = findViewById(R.id.spiJob3);
        spinErfBra = findViewById(R.id.spiBraErf);
        spiErfJob = findViewById(R.id.spiJobErf);
        spiErfJob2 = findViewById(R.id.spiJobErf2);
        spiErfJob3 = findViewById(R.id.spiJobErf3);
        layoutJob2 = findViewById(R.id.layoutJob2);
        layoutJob3 = findViewById(R.id.layoutJob3);
        waehleJob2 = findViewById(R.id.btnNextJob2);
        waehleJob3 = findViewById(R.id.btnNextJob3);
        imvFaKePlus = findViewById(R.id.imvFaKePlus);
        imvFaKeMinus = findViewById(R.id.imvFaKeMinus);
        linLayFaehigKenntn = findViewById(R.id.linLayFaehigKenntn);

        imvFaKePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayFaehigKenntn.setVisibility(LinearLayout.VISIBLE);
                imvFaKeMinus.setVisibility(ImageView.VISIBLE);
                imvFaKePlus.setVisibility(ImageView.GONE);
            }
        });


        imvFaKeMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayFaehigKenntn.setVisibility(LinearLayout.GONE);
                imvFaKeMinus.setVisibility(ImageView.GONE);
                imvFaKePlus.setVisibility(ImageView.VISIBLE);
            }
        });



        requestQueue = Volley.newRequestQueue(this);
        String brancheUrl = "http://192.168.0.105/buero/dropdownbranche.php";
        //String jobsUrl = "http://192.168.64.150/buero/dropdownjobs.php";
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
        spinErfBra.setOnItemSelectedListener(this);
        spinJobs.setOnItemSelectedListener(this);
        spiErfJob.setOnItemSelectedListener(this);
        spinJobs2.setOnItemSelectedListener(this);
        spiErfJob2.setOnItemSelectedListener(this);
        spinJobs3.setOnItemSelectedListener(this);



    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l ) {
        if (adapterView.getId() == R.id.spiBranche) {
            erfList.clear();
            jobList.clear();
            String selectedBranche = adapterView.getSelectedItem().toString();
            String erfUrl = "http://192.168.0.105/buero/dropdownerf.php?branchenname="+selectedBranche;

            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    erfUrl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("erfahrung");
                        for (int i = 0; i<jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String bezeichnung = jsonObject.optString("bezeichnung");
                            erfList.add(bezeichnung);
                            erfAdapter = new ArrayAdapter<>(MyProfile.this, R.layout.item_file, erfList);
                            erfAdapter.setDropDownViewResource(R.layout.item_file);
                            spinErfBra.setAdapter(erfAdapter);
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

        if (adapterView.getId() == R.id.spiBraErf && !adapterView.getSelectedItem().toString().equals("")) {
            jobList.clear();
            String selectedBranche = spinBranche.getSelectedItem().toString();
            String jobUrl = "http://192.168.0.105/buero/dropdownjobs.php?branchenname="+selectedBranche;
            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    jobUrl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("jobs");
                        for (int i = 0; i<jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String job_name = jsonObject.optString("jobname");
                            jobList.add(job_name);
                            jobsAdapter = new ArrayAdapter<>(MyProfile.this, R.layout.item_file, jobList);
                            jobsAdapter.setDropDownViewResource(R.layout.item_file);
                            spinJobs.setAdapter(jobsAdapter);
                            // spinJobs2.setAdapter(jobsAdapter);
                            //spinJobs3.setAdapter(jobsAdapter);
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
        if (adapterView.getId() == R.id.spiJob) {
            erfList.clear();
            String selectedBranche = adapterView.getSelectedItem().toString();
            String erfUrl = "http://192.168.0.105/buero/dropdownerf.php?branchenname="+selectedBranche;
            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    erfUrl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("erfahrung");
                        for (int i = 0; i<jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String bezeichnung = jsonObject.optString("bezeichnung");
                            erfList.add(bezeichnung);
                            erfAdapter = new ArrayAdapter<>(MyProfile.this, R.layout.item_file, erfList);
                            erfAdapter.setDropDownViewResource(R.layout.item_file);
                            spiErfJob.setAdapter(erfAdapter);
                            // spiErfJob2.setAdapter(erfAdapter);
                            //spiErfJob3.setAdapter(erfAdapter);
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
        if (adapterView.getId() == R.id.spiJobErf && !adapterView.getSelectedItem().toString().equals("")) {
            jobList.clear();
            String selectedBranche = spinBranche.getSelectedItem().toString();
            String jobUrl = "http://192.168.0.105/buero/dropdownjobs.php?branchenname="+selectedBranche;
            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    jobUrl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("jobs");
                        for (int i = 0; i<jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String job_name = jsonObject.optString("jobname");
                            jobList.add(job_name);
                            jobsAdapter = new ArrayAdapter<>(MyProfile.this, R.layout.item_file, jobList);
                            jobsAdapter.setDropDownViewResource(R.layout.item_file);
                            spinJobs2.setAdapter(jobsAdapter);
                            // spinJobs2.setAdapter(jobsAdapter);
                            //spinJobs3.setAdapter(jobsAdapter);
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
        if (adapterView.getId() == R.id.spiJob2) {
            erfList.clear();
            String selectedBranche = adapterView.getSelectedItem().toString();
            String erfUrl = "http://192.168.0.105/buero/dropdownerf.php?branchenname="+selectedBranche;
            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    erfUrl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("erfahrung");
                        for (int i = 0; i<jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String bezeichnung = jsonObject.optString("bezeichnung");
                            erfList.add(bezeichnung);
                            erfAdapter = new ArrayAdapter<>(MyProfile.this, R.layout.item_file, erfList);
                            erfAdapter.setDropDownViewResource(R.layout.item_file);
                            spiErfJob2.setAdapter(erfAdapter);
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
        if (adapterView.getId() == R.id.spiJobErf2 && !adapterView.getSelectedItem().toString().equals("")) {
            jobList.clear();
            String selectedBranche = spinBranche.getSelectedItem().toString();
            String jobUrl = "http://192.168.0.105/buero/dropdownjobs.php?branchenname="+selectedBranche;
            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    jobUrl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("jobs");
                        for (int i = 0; i<jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String job_name = jsonObject.optString("jobname");
                            jobList.add(job_name);
                            jobsAdapter = new ArrayAdapter<>(MyProfile.this, R.layout.item_file, jobList);
                            jobsAdapter.setDropDownViewResource(R.layout.item_file);
                            spinJobs3.setAdapter(jobsAdapter);

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
        if (adapterView.getId() == R.id.spiJob3) {
            erfList.clear();
            String selectedBranche = adapterView.getSelectedItem().toString();
            String erfUrl = "http://192.168.0.105/buero/dropdownerf.php?branchenname="+selectedBranche;
            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    erfUrl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("erfahrung");
                        for (int i = 0; i<jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String bezeichnung = jsonObject.optString("bezeichnung");
                            erfList.add(bezeichnung);
                            erfAdapter = new ArrayAdapter<>(MyProfile.this, R.layout.item_file, erfList);
                            erfAdapter.setDropDownViewResource(R.layout.item_file);
                            spiErfJob3.setAdapter(erfAdapter);
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
    public void waehleJob2(View view) {
        layoutJob2.setVisibility(LinearLayout.VISIBLE);
    }
    public void waehleJob3(View view) {
        layoutJob3.setVisibility(LinearLayout.VISIBLE);
    }
   /* public void openFaehigkeiten(View view) {
        linLayFaehig.setVisibility(LinearLayout.VISIBLE);
    }*/
   /* public void closeFaehigkeiten(View view) {
        linLayFaehig.setVisibility(LinearLayout.GONE);
    }*/
}