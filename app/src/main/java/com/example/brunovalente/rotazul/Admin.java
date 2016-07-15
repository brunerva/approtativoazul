package com.example.brunovalente.rotazul;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.brunovalente.rotazul.DB.AdminRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Admin extends AppCompatActivity {

    Button bPesq,button;
    EditText ePlaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ePlaca = (EditText) findViewById(R.id.ePlaca);
        bPesq = (Button) findViewById(R.id.bPesq);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buttonIntente = new Intent(Admin.this,MainActivity.class);
                Admin.this.startActivity(buttonIntente);
            }
        });


        bPesq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String placa = ePlaca.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse =  new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            final int ticket_id = jsonResponse.getInt("ticket_id");
                            final int user_id = jsonResponse.getInt("user_id");
                            final String data = jsonResponse.getString("data");
                            final String placaProcurada = jsonResponse.getString("placa");

                            if (success) {
                                Intent intent = new Intent(Admin.this, Result.class);
                                intent.putExtra("data",data);
                                Admin.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Admin.this);
                                builder.setMessage("Register falhou")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                AdminRequest registerRequest = new AdminRequest(placa, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Admin.this);
                queue.add(registerRequest);
            }
        });
    }
}
