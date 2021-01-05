package za.co.airwide.marketplace.util;

import za.co.airwide.marketplace.dao.HigherLevelSubCategoryDAO;
import za.co.airwide.marketplace.dao.LowerLevelSubCategoryDAO;
import za.co.airwide.marketplace.dao.MiddleLevelSubCategoryDAO;
import za.co.airwide.marketplace.dto.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import static za.co.airwide.marketplace.dao.CategoryDAO.categories;
import static za.co.airwide.marketplace.dao.HigherLevelSubCategoryDAO.higherLevelSubCategoryMap;
import static za.co.airwide.marketplace.dao.SearchFieldDAO.persist;
import static za.co.airwide.marketplace.dao.SearchFieldDAO.searchFields;

public class FileImporter {

    // categories
    private final static Map<CategoryKeyDTO, Integer> KNOWN_CATEGORIES_MAP;
    private final static Set<CategoryKeyDTO> KNOWN_CATEGORY_KEYS;

    private final static Map<Integer, Map<HigherLevelSubCategoryKeyDTO, Long>>
                                CATEGORY_ID_TO_HIGHER_LEVEL_SUB_CATEGORIES_MAP;
    private final static Map<SearchFieldKeyDTO, Set<String>> SEARCH_FIELD_MAP;
    private final static Map<MiddleLevelSubCategoryNameKeyDTO, Long> MIDDLE_LEVEL_SUB_CATEGORY_NAME_CACHE;
    private final static Map<LowerLevelSubCategoryNameKeyDTO, Long> LOWER_LEVEL_SUB_CATEGORY_NAME_CACHE;

    static {

        KNOWN_CATEGORIES_MAP
                = categories();
        assert KNOWN_CATEGORIES_MAP != null;

        KNOWN_CATEGORY_KEYS
                = KNOWN_CATEGORIES_MAP.keySet();

        CATEGORY_ID_TO_HIGHER_LEVEL_SUB_CATEGORIES_MAP
                = higherLevelSubCategoryMap();

        SEARCH_FIELD_MAP
                = new HashMap<>();

        MIDDLE_LEVEL_SUB_CATEGORY_NAME_CACHE
                = new HashMap<>();

        LOWER_LEVEL_SUB_CATEGORY_NAME_CACHE
                = new HashMap<>();
    }

