package com.example.brunovalente.rotazul;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.brunovalente.rotazul.DB.ActivateRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Activate extends AppCompatActivity {

    EditText etPlaca;
    Button bAtiva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate);

        etPlaca = (EditText) findViewById(R.id.etPlaca);
        bAtiva = (Button) findViewById(R.id.bAtiva);

        Intent intent = getIntent();
        final int user_id = intent.getIntExtra("user_id",0);
        final int qtdTicket = intent.getIntExtra("qtdTicket",0);
        final String name = intent.getStringExtra("name");

        bAtiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String placa = etPlaca.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            int user = jsonResponse.getInt("user_id");
                            String nam = jsonResponse.getString("name");
                            String email = jsonResponse.getString("email");
                            long CPF = jsonResponse.getLong("CPF");
                            int qtdTicke = jsonResponse.getInt("qtdTicket");
                            int tipo_user = jsonResponse.getInt("tipo_user");
                            if (success) {
                                Intent ativaIntente = new Intent(Activate.this,Principal.class);
                                ativaIntente.putExtra("user_id",user);
                                ativaIntente.putExtra("name",nam);
                                ativaIntente.putExtra("email",email);
                                ativaIntente.putExtra("CPF",CPF);
                                ativaIntente.putExtra("qtdTicket",qtdTicke);
                                Activate.this.startActivity(ativaIntente);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Activate.this);
                                builder.setMessage("Falhou ativar.")
                                        .setNegativeButton("Tente novamente.",null)
                                        .create()
                                        .show();
                            }
                        } catch(JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                ActivateRequest activateRequest = new ActivateRequest(placa, user_id,responseListener);
                RequestQueue queue = Volley.newRequestQueue(Activate.this);
                queue.add(activateRequest);
            }
        });
    }
}
