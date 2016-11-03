package com.truytimkhachno.ttkn;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import entities.Client;
import entities.clientdetails.Name;
import utilities.Transporter;

public class ClientDetails extends AppCompatActivity {
private Client client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);
        client = (Client) getIntent().getSerializableExtra(ClientList.CLIENT);
        initUI();
    }

    private void initUI(){
        ((TextView)findViewById(R.id.clientId)).setText(client.getId());
        initNameDetails();
    }
    private void initNameDetails(){
        for(Name n: client.getNames()){
            TextView tv = new TextView(this);
            tv.setTextColor(Color.WHITE);
            tv.setText(n.getName());
            ((LinearLayout)findViewById(R.id.names)).addView(tv);
        }
    }
}
