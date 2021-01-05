package za.co.airwide.marketplace.dao;

import za.co.airwide.marketplace.dto.CategoryKeyDTO;
import za.co.airwide.marketplace.util.InternalSystemException;
import za.co.airwide.marketplace.util.ItemNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CategoryDAO {

    public static Map<CategoryKeyDTO, Integer> categories() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = MarketPlaceDAO.getConnection();

            final String CATEGORY_LIST_SQL =

                    "    SELECT id, name, request_type, listing_type                                            " +
                    "      FROM category                                                                        ";

            stmt = conn.prepareStatement(CATEGORY_LIST_SQL);
            rs = stmt.executeQuery();
            Map<CategoryKeyDTO, Integer> map = new HashMap<>();
            while (rs.next()) {
                map.put( new CategoryKeyDTO(
                                rs.getString("name"),
                                rs.getString("request_type"),
                                rs.getString("listing_type")),
                         rs.getInt("id"));
            }

            return map;
        } catch (SQLException e) {
            // TODO handle exception
            e.printStackTrace();
            return null;
        } finally {
            try {rs.close();} catch (Exception e){}
            try {stmt.close();} catch (Exception e){}
            try {conn.close();} catch (Exception e){}
        }
    }

    public static Integer findIdByName( String categoryName, String orderType )
                throws ItemNotFoundException, InternalSystemException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = MarketPlaceDAO.getConnection();

            final String CATEGORY_LIST_SQL =

                    "    SELECT id                                                                              " +
                    "      FROM category                                                                        " +
                    "     WHERE name = ?                                                                        " +
                    "       AND ( request_type = ?                                                              " +
                    "        OR   listing_type = ?  )                                                           ";

            stmt = conn.prepareStatement(CATEGORY_LIST_SQL);
            stmt.setString(1, categoryName);
            stmt.setString(2, orderType);
            stmt.setString(3, orderType);
            rs = stmt.executeQuery();
            Map<CategoryKeyDTO, Integer> map = new HashMap<>();
            if (rs.next()) {
                return rs.getInt("id");
            }

            throw new ItemNotFoundException("No category found with name: " + categoryName
                    + ", and order type: " + orderType);
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
