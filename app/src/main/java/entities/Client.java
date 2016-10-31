package entities;

import java.util.ArrayList;

import entities.clientdetails.Address;
import entities.clientdetails.IdentityCard;
import entities.clientdetails.Image;
import entities.clientdetails.Name;
import entities.clientdetails.Note;
import entities.clientdetails.Phone;

/**
 * Created by Rypon on 10/27/2016.
 */

public class Client {
    private String id;
    private ArrayList<Name> names;
    private ArrayList<IdentityCard> identities;
    private ArrayList<Address> address;
    private ArrayList<Phone> phone;
    private ArrayList<Note> note;
    private ArrayList<Image> images;

    public Client() {
    }

    public Client(String id) {
        this.id = id;
    }

    public Client(String id, ArrayList<Name> names, ArrayList<IdentityCard> identities, ArrayList<Address> address, ArrayList<Phone> phone, ArrayList<Note> note, ArrayList<Image> images) {
        this.id = id;
        this.names = names;
        this.identities = identities;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Name> getNames() {
        return names;
    }

    public void setNames(ArrayList<Name> names) {
        this.names = names;
    }

    public ArrayList<IdentityCard> getIdentities() {
        return identities;
    }

    public void setIdentities(ArrayList<IdentityCard> identities) {
        this.identities = identities;
    }

    public ArrayList<Address> getAddress() {
        return address;
    }

    public void setAddress(ArrayList<Address> address) {
        this.address = address;
    }

    public ArrayList<Phone> getPhone() {
        return phone;
    }

    public void setPhone(ArrayList<Phone> phone) {
        this.phone = phone;
    }

    public ArrayList<Note> getNote() {
        return note;
    }

    public void setNote(ArrayList<Note> note) {
        this.note = note;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        String nm = "";
        for(Name n:getNames()){
            nm+=n.getName()+", ";
        }

        return getId() + " - " + nm;
    }
}
