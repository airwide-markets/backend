package za.co.airwide.marketplace.dto;

/*
               https://jquery-file-upload.appspot.com/
               { "files":[ { "thumbnailUrl":"https://jquery-file-upload.appspot.com/image%2Fjpeg/4327990346065070359/persona2.jpg.80x80.png",
                             "name":"persona2.jpg",
                             "url":"https://jquery-file-upload.appspot.com/image%2Fjpeg/4327990346065070359/persona2.jpg",
                             "deleteType":"DELETE",
                             "type":"image/jpeg",
                             "deleteUrl":"https://jquery-file-upload.appspot.com/image%2Fjpeg/4327990346065070359/persona2.jpg",
                             "size":52197
                             }
                           ]
                        }
                */

public class FileDTO {

    private String thumbnail_url;
    private String name;
    private String url;
    private String deleteType;
    private String type;
    private String deleteUrl;
    private Long size;

    public FileDTO() {
    }

    public FileDTO(String thumbnail_url,
                   String name,
                   String url,
                   String deleteType,
                   String type,
                   String deleteUrl,
                   Long size) {

        this.thumbnail_url = thumbnail_url;
        this.name = name;
        this.url = url;
        this.deleteType = deleteType;
        this.type = type;
        this.deleteUrl = deleteUrl;
        this.size = size;
    }

    public String getThumbnailUrl() {
        return thumbnail_url;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnail_url = thumbnailUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDeleteType() {
        return deleteType;
    }

    public void setDeleteType(String deleteType) {
        this.deleteType = deleteType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeleteUrl() {
        return deleteUrl;
    }

    public void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FileDTO{" +
                "thumbnail_url='" + thumbnail_url + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", deleteType='" + deleteType + '\'' +
                ", type='" + type + '\'' +
                ", deleteUrl='" + deleteUrl + '\'' +
                ", size=" + size +
                '}';
    }
}
