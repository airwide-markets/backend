package za.co.airwide.marketplace.dao;

import za.co.airwide.marketplace.dto.HigherLevelSubCategoryDTO;
import za.co.airwide.marketplace.dto.HigherLevelSubCategoryKeyDTO;
import za.co.airwide.marketplace.util.InternalSystemException;
import za.co.airwide.marketplace.util.ItemNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HigherLevelSubCategoryDAO {

    /**
     * List HigherLevelSubCategory
     *
     * @return
     */
    public static Map<Integer, List<HigherLevelSubCategoryDTO>> findAll()
            throws InternalSystemException, ItemNotFoundException {

        final String HIGHER_LEVEL_SUB_CATEGORY_SQL =

                "   SELECT id, category_id, name, request_type, listing_type                                        " +
                "     FROM higher_level_sub_category                                                                " +
                "    ORDER BY name                                                                                  ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement(HIGHER_LEVEL_SUB_CATEGORY_SQL);
            rs = stmt.executeQuery();

            Map<Integer, List<HigherLevelSubCategoryDTO>> map
                    = new HashMap<>();

            while (rs.next()) {

                List<HigherLevelSubCategoryDTO> items =
                    map.get(rs.getInt("category_id"));
                if (items == null) {
                    items = new ArrayList<>();
                    map.put(rs.getInt("category_id"), items);
                }

                items.add(
                        new HigherLevelSubCategoryDTO(
                                rs.getLong("id"),
                                rs.getInt("parent_id"),
                                rs.getString("name"),
                                rs.getString("request_type"),
                                rs.getString("listing_type")));
            }

            if (map.isEmpty()) {
                throw new ItemNotFoundException("No HIGHER_LEVEL_SUB_CATEGORY found.");
            }
            return map;
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

    /**
     * Retrieve all the know higherLevelSubCategories
     *
     * @return
     */
    public static Map<Integer, Map<HigherLevelSubCategoryKeyDTO, Long>> higherLevelSubCategoryMap() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = MarketPlaceDAO.getConnection();

            // requests
            final String HIGHER_LEVEL_SUB_CATEGORY_LIST_SQL =

                    "    SELECT  id, category_id, name, request_type, listing_type                              " +
                    "      FROM higher_level_sub_category                                                       ";

            stmt = conn.prepareStatement(HIGHER_LEVEL_SUB_CATEGORY_LIST_SQL);
            rs = stmt.executeQuery();
            Map<Integer, Map<HigherLevelSubCategoryKeyDTO, Long>> subCatMap
                                = new HashMap<>();

            while (rs.next()) {

                int categoryId
                        = rs.getInt("category_id");

                Map<HigherLevelSubCategoryKeyDTO, Long> subCatMapForThisCategory
                                = subCatMap.get(categoryId);

                if (subCatMapForThisCategory == null) {
                    subCatMapForThisCategory = new HashMap<>();
                    subCatMap.put(categoryId, subCatMapForThisCategory);
                }
                System.out.println("higherLevelSubCategoryMap(), adding  name"
                        + rs.getString("name") + ", id: " + rs.getLong("id"));
                subCatMapForThisCategory.put(
                                new HigherLevelSubCategoryKeyDTO(
                                        rs.getInt("category_id"),
                                        rs.getString("name"),
                                        rs.getString("request_type"),
                                        rs.getString("listing_type")),
                                rs.getLong("id") );
            }

            return subCatMap;
        } catch (SQLException e) {
            // TODO create an exception file and notify administrator
            e.printStackTrace();

            return null;
        }  finally {
            try {rs.close();} catch (Exception e){}
            try {stmt.close();} catch (Exception e){}
            try {conn.close();} catch (Exception e){}
        }
    }

    /**
     * Retrieve all the know higherLevelSubCategories
     * @param categoryName
     *
     * @return
     */
    public static List<HigherLevelSubCategoryDTO> findByCategoryAndOrderType(String categoryName,
                                                                             String orderType)
            throws InternalSystemException, ItemNotFoundException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = MarketPlaceDAO.getConnection();

            // requests
            final String HIGHER_LEVEL_SUB_CATEGORY_LIST_SQL =

                    "    SELECT h.id, h.category_id, h.name, h.request_type, h.listing_type                     " +
                    "      FROM higher_level_sub_category h                                                     " +
                    "      JOIN category c                                                                      " +
                    "        ON ( h.category_id = c.id)                                                         " +
                    "     WHERE c.name = ?                                                                      " +
                    "       AND ( h.listing_type = ?                                                            " +
                    "        OR   h.request_type = ?  )                                                         " +
                    "     ORDER BY h.name                                                                       ";

            stmt = conn.prepareStatement(HIGHER_LEVEL_SUB_CATEGORY_LIST_SQL);
            stmt.setString(1, categoryName);
            stmt.setString(2, orderType);
            stmt.setString(3, orderType);

            rs = stmt.executeQuery();

            List<HigherLevelSubCategoryDTO> items
                    = new ArrayList<>();

            while (rs.next()) {

                items.add (
                        new HigherLevelSubCategoryDTO(
                                rs.getLong("id"),
                                rs.getInt("category_id"),
                                rs.getString("name"),
                                rs.getString("request_type"),
                                rs.getString("listing_type")));
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

    public static Long persist(HigherLevelSubCategoryDTO item ) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = MarketPlaceDAO.getConnection();

            final String HIGHER_LEVEL_SUB_CATEGORY_LIST_SQL =

                    "    INSERT INTO higher_level_sub_category (                                                " +
                    "                 category_id, name, request_type, listing_type )                           " +
                    "    VALUES ( ?, ?, ?, ? )                                                                  " +
                    "    RETURNING id                                                                           ";

            stmt = conn.prepareStatement(HIGHER_LEVEL_SUB_CATEGORY_LIST_SQL);
            stmt.setInt(1, item.getCategoryId());
            stmt.setString(2, item.getName());
            stmt.setString(3, item.getRequestType());
            stmt.setString(4, item.getListingType());

            rs = stmt.executeQuery();

            Long higherLevelSubCategoryId = null;
            if (rs.next()) {
                higherLevelSubCategoryId = rs.getLong(1);
            }

            return higherLevelSubCategoryId;
        } catch (SQLException e) {
            // TODO create an exception file and notify administrator
            e.printStackTrace();

            return null;
        } finally {
            try {rs.close();} catch (Exception e){}
            try {stmt.close();} catch (Exception e){}
            try {conn.close();} catch (Exception e){}
        }
    }
}
