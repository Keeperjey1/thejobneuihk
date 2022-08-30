package de.jey.thejobneu_v1;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText pas, usr;
    EditText u_name, u_surname, u_age, u_username, u_password;
    String method;
    String name, surname, age, username, password;
    // global variables
    private List<JobOffer> list;
    private JobOfferAdapter adapter;
    private RecyclerView recyclerView;
    // RelativeLayout errorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // errorLayout = findViewById(R.id.errorLayout);
        //initialize adapter
        recyclerView = findViewById(R.id.recyclerViewJobs);
        list = new ArrayList<>();
        adapter = new JobOfferAdapter(this, list);
        //format recycle view
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("tafadhari subiri, inatafut...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        //receive data from php file
        final StringRequest request = new StringRequest("http://192.168.64.150/buero2/fetchbuero2.php",new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                try {
                    JSONArray array = new JSONArray(response);
                    for(int i = 0; i<array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        list.add(new JobOffer(object.getString("beruf"),
                                object.getString("betriebsart"),
                                object.getString("ort"),
                                object.getString("verfuegbarkeit")));

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
    public void login(View view) {
        pas = findViewById(R.id.edtPassword);
        usr = findViewById(R.id.edtUsername);

        String user = usr.getText().toString();
        String pass = pas.getText().toString();

        Login ul = new Login(this);
        ul.execute(user, pass);
        //startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
    public void register(View view) {
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


        method = "register";
        Register reg = new Register(this);
        reg.execute(new Runnable() {
            @Override
            public void run() {
                reg.doInBackground(method, name, surname, age, username, password);
            }
        });

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