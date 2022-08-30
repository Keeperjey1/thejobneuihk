package de.jey.thejobneu_v1;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Login extends AsyncTask <String, Void, String> {
    AlertDialog dialog;
    Context context;

    public Login(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
       dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Login Status");
    }
<<<<<<< HEAD:app/src/main/java/de/jey/thejobneu_v1/UserLogin.java
    @Override
    protected void onPostExecute(String s) {
=======


    @Override
    protected void onPostExecute(String s) {
        dialog.setMessage(s);
        dialog.show();
        if (s.equals("login successfull...!")) {
           /* dialog.setMessage(s);
            dialog.show();*/
            Toast.makeText(context, "login successful!", Toast.LENGTH_LONG).show();
           // dialog.dismiss();
           /* Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);*/
        } else {
            dialog.setMessage(s);
            dialog.show();
            Toast.makeText(context, "login not successful!", Toast.LENGTH_LONG).show();
        }
>>>>>>> 03d472ac84d96624f205adcb1781520be13292d2:app/src/main/java/de/jey/thejobneu_v1/Login.java

    }
    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String username = voids[0];
        String password = voids[1];

<<<<<<< HEAD:app/src/main/java/de/jey/thejobneu_v1/UserLogin.java
        String connect = "http://10.0.2.2/buero/login.php";
=======
        String connect = "http://192.168.64.150/buero2/loginbuero2.php";
>>>>>>> 03d472ac84d96624f205adcb1781520be13292d2:app/src/main/java/de/jey/thejobneu_v1/Login.java
        try {
            URL url = new URL(connect);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
            String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8")
                    + "&&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();


            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "ISO-8859-1"));
            String line = "";
            while ((line = reader.readLine()) !=null) {
                result += line;
            }
            reader.close();
            ips.close();
            http.disconnect();
            return result;




        } catch (MalformedURLException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        }
        return result;

    }



}