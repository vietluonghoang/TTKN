package utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import entities.Client;
import entities.clientdetails.Address;
import entities.clientdetails.IdentityCard;
import entities.clientdetails.Image;
import entities.clientdetails.Name;
import entities.clientdetails.Note;
import entities.clientdetails.Phone;

import static android.R.attr.data;

/**
 * Created by VietLH on 10/30/2016.
 */

public class ClientListJsonParser {
    private JSONObject jsonObj;
    private ArrayList<Client> clients;
    private String code = "";
    private String message = "";

    public ClientListJsonParser(JSONObject jsonObj) {
        this.jsonObj = jsonObj;
        clients = new ArrayList<Client>();
    }

    public ClientListJsonParser(String response) throws JSONException {
        jsonObj = new JSONObject(response);
        clients = new ArrayList<Client>();
    }

    public ArrayList<Client> getClients() throws JSONException {
        if (clients.size() < 1) {
            parser();
        }
        return clients;
    }

    public String getCode() throws JSONException {
        if (code.length() < 1) {
            parser();
        }
        return code;
    }

    public String getMessage() throws JSONException {
        if (message.length() < 1) {
            parser();
        }
        return message;
    }

    private void parser() throws JSONException {
        code = jsonObj.getString("code");
        message = jsonObj.getString("message");
        Object obj = jsonObj.get("data");
        if (obj.getClass().getName().contains("JSONArray")) {
            JSONArray data = jsonObj.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                initClientList(data.getJSONObject(i));
            }
        } else {
            if (obj.getClass().getName().contains("JSONObject")) {
                Iterator<String> iter = jsonObj.getJSONObject("data").keys();
                while (iter.hasNext()) {
                    String key = iter.next();
                    initClientList(jsonObj.getJSONObject("data").getJSONObject(key));
                }
            } else {
                throw new JSONException("Not a valid JSON object.");
            }
        }


    }

    private void initClientList(JSONObject obj) throws JSONException {
        String id = obj.getString("khach_id");
        ArrayList<Name> names = new ArrayList<Name>();
        if (!obj.isNull("name")) {
            JSONArray namesArr = obj.getJSONArray("name");
            if (namesArr.length() > 0) {
                for (int n = 0; n < namesArr.length(); n++) {
                    names.add(new Name(namesArr.getJSONObject(n).getString("name_id")
                            , namesArr.getJSONObject(n).getString("name")
                            , namesArr.getJSONObject(n).getString("chu_id")));
                }
            }
        }
        ArrayList<IdentityCard> identities = new ArrayList<IdentityCard>();
        if (!obj.isNull("giay_to")) {
            JSONArray identityArray = obj.getJSONArray("giay_to");
            if (identityArray.length() > 0) {
                for (int iden = 0; iden < identityArray.length(); iden++) {
                    identities.add(new IdentityCard(identityArray.getJSONObject(iden).getString("id")
                            , identityArray.getJSONObject(iden).getString("loai")
                            , identityArray.getJSONObject(iden).getString("maso")
                            , identityArray.getJSONObject(iden).getString("chu_id")));
                }
            }
        }
        ArrayList<Address> addresses = new ArrayList<Address>();
        if (!obj.isNull("dia_chi")) {
            JSONArray addressArray = obj.getJSONArray("dia_chi");
            if (addressArray.length() > 0) {
                for (int addr = 0; addr < addressArray.length(); addr++) {
                    addresses.add(new Address(addressArray.getJSONObject(addr).getString("diachi_id")
                            , addressArray.getJSONObject(addr).getString("diachi")
                            , addressArray.getJSONObject(addr).getString("chu_id")));
                }
            }
        }
        ArrayList<Note> notes = new ArrayList<Note>();
        if (!obj.isNull("ghichu")) {
            JSONArray noteArray = obj.getJSONArray("ghichu");
            if (noteArray.length() > 0) {
                for (int nt = 0; nt < noteArray.length(); nt++) {
                    notes.add(new Note(noteArray.getJSONObject(nt).getString("ghichu_id")
                            , noteArray.getJSONObject(nt).getString("ghichu")
                            , noteArray.getJSONObject(nt).getString("chu_id")));
                }
            }
        }
        ArrayList<Phone> phones = new ArrayList<Phone>();
        if (!obj.isNull("phone")) {
            JSONArray phoneArray = obj.getJSONArray("phone");
            if (phoneArray.length() > 0) {
                for (int p = 0; p < phoneArray.length(); p++) {
                    phones.add(new Phone(phoneArray.getJSONObject(p).getString("phone_id")
                            , phoneArray.getJSONObject(p).getString("phone")
                            , phoneArray.getJSONObject(p).getString("chu_id")));
                }
            }
        }
        ArrayList<Image> images = new ArrayList<Image>();
        if (!obj.isNull("image")) {
            JSONArray imageArray = obj.getJSONArray("image");
            if (imageArray.length() > 0) {
                for (int img = 0; img < imageArray.length(); img++) {
                    images.add(new Image(imageArray.getJSONObject(img).getString("id")
                            , imageArray.getJSONObject(img).getString("path")
                            , imageArray.getJSONObject(img).getString("chu_id")));
                }
            }
        }
        clients.add(new Client(id, names, identities, addresses, phones, notes, images));
    }
}
