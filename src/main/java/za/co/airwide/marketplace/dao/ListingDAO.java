package za.co.airwide.marketplace.dao;

import za.co.airwide.marketplace.dto.*;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.OK;

public class ListingDAO {

/*
    public Response listing(RequestDTO request) {

        List<ListingDTO> listing = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = MarketPlaceDAO.getConnection();

            // requests
            final String REQUEST_LIST_SQL =

                    "    SELECT r.id, r.user,                                                                       " +
                    "               c.id AS category_id, c.name AS category_name,                                   " +
                    "               c.description AS category_description, c.icon AS category_icon,                 " +
                    "               r.name,                                                                         " +
                    "               t.id AS request_type_id, t.name AS request_type_name,                           " +
                    "               t.description AS request_type_description,                                      " +
                    "               r.added AS date_created, post                                                   " +
                    "      FROM request r                                                                           " +
                    "      JOIN categories c                                                                        " +
                    "        ON ( r.category = c.id )                                                               " +
                    "      JOIN request_type t                                                                      " +
                    "        ON ( r.type = t.id )                                                                   " +
                    "     WHERE r.user = ?                                                                          " +
                    "     ORDER BY r.added DESC                                                                     ";
*/
/*
CREATE TABLE request (
  id bigint NOT NULL DEFAULT nextval('request_id_seq'),
  user bigint NOT NULL,
  category int,
  name varchar(200),
  type int, -- '1','2',
  added timestamp,
  post int, -- '0','1'
)
 */
        /*
            final String CREATE_REQUEST_SQL =
                    "   INSERT INTO request (user, category, name, type, added, post )                           " +
                    "   VALUES ( ?, ?, ?, ?, ?, ? )                                                              ";

            stmt = conn.prepareStatement( CREATE_REQUEST_SQL );
            stmt.setLong(1, userId);
            stmt.setInt(2, userId);
            stmt.setString(3, userId);
            stmt.setInt(4, userId);
            stmt.setTimestamp(5, userId);
            stmt.setInt(6, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                // get listing details
                ListingDetailsDTO listingDetails =
                        listingDetails(
                                rs.getInt("category_id"),
                                rs.getLong("id"),
                                conn );
                listing.add(
                        new ListingDTO(
                                "request",
                                rs.getLong("id"),
                                rs.getLong("user"),
                                new CategoryDTO(
                                        rs.getInt("category_id"),
                                        rs.getString("category_name"),
                                        rs.getString("category_description"),
                                        rs.getString("category_icon")),
                                rs.getString("name"),
                                new RequestTypeDTO(
                                        rs.getInt("request_type_id"),
                                        rs.getString("request_type_name"),
                                        rs.getString("request_type_description")),
                                listingDetails,
                                new Date(rs.getTimestamp("date_created").getTime()),
                                rs.getInt("post")));
            }
            return Response
                    .status(OK)
                    .header("success", true)
                    .header("message", "Listing read.")
                    .entity(listing)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .header("message", "Service Listing Failed. Error: " + e.getMessage())
                    .build();
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }
    */
}
