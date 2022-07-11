package de.jey.thejobneu_v1;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Array;

public class Aktualisierene extends AsyncTask <Void, Void, Void> {


   /* private Array getData() {
        Array testarray;
        String conn = "http://10.0.2.2/cars.php";
    }
*/

    protected String doInBackground() {
            String result = "";
        String conn = "http://10.0.2.2/cars.php";
        try {
            URL url = new URL(conn);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setDoInput(true);
            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "ISO-8859-1"));
            String line = "";
            while ((line = reader.readLine()) !=null) {
                result += line;
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}
