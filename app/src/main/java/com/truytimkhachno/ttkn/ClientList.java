package com.truytimkhachno.ttkn;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcel;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import entities.Client;
import utilities.ClientListJsonParser;
import utilities.ClientTab;
import utilities.ImageLoader;
import utilities.Transporter;

public class ClientList extends AppCompatActivity {
    private ArrayList<ClientTab> clientTabs;
    private ArrayList<Client> clients;
    public static final String CLIENT = "client";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        String res = getIntent().getStringExtra(Loader.RESPONSE);
        clientTabs = new ArrayList<ClientTab>();
        clients = new ArrayList<Client>();
        try {
            if (res != null) {
                clients = new ClientListJsonParser(res).getClients();
                createClientUIView(clients);
            }else {
                ((LinearLayout)findViewById(R.id.noResult)).setVisibility(LinearLayout.VISIBLE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            updateAva();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createClientUIView(ArrayList<Client> clients) throws IOException, ExecutionException, InterruptedException {
        for (Client c : clients) {
            clientTabs.add(new ClientTab(c, this));
        }
        for (ClientTab tab : clientTabs) {
            ((LinearLayout) findViewById(R.id.contentWrapper)).addView(tab.getRoot());
        }
    }

    private void updateAva() throws ExecutionException, InterruptedException {
        for (ClientTab tab : clientTabs) {
            tab.updateAva();
        }
    }

    public void openClientDetails(View view){
        cancelAvaUpdate();

//        Transporter trans=new Transporter(getClient(((TextView)view.findViewById(R.id.clientId)).getText().toString()));
        Intent i = new Intent(ClientList.this, ClientDetails.class);
//        i.putExtra(CLIENT, trans);
        i.putExtra(CLIENT, getClient(((TextView)view.findViewById(R.id.clientId)).getText().toString()));
        startActivity(i);
        finish();
    }
    private Client getClient(String id){
        for(Client c: clients){
            if(c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }
    private void cancelAvaUpdate(){
        for(ClientTab tab: clientTabs){
            tab.cancelAvaUpdate();
        }
    }
}
