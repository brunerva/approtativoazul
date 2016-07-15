package com.example.brunovalente.rotazul;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Result extends AppCompatActivity {

    EditText eRes;
    Button bVolta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        eRes = (EditText) findViewById(R.id.eRes);
        bVolta = (Button) findViewById(R.id.bVolta);
        Intent intent = getIntent();
        final String data = intent.getStringExtra("data");

        eRes.setText(data);

        bVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent(Result.this,Admin.class);
                Result.this.startActivity(resultIntent);
            }
        });

    }
}
