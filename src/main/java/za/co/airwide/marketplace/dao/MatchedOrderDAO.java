package za.co.airwide.marketplace.dao;

import za.co.airwide.marketplace.dto.*;
import za.co.airwide.marketplace.util.InternalSystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MatchedOrderDAO {

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

    public static List<MatchedOrderDTO> findMatchingOrders(
                                                        String categoryName, Long userId, String partnerOrderType )
                throws InternalSystemException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = MarketPlaceDAO.getConnection();

            final String CATEGORY_LIST_SQL =
               "    SELECT a.category_id, c.name as category_name,                                                  " +
               "           a.higher_level_sub_category_id, h.name as higher_level_sub_category_name,                " +
               "           a.middle_level_sub_category_id, m.name as middle_level_sub_category_name,                " +
               "           a.lower_level_sub_category_id,  l.name as lower_level_sub_category_name,  a.order_type,  " +
               "           b.order_type as partner_order_type,                                                      " +
               "           a.order_id as my_order_id, b.order_id as partner_order_id,                               " +
               "           a.description as my_description, b.description as partner_description,                   " +
               "           a.narration as my_narration, b.narration as partner_narration,                           " +
               "           a.order_date as my_order_date, b.order_date as partner_order_date,                       " +
               "           a.available_date as my_available_date, b.available_date as partner_available_date,       " +
               "           a.photo_path as my_photo_path, b.photo_path as partner_photo_path,                       " +
               "           a.country_id, co.country_name,                                                           " +
               "           u.id as partner_id, u.name as partner_name, u.surname as partner_surname,                " +
               "           u.profile_image, u.msisdn                                                                " +
               "     FROM orders a                                                                                  " +
               "     JOIN orders b                                                                                  " +
               "       ON (   a.category_id = b.category_id                                                         " +
               "          AND a.higher_level_sub_category_id = b.higher_level_sub_category_id                       " +
               "          AND a.middle_level_sub_category_id = b.middle_level_sub_category_id                       " +
               "          AND a.lower_level_sub_category_id  = b.lower_level_sub_category_id                        " +
               "          AND a.description = b.description                                                         " +
               "          AND a.country_id                   = b.country_id                                         " +
               "          AND a.user_id <> b.user_id                                                                " +
               "          AND a.order_type <> b.order_type )                                                        " +
               "     JOIN users u                                                                                   " +
               "       ON ( b.user_id = u.id )                                                                      " +
               "     JOIN category c                                                                                " +
               "       ON ( a.category_id = c.id )                                                                  " +
               "     LEFT JOIN higher_level_sub_category h                                                          " +
               "       ON (   a.category_id = h.category_id                                                         " +
               "          AND a.higher_level_sub_category_id = h.id )                                               " +
               "     LEFT JOIN middle_level_sub_category m                                                          " +
               "       ON (   a.category_id = m.category_id                                                         " +
               "          AND a.higher_level_sub_category_id = m.higher_level_sub_category_id                       " +
               "          AND a.middle_level_sub_category_id = m.id )                                               " +
               "     LEFT JOIN lower_level_sub_category l                                                           " +
               "       ON (   a.category_id = l.category_id                                                         " +
               "          AND a.higher_level_sub_category_id = l.higher_level_sub_category_id                       " +
               "          AND a.middle_level_sub_category_id = l.middle_level_sub_category_id                       " +
               "          AND a.lower_level_sub_category_id  = l.id )                                               " +
//             "     LEFT JOIN photos p1                                                                            " +
//             "       ON (   a.category_id = p1.category_id                                                        " +
//             "          AND a.higher_level_sub_category_id = p1.higher_level_sub_category_id                      " +
//             "          AND a.middle_level_sub_category_id = p1.middle_level_sub_category_id                      " +
//             "          AND a.lower_level_sub_category_id  = p1.lower_level_sub_category_id                       " +
//             "          AND a.order_id = p1.order_id                                                              " +
//             "          AND a.photo_id = p1.id  )                                                                 " +
//             "     LEFT JOIN photos p2                                                                            " +
//             "       ON (   a.category_id = p2.category_id                                                        " +
//             "          AND a.higher_level_sub_category_id = p2.higher_level_sub_category_id                      " +
//             "          AND a.middle_level_sub_category_id = p2.middle_level_sub_category_id                      " +
//             "          AND a.lower_level_sub_category_id  = p2.lower_level_sub_category_id                       " +
//             "          AND a.order_id = p2.order_id                                                              " +
//             "          AND a.photo_id = p2.id  )                                                                 " +
               "     LEFT JOIN countries co                                                                         " +
               "       ON (   a.country_id = co.country_id )                                                        " +
               "    WHERE c.name = ?                                                                                " +
               "      AND b.order_type = ?                                                                          " +
               "      AND a.user_id = ?                                                                             ";

            stmt = conn.prepareStatement(CATEGORY_LIST_SQL);
            stmt.setString(1, categoryName);
            stmt.setString(2, partnerOrderType);
            stmt.setLong(3, userId);

            rs = stmt.executeQuery();
             List<MatchedOrderDTO> matchedOrders
                    = new ArrayList<>();
            while (rs.next()) {

                long categoryId = rs.getLong("category_id");
                long orderId = rs.getLong("partner_order_id");
                List<FileDTO> files
                        = PhotoDAO.findPhotos(categoryId, orderId);

                matchedOrders.add(
                                new MatchedOrderDTO(
                                        rs.getInt("category_id"),
                                        rs.getString("category_name"),
                                        rs.getLong("higher_level_sub_category_id"),
                                        rs.getString("higher_level_sub_category_name"),
                                        rs.getLong("middle_level_sub_category_id"),
                                        rs.getString("middle_level_sub_category_name"),
                                        rs.getLong("lower_level_sub_category_id"),
                                        rs.getString("lower_level_sub_category_name"),
                                        rs.getString("partner_order_type"),
                                        rs.getLong("my_order_id"),
                                        rs.getLong("partner_order_id"),
                                        rs.getString("my_description"),
                                        rs.getString("partner_description"),
                                        rs.getString("my_narration"),
                                        rs.getString("partner_narration"),
                                        new Date(rs.getTimestamp("my_order_date").getTime()),
                                        new Date(rs.getTimestamp("partner_order_date").getTime()),
                                        new Date(rs.getTimestamp("my_available_date").getTime()),
                                        new Date(rs.getTimestamp("partner_available_date").getTime()),
                                        rs.getString("my_photo_path"),
                                        rs.getString("partner_photo_path"),
                                        rs.getInt("country_id"),
                                        rs.getString("country_name"),
                                        rs.getLong("partner_id"),
                                        rs.getString("partner_name"),
                                        rs.getString("partner_surname"),
                                        rs.getString("profile_image"),
                                        rs.getString("msisdn"),
                                        files));
            }

            return matchedOrders;
        } catch (SQLException e ) {
            e.printStackTrace();
            throw new InternalSystemException("System error occurred. Please try again.");
        } finally {
            try {rs.close();} catch (Exception e){}
            try {stmt.close();} catch (Exception e){}
            try {conn.close();} catch (Exception e){}
        }
    }

    public static List<MatchedOrderDTO> findOrderListing(
            String categoryName, Long userId, String partnerOrderType )
            throws InternalSystemException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = MarketPlaceDAO.getConnection();

            final String CATEGORY_LIST_SQL =
                    "    SELECT a.category_id, c.name as category_name,                                                  " +
                    "           a.higher_level_sub_category_id, h.name as higher_level_sub_category_name,                " +
                    "           a.middle_level_sub_category_id, m.name as middle_level_sub_category_name,                " +
                    "           a.lower_level_sub_category_id,  l.name as lower_level_sub_category_name,  a.order_type,  " +
                    "           a.order_id as my_order_id, b.order_id as partner_order_id,                               " +
                    "           a.description as my_description, b.description as partner_description,                   " +
                    "           a.narration as my_narration, b.narration as partner_narration,                           " +
                    "           a.order_date as my_order_date, b.order_date as partner_order_date,                       " +
                    "           a.available_date as my_available_date, b.available_date as partner_available_date,       " +
                    "           a.photo_path as my_photo_path, b.photo_path as partner_photo_path,                       " +
                    "           a.country_id, co.country_name,                                                           " +
                    "           u.id as partner_id, u.name as partner_name, u.surname as partner_surname,                " +
                    "           u.profile_image, u.msisdn                                                                " +
                    "     FROM orders a                                                                                  " +
                    "     JOIN users u                                                                                   " +
                    "       ON ( b.user_id = u.id )                                                                      " +
                    "     JOIN category c                                                                                " +
                    "       ON ( a.category_id = c.id )                                                                  " +
                    "     LEFT JOIN higher_level_sub_category h                                                          " +
                    "       ON (   a.category_id = h.category_id                                                         " +
                    "          AND a.higher_level_sub_category_id = h.id )                                               " +
                    "     LEFT JOIN middle_level_sub_category m                                                          " +
                    "       ON (   a.category_id = m.category_id                                                         " +
                    "          AND a.higher_level_sub_category_id = m.higher_level_sub_category_id                       " +
                    "          AND a.middle_level_sub_category_id = m.id )                                               " +
                    "     LEFT JOIN lower_level_sub_category l                                                           " +
                    "       ON (   a.category_id = l.category_id                                                         " +
                    "          AND a.higher_level_sub_category_id = l.higher_level_sub_category_id                       " +
                    "          AND a.middle_level_sub_category_id = l.middle_level_sub_category_id                       " +
                    "          AND a.lower_level_sub_category_id  = l.id )                                               " +
