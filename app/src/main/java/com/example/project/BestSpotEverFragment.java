package com.example.project;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import javax.net.ssl.HttpsURLConnection;

public class BestSpotEverFragment extends Fragment {

    HttpsURLConnection myConnection;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_all_spots, container, false);

        //create URL
        URL api = null;
        try {
            api = new URL("https://www.worldtides.info/api?heights&lat=33.768321&lon=-118.195617&key=044f92f1-f49a-4a68-81c2-8f9ea14e32d6");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Create connection
        try {
            myConnection =
                    (HttpsURLConnection) api.openConnection();
            myConnection.setRequestProperty("Accept",
                    "application/vnd.github.v3+json");
            if (myConnection.getResponseCode() == 200) {
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader =
                        new InputStreamReader(responseBody, "UTF-8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);

                jsonReader.beginObject(); // Start processing the JSON object
                while (jsonReader.hasNext()) { // Loop through all keys
                    String key = jsonReader.nextName(); // Fetch the next key
                    if (key.equals("organization_url")) { // Check if desired key
                        // Fetch the value as a String
                        String value = jsonReader.nextString();

                        ListView listView = (ListView) v.findViewById(R.id.bestlist);

                        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                                getActivity(),
                                android.R.layout.simple_list_item_1,
                                Collections.singletonList(value)
                        );


                        break; // Break out of the loop
                    } else {
                        jsonReader.skipValue(); // Skip values of other keys
                    }
                }
                jsonReader.close();
                myConnection.disconnect();


            } else {
                // Error handling code goes here
            }
        } catch (IOException e) {
            e.printStackTrace();
        }




        return v;
    }
}