    public static void parse(String path) throws IOException, SQLException, InternalSystemException {

        Integer categoryId = null;
        AtomicLong higherLevelSubCategoryId = new AtomicLong();
        AtomicLong middleLevelSubCategoryId = new AtomicLong();
        AtomicLong lowerLevelSubCategoryId = new AtomicLong();

        BufferedReader in
                = new BufferedReader(new FileReader(path));
        String line = null;

        // LINE 1: request type / listing type
        line = in.readLine();
        System.out.println("\nLINE 1: " + line);
        String[] tokens =
                line.toLowerCase()
                        .split(";")[0]
                        .split("/");
        String requestType = tokens[0].trim();
        String listingType = tokens[1].trim();
        System.out.println("requestType: " + requestType + ", listingType: " + listingType );

        while ((line = in.readLine()) != null) {

            tokens = line.split(";");
            int colCount = tokens.length;
            System.out.println("\nLINE: token-count: " + colCount +
                    ", categoryName: " + (colCount > 0 ? tokens[0] : ""));
            // COLUMN 1: -----------------------------------------------------------------------------------------------

            // COLUMN 1 will always be categoryName

            String categoryName = tokens.length > 0 ? tokens[0].toLowerCase() : null;
            CategoryKeyDTO categoryKey =
                    new CategoryKeyDTO(categoryName, requestType, listingType);
            if ( categoryName != null
                    && KNOWN_CATEGORY_KEYS.contains(categoryKey) ) {

                categoryId = KNOWN_CATEGORIES_MAP.get( categoryKey );
                System.out.println("Processing categoryKey: " + categoryKey);
            } else {
                if (categoryId == null) {
                    continue;
                }
            }

            // COLUMN 2: -----------------------------------------------------------------------------------------------

            if ( colCount == 2 ) {
                // 2 columns means COLUMN 2: searchFieldName

                processSearchFieldColumn(
                        new SearchFieldKeyDTO(
                                categoryId,
                                requestType,
                                listingType),
                        tokens[1]);

            } else if (colCount > 2) {
                // 3 or more columns means COLUMN 2: higherLevelSubCategoryName
                String higherLevelSubCategoryName = tokens[1];
                processHigherLevelSubCategoryColumn(
                        new HigherLevelSubCategoryKeyDTO(
                                categoryId,
                                higherLevelSubCategoryName,
                                requestType,
                                listingType ),
                        higherLevelSubCategoryId );
            } else {
                // 1 column means nothing more to process

                continue;
            }

            // COLUMN 3: -----------------------------------------------------------------------------------------------

            if ( colCount == 3 ) {
                //  3 columns means COLUMN 3: searchFieldName

                processSearchFieldColumn( new SearchFieldKeyDTO(
                                                categoryId,
                                                higherLevelSubCategoryId.get(),
                                                requestType,
                                                listingType),
                                            tokens[2] );

            } else if (colCount > 3) {
                // 4 or more columns means COLUMN 3: middleLevelSubCategoryName

                processMiddleLevelSubCategoryColumn(
                        new MiddleLevelSubCategoryNameKeyDTO(
                                    categoryId,
                                    higherLevelSubCategoryId.get(),
                                    tokens[2],
                                    requestType,
                                    listingType ),
                        middleLevelSubCategoryId );
            } else {
                // 2 columns means nothing more to process
                continue;
            }

            // COLUMN 4: -----------------------------------------------------------------------------------------------

            if ( colCount == 4 ) {
                // 4 columns means COLUMN 4: searchFieldName

                processSearchFieldColumn(
                            new SearchFieldKeyDTO (
                                    categoryId,
                                    higherLevelSubCategoryId.get(),
                                    middleLevelSubCategoryId.get(),
                                    requestType,
                                    listingType ),
                            tokens[3] );

            } else if (colCount > 4) {
                // 5 columns means COLUMN 4: lowerLevelSubCategoryName

                processLowerLevelSubCategoryColumn(
                            new LowerLevelSubCategoryNameKeyDTO (
                                    categoryId,
                                    higherLevelSubCategoryId.get(),
                                    middleLevelSubCategoryId.get(),
                                    tokens[3],
                                    requestType,
                                    listingType ),
                            lowerLevelSubCategoryId);
            } else {
                // 3 columns means nothing more to process
                continue;
            }

            // COLUMN 5: -----------------------------------------------------------------------------------------------

            if ( colCount == 5 ) {
                // 5 columns means COLUMN 5: searchFieldName

                processSearchFieldColumn(
                            new SearchFieldKeyDTO(
                                    categoryId,
                                    higherLevelSubCategoryId.get(),
                                    middleLevelSubCategoryId.get(),
                                    lowerLevelSubCategoryId.get(),
                                    requestType,
                                    listingType),
                            tokens[4] );

            } else {
                // columns after 5th should be ignored
                continue;
            }
        }
    }

    private static void processSearchFieldColumn( SearchFieldKeyDTO key,
                                                  String searchFieldName )
            throws InternalSystemException {

        // get existing search fields from cache
        System.out.println(" Searching Search Field Cache for: " + key );
        Set<String> searchFields
                = SEARCH_FIELD_MAP.get( key );
        if (searchFields == null ) {
            // get existing search fields from database
            searchFields
                    = searchFields(key).keySet();
            // cache
            SEARCH_FIELD_MAP.put(key, searchFields);
        }

        // is this a new search field
        System.out.println(" Searching searchFieldName: " + searchFieldName );
        if ( ! searchFields.contains( searchFieldName ) ) {
            // persist search field
            SearchFieldDTO searchField =
                    new SearchFieldDTO(key, searchFieldName);
            try {
                System.out.println("Persisting searchField : " + searchField );
                persist(searchField);
            } catch (SQLException e ) {
                // TODO create an exception file and notify administrator
                e.printStackTrace();
            }
        }
    }

