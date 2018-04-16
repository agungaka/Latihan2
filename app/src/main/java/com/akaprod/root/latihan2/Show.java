package com.akaprod.root.latihan2;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Show extends AppCompatActivity {

    String url = "http://candidate.wallezzdev.com/test/fruits";
    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Data> dataList;
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        mList = findViewById(R.id.main_list);

        dataList = new ArrayList<>();
        adapter = new DataAdapter(getApplicationContext(), dataList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        getData();
    }
    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest inputRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DataHereBerhasil :", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String status = json.getString("status");
                            String code = json.getString("code");
                            JSONArray dataResponse = json.optJSONArray("data");
                            Log.d("DataHereStatus :", status);
                            Log.d("DataHereCode :", code);

                            if (dataResponse != null && dataResponse.length() > 0) {
                                Log.d("DataHereSuccess :", "");
                                for (int i = 0; i < dataResponse.length(); i++) {
                                    JSONObject jsonObject = dataResponse.getJSONObject(i);

                                    Data data = new Data();
                                    data.setId_fruit(jsonObject.getInt("id_fruit"));
                                    data.setTitle(jsonObject.getString("title"));
                                    data.setImage(jsonObject.getString("image"));
                                    data.setDescription(jsonObject.getString("description"));
                                    data.setCreated_at(jsonObject.getString("created_at"));
                                    data.setUpdated_at(jsonObject.getString("updated_at"));

                                    dataList.add(data);
                                }
                                adapter.notifyDataSetChanged();
                                progressDialog.dismiss();
                            } else {
                                Log.d("DataHereError :", "");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("DataHereError2 :", e.getMessage());
                            progressDialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle response dari server ketika gagal
                        Log.d("DataHereError3 :", error.getMessage());
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }
        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("apiKey", "JDJ5JDEwJExUdVZzS0hFRjBDeFF4dWltV3hCUi4xeGpRWm4uUkYuaHczcS9zOVVtQTFqcEtWZjR2Z24u");
                return headers;
            }
        };
        // Buat antrian request pada cache android
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Tambahkan Request pada antrian request
        requestQueue.add(inputRequest);

    }

}

