package za.co.airwide.marketplace.util;

import java.io.File;

public class FileUtil {

    public static String getMimeType(String filename) {

        String mimetype = "";
        if (getSuffix(filename).equalsIgnoreCase("png")) {
            mimetype = "image/png";
        }else if(getSuffix(filename).equalsIgnoreCase("jpg")){
            mimetype = "image/jpg";
        }else if(getSuffix(filename).equalsIgnoreCase("jpeg")){
            mimetype = "image/jpeg";
        }else if(getSuffix(filename).equalsIgnoreCase("gif")){
            mimetype = "image/gif";
        }else {
            mimetype = "image/png";
        }

        return mimetype;
    }

    private static String getSuffix(String filename) {
        String suffix = "";
        int pos = filename.lastIndexOf('.');
        if (pos > 0 && pos < filename.length() - 1) {
            suffix = filename.substring(pos + 1);
        }
        return suffix;
    }
}