    private static void processHigherLevelSubCategoryColumn( HigherLevelSubCategoryKeyDTO key,
                                                             AtomicLong atomicHigherLevelSubCategoryId ) {

        if ( key.getHigherLevelSubCategoryName() != null
                && ! "".equals( key.getHigherLevelSubCategoryName() ) ) {

            // do we have this sub category
            System.out.println("Searching higherLevelSubCategory cache for categoryId: "
                                    + key );
            Map<HigherLevelSubCategoryKeyDTO, Long> higherLevelSubCategoriesForThisCategoryId
                            = CATEGORY_ID_TO_HIGHER_LEVEL_SUB_CATEGORIES_MAP.get( key.getCategoryId() );
            System.out.println("higherLevelSubCategoriesForThisCategoryId: "
                    + higherLevelSubCategoriesForThisCategoryId);

            // is this a new higher level sub category
            System.out.println("Processing higherLevelSubCategoryName: " + key.getHigherLevelSubCategoryName() );

            if (higherLevelSubCategoriesForThisCategoryId == null) {
                higherLevelSubCategoriesForThisCategoryId = new HashMap<>();
                CATEGORY_ID_TO_HIGHER_LEVEL_SUB_CATEGORIES_MAP.put(
                        key.getCategoryId(),
                        higherLevelSubCategoriesForThisCategoryId);
            }

            Long higherLevelSubCategoryId
                    = higherLevelSubCategoriesForThisCategoryId.get(key);
            System.out.println("Processing higherLevelSubCategoryId: " + higherLevelSubCategoryId);

            if (higherLevelSubCategoryId == null) {

                // persist this higher level sub category
                HigherLevelSubCategoryDTO item =
                        new HigherLevelSubCategoryDTO(
                                    key.getCategoryId(),
                                    key.getHigherLevelSubCategoryName(),
                                    key.getRequestType(),
                                    key.getListingType());
                System.out.println("Persisting: " + item);
                // TODO fix duplicate bug - attempt to create two items
                higherLevelSubCategoryId
                        = HigherLevelSubCategoryDAO.persist( item );
                System.out.println("Created higherLevelSubCategoryId: " + higherLevelSubCategoryId);
                higherLevelSubCategoriesForThisCategoryId.put(key, higherLevelSubCategoryId);
            }

            atomicHigherLevelSubCategoryId.set( higherLevelSubCategoryId );
        }
    }

    /**
     * processMiddleLevelSubCategoryColumn
     *
     * @param key
     * @param atomicMiddleLevelSubCategoryId
     */
    private static void processMiddleLevelSubCategoryColumn( MiddleLevelSubCategoryNameKeyDTO key,
                                                             AtomicLong atomicMiddleLevelSubCategoryId )
                throws SQLException {

        String middleLevelSubCategoryName
                = key.getMiddleLevelSubCategoryName();

        if ( middleLevelSubCategoryName != null
                && ! "".equals( middleLevelSubCategoryName ) ) {

            // do we have this middleLevelSubCategoryName in cache
            Long middleLevelSubCategoryId
                    = MIDDLE_LEVEL_SUB_CATEGORY_NAME_CACHE.get( key );

            // is this a new higher level sub category
            if ( middleLevelSubCategoryId == null) {

                // persist this middle level sub category
                middleLevelSubCategoryId
                        = MiddleLevelSubCategoryDAO.persist( key.getCategoryId(),
                                                             key.getHigherLevelSubCategoryId(),
                                                             middleLevelSubCategoryName,
                                                             key.getRequestType(),
                                                             key.getListingType());
                // update cache
                MIDDLE_LEVEL_SUB_CATEGORY_NAME_CACHE.put(key, middleLevelSubCategoryId );
            }

            atomicMiddleLevelSubCategoryId.set( middleLevelSubCategoryId );
        }
    }

    private static void processLowerLevelSubCategoryColumn (
                                                LowerLevelSubCategoryNameKeyDTO key,
                                                AtomicLong atomicLowerLevelSubCategoryId )
                throws SQLException {

        String lowerLevelSubCategoryName
                = key.getLowerLevelSubCategoryName();

        if ( lowerLevelSubCategoryName != null
                && ! "".equals( lowerLevelSubCategoryName ) ) {

            // do we have this middleLevelSubCategoryName in cache
            Long lowerLevelSubCategoryId
                    = LOWER_LEVEL_SUB_CATEGORY_NAME_CACHE.get( key );

            // is this a new lower level sub category
            if ( lowerLevelSubCategoryId == null) {

                // persist this lower level sub category
                lowerLevelSubCategoryId
                        = LowerLevelSubCategoryDAO
                                .persist(
                                    key.getCategoryId(),
                                    key.getHigherLevelSubCategoryId(),
                                    key.getMiddleLevelSubCategoryId(),
                                    lowerLevelSubCategoryName,
                                    key.getRequestType(),
                                    key.getListingType());
                // update cache
                LOWER_LEVEL_SUB_CATEGORY_NAME_CACHE.put(key, lowerLevelSubCategoryId );
            }

            atomicLowerLevelSubCategoryId.set( lowerLevelSubCategoryId );
        }
    }

    public static void main(String[] args)
            throws IOException, SQLException, InternalSystemException {
        parse(args[0]);
    }
}
