package entities.clientdetails;

/**
 * Created by Rypon on 10/28/2016.
 */

public class Phone {

    private String id;
    private String phone;
    private String ownerID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public Phone(String id, String phone, String ownerID) {

        this.id = id;
        this.phone = phone;
        this.ownerID = ownerID;
    }

    public Phone() {

    }
}
