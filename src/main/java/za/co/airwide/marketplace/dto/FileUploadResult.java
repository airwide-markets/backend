package za.co.airwide.marketplace.dto;

import org.json.JSONArray;

public class FileUploadResult {

    JSONArray files;

    public FileUploadResult() {
    }

    public FileUploadResult(JSONArray files) {
        this.files = files;
    }

    public JSONArray getFiles() {
        return files;
    }

    public void setFiles(JSONArray files) {
        this.files = files;
    }
}
