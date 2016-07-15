package com.example.brunovalente.rotazul;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.brunovalente.rotazul.DB.BuyRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Principal extends AppCompatActivity {

    Button bLogout, bBuy, bActivate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Intent intent = getIntent();
        final int user_id = intent.getIntExtra("user_id", 0);
        final String name = intent.getStringExtra("name");
        final String email = intent.getStringExtra("email");
        final int CPF = intent.getIntExtra("CPF", -1);
        final int qtdTicket = intent.getIntExtra("qtdTicket", 0);


        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        EditText etUser = (EditText) findViewById(R.id.etUser);
        EditText etAge = (EditText) findViewById(R.id.etAge);
        bBuy = (Button) findViewById(R.id.bBuy);
        bLogout = (Button) findViewById(R.id.bLogout);
        bActivate = (Button) findViewById(R.id.bActivate);

        // Display user details
        etUsername.setText(email);
        etAge.setText(user_id + "");
        etUser.setText(qtdTicket + "");

        bActivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qtdTicket == 0) {
                    AlertDialog.Builder build = new AlertDialog.Builder(Principal.this);
                    build.setMessage("Necessário comprar ticket.")
                            .setNegativeButton("Tente novamente.", null)
                            .create()
                            .show();
                }else {
                    Intent actIntent = new Intent(Principal.this, Activate.class);
                    actIntent.putExtra("user_id", user_id);
                    actIntent.putExtra("name", name);
                    actIntent.putExtra("qtdTicket", qtdTicket);
                    Principal.this.startActivity(actIntent);
                }
            }
        });

        bBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListenet = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            int user = jsonResponse.getInt("user_id");
                            String nam = jsonResponse.getString("name");
                            String emai = jsonResponse.getString("email");
                            int CP = jsonResponse.getInt("CPF");
                            int qtdTicke = jsonResponse.getInt("qtdTicket");
                            if (success) {
                                Intent buyIntent = new Intent(Principal.this,Buy.class);
                                buyIntent.putExtra("user_id",user);
                                buyIntent.putExtra("name", nam);
                                buyIntent.putExtra("email", emai);
                                buyIntent.putExtra("CPF", CP);
                                buyIntent.putExtra("qtdTicket",qtdTicke);
                                Principal.this.startActivity(buyIntent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Principal.this);
                                builder.setMessage("Compra falhou.")
                                        .setNegativeButton("Tente novamente.",null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                BuyRequest buyRequest = new BuyRequest(user_id,responseListenet);
                RequestQueue queue = Volley.newRequestQueue(Principal.this);
                queue.add(buyRequest);
            }
        });

        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutIntent = new Intent(Principal.this,MainActivity.class);
                Principal.this.startActivity(logoutIntent);
            }
        });
    }
}
