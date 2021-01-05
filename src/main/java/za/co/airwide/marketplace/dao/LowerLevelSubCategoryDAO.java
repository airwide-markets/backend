package za.co.airwide.marketplace.dao;

import za.co.airwide.marketplace.dto.LowerLevelSubCategoryDTO;
import za.co.airwide.marketplace.dto.MiddleLevelSubCategoryDTO;
import za.co.airwide.marketplace.util.InternalSystemException;
import za.co.airwide.marketplace.util.ItemNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LowerLevelSubCategoryDAO {

    /**
     * List HigherLevelSubCategory
     *
     * @return
     */
    public static List<LowerLevelSubCategoryDTO> find(
                                                        String categoryName,
                                                        Long higherLevelSubCategoryId,
                                                        Long middleLevelSubCategoryId,
                                                        String orderType)
                      throws InternalSystemException, ItemNotFoundException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = MarketPlaceDAO.getConnection();

            // requests
            final String HIGHER_LEVEL_SUB_CATEGORY_LIST_SQL =

                    "    SELECT l.id, l.category_id, l.higher_level_sub_category_id,                            " +
                    "           l.middle_level_sub_category_id, l.name                                          " +
                    "      FROM lower_level_sub_category l                                                      " +
                    "      JOIN category c                                                                      " +
                    "        ON ( l.category_id = c.id)                                                         " +
                    "     WHERE c.name = ?                                                                      " +
                    "       AND l.higher_level_sub_category_id = ?                                              " +
                    "       AND l.middle_level_sub_category_id = ?                                              " +
                    "       AND ( m.listing_type = ?                                                            " +
                    "        OR   m.request_type = ?  )                                                         " +
                    "     ORDER BY l.name                                                                       ";

            stmt = conn.prepareStatement(HIGHER_LEVEL_SUB_CATEGORY_LIST_SQL);
            stmt.setString(1, categoryName);
            stmt.setLong(  2, higherLevelSubCategoryId);
            stmt.setLong(  2, middleLevelSubCategoryId);
            stmt.setString(3, orderType);
            stmt.setString(4, orderType);

            rs = stmt.executeQuery();

            List<LowerLevelSubCategoryDTO> items
                    = new ArrayList<>();

            while (rs.next()) {

                items.add (
                        new LowerLevelSubCategoryDTO(
                                rs.getLong("id"),
                                rs.getInt("category_id"),
                                rs.getLong("higher_level_sub_category_id"),
                                rs.getLong("middle_level_sub_category_id"),
                                rs.getString("name"),
                                orderType));
            }

            /*
            if (items.size() == 0) {
                throw new ItemNotFoundException("No sub categories for category: "
                        + categoryName + ", order type: " + orderType );
            }
            */

            return items;
        } catch (SQLException e) {
            // TODO create an exception file and notify administrator
            e.printStackTrace();
            throw new InternalSystemException("System error occurred. Please try again.");
        }  finally {
            try {rs.close();} catch (Exception e){}
            try {stmt.close();} catch (Exception e){}
            try {conn.close();} catch (Exception e){}
        }
    }

    /**
     * List HigherLevelSubCategory
     *
     * @return
     */
    /*
    public static List<MiddleLevelSubCategoryDTO> findAll(Integer parentId)
            throws InternalSystemException, ItemNotFoundException {

        final String HIGHER_LEVEL_SUB_CATEGORY_SQL =

                "   SELECT id, parent_id, name, description                                                         " +
                "     FROM middle_level_sub_category                                                                " +
                "    WHERE parent_id = ?                                                                            " +
                "    ORDER BY name                                                                                  ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement(HIGHER_LEVEL_SUB_CATEGORY_SQL);
            stmt.setInt(1, parentId);
            rs = stmt.executeQuery();

            List<MiddleLevelSubCategoryDTO> items
                    = new ArrayList<>();

            while (rs.next()) {

                items.add(new MiddleLevelSubCategoryDTO(
                        rs.getInt("id"),
                        rs.getInt("parent_id"),
                        rs.getString("name"),
                        rs.getString("description")));
            }

            if (items.isEmpty()) {
                throw new ItemNotFoundException("No HIGHER_LEVEL_SUB_CATEGORY found.");
            }
            return items;
        } catch (SQLException e) {
            // TODO log exception
            e.printStackTrace();
            throw new InternalSystemException(e.getMessage());
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }
    */

    public static Long persist( Integer categoryId,
                                Long higherLevelSubCategoryId,
                                Long middleLevelSubCategoryId,
                                String lowerLevelSubCategoryName,
                                String requestType,
                                String listingType )
            throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = MarketPlaceDAO.getConnection();

            final String LOWER_LEVEL_SUB_CATEGORY_LIST_SQL =

                    "    INSERT INTO lower_level_sub_category ( category_id, higher_level_sub_category_id,      " +
                    "                                           middle_level_sub_category_id, name,             " +
                    "                                           request_type, listing_type )                    " +
                    "    VALUES ( ?, ?, ?, ?, ?, ? )                                                            " +
                    "    RETURNING id                                                                           ";

            stmt = conn.prepareStatement( LOWER_LEVEL_SUB_CATEGORY_LIST_SQL );
            stmt.setInt(1, categoryId);
            stmt.setLong(2, higherLevelSubCategoryId);
            stmt.setLong(3, middleLevelSubCategoryId);
            stmt.setString(4, lowerLevelSubCategoryName);
            stmt.setString(5, requestType);
            stmt.setString(6, listingType );

            rs = stmt.executeQuery();

            Long lowerLevelSubCategoryId = null;
            if (rs.next()) {
                lowerLevelSubCategoryId = rs.getLong(1);
            }

            return lowerLevelSubCategoryId;
        } finally {
            try {rs.close();} catch (Exception e){}
            try {stmt.close();} catch (Exception e){}
            try {conn.close();} catch (Exception e){}
        }
    }
}
