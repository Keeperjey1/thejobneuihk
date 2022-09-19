package de.jey.thejobneu_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowJobOfferDetails extends AppCompatActivity {

    // global variables
    private List<JobOffer> list;
    private JobOfferDetailsAdapter adapter;
    private RecyclerView recyclerView;
    private String id5;

    public String setId5(String id) {
        id5 = id;
        return id5;
    }

    // RelativeLayout errorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_job_offer);
        // errorLayout = findViewById(R.id.errorLayout);
        //initialize adapter
        recyclerView = findViewById(R.id.recyclerViewJobOfferDetails);
        list = new ArrayList<>();
        adapter = new JobOfferDetailsAdapter(this, list);
        //format recycle view
        // recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("tafadhari subiri, inatafut...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        //receive data from php file
        //String selectedId = getIntent()
       // intent.putExtra("id", jobOffer.getId());
        final StringRequest request = new StringRequest("http://192.168.64.150/buero2/fetchbuero22.php",new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                try {
                    JSONArray array = new JSONArray(response);
                    for(int i = 0; i<array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        list.add(new JobOffer(object.getString("joboffer.id_joboffer"),
                                object.getString("beruf.bezeichnung"),
                                object.getString("betriebsart.bezeichnung"),
                                object.getString("ort.ortsname"),
                                object.getString("verfuegbarkeit.bezeichnung")));
                    }
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext()).create();
                    alertDialog.setTitle("Kuna tatizo limetokea");
                    alertDialog.setMessage("tatizo: " + e.getMessage());
                    alertDialog.show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                DisplayError("jaribu tena baadae");
            }
        });
        Volley.newRequestQueue(this).add(request);
    }
    void DisplayError(String putErroer) {
        // Snackbar.make(errorLayout, putErroer, Snackbar.LENGTH_LONG).show();
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }



    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itLogin:
                setContentView(R.layout.login);
                return true;
            case R.id.itRegister:
                setContentView(R.layout.register);
                return true;
            case R.id.itProfile:
                startActivity(new Intent(getApplicationContext(), MyProfile.class));
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