//             "     LEFT JOIN photos p1                                                                            " +
//             "       ON (   a.category_id = p1.category_id                                                        " +
//             "          AND a.higher_level_sub_category_id = p1.higher_level_sub_category_id                      " +
//             "          AND a.middle_level_sub_category_id = p1.middle_level_sub_category_id                      " +
//             "          AND a.lower_level_sub_category_id  = p1.lower_level_sub_category_id                       " +
//             "          AND a.order_id = p1.order_id                                                              " +
//             "          AND a.photo_id = p1.id  )                                                                 " +
//             "     LEFT JOIN photos p2                                                                            " +
//             "       ON (   a.category_id = p2.category_id                                                        " +
//             "          AND a.higher_level_sub_category_id = p2.higher_level_sub_category_id                      " +
//             "          AND a.middle_level_sub_category_id = p2.middle_level_sub_category_id                      " +
//             "          AND a.lower_level_sub_category_id  = p2.lower_level_sub_category_id                       " +
//             "          AND a.order_id = p2.order_id                                                              " +
//             "          AND a.photo_id = p2.id  )                                                                 " +
                    "     LEFT JOIN countries co                                                                         " +
                    "       ON (   a.country_id = co.country_id )                                                        " +
                    "    WHERE c.name = ?                                                                                " +
                    "      AND a.order_type = ?                                                                          " +
                    "      AND a.user_id = ?                                                                             ";

            stmt = conn.prepareStatement(CATEGORY_LIST_SQL);
            stmt.setString(1, categoryName);
            stmt.setString(2, partnerOrderType);
            stmt.setLong(3, userId);

            rs = stmt.executeQuery();
            List<MatchedOrderDTO> matchedOrders
                    = new ArrayList<>();
            while (rs.next()) {

                long categoryId = rs.getLong("category_id");
                long orderId = rs.getLong("partner_order_id");
                List<FileDTO> files
                        = PhotoDAO.findPhotos(categoryId, orderId);

                matchedOrders.add(
                        new MatchedOrderDTO(
                                rs.getInt("category_id"),
                                rs.getString("category_name"),
                                rs.getLong("higher_level_sub_category_id"),
                                rs.getString("higher_level_sub_category_name"),
                                rs.getLong("middle_level_sub_category_id"),
                                rs.getString("middle_level_sub_category_name"),
                                rs.getLong("lower_level_sub_category_id"),
                                rs.getString("lower_level_sub_category_name"),
                                rs.getString("partner_order_type"),
                                rs.getLong("my_order_id"),
                                rs.getLong("partner_order_id"),
                                rs.getString("my_description"),
                                rs.getString("partner_description"),
                                rs.getString("my_narration"),
                                rs.getString("partner_narration"),
                                new Date(rs.getTimestamp("my_order_date").getTime()),
                                new Date(rs.getTimestamp("partner_order_date").getTime()),
                                new Date(rs.getTimestamp("my_available_date").getTime()),
                                new Date(rs.getTimestamp("partner_available_date").getTime()),
                                rs.getString("my_photo_path"),
                                rs.getString("partner_photo_path"),
                                rs.getInt("country_id"),
                                rs.getString("country_name"),
                                rs.getLong("partner_id"),
                                rs.getString("partner_name"),
                                rs.getString("partner_surname"),
                                rs.getString("profile_image"),
                                rs.getString("msisdn"),
                                files));
            }

            return matchedOrders;
        } catch (SQLException e ) {
            e.printStackTrace();
            throw new InternalSystemException("System error occurred. Please try again.");
        } finally {
            try {rs.close();} catch (Exception e){}
            try {stmt.close();} catch (Exception e){}
            try {conn.close();} catch (Exception e){}
        }
    }

