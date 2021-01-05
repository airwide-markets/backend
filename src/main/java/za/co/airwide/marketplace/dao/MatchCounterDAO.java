package za.co.airwide.marketplace.dao;

import za.co.airwide.marketplace.dto.CategoryItemDTO;
import za.co.airwide.marketplace.dto.CategoryKeyDTO;
import za.co.airwide.marketplace.util.InternalSystemException;
import za.co.airwide.marketplace.util.ItemNotFoundException;

import javax.ws.rs.PathParam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchCounterDAO {

    public static Map<String, Long> categoryCount()
            throws InternalSystemException {

        Map<String, Long> result
                = new HashMap<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = MarketPlaceDAO.getConnection();

            final String LISTING_COUNTER_SQL =

                    "   SELECT c.name as category_name , count(*) as category_count                         " +
                    "     FROM search_fields s                                                              " +
                    "     JOIN category c                                                                   " +
                    "       ON (s.category_id = c.id)                                                       " +
                    "    GROUP BY c.name                                                                    ";

            stmt = conn.prepareStatement(LISTING_COUNTER_SQL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                result.put(rs.getString("category_name"), rs.getLong("category_count"));
            }

            return result;

        } catch (SQLException e) {
            // TODO handle exception
            e.printStackTrace();
            throw new InternalSystemException("Error calculating matched. Please try again.");
        } finally {
            try {rs.close();} catch (Exception e){}
            try {stmt.close();} catch (Exception e){}
            try {conn.close();} catch (Exception e){}
        }
    }

    public static List<CategoryItemDTO> categoryItems( String categoryName,
                                                       Integer pageNumber,
                                                       Integer itemCount )
            throws InternalSystemException {

        List<CategoryItemDTO> result
                = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = MarketPlaceDAO.getConnection();

            final String LISTING_COUNTER_SQL =

                    "   SELECT s.name, c.name as category_name, MIN(s.id) AS id                             " +
                    "     FROM search_fields s                                                              " +
                    "     JOIN category c                                                                   " +
                    "       ON (s.category_id = c.id)                                                       " +
                    "    WHERE c.name = ?                                                                   " +
                    "    GROUP BY s.name, category_name                                                     " +
                    "    ORDER BY s.name                                                                    " +
                    "   OFFSET ?                                                                            " +
                    "    LIMIT ?                                                                             ";

            stmt = conn.prepareStatement(LISTING_COUNTER_SQL);
            stmt.setString(1, categoryName);
            int offset = (pageNumber - 1) * 10;
            stmt.setInt(2, offset);
            stmt.setInt(3, itemCount);

            rs = stmt.executeQuery();

            while (rs.next()) {
                result.add( new CategoryItemDTO(rs.getString("category_name"),
                                                rs.getLong("id"),
                                                rs.getString("name")));
            }

            return result;

        } catch (SQLException e) {
            // TODO handle exception
            e.printStackTrace();
            throw new InternalSystemException("Error calculating matched. Please try again.");
        } finally {
            try {rs.close();} catch (Exception e){}
            try {stmt.close();} catch (Exception e){}
            try {conn.close();} catch (Exception e){}
        }
    }

    public static Map<String, Long> countListing(Long userId)
            throws InternalSystemException {

        Map<String, Long> result
                = new HashMap<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = MarketPlaceDAO.getConnection();

            final String LISTING_COUNTER_SQL =

                    "   SELECT c.name AS category_name, a.order_type, count(*) AS listing_count                     " +
                    "     FROM orders a                                                                             " +
                    "     JOIN category c                                                                           " +
                    "       ON ( a.category_id = c.id )                                                             " +
                    "    WHERE a.user_id = ?                                                                        " +
                    "    GROUP BY c.name, a.order_type;                                                                           ";

            stmt = conn.prepareStatement(LISTING_COUNTER_SQL);
            stmt.setLong(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                result.put(rs.getString("category_name") + "-" + rs.getString("order_type"),
                        rs.getLong("listing_count"));
            }

            return result;

        } catch (SQLException e) {
            // TODO handle exception
            e.printStackTrace();
            throw new InternalSystemException("Error calculating matched. Please try again.");
        } finally {
            try {rs.close();} catch (Exception e){}
            try {stmt.close();} catch (Exception e){}
            try {conn.close();} catch (Exception e){}
        }
    }

    public static Map<String, Long> countMatches(Long userId)
            throws InternalSystemException {

        Map<String, Long> result
                = new HashMap<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = MarketPlaceDAO.getConnection();

            final String MATCH_COUNTER_SQL =

                "   SELECT c.name AS category_name, b.order_type, count(*) AS match_count                       " +
                "     FROM orders a                                                                             " +
                "     JOIN orders b                                                                             " +
                "       ON (   a.category_id = b.category_id                                                    " +
                "          AND a.higher_level_sub_category_id = b.higher_level_sub_category_id                  " +
                "          AND a.middle_level_sub_category_id = b.middle_level_sub_category_id                  " +
                "          AND a.lower_level_sub_category_id  = b.lower_level_sub_category_id                   " +
                "          AND a.description = b.description                                                    " +
                "          AND a.user_id <> b.user_id                                                           " +
                "          AND a.order_type <> b.order_type )                                                   " +
                "     JOIN category c                                                                           " +
                "       ON ( a.category_id = c.id )                                                             " +
                "    WHERE a.user_id = ?                                                                        " +
                "    GROUP BY c.name, b.order_type;                                                                           ";

            stmt = conn.prepareStatement(MATCH_COUNTER_SQL);
            stmt.setLong(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                result.put(rs.getString("category_name") + "-" + rs.getString("order_type"),
                                rs.getLong("match_count"));
            }

            return result;

        } catch (SQLException e) {
            // TODO handle exception
            e.printStackTrace();
            throw new InternalSystemException("Error calculating matched. Please try again.");
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
