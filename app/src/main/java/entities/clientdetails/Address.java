package entities.clientdetails;

/**
 * Created by Rypon on 10/28/2016.
 */

public class Address {
    private String id;
    private String address;
    private String ownerID;

    public Address() {
    }

    public Address(String id, String address, String ownerID) {

        this.id = id;
        this.address = address;
        this.ownerID = ownerID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }
}
