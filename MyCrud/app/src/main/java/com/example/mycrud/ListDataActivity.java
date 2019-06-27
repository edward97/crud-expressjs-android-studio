package com.example.mycrud;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListDataActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    ListView list_data;
    String json_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        list_data = (ListView) findViewById(R.id.list_template);
        list_data.setOnItemClickListener(this);
        getJSON();
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading =ProgressDialog.show(ListDataActivity.this, "Fetch data", "Please wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                json_string = s;
                showData();
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                String s =requestHandler.sendGetRequest(Konfigurasi.URL_GET_ALL_DATA);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showData() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        try {
            jsonObject = new JSONObject(json_string);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_ARRAY);

            for (int i=0; i<result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);

                String id = jo.getString(Konfigurasi.TAG_ID);
                String email = jo.getString(Konfigurasi.TAG_EMAIL);

                HashMap<String, String> data = new HashMap<>();
                data.put(Konfigurasi.TAG_ID, id);
                data.put(Konfigurasi.TAG_EMAIL, email);
                list.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
            ListDataActivity.this, list,R.layout.list_data,
                new String[] {Konfigurasi.TAG_ID, Konfigurasi.TAG_EMAIL},
                new int[] {R.id.list_id, R.id.list_email}
        );
        list_data.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
        String s = map.get(Konfigurasi.TAG_EMAIL);

        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
