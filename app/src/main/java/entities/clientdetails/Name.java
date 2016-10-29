package entities.clientdetails;

/**
 * Created by Rypon on 10/28/2016.
 */

public class Name {
    private String id;
    private String name;
    private String ownerID;

    public Name() {
    }

    public Name(String id, String name, String ownerID) {

        this.id = id;
        this.name = name;
        this.ownerID = ownerID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }
}
