package za.co.airwide.marketplace.dao;

import za.co.airwide.marketplace.dto.SearchFieldDTO;
import za.co.airwide.marketplace.dto.SearchFieldKeyDTO;
import za.co.airwide.marketplace.util.InternalSystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SearchFieldDAO {

    public static void persist(SearchFieldDTO item )
            throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = MarketPlaceDAO.getConnection();

            final String PERSIST_SEARCH_FIELD_SQL =

                    "    INSERT INTO search_fields ( " +
                    "       category_id,                                                                            " +
                            ( item.getHigherLevelSubCategoryId() != null ? " higher_level_sub_category_id, " : "" )   +
                            ( item.getMiddleLevelSubCategoryId() != null ? " middle_level_sub_category_id, " : "" )   +
                            ( item.getLowerLevelSubCategoryId()  != null ? " lower_level_sub_category_id, " : ""  )   +
                    "       name, request_type, listing_type  )                                                     " +
                    "    VALUES ( ?," +
                            ( item.getHigherLevelSubCategoryId() != null ? " ?, " : "" ) +
                            ( item.getMiddleLevelSubCategoryId() != null ? " ?, " : "" ) +
                            ( item.getLowerLevelSubCategoryId()  != null ? " ?, " : "" ) +
                    "            ?, ?, ? )                                                                          ";

            stmt = conn.prepareStatement(PERSIST_SEARCH_FIELD_SQL);
            int idx = 0;
            stmt.setInt(++idx, item.getCategoryId());
            if (item.getHigherLevelSubCategoryId() != null) {
                stmt.setLong(++idx, item.getHigherLevelSubCategoryId());
            }
            if (item.getMiddleLevelSubCategoryId() != null) {
                stmt.setLong(++idx, item.getMiddleLevelSubCategoryId());
            }
            if (item.getLowerLevelSubCategoryId() != null) {
                stmt.setLong(++idx, item.getLowerLevelSubCategoryId());
            }
            stmt.setString(++idx, item.getName());
            stmt.setString(++idx, item.getRequestType());
            stmt.setString(++idx, item.getListingType());
            stmt.executeUpdate();
        } finally {
            try {rs.close();} catch (Exception e){}
            try {stmt.close();} catch (Exception e){}
            try {conn.close();} catch (Exception e){}
        }
    }

    /**
     * searchFields
     * @param key
     * @return
     * @throws SQLException
     */
    public static Map<String, SearchFieldDTO> searchFields( SearchFieldKeyDTO key )
            throws InternalSystemException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = MarketPlaceDAO.getConnection();

            final String SEARCH_FIELD_LIST_SQL =

                    "    SELECT id, category_id,                                                                " +
                            "           higher_level_sub_category_id, middle_level_sub_category_id,             " +
                            "           lower_level_sub_category_id, name, request_type, listing_type           " +
                            "      FROM search_fields                                                           " +
                            "     WHERE category_id = ?                                                         " +
                            (key.getHigherLevelSubCategoryId() != null ?
                                    "       AND higher_level_sub_category_id = ? " : "                          ") +
                            (key.getMiddleLevelSubCategoryId() != null ?
                                    "       AND middle_level_sub_category_id = ? " : "                          ") +
                            (key.getLowerLevelSubCategoryId() != null ?
                                    "       AND lower_level_sub_category_id = ? " : "                           ") +
                            "       AND ( request_type = ?                                                      " +
                            "        OR   listing_type = ? )                                                    ";

            System.out.println("SEARCH_FIELD_LIST_SQL : "  + SEARCH_FIELD_LIST_SQL );

            stmt = conn.prepareStatement(SEARCH_FIELD_LIST_SQL);
            int index = 0;
            stmt.setInt(++index, key.getCategoryId());
            if (key.getHigherLevelSubCategoryId() != null) {
                stmt.setLong(++index, key.getHigherLevelSubCategoryId());
            }
            if (key.getMiddleLevelSubCategoryId() != null) {
                stmt.setLong(++index, key.getMiddleLevelSubCategoryId());
            }
            if (key.getLowerLevelSubCategoryId() != null) {
                stmt.setLong(++index, key.getLowerLevelSubCategoryId());
            }
            stmt.setString(++index, key.getRequestType());
            stmt.setString(++index, key.getListingType());

            Map<String, SearchFieldDTO> searchFieldMap
                    = new HashMap<>();

            rs = stmt.executeQuery();
            while (rs.next()) {
                searchFieldMap.put(
                        rs.getString("name"),
                        new SearchFieldDTO(
                                rs.getLong("id"),
                                rs.getInt("category_id"),
                                rs.getLong("higher_level_sub_category_id"),
                                rs.getLong("middle_level_sub_category_id"),
                                rs.getLong("lower_level_sub_category_id"),
                                rs.getString("name"),
                                rs.getString("request_type"),
                                rs.getString("listing_type")));
            }

            return searchFieldMap;
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new InternalSystemException("System error occurred. Please try again.");
        } finally {
            try {rs.close();} catch (Exception e){}
            try {stmt.close();} catch (Exception e){}
            try {conn.close();} catch (Exception e){}
        }
    }
}
