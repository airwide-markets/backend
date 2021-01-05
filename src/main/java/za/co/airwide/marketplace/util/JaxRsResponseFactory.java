package za.co.airwide.marketplace.util;

import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status;

public class JaxRsResponseFactory {

    public static Response response(boolean isSuccess, Status status, String message) {

        return Response
                .status(status)
                .header("success", isSuccess)
                .header("message",  message)
                .build();
    }

    public static Response success(String message) {
        return response(true, Status.OK, message);
    }

    public static Response success(String message, Object entity) {
        return Response
                .status(Status.OK)
                .header("success", true)
                .header("message",  message)
                .entity(entity)
                .build();
    }

    public static Response fail(Status status, String message) {
        return response(false, status, message);
    }
}
