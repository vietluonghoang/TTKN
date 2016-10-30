package utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import entities.Client;
import entities.clientdetails.Address;
import entities.clientdetails.IdentityCard;
import entities.clientdetails.Image;
import entities.clientdetails.Name;
import entities.clientdetails.Note;
import entities.clientdetails.Phone;

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

    public ArrayList<Client> getClients() {
        return clients;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private void parser() throws JSONException {
        code = jsonObj.getString("code");
        message = jsonObj.getString("message");
        JSONArray data = jsonObj.getJSONArray("data");
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
}
