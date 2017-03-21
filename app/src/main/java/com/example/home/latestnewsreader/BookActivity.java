package com.example.home.latestnewsreader;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class BookActivity extends AppCompatActivity {

    EditText bookName;
    TextView ResulttextView;

    public void onClickChangeToRecommendationActivity(View view) {
        //Intent i;
        //i = new Intent(getApplicationContext(),AnonymousAuthActivity.class);

        // startActivity(i);
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(bookName.getWindowToken(), 0);
        DownloadTask task = new DownloadTask();
        task.execute("https://www.tastekid.com/api/similar?q=" + bookName.getText().toString() + "&k=259403-WhatToRe-MJ7ZTG55");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        bookName = (EditText) findViewById(R.id.bookName);
        ResulttextView = (TextView) findViewById(R.id.ResulttextView);


    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();

                }

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
               // e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Could not find book",Toast.LENGTH_LONG).show();

            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            try {

                String message = "";

               // JSONObject jsonObject = new JSONObject(result);

               message = result.toString();

               ResulttextView.setText(message);
                //String recommendations = "";

                 JSONObject jsonObject = new JSONObject(result);

                /* I have commented out all my attempts of parsing the json result from my second API call but was not successful. I am able to display the information to the user
                but its not properly formatted.
                 */

               // if (jsonObject != null) {

                  // String similarObject = jsonObject.getString("Similar");
                   // JSONArray resultsArray = similarObject.getJSONArray("Results");
                   // for (int i = 0; i < resultsArray.length(); i++) {
                      //  Log.i("This","Similar");

                      //  }
                   // }
                //String results = jsonObject.getString("Similar");
                    // Log.i("This",results);
                    //JSONArray arr = new JSONArray(results);
                   /* if (arr != null) {
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject jsonPart = arr.getJSONObject(i);
                            if (jsonPart != null) {
                                JSONArray Results = jsonPart.getJSONArray("Results");

                                if (Results != null) {
                                    for (int j = 0; j < Results.length(); j++) {
                                        JSONObject recom = Results.getJSONObject(j);
                                        if (recom != null) {
                                            String name = recom.getString("Name");
                                            recommendations += name + "\r\n";
                                        }
                                    }
                                }
                            }
                        }

                    }
                }




                    ResulttextView.setText(recommendations);*/






            } catch (JSONException e) {
                //e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Could not find book",Toast.LENGTH_LONG).show();


            }
        }
    }
}


