package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity3 extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();

        String idCategoria = intent.getStringExtra("ID_CATEGORIA");
        String idCategoria2 = intent.getStringExtra("ID_CATEGORIA2");

        String url = "https://uealecpeterson.net/turismo/lugar_turistico/json_getlistadoGridLT/" + idCategoria + "/" + idCategoria2;

        Map<String, String> datos = new HashMap<>();
        WebService ws = new WebService(url, datos, MainActivity3.this, MainActivity3.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtResp = (TextView) findViewById(R.id.txtLugares);
        String lstNombres = "";
        JSONObject jsonResponse = new JSONObject(result);
        JSONArray JSONlista = jsonResponse.getJSONArray("data");
        for (int i = 0; i < JSONlista.length(); i++) {
            JSONObject lugar = JSONlista.getJSONObject(i);
            lstNombres += "\n" +
                    lugar.getString("id") + " - " +
                    lugar.getString("subcategoria") + " - " +
                    lugar.getString("nombre_lugar").toString();
        }
        txtResp.setText("Lista de lugares:" + lstNombres);
    }
}