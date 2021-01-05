package za.co.airwide.marketplace.dto;

/*
    getFilesFromResponse: function (data) {
        if (data.result && $.isArray(data.result.files)) {
          return data.result.files;
        }
        return [];
    },
 */

import java.io.Serializable;
import java.util.List;

public class FileUploadData implements Serializable {

    List<FileDTO> files;

    public FileUploadData() {
    }

    public FileUploadData(List<FileDTO> files) {
        this.files = files;
    }

    public List<FileDTO> getFiles() {
        return files;
    }

    public void setFiles(List<FileDTO> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "FileUploadData{" +
                "files=" + files +
                '}';
    }
}
