package com.truytimkhachno.ttkn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entities.Client;
import entities.clientdetails.Address;
import entities.clientdetails.IdentityCard;
import entities.clientdetails.Image;
import entities.clientdetails.Name;
import entities.clientdetails.Note;
import entities.clientdetails.Phone;

public class SearchClients extends AppCompatActivity {
    private ArrayList<Client> clients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_clients);
    }

    public void searchClient(View view) {
        String url = "http://truytimkhachno.com/api/v1/search_khach";
        volleyPostRequest(url);
    }

    private void volleyRequest(String url) {
        final TextView txtResult = (TextView) findViewById(R.id.txtResult);
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest strRq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                txtResult.setText("Response: " + response.substring(0, 100));
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                txtResult.setText("Error: " + error.getMessage());
            }
        });

        queue.add(strRq);
    }

    private void volleyPostRequest(String url) {
        final TextView txtResult = (TextView) findViewById(R.id.txtResult);
        RequestQueue queue = Volley.newRequestQueue(this);


        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String code = "";
                        String message = "";
                        clients = new ArrayList<Client>();
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            code = jsonObj.getString("code");
                            message = jsonObj.getString("message");
                            JSONArray data = jsonObj.getJSONArray("data");
                            if (data.length() > 0) {
                                for (int i = 0; i < data.length(); i++) {
                                    String id = data.getJSONObject(i).getString("khach_id");
                                    JSONArray namesArr = data.getJSONObject(i).getJSONArray("name");
                                    ArrayList<Name> names = new ArrayList<Name>();
                                    if (namesArr.length() > 0) {
                                        for (int n = 0; n < namesArr.length(); n++) {
                                            names.add(new Name(namesArr.getJSONObject(n).getString("name_id")
                                                    , namesArr.getJSONObject(n).getString("name")
                                                    , namesArr.getJSONObject(n).getString("chu_id")));
                                        }
                                    }
                                    JSONArray identityArray = data.getJSONObject(i).getJSONArray("giay_to");
                                    ArrayList<IdentityCard> identities = new ArrayList<IdentityCard>();
                                    if (namesArr.length() > 0) {
                                        for (int iden = 0; iden < identityArray.length(); iden++) {
                                            identities.add(new IdentityCard(identityArray.getJSONObject(iden).getString("id")
                                                    , identityArray.getJSONObject(iden).getString("loai")
                                                    , identityArray.getJSONObject(iden).getString("maso")
                                                    , identityArray.getJSONObject(iden).getString("chu_id")));
                                        }
                                    }
                                    JSONArray addressArray = data.getJSONObject(i).getJSONArray("dia_chi");
                                    ArrayList<Address> addresses = new ArrayList<Address>();
                                    if (addressArray.length() > 0) {
                                        for (int addr = 0; addr < addressArray.length(); addr++) {
                                            addresses.add(new Address(addressArray.getJSONObject(addr).getString("diachi_id")
                                                    , addressArray.getJSONObject(addr).getString("diachi")
                                                    , addressArray.getJSONObject(addr).getString("chu_id")));
                                        }
                                    }
                                    JSONArray noteArray = data.getJSONObject(i).getJSONArray("ghichu");
                                    ArrayList<Note> notes = new ArrayList<Note>();
                                    if (noteArray.length() > 0) {
                                        for (int nt = 0; nt < noteArray.length(); nt++) {
                                            notes.add(new Note(noteArray.getJSONObject(nt).getString("ghichu_id")
                                                    , noteArray.getJSONObject(nt).getString("ghichu")
                                                    , noteArray.getJSONObject(nt).getString("chu_id")));
                                        }
                                    }
                                    JSONArray phoneArray = data.getJSONObject(i).getJSONArray("phone");
                                    ArrayList<Phone> phones = new ArrayList<Phone>();
                                    if (phoneArray.length() > 0) {
                                        for (int p = 0; p < phoneArray.length(); p++) {
                                            phones.add(new Phone(phoneArray.getJSONObject(p).getString("phone_id")
                                                    , phoneArray.getJSONObject(p).getString("phone")
                                                    , phoneArray.getJSONObject(p).getString("chu_id")));
                                        }
                                    }
                                    JSONArray imageArray = data.getJSONObject(i).getJSONArray("image");
                                    ArrayList<Image> images = new ArrayList<Image>();
                                    if (imageArray.length() > 0) {
                                        for (int img = 0; img < imageArray.length(); img++) {
                                            images.add(new Image(imageArray.getJSONObject(img).getString("id")
                                                    , imageArray.getJSONObject(img).getString("path")
                                                    , imageArray.getJSONObject(img).getString("chu_id")));
                                        }
                                    }
                                    clients.add(new Client(id, names, identities, addresses, phones, notes, images));
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(clients.size()>0) {
                            Client c = clients.get(0);
                            String results = code + " - " + message + "\n";
                            String name = "";
                            for (Name n : c.getNames()) {
                                name += n.getName() + ", ";
                            }
                            String iden = "";
                            for (IdentityCard ide : c.getIdentities()) {
                                iden += ide.getType() + ": " + ide.getNumber() + ", ";
                            }
                            String adr = "";
                            for (Address ad : c.getAddress()) {
                                adr += ad.getAddress() + ", ";
                            }
                            String phn = "";
                            for (Phone ph : c.getPhone()) {
                                phn += ph.getPhone() + ", ";
                            }
                            String not = "";
                            for (Note nt : c.getNote()) {
                                not += nt.getNote() + ", ";
                            }
                            String imag = "";
                            for (Image im : c.getImages()) {
                                imag += im.getPath() + ", ";
                            }
                            txtResult.setText(results + " - " + name + " - " + iden + " - " + adr + " - " + phn + " - " + not + " - " + imag);
                        }else {
                            txtResult.setText("No record found");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtResult.setText("Response: " + error.getMessage());
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("session", "22");
                params.put("name", getQryName());
                params.put("giayto", "");
                params.put("address", "");
                params.put("phone", "");
                params.put("note", "");
                return params;
            }
        };
        queue.add(postRequest);
    }

    private String getQryName(){
        return escapeString(((EditText)findViewById(R.id.txtName)).getText().toString());
    }

    public String escapeString(String str){
        return str;
    }
}
