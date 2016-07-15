package com.example.brunovalente.rotazul;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.brunovalente.rotazul.DB.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        final Button bLogin = (Button) findViewById(R.id.bSignIn);

        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, Register.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            int user_id = jsonResponse.getInt("user_id");
                            String name = jsonResponse.getString("name");
                            int CPF = jsonResponse.getInt("CPF");
                            int qtdTicket = jsonResponse.getInt("qtdTicket");
                            int tipo_user = jsonResponse.getInt("tipo_user");
                            if (success) {
                                if (tipo_user == 0) {
                                    Intent principalIntent = new Intent(MainActivity.this, Principal.class);
                                    principalIntent.putExtra("user_id", user_id);
                                    principalIntent.putExtra("name", name);
                                    principalIntent.putExtra("CPF", CPF);
                                    principalIntent.putExtra("email", email);
                                    principalIntent.putExtra("qtdTicket", qtdTicket);
                                    MainActivity.this.startActivity(principalIntent);
                                } else {
                                    Intent principalIntent = new Intent(MainActivity.this,Admin.class);
                                    MainActivity.this.startActivity(principalIntent);
                                }
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Login falhou")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}