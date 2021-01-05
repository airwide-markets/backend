package za.co.airwide.marketplace.dto;

public abstract class ListingDetailsDTO {

    protected long id;
    protected int parent;
    protected String type;

    public ListingDetailsDTO() {
    }

    public ListingDetailsDTO( long id,
                              int parent,
                              String type) {
        this.id = id;
        this.parent = parent;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
