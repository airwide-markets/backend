package za.co.airwide.marketplace.dao;

import za.co.airwide.marketplace.dto.FileDTO;
import za.co.airwide.marketplace.dto.OrderDetailDTO;
import za.co.airwide.marketplace.dto.OrderKeyDTO;
import za.co.airwide.marketplace.util.InternalSystemException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static za.co.airwide.marketplace.util.FileUtil.getMimeType;

public class PhotoDAO {

    public static Long insert( Long categoryId, Long orderId )
                throws SQLException {

        final String INSERT_PHOTO_SQL =

                "   INSERT INTO photos ( category_id, order_id )                       " +
                "   VALUES             ( ?, ? )                                        " +
                "   RETURNING id                                                       ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = MarketPlaceDAO.getConnection();

            stmt = conn.prepareStatement( INSERT_PHOTO_SQL );
            stmt.setLong(1, categoryId );
            stmt.setLong(2, orderId );

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

    public static void update( Long categoryId, Long orderId, Long photoId, String path )
            throws SQLException {

        final String UPDATE_PHOTO_SQL =

                "   UPDATE photos SET photo_path = ?                                                         " +
                "    WHERE category_id = ?                                                                   " +
                "      AND order_id = ?                                                                      " +
                "      AND id = ?                                                                            ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement( UPDATE_PHOTO_SQL );
            stmt.setString(1, path);
            stmt.setLong(2, categoryId);
            stmt.setLong(3, orderId);
            stmt.setLong(4, photoId);

            stmt.executeUpdate();
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

    /*
    category_id                  | 1
    higher_level_sub_category_id |
    middle_level_sub_category_id |
    lower_level_sub_category_id  |
    order_id                     | 46
    id                           | 3
    photo_path                   | /opt/airwide/data/images/1-46-3-persona2.jpg

     */
    public static List<FileDTO> findPhotos(Long categoryId, Long orderId)
            throws InternalSystemException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = MarketPlaceDAO.getConnection();

            final String ORDER_DETAIL_LIST_SQL =

                    "    SELECT photo_path                                                                               " +
                            "      FROM photos                                                                                   " +
                            "     WHERE category_id = ?                                                                          " +
                            "       AND order_id = ?                                                                             " +
                            "     ORDER BY id ASC                                                                                " ;

            System.out.println( ORDER_DETAIL_LIST_SQL );

            stmt = conn.prepareStatement(ORDER_DETAIL_LIST_SQL);
            stmt.setLong(1, categoryId);
            stmt.setLong(2, orderId);

            rs = stmt.executeQuery();
            List<FileDTO> files
                    = new ArrayList<>();
            while (rs.next()) {

                String filename = rs.getString("photo_path");
                // /opt/airwide/data/images/1-48-8-persona1.jpg
                filename = filename != null ? filename.split("/")[5] : "";
                files.add(
                        new FileDTO( "/file-manager/download-thumbnail/" + filename,
                                filename,
                                "/file-manager/download-file/" + filename,
                                "GET",
                                getMimeType(filename),
                                "/file-manager/delete-file/" + filename,
                                0L));
            }

            return files;
        } catch (SQLException e ) {
            e.printStackTrace();
            throw new InternalSystemException("System error occurred. Please try again.");
        } finally {
            try {rs.close();} catch (Exception e){}
            try {stmt.close();} catch (Exception e){}
            try {conn.close();} catch (Exception e){}
        }
    }

    public static String findFirstPhoto(Long categoryId, Long orderId)
            throws InternalSystemException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = MarketPlaceDAO.getConnection();

            final String READ_FIRST_PHOTO_PATH_SQL =

                    "  SELECT split_part(photo_path,'/',6) AS photo_path                                    " +
                    "    FROM photos                                                                        " +
                    "   WHERE category_id = ?                                                               " +
                    "     AND order_id = ?                                                                  " +
                    "   ORDER BY id ASC                                                                     " +
                    "   LIMIT 1                                                                             " ;

            System.out.println( READ_FIRST_PHOTO_PATH_SQL );

            stmt = conn.prepareStatement(READ_FIRST_PHOTO_PATH_SQL);
            stmt.setLong(1, categoryId);
            stmt.setLong(2, orderId);

            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("photo_path");
            } else {
                return "white-sticky-note.png";
            }
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
