package com.akaprod.root.latihan2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Input extends AppCompatActivity {

    EditText txtIsiNama;
    EditText txtIsiAlamat;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        txtIsiNama = (EditText) findViewById(R.id.txtIsiNama);
        txtIsiAlamat = (EditText) findViewById(R.id.txtIsiAlamat);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendInput();
            }
        });
    }

    //Fungsi POST request

    private void sendInput() {
        StringRequest inputRequest = new StringRequest(Request.Method.POST, "http://candidate.wallezzdev.com/test/biodata",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle response dari server ketika sukses dengan mengkonvert menjadi JSON
                        Log.d("responseBerhasil :", response);

                        try {
                            JSONObject json = new JSONObject(response);
                            //Mengambil variable status pada response
                            Boolean status = json.getBoolean("status");
                            if (status) {
                                Toast.makeText(getApplicationContext(), "Data Berhasil Di Input", Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            } else {
                                Log.d("statusError :", String.valueOf(status));
                                Toast.makeText(getApplicationContext(), "Data Gagal Di Input", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle response dari server ketika gagal
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                params.put("nama", txtIsiNama.getText().toString());
                params.put("alamat", txtIsiAlamat.getText().toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("apikey", "JDJ5JDEwJExUdVZzS0hFRjBDeFF4dWltV3hCUi4xeGpRWm4uUkYuaHczcS9zOVVtQTFqcEtWZjR2Z24u");
                return headers;
            }

        };

        // Buat antrian request pada cache android
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Tambahkan Request pada antrian request
        requestQueue.add(inputRequest);
    }
}