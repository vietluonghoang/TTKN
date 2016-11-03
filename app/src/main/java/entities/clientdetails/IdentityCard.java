package entities.clientdetails;

import java.io.Serializable;

/**
 * Created by Rypon on 10/27/2016.
 */

public class IdentityCard implements Serializable {
    private String id;
    private String type;
    private String number;
    private String ownerID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public IdentityCard() {

    }

    public IdentityCard(String id, String type, String number, String ownerID) {

        this.id = id;
        this.type = type;
        this.number = number;
        this.ownerID = ownerID;
    }
}
