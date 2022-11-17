package com.example.hserban.lecture8_samplecode;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrentWeatherActivity extends AppCompatActivity {

    TextView txtWeatherDisplay, txtTemp, txtLocation , cCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);
        checkConnection();

        txtWeatherDisplay = (TextView) findViewById(R.id.textView2);
        txtLocation = (TextView)findViewById(R.id.textView3);
        txtTemp = (TextView)findViewById(R.id.textView4);
        cCode = (TextView)findViewById(R.id.textView5);
    }

    public void buttonGetWeather(View view) {
        EditText txtLat = (EditText) findViewById(R.id.txtLat);
        EditText txtLong = (EditText) findViewById(R.id.txtLong);


        new ReadWeatherJSONDataTask().execute(
                "http://api.geonames.org/findNearByWeatherJSON?lat=" +
                        txtLat.getEditableText().toString() + "&lng=" +
                        txtLong.getText().toString() + "&username=free");
    }


    public void checkConnection(){
        ConnectivityManager connectMgr =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            //fetch data

            String networkType = networkInfo.getTypeName().toString();
            Toast.makeText(this, "connected to " + networkType, Toast.LENGTH_LONG).show();
        }
        else {
            //display error
            Toast.makeText(this, "no network connection", Toast.LENGTH_LONG).show();
        }
    }


    private String readJSONData(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 2500;

        URL url = new URL(myurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        try {
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("tag", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
                conn.disconnect();
            }
        }
    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

    private class ReadWeatherJSONDataTask extends AsyncTask<String, Void, String> {

        Exception exception = null;

        protected String doInBackground(String... urls) {
            try{
                return readJSONData(urls[0]);
            }catch(IOException e){
                exception = e;
            }
            return null;
        }

        protected void onPostExecute(String result) {
            Log.d("ReadWeatherJSONDataTask", "Starting onPostEx");
            try {
                Log.d("ReadWeatherJSONDataTask", "In Try");
                JSONObject jsonObject = new JSONObject(result);
                JSONObject weatherObservationItems =
                        new JSONObject(jsonObject.getString("weatherObservation"));

                Toast.makeText(getBaseContext(),
                        weatherObservationItems.getString("temperature") +
                                " - " + weatherObservationItems.getString("stationName"),
                        Toast.LENGTH_SHORT).show();
                txtLocation.setText("LOCATION: " + weatherObservationItems.getString("stationName"));
                txtTemp.setText("TEMPERATURE: " + weatherObservationItems.getString("temperature"));
                txtWeatherDisplay.setText("FULL INFO: " + weatherObservationItems.toString());
                cCode.setText("Country Code: " + weatherObservationItems.getString("countryCode"));
            } catch (Exception e) {
                Log.d("ReadWeatherJSONDataTask", "In Try");
                Log.d("ReadWeatherJSONDataTask", e.getLocalizedMessage());
            }
            Log.d("ReadWeatherJSONDataTask", "End of onPostEx");
        }
    }

}
