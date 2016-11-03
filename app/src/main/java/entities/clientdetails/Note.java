package entities.clientdetails;

import java.io.Serializable;

/**
 * Created by Rypon on 10/28/2016.
 */

public class Note implements Serializable {
    private String id;
    private String note;
    private String ownerID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public Note() {

    }

    public Note(String id, String note, String ownerID) {

        this.id = id;
        this.note = note;
        this.ownerID = ownerID;
    }
}
