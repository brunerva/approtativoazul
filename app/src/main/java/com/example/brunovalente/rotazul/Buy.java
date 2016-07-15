package com.example.brunovalente.rotazul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Buy extends AppCompatActivity {

    Button buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        buy = (Button) findViewById(R.id.buy);

        Intent intent = getIntent();
        final int user_id = intent.getIntExtra("user_id", 0);
        final String name = intent.getStringExtra("name");
        final String email = intent.getStringExtra("email");
        final int CPF = intent.getIntExtra("CPF", -1);
        final int qtdTicket = intent.getIntExtra("qtdTicket", 0);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buyIntent = new Intent(Buy.this,Principal.class);
                buyIntent.putExtra("user_id",user_id);
                buyIntent.putExtra("name", name);
                buyIntent.putExtra("email", email);
                buyIntent.putExtra("CPF", CPF);
                buyIntent.putExtra("qtdTicket",qtdTicket);
                Buy.this.startActivity(buyIntent);
            }
        });


    }
}
