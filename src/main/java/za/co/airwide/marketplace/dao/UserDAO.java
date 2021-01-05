package za.co.airwide.marketplace.dao;

import za.co.airwide.marketplace.dto.UserDTO;
import za.co.airwide.marketplace.util.InternalSystemException;
import za.co.airwide.marketplace.util.ItemNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public static UserDTO findById( Long userId )
                throws ItemNotFoundException, InternalSystemException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = MarketPlaceDAO.getConnection();

            final String CATEGORY_LIST_SQL =

                    "    SELECT id, msisdn, name, surname, email                                                " +
                    "      FROM users                                                                           " +
                    "     WHERE id = ?                                                                          " ;

            stmt = conn.prepareStatement(CATEGORY_LIST_SQL);
            stmt.setLong(1, userId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new UserDTO( rs.getLong("id"),
                                    rs.getString("msisdn"),
                                    rs.getString("name"),
                                    rs.getString("surname"),
                                    rs.getString("email"));
            }

            throw new ItemNotFoundException("No user found with ID: " + userId);
        } catch (SQLException e ) {
            e.printStackTrace();
            throw new InternalSystemException("System error occurred. Please try again.");
        } finally {
            try {rs.close();} catch (Exception e){}
            try {stmt.close();} catch (Exception e){}
            try {conn.close();} catch (Exception e){}
        }
    }
}
