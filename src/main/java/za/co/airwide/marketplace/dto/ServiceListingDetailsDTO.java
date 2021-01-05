package za.co.airwide.marketplace.dto;

public final class ServiceListingDetailsDTO
    extends  ListingDetailsDTO {

    private String name;      // category id: 1
    private String details;   // category id: 1

    public ServiceListingDetailsDTO() {
    }

    public ServiceListingDetailsDTO (
                        long id,
                        int parent,
                        String type,
                        String name,
                        String details) {
        super(id, parent, type);
        this.name = name;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
