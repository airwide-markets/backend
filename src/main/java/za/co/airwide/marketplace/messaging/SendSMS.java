package za.co.airwide.marketplace.messaging;

import com.google.gson.Gson;
import za.co.airwide.marketplace.dto.SmsDTO;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class SendSMS {

    public static int sendSMS(String to, String text)
            throws IOException {

        // token
        /*
        https://stackoverflow.com/questions/34976086/base64-in-java-and-php
         */
        final Base64.Encoder encoder = Base64.getEncoder();
        String token
                = encoder.encodeToString(("Worldmixapp1" + ":" + "Fantel@17").getBytes());
        // payload
        /*
        $request = json_encode(array('from' => "Worldmix", 'to' => $this->getTo(), 'text' => $this->getMessage()));
         */
        SmsDTO smsDTO = new SmsDTO("Worldmix", to, text);
        String request = new Gson().toJson(smsDTO);

        String apikey="xxxxxxx";
        String camp="17";
        URL url = new URL("https://api.infobip.com/sms/2/text/single");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setInstanceFollowRedirects(true);
        conn.setRequestProperty("Content-length", String.valueOf(request.length()));
        // authentication
        // https://www.baeldung.com/java-http-url-connection
        conn.setRequestProperty("Authorization", "Basic " + token);
        conn.setRequestProperty("Cache-Control", "no-cache");
        conn.setRequestProperty("Content-Type", "application/json");

        conn.setDoOutput(true);
        conn.setDoInput(true);

        DataOutputStream output = new DataOutputStream(conn.getOutputStream());
        output.writeBytes(request);
        output.close();

        int responseCode = conn.getResponseCode(); // 200 = HTTP_OK

        System.out.println("Response    (Code):" + responseCode);
        System.out.println("Response (Message):" + conn.getResponseMessage());

        /*
        DataInputStream input = new DataInputStream(conn.getInputStream());
        int c;
        StringBuilder resultBuf = new StringBuilder();
        while ( (c = input.read()) != -1) {
            resultBuf.append((char) c);
        }
        input.close();

        return resultBuf.toString();
        */

        return responseCode;
    }

    public static void main(String[] args) {
        try {
            sendSMS("27729745087", "Hi Farai. This is David. I have Send SMS working. " +
                    "Can we talk about the progress and outstanding items say at 9:30");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