//    public static List<MatchedOrderDTO> findMatchingOrders(OrderDTO order)
//                throws InternalSystemException {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        try {
//
//            conn = MarketPlaceDAO.getConnection();
//
//            final String CATEGORY_LIST_SQL =
//            "    SELECT a.category_id, c.name as category_name ,                                                 " +
//            "           a.higher_level_sub_category_id, h.name as higher_level_sub_category_name,                " +
//            "           a.middle_level_sub_category_id, m.name as middle_level_sub_category_name,                " +
//            "           a.lower_level_sub_category_id,  l.name as lower_level_sub_category_name,  a.order_type,  " +
//            "           a.order_id as my_order_id, b.order_id as partner_order_id,                               " +
//            "           a.description as my_description, b.description as partner_description,                   " +
//            "           a.narration as my_narration, b.narration as partner_narration,                           " +
//            "           a.order_date as my_order_date, b.order_date as partner_order_date,                       " +
//            "           a.available_date as my_available_date, b.available_date as partner_available_date,       " +
//            "           p1.photo_path as my_photo_path, p2.photo_path as partner_photo_path,                     " +
//            "           a.country_id, co.country_name,                                                           " +
//            "           u.id as partner_id, u.name as partner_name, u.surname as partner_surname,                " +
//            "           u.profile_image, u.msisdn                                                                " +
//            "     FROM orders a                                                                                  " +
//            "     JOIN orders b                                                                                  " +
//            "       ON (   a.category_id = b.category_id                                                         " +
//            "          AND a.higher_level_sub_category_id = b.higher_level_sub_category_id                       " +
//            "          AND a.middle_level_sub_category_id = b.middle_level_sub_category_id                       " +
//            "          AND a.lower_level_sub_category_id  = b.lower_level_sub_category_id                        " +
//            "          AND a.description                  = b.description                                        " +
//            "          AND a.country_id                   = b.country_id                                         " +
//            "          AND a.user_id <> b.user_id                                                                " +
//            "          AND a.order_type <> b.order_type )                                                        " +
//            "     JOIN users u                                                                                   " +
//            "       ON ( b.user_id = u.id )                                                                      " +
//            "     JOIN category c                                                                                " +
//            "       ON ( a.category_id = c.id )                                                                  " +
//            "     LEFT JOIN higher_level_sub_category h                                                          " +
//            "       ON (   a.category_id = h.category_id                                                         " +
//            "          AND a.higher_level_sub_category_id = h.id )                                               " +
//            "     LEFT JOIN middle_level_sub_category m                                                          " +
//            "       ON (   a.category_id = m.category_id                                                         " +
//            "          AND a.higher_level_sub_category_id = m.higher_level_sub_category_id                       " +
//            "          AND a.middle_level_sub_category_id = m.id )                                               " +
//            "     LEFT JOIN lower_level_sub_category l                                                           " +
//            "       ON (   a.category_id = l.category_id                                                         " +
//            "          AND a.higher_level_sub_category_id = l.higher_level_sub_category_id                       " +
//            "          AND a.middle_level_sub_category_id = l.middle_level_sub_category_id                       " +
//            "          AND a.lower_level_sub_category_id  = l.id )                                               " +
//            "     LEFT JOIN photos p1                                                                            " +
//            "       ON (   a.category_id = p1.category_id                                                        " +
//            "          AND a.higher_level_sub_category_id = p1.higher_level_sub_category_id                      " +
//            "          AND a.middle_level_sub_category_id = p1.middle_level_sub_category_id                      " +
//            "          AND a.lower_level_sub_category_id  = p1.lower_level_sub_category_id                       " +
//            "          AND a.order_id = p1.order_id                                                              " +
//            "          AND a.photo_id = p1.id  )                                                                 " +
//            "     LEFT JOIN photos p2                                                                            " +
//            "       ON (   a.category_id = p2.category_id                                                        " +
//            "          AND a.higher_level_sub_category_id = p2.higher_level_sub_category_id                      " +
//            "          AND a.middle_level_sub_category_id = p2.middle_level_sub_category_id                      " +
//            "          AND a.lower_level_sub_category_id  = p2.lower_level_sub_category_id                       " +
//            "          AND a.order_id = p2.order_id                                                              " +
//            "          AND a.photo_id = p2.id  )                                                                 " +
//            "     LEFT JOIN countries co                                                                         " +
//            "       ON (   a.country_id = co.country_id )                                                        " +
//            "    WHERE c.id = ?                                                                                  " +
//            "      AND a.user_id = ?                                                                             " +
//            "      AND a.higher_level_sub_category_id = ?                                                        " +
//            "      AND a.middle_level_sub_category_id = ?                                                        " +
//            "      AND a.lower_level_sub_category_id = ?                                                         " +
//            "      AND a.country_id = ?                                                                          ";
//
//            stmt = conn.prepareStatement(CATEGORY_LIST_SQL);
//
//            stmt.setInt(1, order.getCategoryId());
//            stmt.setLong(2, order.getUserId());
//            stmt.setLong(3, order.getHigherLevelSubCategoryId());
//            stmt.setLong(4, order.getMiddleLevelCategoryId());
//            stmt.setLong(5, order.getLowLevelSubCategoryId());
//            stmt.setInt(6, order.getCountryId());
//
//            rs = stmt.executeQuery();
//            List<MatchedOrderDTO> matchedOrders
//                    = new ArrayList<>();
//            while (rs.next()) {
//
//                matchedOrders.add(
//                                new MatchedOrderDTO(
//                                        rs.getInt("category_id"),
//                                        rs.getString("category_name"),
//                                        rs.getLong("higher_level_sub_category_id"),
//                                        rs.getString("higher_level_sub_category_name"),
//                                        rs.getLong("middle_level_sub_category_id"),
//                                        rs.getString("middle_level_sub_category_name"),
//                                        rs.getLong("lower_level_sub_category_id"),
//                                        rs.getString("lower_level_sub_category_name"),
//                                        rs.getString("order_type"),
//                                        rs.getLong("my_order_id"),
//                                        rs.getLong("partner_order_id"),
//                                        rs.getString("my_description"),
//                                        rs.getString("partner_description"),
//                                        rs.getString("my_narration"),
//                                        rs.getString("partner_narration"),
//                                        new Date(rs.getTimestamp("my_order_date").getTime()),
//                                        new Date(rs.getTimestamp("partner_order_date").getTime()),
//                                        new Date(rs.getTimestamp("my_available_date").getTime()),
//                                        new Date(rs.getTimestamp("partner_available_date").getTime()),
//                                        rs.getString("my_photo_path"),
//                                        rs.getString("partner_photo_path"),
//                                        rs.getInt("country_id"),
//                                        rs.getString("country_name"),
//                                        rs.getLong("partner_id"),
//                                        rs.getString("partner_name"),
//                                        rs.getString("partner_surname"),
//                                        rs.getString("profile_image"),
//                                        rs.getString("msisdn")));
//            }
//
//            return matchedOrders;
//        } catch (SQLException e ) {
//            e.printStackTrace();
//            throw new InternalSystemException("System error occurred. Please try again.");
//        } finally {
//            try {rs.close();} catch (Exception e){}
//            try {stmt.close();} catch (Exception e){}
//            try {conn.close();} catch (Exception e){}
//        }
//    }

    public static List<UserDTO> findMatchingPartners(OrderDTO order)
            throws InternalSystemException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = MarketPlaceDAO.getConnection();

            final String CATEGORY_LIST_SQL =
            "    SELECT DISTINCT u.id as partner_id, u.name as partner_name, u.surname as partner_surname,       " +
            "           u.profile_image, u.msisdn, b.notification_email as partner_notification_email            " +
            "     FROM orders a                                                                                  " +
            "     JOIN orders b                                                                                  " +
            "       ON (   a.category_id = b.category_id                                                         " +
            "          AND a.higher_level_sub_category_id = b.higher_level_sub_category_id                       " +
            "          AND a.middle_level_sub_category_id = b.middle_level_sub_category_id                       " +
            "          AND a.lower_level_sub_category_id  = b.lower_level_sub_category_id                        " +
            "          AND a.description                  = b.description                                        " +
            "          AND a.country_id                   = b.country_id                                         " +
            "          AND a.user_id <> b.user_id                                                                " +
            "          AND a.order_type <> b.order_type )                                                        " +
            "     JOIN users u                                                                                   " +
            "       ON ( b.user_id = u.id )                                                                      " +
            "    WHERE a.category_id = ?                                                                         " +
            "      AND a.user_id = ?                                                                             " +
            "      AND a.higher_level_sub_category_id = ?                                                        " +
            "      AND a.middle_level_sub_category_id = ?                                                        " +
            "      AND a.lower_level_sub_category_id = ?                                                         " +
            "      AND a.country_id = ?                                                                          " +
            "      AND a.order_id = ?                                                                            ";

            stmt = conn.prepareStatement(CATEGORY_LIST_SQL);

            stmt.setInt(1, order.getCategoryId());
            stmt.setLong(2, order.getUserId());
            stmt.setLong(3, order.getHigherLevelSubCategoryId());
            stmt.setLong(4, order.getMiddleLevelCategoryId());
            stmt.setLong(5, order.getLowLevelSubCategoryId());
            stmt.setInt(6, order.getCountryId());
            stmt.setLong(7, order.getOrderId());

            rs = stmt.executeQuery();
            List<UserDTO> matchedOrders
                    = new ArrayList<>();

            while (rs.next()) {

                matchedOrders.add(
                        new UserDTO(
                                rs.getLong("partner_id"),
                                rs.getString("msisdn"),
                                rs.getString("partner_name"),
                                rs.getString("partner_surname"),
                                rs.getString("partner_notification_email")));
            }

            return matchedOrders;
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
