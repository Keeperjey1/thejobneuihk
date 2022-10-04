package de.jey.thejobneu_v1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShowJobOfferDetails extends AppCompatActivity{
    TextView tvId, tvBeruf, tvBetriebsart, tvOrt, tvVerfuegbarkeit;
    private String strJson, apiUrl = "http://192.168.64.150/buero2/fetchbuero22.php?selectedId=";
    private OkHttpClient client;
    private Response response;
    private RequestBody requestBody;
    private Request request;
    private ProgressDialog dialog;
    String selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_job_offer_details);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Please Wait....");
        dialog.setCanceledOnTouchOutside(true);

        tvId = findViewById(R.id.tvShowId);
        tvBeruf = findViewById(R.id.tvShowBeruf);
        tvBetriebsart = findViewById(R.id.tvShowBetriebsart);
        tvOrt = findViewById(R.id.tvShowOrt);
        tvVerfuegbarkeit = findViewById(R.id.tvShowVerfuegbarkeit);
        dialog.show();

        client = new OkHttpClient();
        new GetUserDataRequest().execute();
    }

    public class GetUserDataRequest extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
           // DbTransfer.id = "1";
            selectedId = DbTransfer.id;
            request = new Request.Builder().url(apiUrl+selectedId).build();
            try {
                response = client.newCall(request).execute();


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            try {
                strJson = response.body().string();
                updateUserData(strJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateUserData(String strJson) {


        try {
            JSONArray parent = new JSONArray(strJson);
            JSONObject child = parent.getJSONObject(0);
            String id = child.getString("joboffer.id_joboffer");
            String beruf = child.getString("beruf.bezeichnung");
            String betriebsart = child.getString("betriebsart.bezeichnung");
            String ort = child.getString("ort.ortsname");
            String verfuegbarkeit = child.getString("verfuegbarkeit.bezeichnung");

            tvId.setText(id);
            tvBeruf.setText(beruf);
            tvBetriebsart.setText(betriebsart);
            tvOrt.setText(ort);
            tvVerfuegbarkeit.setText(verfuegbarkeit);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}