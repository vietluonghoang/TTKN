package com.truytimkhachno.ttkn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entities.Client;
import entities.LoaderAttributes;
import utilities.HTTPRequestSender;
import utilities.TextHelper;

public class SearchClients extends AppCompatActivity {
    private ArrayList<Client> clients;
    private HTTPRequestSender sender = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_clients);
        initDropdown();
    }

    public void searchClient(View view) {
        String url = "http://truytimkhachno.com/api/v1/search_khach";
        LoaderAttributes.initAttribute(url, Request.Method.POST,initParams(),ClientList.class);
        Intent intent = new Intent(this,Loader.class);
        startActivity(intent);
    }

    private Map<String, String> initParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("session", "22");
        params.put("name", getQryName());
        params.put("giayto", getQryIdentity());
        params.put("address", getQryAddress());
        params.put("phone", getQryPhone());
        params.put("note", getQryNote());
        return params;
    }

    private String getQryName() {
        return TextHelper.escapeString(((EditText) findViewById(R.id.txtName)).getText().toString());
    }

    private String getQryIdentity() {
        return TextHelper.escapeString(((EditText) findViewById(R.id.txtIdentity)).getText().toString());
    }

    private String getQryAddress() {
        return TextHelper.escapeString(((EditText) findViewById(R.id.txtAddress)).getText().toString());
    }

    private String getQryPhone() {
        return TextHelper.escapeString(((EditText) findViewById(R.id.txtPhone)).getText().toString());
    }

    private String getQryNote() {
        return TextHelper.escapeString(((EditText) findViewById(R.id.txtNote)).getText().toString());
    }

    public void initDropdown() {
        Spinner spinner = (Spinner) findViewById(R.id.spnrIdentityType);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spnr_identity_type, android.R.layout.simple_spinner_dropdown_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}
