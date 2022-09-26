package de.jey.thejobneu_v1;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class SafeProfile extends AsyncTask<String, Void, String> {
    AlertDialog dialog;
    Context context;
    public SafeProfile(Context context) {
        this.context = context;
    }

    public SafeProfile() {
        super();
    }
    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String reg_url = "http://192.168.0.105/buero/registerbuero.php";
        String safeProfile = voids[0];
        if (safeProfile.equals("safeProfile")) {
            String name = voids[1];
            String surname = voids[2];
            String age = voids[3];
            String username = voids[4];
            String password = voids[5];

            try {
                URL url = new URL(reg_url);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoOutput(true);
                OutputStream ops = http.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")
                        + "&" + URLEncoder.encode("surname", "UTF-8") + "=" + URLEncoder.encode(surname, "UTF-8")
                        + "&" + URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8")
                        + "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8")
                        + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                writer.write(data);
                writer.flush();
                writer.close();
                ops.close();
                InputStream is = http.getInputStream();
                is.close();
                // InputStream wird benötigt um dia Daten zu uebergeben damit die php-Datei sie auslesen kann
                return result;
            } catch (MalformedURLException e) {
                result = e.getMessage();
            } catch (IOException e) {
                result = e.getMessage();
            }

        }
        return null;
    }
    @Override
    protected void onPreExecute()
    {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Registrierungsstatus Status");
    }
    @Override
    protected void onPostExecute(String s) {
        dialog.setMessage(s);
        dialog.show();
        if (s.equals("insert successful")) {
            dialog.setMessage(s);
            dialog.show();
            Toast.makeText(context, "insert successful!", Toast.LENGTH_LONG).show();
            // dialog.dismiss();
           /* Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);*/
        } else {
            dialog.setMessage(s);
            dialog.show();
            Toast.makeText(context, "insert failed!", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }
}
