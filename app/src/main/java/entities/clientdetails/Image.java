package entities.clientdetails;

import java.io.Serializable;

/**
 * Created by Rypon on 10/28/2016.
 */

public class Image implements Serializable {

    private String id;
    private String path;
    private String ownerID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public Image() {

    }

    public Image(String id, String path, String ownerID) {

        this.id = id;
        this.path = path;
        this.ownerID = ownerID;
    }
}
