package de.jey.thejobneu_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;

public class MyProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    String jobauswahl;
    TextView vorJobAuswahl;
    EditText profilname;
    TextView vorProfilname;
    String derProfilname;


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
    LinearLayout liFaKe;
    LinearLayout liTheBar;
    LinearLayout liTheBarAll;
    LinearLayout liZaMiAu;
    LinearLayout liProKen;
    LinearLayout liTheBarLei;
    LinearLayout liTheBarPla;

    LinearLayout liServ;
    LinearLayout liKue;
    LinearLayout liGaHe;
    LinearLayout liBetLei;
    LinearLayout liEveVer;
    LinearLayout liFuB;
    LinearLayout liDirec;
    LinearLayout liEmp;
    LinearLayout liHouKee;
    LinearLayout liBueVer;

    ImageView imFaKePlus;
    ImageView imFaKeMinus;
    ImageView imTheBarPlus;
    ImageView imTheBarMinus;
    ImageView imTheBarAllPlus;
    ImageView imTheBarAllMinus;
    ImageView imZaMiAuPlus;
    ImageView imZaMiAuMinus;
    ImageView imProKenPlus;
    ImageView imProKenMinus;
    ImageView imTheBarLeiPlus;
    ImageView imTheBarLeiMinus;
    ImageView imTheBarPlaPlus;
    ImageView imTheBarPLaMinus;
    ImageView imServPlus;
    ImageView imServMinus;
    ImageView imKuePlus;
    ImageView imKueMinus;
    ImageView imGaHePlus;
    ImageView imGaHeMinus;
    ImageView imBetLeiPlus;
    ImageView imBetLeiMinus;
    ImageView imEveVerPlus;
    ImageView imEveVerMinus;
    ImageView imFuBPlus;
    ImageView imFuBMinus;
    ImageView imDirecPlus;
    ImageView imDirecMinus;
    ImageView imEmpPlus;
    ImageView imEmpMinus;
    ImageView imHouKeePlus;
    ImageView imHouKeeMinus;
    ImageView imBueVerPlus;
    ImageView imBueVerMinus;
    Button vorAnz;
    Button prosave;

    Array showJobOffer;
    ArrayList<String> brancheList = new ArrayList<>();
    ArrayList<String> jobList = new ArrayList<>();
    ArrayList<String> erfList = new ArrayList<>();
    ArrayAdapter<String> brancheAdapter;
    ArrayAdapter<String> jobsAdapter;
    ArrayAdapter<String> erfAdapter;
    RequestQueue requestQueue;
    String urlHome = "http://192.168.0.105/buero/";
    String urlSchule = "http://192.168.64.150/buero2/";

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
        profilname = findViewById(R.id.edtProfilname);
        vorProfilname = findViewById(R.id.tvVorProfilname);


        vorJobAuswahl = findViewById(R.id.tvVorJobAus);

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
        imFaKePlus = findViewById(R.id.imvFaKePlus);
        imFaKeMinus = findViewById(R.id.imvFaKeMinus);
        imTheBarPlus = findViewById(R.id.imvTheBarPlus);
        imTheBarMinus = findViewById(R.id.imvTheBarMinus);
        imTheBarAllPlus = findViewById(R.id.imvTheBarAllPlus);
        imTheBarAllMinus = findViewById(R.id.imvTheBarAllMinus);
        imZaMiAuPlus = findViewById(R.id.imvZaMiAuPlus);
        imZaMiAuMinus = findViewById(R.id.imvZaMiAuMinus);
        imProKenPlus = findViewById(R.id.imvProKenPlus);
        imProKenMinus = findViewById(R.id.imvProKenMinus);
        imTheBarLeiPlus = findViewById(R.id.imvTheBarLeiPlus);
        imTheBarLeiMinus = findViewById(R.id.imvTheBarLeiMinus);
        imTheBarPlaPlus = findViewById(R.id.imvTheBarPlaPlus);
        imTheBarPLaMinus = findViewById(R.id.imvTheBarPlaMinus);
        imServPlus = findViewById(R.id.imvServPlus);
        imServMinus = findViewById(R.id.imvServMinus);
        imKuePlus = findViewById(R.id.imvKuePlus);
        imKueMinus = findViewById(R.id.imvKueMinus);
        imGaHePlus = findViewById(R.id.imvGaHePlus);
        imGaHeMinus = findViewById(R.id.imvGaHeMinus);
        imBetLeiPlus = findViewById(R.id.imvBetLeiPlus);
        imBetLeiMinus = findViewById(R.id.imvBetLeiMinus);
        imEveVerPlus = findViewById(R.id.imvEveVerPlus);
        imEveVerMinus = findViewById(R.id.imvEveVerMinus);
        imFuBPlus = findViewById(R.id.imvFuBPlus);
        imFuBMinus = findViewById(R.id.imvFuBMinus);
        imDirecPlus = findViewById(R.id.imvDirecPlus);
        imDirecMinus = findViewById(R.id.imvDirecMinus);
        imEmpPlus = findViewById(R.id.imvEmpPlus);
        imEmpMinus = findViewById(R.id.imvEmpMinus);
        imHouKeePlus = findViewById(R.id.imvHouKeePlus);
        imHouKeeMinus = findViewById(R.id.imvHouKeeMinus);
        imBueVerPlus = findViewById(R.id.imvBueVerPlus);
        imBueVerMinus = findViewById(R.id.imvBueVerMinus);

        liFaKe = findViewById(R.id.linFaKe);
        liTheBar = findViewById(R.id.linTheBar);
        liTheBarAll = findViewById(R.id.linTheBarAll);
        liZaMiAu = findViewById(R.id.linZaMiAu);
        liProKen = findViewById(R.id.linProKen);
        liTheBarLei = findViewById(R.id.linTheBarLei);
        liTheBarPla = findViewById(R.id.linTheBarPla);
        liServ = findViewById(R.id.linServ);
        liKue = findViewById(R.id.linKue);
        liGaHe = findViewById(R.id.linGaHe);
        liBetLei = findViewById(R.id.linBetLei);
        liEveVer = findViewById(R.id.linEveVer);
        liFuB = findViewById(R.id.linFuB);
        liDirec = findViewById(R.id.linDirec);
        liEmp = findViewById(R.id.linEmp);
        liHouKee = findViewById(R.id.linHouKee);
        liBueVer = findViewById(R.id.linBueVer);

        vorAnz = findViewById(R.id.btnVorAnz);
        prosave = findViewById(R.id.btnProSav);

        imFaKePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("FaKePl");
            }});
        imFaKeMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("FaKeMi");
            }});

        imTheBarPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("TheBarPl");
            }});
        imTheBarMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("TheBarMi");
            }});

        imTheBarAllPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("TheBarAllPl");
            }});
        imTheBarAllMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("TheBarAllMi");
            }});

        imZaMiAuPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("ZaMiAuPl");
            }});
        imZaMiAuMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("ZaMiAuMi");
            }});

        imProKenPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("ProKenPl");
            }});
        imProKenMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("ProKenMi");
            }});

        imTheBarLeiPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("TheBarLeiPL");
            }});
        imTheBarLeiMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("TheBarLeiMi");
            }});

        imTheBarPlaPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("TheBarPlaPL");
            }});
        imTheBarPLaMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("TheBarPlaMi");
            }});

        imServPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("ServPl");
            }});
        imServMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("ServMi");
            }});

        imKuePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("KuePl");
            }});
        imKueMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("KueMi");
            }});

        imGaHePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("GaHePl");
            }});
        imGaHeMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("GaHeMi");
            }});

        imBetLeiPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("BetLeiPl");
            }});
        imBetLeiMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("BetLeiMi");
            }});

        imEveVerPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("EveVerPl");
            }});
        imEveVerMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("EveVerMi");
            }});

        imFuBPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("FuBPl");
            }});
        imFuBMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("FuBMi");
            }});

        imDirecPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("DirecPl");
            }});
        imDirecMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("DirecMi");
            }});

        imEmpPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("EmpPl");
            }});
        imEmpMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("EmpMi");
            }});

        imHouKeePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("HouKeePl");
            }});
        imHouKeeMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("HouKeeMi");
            }});

        imBueVerPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("BueVerPl");
            }});
        imBueVerMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLayOpCl("BueVerMi");
            }});




        requestQueue = Volley.newRequestQueue(this);
        String brancheUrl = "http://192.168.64.150/buero2/dropdownbranche.php";
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
            String erfUrl = "http://192.168.64.150/buero2/dropdownerf.php?branchenname="+selectedBranche;

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

        if (adapterView.getId() == R.id.spiBraErf && !adapterView.getSelectedItem().toString().equals("Auswahl treffen")) {
            jobList.clear();
            String selectedBranche = spinBranche.getSelectedItem().toString();
            String jobUrl = "http://192.168.64.150/buero2/dropdownjobs2.php?branchenname="+selectedBranche;
            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    jobUrl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("jobs");
                        for (int i = 0; i<jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String job_name = jsonObject.optString("job_name");
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
            String erfUrl = "http://192.168.64.150/buero2/dropdownerf.php?branchenname="+selectedBranche;
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
        if (adapterView.getId() == R.id.spiJobErf && !adapterView.getSelectedItem().toString().equals("Auswahl treffen")) {
            jobList.clear();
            String selectedBranche = spinBranche.getSelectedItem().toString();
            String jobUrl = "http://192.168.64.150/buero2/dropdownjobs2.php?branchenname="+selectedBranche;
            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    jobUrl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("jobs");
                        for (int i = 0; i<jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String job_name = jsonObject.optString("job_name");
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
            String erfUrl = "http://192.168.64.150/buero2/dropdownerf.php?branchenname="+selectedBranche;
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
        if (adapterView.getId() == R.id.spiJobErf2 && !adapterView.getSelectedItem().toString().equals("Auswahl treffen")) {
            jobList.clear();
            String selectedBranche = spinBranche.getSelectedItem().toString();
            String jobUrl = "http://192.168.64.150/buero2/dropdownjobs2.php?branchenname="+selectedBranche;
            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    jobUrl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("jobs");
                        for (int i = 0; i<jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String job_name = jsonObject.optString("job_name");
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
            String erfUrl = "http://192.168.64.150/buero2/dropdownerf.php?branchenname="+selectedBranche;
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
    public void linLayOpCl(String linName) {
        switch (linName) {
            case "FaKePl":
                liFaKe.setVisibility(LinearLayout.VISIBLE);
                imFaKeMinus.setVisibility(ImageView.VISIBLE);
                imFaKePlus.setVisibility(ImageView.GONE);
                break;
            case "FaKeMi":
                liFaKe.setVisibility(LinearLayout.GONE);
                imFaKeMinus.setVisibility(ImageView.GONE);
                imFaKePlus.setVisibility(ImageView.VISIBLE);
                break;
            case "TheBarPl":
                liTheBar.setVisibility(LinearLayout.VISIBLE);
                imTheBarMinus.setVisibility(ImageView.VISIBLE);
                imTheBarPlus.setVisibility(ImageView.GONE);
                break;
            case "TheBarMi":
                liFaKe.setVisibility(LinearLayout.GONE);
                imTheBarMinus.setVisibility(ImageView.GONE);
                imTheBarPlus.setVisibility(ImageView.VISIBLE);
                break;
            case "TheBarAllPl":
                liTheBarAll.setVisibility(LinearLayout.VISIBLE);
                imTheBarAllMinus.setVisibility(ImageView.VISIBLE);
                imTheBarAllPlus.setVisibility(ImageView.GONE);
                break;
            case "TheBarAllMi":
                liTheBarAll.setVisibility(LinearLayout.GONE);
                imTheBarAllMinus.setVisibility(ImageView.GONE);
                imTheBarAllPlus.setVisibility(ImageView.VISIBLE);
                break;
            case "ZaMiAuPl":
                liZaMiAu.setVisibility(LinearLayout.VISIBLE);
                imZaMiAuMinus.setVisibility(ImageView.VISIBLE);
                imZaMiAuPlus.setVisibility(ImageView.GONE);
                break;
            case "ZaMiAuMi":
                liZaMiAu.setVisibility(LinearLayout.GONE);
                imZaMiAuMinus.setVisibility(ImageView.GONE);
                imZaMiAuPlus.setVisibility(ImageView.VISIBLE);
                break;
            case "ProKenPl":
                liProKen.setVisibility(LinearLayout.VISIBLE);
                imProKenMinus.setVisibility(ImageView.VISIBLE);
                imProKenPlus.setVisibility(ImageView.GONE);
                break;
            case "ProKenMi":
                liProKen.setVisibility(LinearLayout.GONE);
                imProKenMinus.setVisibility(ImageView.GONE);
                imProKenPlus.setVisibility(ImageView.VISIBLE);
                break;
            case "TheBarLeiPL":
                liTheBarLei.setVisibility(LinearLayout.VISIBLE);
                imTheBarLeiMinus.setVisibility(ImageView.VISIBLE);
                imTheBarLeiPlus.setVisibility(ImageView.GONE);
                break;
            case "TheBarLeiMi":
                liTheBarLei.setVisibility(LinearLayout.GONE);
                imTheBarLeiMinus.setVisibility(ImageView.GONE);
                imTheBarLeiPlus.setVisibility(ImageView.VISIBLE);
                break;
            case "TheBarPlaPL":
                liTheBarPla.setVisibility(LinearLayout.VISIBLE);
                imTheBarPLaMinus.setVisibility(ImageView.VISIBLE);
                imTheBarPlaPlus.setVisibility(ImageView.GONE);
                break;
            case "TheBarPlaMi":
                liTheBarPla.setVisibility(LinearLayout.GONE);
                imTheBarPLaMinus.setVisibility(ImageView.GONE);
                imTheBarPlaPlus.setVisibility(ImageView.VISIBLE);
                break;
            case "ServPl":
                liServ.setVisibility(LinearLayout.VISIBLE);
                imServMinus.setVisibility(ImageView.VISIBLE);
                imServPlus.setVisibility(ImageView.GONE);
                break;
            case "ServMi":
                liServ.setVisibility(LinearLayout.GONE);
                imServMinus.setVisibility(ImageView.GONE);
                imServPlus.setVisibility(ImageView.VISIBLE);
                break;
            case "KuePl":
                liKue.setVisibility(LinearLayout.VISIBLE);
                imKueMinus.setVisibility(ImageView.VISIBLE);
                imKuePlus.setVisibility(ImageView.GONE);
                break;
            case "KueMi":
                liKue.setVisibility(LinearLayout.GONE);
                imKueMinus.setVisibility(ImageView.GONE);
                imKuePlus.setVisibility(ImageView.VISIBLE);
                break;
            case "GaHePl":
                liGaHe.setVisibility(LinearLayout.VISIBLE);
                imGaHeMinus.setVisibility(ImageView.VISIBLE);
                imGaHePlus.setVisibility(ImageView.GONE);
                break;
            case "GaHeMi":
                liGaHe.setVisibility(LinearLayout.GONE);
                imGaHeMinus.setVisibility(ImageView.GONE);
                imGaHePlus.setVisibility(ImageView.VISIBLE);
                break;
            case "BetLeiPl":
                liBetLei.setVisibility(LinearLayout.VISIBLE);
                imBetLeiMinus.setVisibility(ImageView.VISIBLE);
                imBetLeiPlus.setVisibility(ImageView.GONE);
                break;
            case "BetLeiMi":
                liBetLei.setVisibility(LinearLayout.GONE);
                imBetLeiMinus.setVisibility(ImageView.GONE);
                imBetLeiPlus.setVisibility(ImageView.VISIBLE);
                break;
            case "EveVerPl":
                liEveVer.setVisibility(LinearLayout.VISIBLE);
                imEveVerMinus.setVisibility(ImageView.VISIBLE);
                imEveVerPlus.setVisibility(ImageView.GONE);
                break;
            case "EveVerMi":
                liEveVer.setVisibility(LinearLayout.GONE);
                imEveVerMinus.setVisibility(ImageView.GONE);
                imEveVerPlus.setVisibility(ImageView.VISIBLE);
                break;
            case "FuBPl":
                liFuB.setVisibility(LinearLayout.VISIBLE);
                imFuBMinus.setVisibility(ImageView.VISIBLE);
                imFuBPlus.setVisibility(ImageView.GONE);
                break;
            case "FuBMi":
                liFuB.setVisibility(LinearLayout.GONE);
                imFuBMinus.setVisibility(ImageView.GONE);
                imFuBPlus.setVisibility(ImageView.VISIBLE);
                break;
            case "DirecPl":
                liDirec.setVisibility(LinearLayout.VISIBLE);
                imDirecMinus.setVisibility(ImageView.VISIBLE);
                imDirecPlus.setVisibility(ImageView.GONE);
                break;
            case "DirecMi":
                liDirec.setVisibility(LinearLayout.GONE);
                imDirecMinus.setVisibility(ImageView.GONE);
                imDirecPlus.setVisibility(ImageView.VISIBLE);
                break;
            case "EmpPl":
                liEmp.setVisibility(LinearLayout.VISIBLE);
                imEmpMinus.setVisibility(ImageView.VISIBLE);
                imEmpPlus.setVisibility(ImageView.GONE);
                break;
            case "EmpMi":
                liEmp.setVisibility(LinearLayout.GONE);
                imEmpMinus.setVisibility(ImageView.GONE);
                imEmpPlus.setVisibility(ImageView.VISIBLE);
                break;
            case "HouKeePl":
                liHouKee.setVisibility(LinearLayout.VISIBLE);
                imHouKeeMinus.setVisibility(ImageView.VISIBLE);
                imHouKeePlus.setVisibility(ImageView.GONE);
                break;
            case "HouKeeMi":
                liHouKee.setVisibility(LinearLayout.GONE);
                imHouKeeMinus.setVisibility(ImageView.GONE);
                imHouKeePlus.setVisibility(ImageView.VISIBLE);
                break;
            case "BueVerPl":
                liBueVer.setVisibility(LinearLayout.VISIBLE);
                imBueVerMinus.setVisibility(ImageView.VISIBLE);
                imBueVerPlus.setVisibility(ImageView.GONE);
                break;
            case "BueVerMi":
                liBueVer.setVisibility(LinearLayout.GONE);
                imBueVerMinus.setVisibility(ImageView.GONE);
                imBueVerPlus.setVisibility(ImageView.VISIBLE);
                break;
        }
    }




    public void waehleJob2(View view) {
        layoutJob2.setVisibility(LinearLayout.VISIBLE);
    }
    public void waehleJob3(View view) {
        layoutJob3.setVisibility(LinearLayout.VISIBLE);
    }
    public void vorschauAnzeigen(View view) {
        //setContentView(R.layout.profilvorschau);
       Intent intent = new Intent(this, ProfilVorschau.class);
       intent.putExtra("vorProfilname", profilname.getText().toString());
       intent.putExtra("firstJob", spinJobs.getSelectedItem().toString());
      startActivity(intent);
      //jobauswahl = spinJobs.getSelectedItem().toString();

       //vorJobAuswahl.setText(spinJobs.getSelectedItem().toString());


        //profilname = pickupData.getString("vorProfilname");
       //vorProfilname.setText(derProfilname);
      // intent.putExtra("jobauswahl", spinJobs.getSelectedItem().toString());




    }

   /* public void openFaehigkeiten(View view) {
        linLayFaehig.setVisibility(LinearLayout.VISIBLE);
    }*/
   /* public void closeFaehigkeiten(View view) {
        linLayFaehig.setVisibility(LinearLayout.GONE);
    }*/
}