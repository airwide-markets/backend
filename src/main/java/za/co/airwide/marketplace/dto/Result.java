package za.co.airwide.marketplace.dto;

public class Result {

    private FileUploadData data;

    public Result(FileUploadData data) {
        this.data = data;
    }

    public FileUploadData getData() {
        return data;
    }

    public void setData(FileUploadData data) {
        this.data = data;
    }
}
