package com.truytimkhachno.ttkn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

public class ClientList extends AppCompatActivity {
    private ArrayList<ClientTab> clientTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        String res = getIntent().getStringExtra(Loader.RESPONSE);
        try {
            ClientListJsonParser parser = new ClientListJsonParser(res);
            createClientUIView(parser.getClients());
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        updateAva();
    }

    private void createClientUIView(ArrayList<Client> clients) throws IOException, ExecutionException, InterruptedException {
        clientTabs=new ArrayList<ClientTab>();
        for (Client c : clients) {
            clientTabs.add(new ClientTab(c,this));
        }
        for(ClientTab tab:clientTabs){
            ((LinearLayout) findViewById(R.id.contentWrapper)).addView(tab.getRoot());
        }
    }

    private void updateAva(){
        for(ClientTab tab:clientTabs){
            tab.updateAva();
        }
    }
}
