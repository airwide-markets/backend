package za.co.airwide.marketplace.dao;

import za.co.airwide.marketplace.dto.*;
import za.co.airwide.marketplace.util.InternalSystemException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public static Long insert( OrderDTO order )
                throws SQLException {

        final String CREATE_ORDER_SQL =

                "   INSERT INTO orders ( category_id, higher_level_sub_category_id, middle_level_sub_category_id,   " +
                "                        lower_level_sub_category_id, user_id, description, narration, order_date,  " +
                "                        available_date, order_type, country_id, photo_path, notification_email )                       " +
                "   VALUES             ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )                                       " +
                "   RETURNING order_id                                                                              ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement( CREATE_ORDER_SQL );
            stmt.setInt(1, order.getCategoryId());
            stmt.setLong(2, order.getHigherLevelSubCategoryId());
            stmt.setLong(3, order.getMiddleLevelCategoryId());
            stmt.setLong(4, order.getLowLevelSubCategoryId());
            stmt.setLong(5, order.getUserId());
            stmt.setString(6, order.getDescription());
            stmt.setString(7, order.getNarration());
            stmt.setTimestamp(8, new Timestamp(order.getOrderDate().getTime()));
            stmt.setTimestamp(9, new Timestamp(order.getAvailableDate().getTime()));
            stmt.setString(10, order.getOrderType());
            stmt.setInt(11, order.getCountryId());
            stmt.setString(12, order.getPhotoPath());
            stmt.setString(13, order.getNotificationEmail());
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
            return 0L;
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }

    public static void delete( OrderKeyDTO orderKey )
            throws SQLException {

        final String DELETE_ORDER_SQL =

                "   DELETE FROM orders                                                                          " +
                "    WHERE category_id = ?                                                                      " +
                "      AND higher_level_sub_category_id = ?                                                     " +
                "      AND middle_level_sub_category_id = ?                                                     " +
                "      AND lower_level_sub_category_id = ?                                                      " +
                "      AND order_id = ?                                                                         ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement( DELETE_ORDER_SQL );
            stmt.setInt(1, orderKey.getCategoryId());
            stmt.setLong(2, orderKey.getHigherLevelSubCategoryId());
            stmt.setLong(3, orderKey.getMiddleLevelSubCategoryId());
            stmt.setLong(4, orderKey.getLowerLevelSubCategoryId());
            stmt.setLong(5, orderKey.getOrderId());

            stmt.executeUpdate();
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }

    public static List<OrderDetailDTO> findOrders(Long userId, String categoryName, String orderType)
            throws InternalSystemException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = MarketPlaceDAO.getConnection();

            final String ORDER_DETAIL_LIST_SQL =

            "    SELECT a.category_id, c.name as category_name,                                                  " +
            "           a.higher_level_sub_category_id, h.name as higher_level_sub_category_name,                " +
            "           a.middle_level_sub_category_id, m.name as middle_level_sub_category_name,                " +
            "           a.lower_level_sub_category_id,  l.name as lower_level_sub_category_name,                 " +
            "           a.order_id, a.order_type, a.description, a.narration, a.order_date, a.available_date,    " +
            "           a.country_id, co.country_name, a.photo_path                                              " +
            "     FROM orders a                                                                                  " +
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
            "          AND a.lower_level_sub_category_id  = l.id )                                              " +
