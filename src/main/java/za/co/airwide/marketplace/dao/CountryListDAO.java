package za.co.airwide.marketplace.dao;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.co.airwide.marketplace.dto.CountryDTO;
import za.co.airwide.marketplace.dto.HigherLevelSubCategoryDTO;
import za.co.airwide.marketplace.util.InternalSystemException;
import za.co.airwide.marketplace.util.ItemNotFoundException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryListDAO {

    /**
     * List Countries
     *
     * @return
     */
    public static List<CountryDTO> findAll()
            throws InternalSystemException, ItemNotFoundException {

        final String COUNTRY_LIST_SQL =

                "   SELECT country_id, country_name, status                                                         " +
                "     FROM countries                                                                                " +
                "    ORDER BY country_name                                                                          ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement(COUNTRY_LIST_SQL);
            rs = stmt.executeQuery();

            List<CountryDTO> items
                    = new ArrayList<>();

            while (rs.next()) {
                items.add(
                        new CountryDTO(
                                rs.getInt("country_id"),
                                rs.getString("country_name"),
                                rs.getInt("status")));
            }

            if (items.isEmpty()) {
                throw new ItemNotFoundException("No country found.");
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
//    /**
//     * refresh country list
//     */
//    private static void refresh() {
//
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
////                .url("https://nanosdk-countries-v1.p.rapidapi.com/countries?fields=alpha2%252Ccapital&alpha2=US")
//                .url("https://nanosdk-countries-v1.p.rapidapi.com/countries")
//                .get()
//                .addHeader("x-rapidapi-host", "nanosdk-countries-v1.p.rapidapi.com")
//                .addHeader("x-rapidapi-key", "fa4f61b495msh0e6123c83dab402p10bf15jsn771f7456958c")
//                .build();
//
//
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            Response response
//                    = client.newCall(request).execute();
////            System.out.println(response.body().toString());
//
//            String jsonData = response.body().string();
//            JSONObject Jobject = new JSONObject(jsonData);
//            JSONArray Jarray = Jobject.getJSONArray("countries");
//
//            for (int i = 0; i < Jarray.length(); i++) {
//                JSONObject object     = Jarray.getJSONObject(i);
//                System.out.println(object);
//                System.out.println(object.toString());
//            }
//
//            // insert country list into database
//
//            final String READ_USER_SQL =
//                    "  INSERT INTO countries( country_id int NOT NULL,\n" +
//                            "  country_name varchar(60) NOT NULL,                                                                              " ;
//            try {
//                conn = MarketPlaceDAO.getConnection();
//                stmt = conn.prepareStatement(READ_USER_SQL);
//                rs = stmt.executeQuery();
//                if (rs.next()) {
//                    String hashedPassword = rs.getString("password");
//                    System.out.println("returning: " + hashedPassword);
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        refresh();
//    }
}