//          "     LEFT JOIN photos p                                                                             " +
//          "       ON (   a.category_id = p.category_id                                                         " +
//          "          AND a.higher_level_sub_category_id = p.higher_level_sub_category_id                       " +
//          "          AND a.middle_level_sub_category_id = p.middle_level_sub_category_id                       " +
//          "          AND a.lower_level_sub_category_id  = p.lower_level_sub_category_id                        " +
//          "          AND a.order_id = p.order_id                                                               " +
//          "          AND a.photo_id = p.id  )                                                                  " +
            "     LEFT JOIN countries co                                                                         " +
            "       ON (   a.country_id = co.country_id )                                                        " +
            "    WHERE a.user_id = ?                                                                             " +
            "      AND c.name = ?                                                                                " +
            "      AND a.order_type = ?                                                                          " +
            "    ORDER BY a.order_date DESC                                                                      ";

            System.out.println( ORDER_DETAIL_LIST_SQL );

            stmt = conn.prepareStatement(ORDER_DETAIL_LIST_SQL);
            stmt.setLong(1, userId);
            stmt.setString(2, categoryName);
            stmt.setString(3, orderType);

            rs = stmt.executeQuery();
            List<OrderDetailDTO> orders
                    = new ArrayList<>();
            while (rs.next()) {

                long categoryId = rs.getLong("category_id");
                long orderId = rs.getLong("order_id");
                List<FileDTO> files
                        = PhotoDAO.findPhotos(categoryId, orderId);

                orders.add(
                    new OrderDetailDTO(
                            userId,
                            rs.getInt("category_id"),
                            rs.getString("category_name"),
                            rs.getLong("higher_level_sub_category_id"),
                            rs.getString("higher_level_sub_category_name"),
                            rs.getLong("middle_level_sub_category_id"),
                            rs.getString("middle_level_sub_category_name"),
                            rs.getLong("lower_level_sub_category_id"),
                            rs.getString("lower_level_sub_category_name"),
                            rs.getLong("order_id"),
                            rs.getString("order_type"),
                            rs.getString("description"),
                            rs.getString("narration"),
                            new Date( rs.getTimestamp("order_date").getTime()),
                            new Date( rs.getTimestamp("available_date").getTime()),
                            rs.getInt("country_id"),
                            rs.getString("country_name"),
                            rs.getString("photo_path"),
                            "" + rs.getInt("category_id") + "a"
                                        + rs.getLong("higher_level_sub_category_id") + "a"
                                        + rs.getLong("middle_level_sub_category_id") + "a"
                                        + rs.getLong("lower_level_sub_category_id") + "a"
                                        + rs.getLong("order_id"),
                            files));
            }

            return orders;
        } catch (SQLException e ) {
            e.printStackTrace();
            throw new InternalSystemException("System error occurred. Please try again.");
        } finally {
            try {rs.close();} catch (Exception e){}
            try {stmt.close();} catch (Exception e){}
            try {conn.close();} catch (Exception e){}
        }
    }

    /*
    public static void insertProductOrder( ProductOrderDTO order )
            throws SQLException {

        final String CREATE_ORDER_SQL =

                "   INSERT INTO orders ( category_id,                                                               " +
                "                        higher_level_sub_category_id, middle_level_sub_category_id,                " +
                "                        lower_level_sub_category_id, user_id, order_date, order_type  )            " +
                "   VALUES             ( ?, ?, ?, ?, ?, ?, ? )                                                      " +
                "   RETURNING order_id                                                                              ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement( CREATE_ORDER_SQL );
            stmt.setInt(1, order.getCategoryId());
            stmt.setLong(2, order.getHigherLevelSubCategoryId());
            stmt.setLong(3, order.getMiddleLevelCategoryId());
            stmt.setLong(4, order.getLowLevelSubCategoryId());
            stmt.setLong(5, order.getUserId());
            stmt.setTimestamp(6, new Timestamp(order.getOrderDate().getTime()));
            stmt.setString(7, order.getOrderType());

            rs = stmt.executeQuery();
            Long orderId = 0L;
            if (rs.next()) {
                orderId = rs.getLong(1);
            }

            // order details

            final String CREATE_SERVICE_ORDER_SQL =

                "   INSERT INTO product_orders ( category_id, higher_level_sub_category_id,                         " +
                "                               middle_level_sub_category_id, lower_level_sub_category_id,          " +
                "                               order_id, description, narration   )                                " +
                "   VALUES                    ( ?, ?, ?, ?, ?, ?, ? )                                               ";
            stmt = conn.prepareStatement( CREATE_SERVICE_ORDER_SQL );
            stmt.setInt(1, order.getCategoryId());
            stmt.setLong(2, order.getHigherLevelSubCategoryId());
            stmt.setLong(3, order.getMiddleLevelCategoryId());
            stmt.setLong(4, order.getLowLevelSubCategoryId());
            stmt.setLong(5, order.getOrderId());
            stmt.setString(6, order.getDescription());
            stmt.setString(7, order.getNarration());

            stmt.executeUpdate();

        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }

    public static void insertVehicleOrder(VehicleOrderDTO order )
            throws SQLException {

        final String CREATE_ORDER_SQL =

                "   INSERT INTO orders ( category_id,                                                               " +
                "                        higher_level_sub_category_id, middle_level_sub_category_id,                " +
                "                        lower_level_sub_category_id, user_id, order_date, order_type  )            " +
                "   VALUES             ( ?, ?, ?, ?, ?, ?, ? )                                                      " +
                "   RETURNING order_id                                                                              ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement( CREATE_ORDER_SQL );
            stmt.setInt(1, order.getCategoryId());
            stmt.setLong(2, order.getHigherLevelSubCategoryId());
            stmt.setLong(3, order.getMiddleLevelCategoryId());
            stmt.setLong(4, order.getLowLevelSubCategoryId());
            stmt.setLong(5, order.getUserId());
            stmt.setTimestamp(6, new Timestamp(order.getOrderDate().getTime()));
            stmt.setString(7, order.getOrderType());

            rs = stmt.executeQuery();
            Long orderId = 0L;
            if (rs.next()) {
                orderId = rs.getLong(1);
            }

            // order details

            final String CREATE_ORDER_DETAIL_SQL =

                    "   INSERT INTO vehicle_orders ( category_id, higher_level_sub_category_id,                     " +
                    "                               middle_level_sub_category_id, lower_level_sub_category_id,      " +
                    "                               order_id, description, narration, country_id )                  " +
                    "   VALUES                    ( ?, ?, ?, ?, ?, ?, ?, ?)                                           ";
            stmt = conn.prepareStatement( CREATE_ORDER_DETAIL_SQL );
            stmt.setInt(1, order.getCategoryId());
            stmt.setLong(2, order.getHigherLevelSubCategoryId());
            stmt.setLong(3, order.getMiddleLevelCategoryId());
            stmt.setLong(4, order.getLowLevelSubCategoryId());
            stmt.setLong(5, order.getOrderId());
            stmt.setString(6, order.getDescription());
            stmt.setString(7, order.getNarration());
            stmt.setInt(8, order.getCountryId());

            stmt.executeUpdate();

        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }

    public static void insertMachineryOrder( MachineryOrderDTO order )
            throws SQLException {

        final String CREATE_ORDER_SQL =

                "   INSERT INTO orders ( category_id,                                                               " +
                        "                        higher_level_sub_category_id, middle_level_sub_category_id,                " +
                        "                        lower_level_sub_category_id, user_id, order_date, order_type  )            " +
                        "   VALUES             ( ?, ?, ?, ?, ?, ?, ? )                                                      " +
                        "   RETURNING order_id                                                                              ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement( CREATE_ORDER_SQL );
            stmt.setInt(1, order.getCategoryId());
            stmt.setLong(2, order.getHigherLevelSubCategoryId());
            stmt.setLong(3, order.getMiddleLevelCategoryId());
            stmt.setLong(4, order.getLowLevelSubCategoryId());
            stmt.setLong(5, order.getUserId());
            stmt.setTimestamp(6, new Timestamp(order.getOrderDate().getTime()));
            stmt.setString(7, order.getOrderType());

            rs = stmt.executeQuery();
            Long orderId = 0L;
            if (rs.next()) {
                orderId = rs.getLong(1);
            }

            // order details

            final String CREATE_SERVICE_ORDER_SQL =

                    "   INSERT INTO machinery_orders ( category_id, higher_level_sub_category_id,                         " +
                            "                               middle_level_sub_category_id, lower_level_sub_category_id,          " +
                            "                               order_id, description, narration   )                                " +
                            "   VALUES                    ( ?, ?, ?, ?, ?, ?, ? )                                               ";
            stmt = conn.prepareStatement( CREATE_SERVICE_ORDER_SQL );
            stmt.setInt(1, order.getCategoryId());
            stmt.setLong(2, order.getHigherLevelSubCategoryId());
            stmt.setLong(3, order.getMiddleLevelCategoryId());
            stmt.setLong(4, order.getLowLevelSubCategoryId());
            stmt.setLong(5, order.getOrderId());
            stmt.setString(6, order.getDescription());
            stmt.setString(7, order.getNarration());

            stmt.executeUpdate();

        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }
    */
}
