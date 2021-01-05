package za.co.airwide.marketplace;

import io.jsonwebtoken.Claims;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.imgscalr.Scalr;
import org.json.JSONObject;
import org.mindrot.BCrypt;
import za.co.airwide.marketplace.dao.*;
import za.co.airwide.marketplace.dto.*;
import za.co.airwide.marketplace.messaging.SendMailService;
import za.co.airwide.marketplace.messaging.SendSMS;
import za.co.airwide.marketplace.model.ScheduleDTO;
import za.co.airwide.marketplace.model.UserDTO;
import za.co.airwide.marketplace.util.*;

import javax.imageio.ImageIO;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import static javax.ws.rs.core.Response.Status.*;
import static za.co.airwide.marketplace.util.FileUtil.getMimeType;

@Path("/airwide")
public class MarketPlaceService {

    // array of supported extensions
    static final String[] EXTENSIONS = new String[] { "jpg", "jpeg", "gif", "png", "bmp" };

    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };

    /*
    @GET
    @Path("{path:.*}")
    @Produces("image/*")
    public Response getFile(@PathParam("path") String path, @Context ServletContext context) {
        System.out.println("path:: " + format("/WEB-INF/%s", path));
        InputStream resource = context.getResourceAsStream(format("/WEB-INF/%s", path));
        return null == resource
                ? Response.status(NOT_FOUND).build()
                : Response.ok().entity(resource).build();
    }
    */
//    public Response matches() {

        /*
                try{
            $payload = JWT::decode($this->getToken(), SECRETE_KEY, ['HS256']);
            $id = $payload->userId;
            $services = $this->countMatches(1, $id);
            $accommodation = $this->countMatches(2, $id);
            $jobs = $this->countMatches(3, $id);
            $vehicles = $this->countMatches(4, $id);
            return array(
                'services' => $services, 'accommodation' => $accommodation, 'jobs' => $jobs, 'vehicle' => $vehicles
            );
        }catch (\Exception $e){
            return array(
                'success' => false,
                'statusCode' => INTERNAL_SERVER_ERROR,
                'error' => array('type' => 'PROCESS_SERVER_ERROR', 'message' => $e->getMessage())
            );
        }

                $sql = "SELECT * FROM `matches` WHERE `user` = '$id' AND  `category` = '$cat' AND `status` = '0'";
        $qry = mysqli_query($this->con, $sql);
        return mysqli_num_rows($qry);
         */
//        return null;
//    }

    /*
        ListingDTO item = null;
        if (item.getCategoryId() != 2 ) {
            item.setNarrative(rs.getString("narrative"));
        }

        if (Arrays.asList(2, 4).contains(item.getCategoryId())) {
            item.setPrice(rs.getDecimal("sell_price"));
        }

        String requestTypeDescription = REQUEST_TYPES.get( item.getRequestType() )
        */

            /*
                            'id' => $rs['id'],
                            'type' => $rs['type'],
                            'parent' => $rs['parent_id'],
                            'name' => $rs['name'],
                            'details' => $rs['description']
         */

    @GET
    @Path("/listing/{user-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listing(@PathParam("user-id") long userId) {

        List<ListingDTO> listing = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = MarketPlaceDAO.getConnection();

            // requests
            final String REQUEST_LIST_SQL =

                    "    SELECT r.id, r.user,                                                                       " +
                    "               c.id AS category_id, c.name AS category_name,                                   " +
                    "               c.description AS category_description, c.icon AS category_icon,                 " +
                    "               r.name,                                                                         " +
                    "               t.id AS request_type_id, t.name AS request_type_name,                           " +
                    "               t.description AS request_type_description,                                      " +
                    "               r.added AS date_created, post                                                   " +
                    "      FROM request r                                                                           " +
                    "      JOIN categories c                                                                        " +
                    "        ON ( r.category = c.id )                                                               " +
                    "      JOIN request_type t                                                                      " +
                    "        ON ( r.type = t.id )                                                                   " +
                    "     WHERE r.user = ?                                                                          " +
                    "     ORDER BY r.added DESC                                                                     ";

            stmt = conn.prepareStatement( REQUEST_LIST_SQL );
            stmt.setLong(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                // get listing details
                ListingDetailsDTO listingDetails =
                        listingDetails(
                                rs.getInt("category_id"),
                                rs.getLong("id"),
                                conn );
                listing.add(
                        new ListingDTO(
                                "request",
                                rs.getLong("id"),
                                rs.getLong("user"),
                                new CategoryDTO(
                                        rs.getInt("category_id"),
                                        rs.getString("category_name"),
                                        rs.getString("category_description"),
                                        rs.getString("category_icon")),
                                rs.getString("name"),
                                new RequestTypeDTO(
                                        rs.getInt("request_type_id"),
                                        rs.getString("request_type_name"),
                                        rs.getString("request_type_description")),
                                listingDetails,
                                new Date(rs.getTimestamp("date_created").getTime()),
                                rs.getInt("post")));
            }
            return Response
                    .status(OK)
                    .header("success", true)
                    .header("message", "Listing read.")
                    .entity(listing)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .header("message", "Service Listing Failed. Error: " + e.getMessage())
                    .build();
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }

    /**
     * listingDetails
     * @param categoryId
     * @param listingId
     */
    private ListingDetailsDTO listingDetails(int categoryId, long listingId, Connection conn)
            throws SQLException {

        switch (categoryId) {
            case 1:
                return service(listingId, conn);
            case 2:
                return accommodation(listingId, conn);
            case 3:
                return jobs(listingId, conn);
            case 4:
                return vehicles(listingId, conn);
            default:
                throw new SQLException("Category ID: " + categoryId + " Not Found.");
        }
    }

    /**
     * service
     * @param listingId
     * @return
     */
    private ListingDetailsDTO service(long listingId, Connection conn)
            throws SQLException {

        final String SERVICE_SQL =
                "   SELECT id, type, parent_id, name, description                                                   " +
                "     FROM services                                                                                 " +
                "    WHERE type = ?                                                                                 " +
                "      AND listing_id = ?                                                                           ";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement( SERVICE_SQL );
            stmt.setString(1, "Request");
            stmt.setLong(2, listingId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new ServiceListingDetailsDTO (
                        rs.getLong("id"),
                        rs.getInt("parent_id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("description"));
            } else {
                return new ServiceListingDetailsDTO();
            }
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
        }
    }

    @POST
    @Path("/post/order")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postOrder( OrderDTO order ) {

        try {

            order.setCategoryId(
                    CategoryDAO.findIdByName(
                            order.getCategoryName(),
                            order.getOrderType() ));

            order.setUserId (
                    userId( order.getUserToken() ) );

            Long orderId = OrderDAO.insert( order );
            order.setOrderId( orderId );
            List<za.co.airwide.marketplace.dto.UserDTO> matchingOrders
                    = MatchedOrderDAO.findMatchingPartners(order);

//            boolean matchesFound = false;
            // notify matching orders partners if any
            za.co.airwide.marketplace.dto.UserDTO user;
            if (matchingOrders.size() > 0) {

//                matchesFound = true;
                user = UserDAO.findById(order.getUserId());


                for ( za.co.airwide.marketplace.dto.UserDTO partner : matchingOrders) {
                    String textMessage =
                            order.getDescription() + " Order has been matched to: \n" + user.getMobileName() +
                                    ", " + user.getName() + " " + user.getSurname() +
                                    ". \n\nVisit www.eworldmix.com for details. ";
                    // notify partners
                    try {
                        System.out.println("partner.getNotificationEmail : " + partner.getNotificationEmail());
                        if ( partner.getNotificationEmail() != null
                                && ! "".equals(partner.getNotificationEmail().trim()) ) {

                            sendMessage(partner.getNotificationEmail(),
                                    user.getNotificationEmail(),
                                    partner.getName() + " " + partner.getSurname(),
                                    user.getMobileName(), partner.getMobileName(),
                                    "eWorldMix Order Match Found", textMessage);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // notify self
                    try {
                        textMessage =
                                order.getDescription() + " Order has been matched to: " + partner.getMobileName() +
                                        ", " + partner.getName() + " " + partner.getSurname() +
                                        ". Visit www.eworldmix.com for details. ";
                        System.out.println("user.getNotificationEmail : " + user.getNotificationEmail());
                        if (user.getNotificationEmail() != null
                                && !"".equals(user.getNotificationEmail().trim())) {
                            sendMessage(user.getNotificationEmail(),
                                    partner.getNotificationEmail(),
                                    user.getName() + " " + user.getSurname(),
                                    partner.getMobileName(), user.getMobileName(),
                                    "eWorldMix Order Match Found", textMessage);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return Response
                    .status(OK)
                    .header("success", true)
                    .entity(order)
                    .build();
        } catch (SQLException e) {
            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (Exception e) {
            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /*
    @POST
    @Path("/upload/order")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public void uploadFile( @FormDataParam("file") InputStream fileInputStream,
                            @FormDataParam("file") FormDataContentDisposition fileMetaData,
                            @Context ServletContext context) {

        System.out.println("uploadFile ...");
        String filename = fileMetaData.getFileName();

//        System.out.println("path:: " + format("/WEB-INF/%s", filename));
//        Set<String> paths = context.getResourcePaths("/WEB-INF/public/images/thumbnails/");
//        Iterator<String> i
//                = paths.iterator();
//        String path = "";
//        if (i.hasNext()) {
//            path = i.next();

            try {
                int read = 0;
                byte[] bytes = new byte[1024];

                OutputStream out = new FileOutputStream(
                        new File("/opt/airwide/data/images/thumbnails/" + filename));
                while ((read = fileInputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                out.flush();
                out.close();
//                return JaxRsResponseFactory.success("File load successful." );
            } catch (IOException e) {
                e.printStackTrace();
                throw new WebApplicationException("Error while uploading file. Please try again !!");
            }
//        } else {
//             System.out.println("File load aborted. Destination path doesnt exist " );
//        }
    }

    @FormDataParam("files") List<FormDataBodyPart>
    */
    // http://eworldmix.com/airwide-marketplace-backend/rest/airwide/file-manager/download-thumbnail/persona1.jpg
    @GET
    @Path("/file-manager/{command}/{filename}")
    @Produces("image/png")
    public void fileManager( @PathParam("command") String command,
                             @PathParam("filename") String filename,
                             @Context HttpServletResponse response)
            throws IOException {

        System.out.println("file manager / " + command + "/" + filename);

        final String FILE_PATH = "/opt/airwide/data/images/" + filename;

        File file = new File(FILE_PATH);

        /*
        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
                "attachment; filename=image_from_server.png");
        return response.build();
        */

        if (file.exists()) {

            if ( "download-file".equals( command ) ) {

                int bytes = 0;
                ServletOutputStream op = response.getOutputStream();

                response.setContentType(getMimeType(file.getName()));
                response.setContentLength((int) file.length());
                response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");

                byte[] bbuf = new byte[1024];
                DataInputStream in = new DataInputStream(new FileInputStream(file));

                while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
                    op.write(bbuf, 0, bytes);
                }

                in.close();
                op.flush();
                op.close();
            } else if ( "download-thumbnail".equals( command )) {
                System.out.println(file.getAbsolutePath());
                String mimetype = getMimeType(file.getName());
                if (    mimetype.endsWith("png")
                     || mimetype.endsWith("jpeg")
                     || mimetype.endsWith("jpg")
                     || mimetype.endsWith("gif")) {

                    BufferedImage bufferedImage = ImageIO.read(file);

                    if ( bufferedImage != null ) {

                        BufferedImage thumb = Scalr.resize(bufferedImage, 75);
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        if (mimetype.endsWith("png")) {
                            ImageIO.write(thumb, "PNG" , os);
                            response.setContentType("image/png");
                        } else if (mimetype.endsWith("jpeg")) {
                            ImageIO.write(thumb, "jpg" , os);
                            response.setContentType("image/jpeg");
                        } else if (mimetype.endsWith("jpg")) {
                            ImageIO.write(thumb, "jpg" , os);
                            response.setContentType("image/jpeg");
                        } else {
                            ImageIO.write(thumb, "GIF" , os);
                            response.setContentType("image/gif");
                        }
                        ServletOutputStream srvos = response.getOutputStream();
                        response.setContentLength(os.size());
                        response.setHeader( "Content-Disposition", "inline; filename=\"" + file.getName() + "\"" );
                        os.writeTo(srvos);
                        srvos.flush();
                        srvos.close();
                    }
                }
            } else if ("delete-file".equals( command ) ) {
                file.delete(); // TODO:check and report success
                PrintWriter writer = response.getWriter();
                writer.write("deleted");
            }
        } else {
            PrintWriter writer = response.getWriter();
            writer.write("call POST with multipart form data");
        }
        /*
        if (request.getParameter("getfile") != null && !request.getParameter("getfile").isEmpty()) {
            File file = new File(request.getServletContext().getRealPath("/")+"imgs/"+request.getParameter("getfile"));
            if (file.exists()) {
                int bytes = 0;
                ServletOutputStream op = response.getOutputStream();

                response.setContentType(getMimeType(file));
                response.setContentLength((int) file.length());
                response.setHeader( "Content-Disposition", "inline; filename=\"" + file.getName() + "\"" );

                byte[] bbuf = new byte[1024];
                DataInputStream in = new DataInputStream(new FileInputStream(file));

                while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
                    op.write(bbuf, 0, bytes);
                }

                in.close();
                op.flush();
                op.close();
            }
        } else if (request.getParameter("delfile") != null && !request.getParameter("delfile").isEmpty()) {
            File file = new File(request.getServletContext().getRealPath("/")+"imgs/"+ request.getParameter("delfile"));
            if (file.exists()) {
                file.delete(); // TODO:check and report success
            }
        } else if (request.getParameter("getthumb") != null && !request.getParameter("getthumb").isEmpty()) {
            File file = new File(request.getServletContext().getRealPath("/")+"imgs/"+request.getParameter("getthumb"));
            if (file.exists()) {
                System.out.println(file.getAbsolutePath());
                String mimetype = getMimeType(file);
                if (mimetype.endsWith("png") || mimetype.endsWith("jpeg")|| mimetype.endsWith("jpg") || mimetype.endsWith("gif")) {
                    BufferedImage im = ImageIO.read(file);
                    if (im != null) {
                        BufferedImage thumb = Scalr.resize(im, 75);
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        if (mimetype.endsWith("png")) {
                            ImageIO.write(thumb, "PNG" , os);
                            response.setContentType("image/png");
                        } else if (mimetype.endsWith("jpeg")) {
                            ImageIO.write(thumb, "jpg" , os);
                            response.setContentType("image/jpeg");
                        } else if (mimetype.endsWith("jpg")) {
                            ImageIO.write(thumb, "jpg" , os);
                            response.setContentType("image/jpeg");
                        } else {
                            ImageIO.write(thumb, "GIF" , os);
                            response.setContentType("image/gif");
                        }
                        ServletOutputStream srvos = response.getOutputStream();
                        response.setContentLength(os.size());
                        response.setHeader( "Content-Disposition", "inline; filename=\"" + file.getName() + "\"" );
                        os.writeTo(srvos);
                        srvos.flush();
                        srvos.close();
                    }
                }
            } // TODO: check and report success
        } else {
            PrintWriter writer = response.getWriter();
            writer.write("call POST with multipart form data");
        }
        */
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/upload/order/{categoryId}/{orderId}")
    public Response uploadFile( @Context HttpServletRequest request,
                                @PathParam("categoryId") Long categoryId,
                                @PathParam("orderId") Long orderId) {

        System.out.println("uploadFile() categoryId : " + categoryId + ", orderId: " + orderId);

        final String FILE_UPLOAD_PATH = "/opt/airwide/data/images";
        String name = null;
        int code = 200;
        // String msg = "Files uploaded successfully";
        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            try {
                List<FileDTO> files = new ArrayList<>();
                List<FileItem> items = fileUpload.parseRequest(request);
                if (items != null) {
                    Iterator<FileItem> iter = items.iterator();
                    /*
                     * Return true if the instance represents a simple form
                     * field. Return false if it represents an uploaded file.
                     */
                    while (iter.hasNext()) {
                        final FileItem item = iter.next();
                        final String itemName = item.getName();
                        final String fieldName = item.getFieldName();
                        final String fieldValue = item.getString();
                        if (item.isFormField()) {
                            name = fieldValue;
                            System.out.println("Field Name: " + fieldName + ", Field Value: " + fieldValue);
                            System.out.println("Candidate Name: " + name);
                        } else {

                            long photoId = PhotoDAO.insert(categoryId, orderId);
                            String photoPath = itemName.replaceAll(" ", "_");
                            photoPath = FILE_UPLOAD_PATH + File.separator +
                                    categoryId + "-" + orderId + "-" + photoId + "-" + photoPath;

                            final File file = new File( photoPath );
                            File dir = file.getParentFile();
                            if(!dir.exists()) {
                                dir.mkdir();
                            }

                            // if(!file.exists()) {
                            //    file.createNewFile();
                            //}
                            System.out.println("Saving the file: " + file.getName());
                            item.write(file);

                            PhotoDAO.update(categoryId, orderId, photoId, photoPath);
                            /*
                            JSONObject jsono = new JSONObject();
                            jsono.put("name", item.getName());
                            jsono.put("size", item.getSize());
                            jsono.put("url", "UploadServlet?getfile=" + item.getName());
                            jsono.put("thumbnail_url", "UploadServlet?getthumb=" + item.getName());
                            jsono.put("delete_url", "UploadServlet?delfile=" + item.getName());
                            jsono.put("delete_type", "GET");
                            */

                            files.add(
                                    new FileDTO( "/file-manager/download-thumbnail/" + item.getName(),
                                                 item.getName(),
                                                "/file-manager/download-file/" + item.getName(),
                                            "GET",
                                                getMimeType(item.getName()),
                                            "/file-manager/delete-file/" + item.getName(),
                                            item.getSize()));
//                            System.out.println(json.toString());
                        }
                    }
                }

                /*
                https://jquery-file-upload.appspot.com/
                { "files":[ { "thumbnailUrl":"https://jquery-file-upload.appspot.com/image%2Fjpeg/4327990346065070359/persona2.jpg.80x80.png",
                              "name":"persona2.jpg",
                              "url":"https://jquery-file-upload.appspot.com/image%2Fjpeg/4327990346065070359/persona2.jpg",
                              "deleteType":"DELETE",
                              "type":"image/jpeg",
                              "deleteUrl":"https://jquery-file-upload.appspot.com/image%2Fjpeg/4327990346065070359/persona2.jpg",
                              "size":52197
                              }
                            ]
                         }
                 */

                FileUploadData data
                        = new FileUploadData(files);

                System.out.println("entity: " + data);

                return Response.status(code).entity(data).build();

            } catch (FileUploadException e) {
                code = 404;
                // msg = e.getMessage();
                e.printStackTrace();
                return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                code = 404;
                // msg = e.getMessage();
                return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }

        return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, "No upload files");
    }
        /*
        System.out.println("path: " + request.getServletPath());
        if (!ServletFileUpload.isMultipartContent(request)) {

            System.out.println("isMultipartContent false. Skipping");

            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }
        System.out.println("isMultipartContent true. Processing");

        PrintWriter writer = null;
        JSONArray json = new JSONArray();

        try {

            ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
            writer = response.getWriter();
            response.setContentType("application/json");

            List<FileItem> items = uploadHandler.parseRequest(request);
            System.out.println("items: " + items.size());
            for (FileItem item : items) {
                System.out.println("item is form field: " + item.isFormField());
                if (!item.isFormField()) {

                    System.out.println("processing item ");

                    System.out.println("path: " + request.getServletPath() + "imgs/" + item.getName());
                    File file = new File(request.getServletPath() + "imgs/", item.getName());
                    item.write(file);
                    JSONObject jsono = new JSONObject();
                    jsono.put("name", item.getName());
                    jsono.put("size", item.getSize());
                    jsono.put("url", "UploadServlet?getfile=" + item.getName());
                    jsono.put("thumbnail_url", "UploadServlet?getthumb=" + item.getName());
                    jsono.put("delete_url", "UploadServlet?delfile=" + item.getName());
                    jsono.put("delete_type", "GET");
                    json.put(jsono);
                    System.out.println(json.toString());
                } else {
                    System.out.println("skipping item ");

                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try { writer.write(json.toString()); } catch (Exception e){}
            try { writer.close(); } catch (Exception e){}
        }
        */
   // }

    // save uploaded file to new location
    private void writeToFile(InputStream uploadedInputStream,
                             String uploadedFileLocation) {

        try {
            OutputStream out = new FileOutputStream(new File(
                    uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    @POST
    @Path("/delete/order")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOrder( OrderKeyDTO orderKey ) {

        try {
            OrderDAO.delete( orderKey );
            return JaxRsResponseFactory.success("Order successful deleted. ");
        } catch (SQLException e) {
            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (Exception e) {
            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

//    @POST
//    @Path("/post/product-order")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response postProductOrder( ProductOrderDTO productOrder ) {
//
//        try {
//
//            productOrder.setCategoryId(
//                    CategoryDAO.findIdByName(
//                            productOrder.getCategoryName(),
//                            productOrder.getOrderType() ));
//
//            productOrder.setUserId (
//                    userId( productOrder.getUserToken() ) );
//
//            OrderDAO.insertProductOrder( productOrder );
//            return JaxRsResponseFactory.success("Product listing successful.");
//        } catch (SQLException e) {
//            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
//        } catch (Exception e) {
//            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
//        }
//    }

//    @POST
//    @Path("/post/machinery-order")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response postMachineryOrder( MachineryOrderDTO machineryOrder ) {
//
//        try {
//
//            machineryOrder.setCategoryId(
//                    CategoryDAO.findIdByName(
//                            machineryOrder.getCategoryName(),
//                            machineryOrder.getOrderType() ));
//
//            machineryOrder.setUserId (
//                    userId( machineryOrder.getUserToken() ) );
//
//            OrderDAO.insertMachineryOrder( machineryOrder );
//            return JaxRsResponseFactory.success("Machinery listing successful.");
//        } catch (SQLException e) {
//            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
//        } catch (Exception e) {
//            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
//        }
//    }

//    @POST
//    @Path("/post/vehicle-order")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response postVehicleOrder( VehicleOrderDTO vehicleOrder ) {
//
//        try {
//
//            vehicleOrder.setCategoryId(
//                    CategoryDAO.findIdByName(
//                            vehicleOrder.getCategoryName(),
//                            vehicleOrder.getOrderType() ));
//
//            vehicleOrder.setUserId (
//                    userId( vehicleOrder.getUserToken() ) );
//
//            OrderDAO.insertVehicleOrder( vehicleOrder );
//            return JaxRsResponseFactory.success("Vehicle listing successful.");
//        } catch (SQLException e) {
//            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
//        } catch (Exception e) {
//            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
//        }
//    }

    @GET
    @Path("/calculate/category-count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response categoryCount( ) {

        try {

            Map<String, Long> categories
                    = MatchCounterDAO.categoryCount();

            return JaxRsResponseFactory.success("Category count calculated successfully.", categories);
        } catch (InternalSystemException e) {
            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GET
    @Path("/find/category-items/{categoryName}/{pageNumber}/{itemCount}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response categoryItems( @PathParam("categoryName") String categoryName,
                                   @PathParam("pageNumber") Integer pageNumber,
                                   @PathParam("itemCount") Integer itemCount ) {

        try {

            List<CategoryItemDTO> categories
                    = MatchCounterDAO.categoryItems(categoryName, pageNumber, itemCount);

            return JaxRsResponseFactory.success("Category items listing successfully.", categories);
        } catch (InternalSystemException e) {
            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GET
    @Path("/calculate/listing-count/{userToken}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listingCount( @PathParam("userToken") String userToken) {

        try {

            Long userId =
                    userId( userToken );

            Map<String, Long> matches
                    = MatchCounterDAO.countListing(userId);

            return JaxRsResponseFactory.success("Listing count calculated successfully.", matches);
        } catch (InternalSystemException e) {
            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GET
    @Path("/calculate/match-count/{userToken}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response matchCount( @PathParam("userToken") String userToken) {

        try {

            Long userId =
                    userId( userToken );

            Map<String, Long> matches
                    = MatchCounterDAO.countMatches(userId);

            return JaxRsResponseFactory.success("Matches calculated successfully.", matches);
        } catch (InternalSystemException e) {
            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

//    @GET
//    @Path("/find/matching-orders/{userToken}/{categoryName}/{partnerOrderType}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response findMatchingOrders(
//                            @PathParam("userToken") String userToken,
//                            @PathParam("categoryName") String categoryName,
//                            @PathParam("partnerOrderType") String partnerOrderType) {
//
//        try {
//
//            Long userId =
//                    userId( userToken );
//            System.out.println("userId: " + userId + ", categoryName: " + categoryName);
//            Map<String, List<MatchedOrderDTO>> matchingOrders
//                    = MatchedOrderDAO.findMatchingOrders(categoryName, userId, partnerOrderType);
//            System.out.println("matchingOrders: " + matchingOrders.size());
//
//            return JaxRsResponseFactory.success("Matches retrieved successfully.", matchingOrders);
//        } catch (InternalSystemException e) {
//            e.printStackTrace();
//            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
//        }
//    }

    @GET
    @Path("/find/matching-orders/{userToken}/{categoryName}/{partnerOrderType}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findMatchingOrders(
            @PathParam("userToken") String userToken,
            @PathParam("categoryName") String categoryName,
            @PathParam("partnerOrderType") String partnerOrderType) {

        try {

            Long userId =
                    userId( userToken );
            System.out.println("userId: " + userId + ", categoryName: " + categoryName
                    + ", partnerOrderType: " + partnerOrderType);
            List<MatchedOrderDTO> matchingOrders
                    = MatchedOrderDAO.findMatchingOrders(categoryName, userId, partnerOrderType);
            System.out.println("matchingOrders: " + matchingOrders.size());

            return JaxRsResponseFactory.success("Matches retrieved successfully.", matchingOrders);
        } catch (InternalSystemException e) {
            e.printStackTrace();
            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GET
    @Path("/find/listing/{userToken}/{categoryName}/{orderType}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOrders(
            @PathParam("userToken") String userToken,
            @PathParam("categoryName") String categoryName,
            @PathParam("orderType") String orderType) {

        try {

            Long userId =
                    userId( userToken );
            System.out.println("userId: " + userId );
            List<OrderDetailDTO> orders
                    = OrderDAO.findOrders(userId, categoryName, orderType);
            System.out.println("orders: " + orders.size());

            return JaxRsResponseFactory.success("Orders retrieved successfully.", orders);
        } catch (InternalSystemException e) {
            e.printStackTrace();
            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @POST
    @Path("/accommodation/list")
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccommodationList(RequestDTO request) {

        // parse token
        String userToken = request.getUserToken();
        Claims claims
                = JavaWebToken.parseJWT(userToken);

        Long userId = Long.parseLong(claims.getId());

        final String CREATE_REQUEST_SQL =

                "   INSERT INTO request( user_id, category, name, type, added, post )                               " +
                "   VALUES ( ?, ?, ?, ?, ?, ? )                                                                     " +
                "   RETURNING id                                                                                    ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        System.out.println(CREATE_REQUEST_SQL);
        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement( CREATE_REQUEST_SQL );
            stmt.setLong(1, userId);
            stmt.setInt(2, request.getCategory());
            stmt.setString(3, request.getName());
            stmt.setLong(4, request.getType());
            stmt.setTimestamp(5, new Timestamp(new java.util.Date().getTime()));
            stmt.setInt(6, 0);

            rs = stmt.executeQuery();
            Long listingId = 0L;
            if (rs.next()) {
                listingId = rs.getLong(1);
            }
            // accommodation
            AccommodationDTO accommodation
                    = request.getDetails();

            final String CREATE_ACCOMMODATION_SQL =

                    "   INSERT INTO accommodation( listing_id, type, name, higher_level_sub_category,             " +
                    "                 thumbnail, bedrooms, price, town, country, date_vacant )                    " +

                    "   VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )                                                                     ";
            stmt = conn.prepareStatement( CREATE_ACCOMMODATION_SQL );
            stmt.setLong(1, listingId);
            stmt.setString(2, accommodation.getType());
            stmt.setString(3, accommodation.getName());
            stmt.setInt(4, accommodation.getProperty());
            stmt.setString(5, accommodation.getThumbnail());
            stmt.setInt(6, accommodation.getBedrooms());
            stmt.setBigDecimal(7, accommodation.getPrice());
            stmt.setInt(8, accommodation.getTown());
            stmt.setInt(9, accommodation.getCountry());
            stmt.setTimestamp(10, new Timestamp(accommodation.getDateVacant().getTime()));

            stmt.executeUpdate();

            return Response
                    .status(OK)
                    .header("success", true)
                    .header("message", "Accommodation Listing Created.")
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .header("message", "Create Buy Order Failed. Error: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .header("message", "Create Buy Order Failed. Error: " + e.getMessage())
                    .build();
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }

    @POST
    @Path("/vehicle/list")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postVehicle(VehicleRequestDTO request) {

        // parse token
        String userToken = request.getUserToken();
        Claims claims
                = JavaWebToken.parseJWT(userToken);

        Long userId = Long.parseLong(claims.getId());

        final String CREATE_REQUEST_SQL =

                "   INSERT INTO request( user_id, category, name, type, added, post )                               " +
                "   VALUES ( ?, ?, ?, ?, ?, ? )                                                                     " +
                "   RETURNING id                                                                                    ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        System.out.println(CREATE_REQUEST_SQL);
        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement( CREATE_REQUEST_SQL );
            stmt.setLong(1, userId);
            stmt.setInt(2, request.getCategory());
            stmt.setString(3, request.getName());
            stmt.setLong(4, request.getType());
            stmt.setTimestamp(5, new Timestamp(new java.util.Date().getTime()));
            stmt.setInt(6, 0);

            rs = stmt.executeQuery();
            Long listingId = 0L;
            if (rs.next()) {
                listingId = rs.getLong(1);
            }

            VehicleDTO v
                    = request.getDetails();

            final String CREATE_VEHICLE_SQL =

                    "  INSERT INTO vehicles (" +
                    "             type, listing_id, name, higher_level_sub_category, middle_level_sub_category,     " +
                    "             make, thumbnail, description, transmission, fuel,                                 " +
                    "             city, country, price, date_created    )                                           " +
                    "  VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )                                       ";

            stmt = conn.prepareStatement( CREATE_VEHICLE_SQL );
            stmt.setString(1, v.getType());
            stmt.setLong(2, listingId);
            stmt.setString(3, v.getName());
            stmt.setLong(4, v.getHigherLevelSubCategoryId());
            stmt.setLong(5, v.getMiddleLevelSubCategory());
            stmt.setString(6, v.getMake());
            stmt.setString(7, v.getThumbnail());
            stmt.setString(8, v.getDescription());
            stmt.setString(9, v.getTransmission());
            stmt.setString(10, v.getFuel());
            stmt.setLong(11, v.getCity());
            stmt.setLong(12, v.getCountry());
            stmt.setBigDecimal(13, v.getPrice());
            stmt.setTimestamp(14, new Timestamp(v.getDateCreated().getTime()));

            stmt.executeUpdate();

            return Response
                    .status(OK)
                    .header("success", true)
                    .header("message", "Vehicle Listing Created.")
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .header("message", "Vehicle Listing Failed. Error: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .header("message", "Vehicle Listing Failed. Error: " + e.getMessage())
                    .build();
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }

    /**
     * accommodation
     *
     * @param listingId
     * @param conn
     * @return
     * @throws SQLException
     */
    private ListingDetailsDTO accommodation(long listingId, Connection conn)
            throws SQLException {

        final String SERVICE_SQL =
                "   SELECT id, listing_id AS parent, type,                                                          " +
                "          property, thumbnail, bedrooms, price, town, country, date_vacant                         " +
                "     FROM accommodation                                                                            " +
                "    WHERE type = ?                                                                                 " +
                "      AND listing_id = ?                                                                           ";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement( SERVICE_SQL );
            stmt.setString(1, "Request");
            stmt.setLong(2, listingId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new AccommodationListingDetailsDTO (
                        rs.getLong("id"),
                        rs.getInt("parent"),
                        rs.getString("type"),
                        rs.getInt("property"),
                        rs.getString("thumbnail"),
                        rs.getInt("bedrooms"),
                        rs.getBigDecimal("price"),
                        rs.getString("town"),
                        rs.getString("country"),
                        new Date( rs.getTimestamp("date_vacant").getTime()));
            } else {
                return new AccommodationListingDetailsDTO();
            }
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
        }
    }

    /**
     * jobs
     * @param listingId
     * @param conn
     * @return
     * @throws SQLException
     */
    private ListingDetailsDTO jobs(long listingId, Connection conn)
            throws SQLException {

        final String SERVICE_SQL =

                "   SELECT  id, listing_id AS parent, type,                                                         " +
                "           higher_level_subcategory AS category, medium_level_subcategory AS field,                " +
                "           level, qualification, name AS title, deadline, description AS details,                  " +
                "           city, country                                                                           " +
                "     FROM jobs                                                                                     " +
                "    WHERE type = ?                                                                                 " +
                "      AND listing_id = ?                                                                           ";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement( SERVICE_SQL );
            stmt.setString(1, "Request");
            stmt.setLong(2, listingId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new JobListingDetailsDTO (
                        rs.getLong("id"),
                        rs.getInt("parent"),
                        rs.getString("type"),
                        rs.getInt("category"),
                        rs.getInt("field"),
                        rs.getInt("level"),
                        rs.getInt("qualification"),
                        rs.getString("title"),
                        new Date(rs.getTimestamp("deadline").getTime()),
                        rs.getString("details"),
                        rs.getString("city"),
                        rs.getString("country"));
            } else {
                return new JobListingDetailsDTO();
            }
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
        }
    }

    /**
     * vehicles
     * @param listingId
     * @param conn
     * @return
     * @throws SQLException
     */
    private ListingDetailsDTO vehicles(long listingId, Connection conn)
            throws SQLException {

        final String SERVICE_SQL =

                "   SELECT  v.id, v.listing_id AS parent, v.type,                                                   " +
                "           v.higher_level_sub_category AS mode, v.middle_level_sub_category AS vehicle_type,       " +
                "           v.lower_level_subcategory AS sub_type,                                                  " +
                "           b.id AS brand_id, b.category AS brand_category, b.higher AS brand_higher,               " +
                "           b.medium AS brand_medium, b.lower AS brand_lower,                                       " +
                "           b.name AS brand_name, b.icon AS brand_icon,                                             " +
                "           thumbnail, price, description, transmission, fuel, city, country, date_created          " +
                "     FROM vehicles v                                                                               " +
                "     JOIN brand b                                                                                  " +
                "       ON (v.brand = b.id )                                                                        " +
                "    WHERE type = ?                                                                                 " +
                "      AND listing_id = ?                                                                           ";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement( SERVICE_SQL );
            stmt.setString(1, "Request");
            stmt.setLong(2, listingId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new VehicleListingDetailsDTO (
                        rs.getLong("id"),
                        rs.getInt("parent"),
                        rs.getString("type"),
                        rs.getLong("mode"),
                        rs.getLong("vehicle_type"),
                        rs.getLong("sub_type"),
                        new BrandDTO(
                            rs.getLong("brand_id"),
                            rs.getInt("brand_category"),
                            rs.getLong("brand_higher"),
                            rs.getLong("brand_medium"),
                            rs.getLong("brand_lower"),
                            rs.getString("brand_name"),
                            rs.getString("brand_icon")),
                        rs.getString("thumbnail"),
                        rs.getBigDecimal("price"),
                        rs.getString("description"),
                        rs.getString("transmission"),
                        rs.getString("fuel"),
                        rs.getString("city"),
                        rs.getString("country"),
                        new Date(rs.getTimestamp("deadline").getTime()));
            } else {
                return new VehicleListingDetailsDTO();
            }
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
        }
    }

    @POST
    @Path("/services/create-buy-order")
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response createBuyOrder(BuyOrderDTO request) {


        final String CREATE_SELL_ORDER_SQL =
                "   INSERT INTO buy_orders ( " +
                        "              user_id, category_id, product_class_id, product_id, order_date )" +
                        "   VALUES ( ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        System.out.println(CREATE_SELL_ORDER_SQL);
        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement( CREATE_SELL_ORDER_SQL );
            stmt.setLong(1, request.getUserId());
            stmt.setInt(2, request.getCategoryId());
            stmt.setInt(3, request.getProductClassId());
            stmt.setLong(4, request.getProductId());
            stmt.setTimestamp(5, new Timestamp(new java.util.Date().getTime()));

            stmt.executeUpdate();

            return Response
                    .status(OK)
                    .header("success", true)
                    .header("message", "Services Buy Order Created.")
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .header("message", "Create Buy Order Failed. Error: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .header("message", "Create Buy Order Failed. Error: " + e.getMessage())
                    .build();
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }

    /*
       @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getMsg(@PathParam("param") String userRole) {
     */

    @POST
    @Path("/services/create-sell-order")
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response createSellOrder(SellOrderDTO request) {

        final String CREATE_SELL_ORDER_SQL =
                "   INSERT INTO sell_orders ( " +
                "              user_id, category_id, product_class_id, product_id, narrative, order_date )" +
                "   VALUES ( ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        System.out.println(CREATE_SELL_ORDER_SQL);
        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement( CREATE_SELL_ORDER_SQL );
            stmt.setLong(1, request.getUserId());
            stmt.setInt(2, request.getCategoryId());
            stmt.setInt(3, request.getProductClassId());
            stmt.setLong(4, request.getProductId());
            stmt.setString(5, request.getNarrative());
            stmt.setTimestamp(6, new Timestamp(new java.util.Date().getTime()));

            stmt.executeUpdate();

            return Response
                    .status(OK)
                    .header("success", true)
                    .header("message", "Services Sell Order Created.")
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .header("message", "Create Sell Order Failed. Error: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .header("message", "Create Sell Order Failed. Error: " + e.getMessage())
                    .build();
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }

    @GET
    @Path("/services/{categoryId}/{productClassId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response services( @PathParam("categoryId") Integer categoryId,
                              @PathParam("productClassId") Integer productClassId) {

        final String READ_SERVICE_LIST_SQL =
                "   SELECT category_id, product_class_id, id, name                                  " +
                "     FROM product_search_field                                                     " +
                "    WHERE category_id = ?                                                          " +
                "      AND product_class_id = ?                                                     " +
                "    ORDER BY name                                                                  ";

        System.out.println("/services" + "categoryId = " + categoryId + ", productClassId = " + productClassId);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, ProductSearchFieldDTO> services = new HashMap<>();

        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement(READ_SERVICE_LIST_SQL);
            stmt.setInt(1, categoryId);
            stmt.setInt(2, productClassId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                services.put( rs.getString("name"),
                        new ProductSearchFieldDTO(rs.getInt("category_id"),
                                       rs.getInt("product_class_id"),
                                       rs.getLong("id"),
                                       rs.getString("name")));
            }

            return Response
                    .status(OK)
                    .header("success", true)
                    .entity(services)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .entity(new ErrorDTO("SERVER_ERROR",
                            "Get Service List Error: " + e.getMessage()))
                    .build();
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }

    /*
    @GET
    @Path("/find-all/search-fields/{categoryId}/{higherLevelSubCategoryId}/{middleLevelSubCategoryId}/{lowerLevelSubCategoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchFields(   @PathParam("categoryId") Integer categoryId,
                                    @PathParam("higherLevelSubCategoryId") Long higherLevelSubCategoryId,
                                    @PathParam("middleLevelSubCategoryId") Long middleLevelSubCategoryId,
                                    @PathParam("middleLevelSubCategoryId") Long lowerLevelSubCategoryId ) {

        final String READ_SERVICE_LIST_SQL =
                "   SELECT id, category_id, higher_level_sub_category, middle_level_sub_category, name,         " +
                "           request_type, listing_type                                                          " +
                "     FROM search_fields                                                                        " +
                "    WHERE category_id = ?                                                                      " +
                "      AND higher_level_sub_category_id = ?                                                     " +
                "      AND middle_level_sub_category_id = ?                                                     " +
                "      AND lower_level_sub_category_id = ?                                                      " +
                "    ORDER BY name                                                                              ";

        System.out.println(READ_SERVICE_LIST_SQL);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<SearchFieldDTO> items = new ArrayList<>();

        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement(READ_SERVICE_LIST_SQL);
            stmt.setInt(1, categoryId);
            stmt.setLong(2, higherLevelSubCategoryId);
            stmt.setLong(3, middleLevelSubCategoryId);
            stmt.setLong(4, lowerLevelSubCategoryId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                items.add( new SearchFieldDTO(
                                rs.getLong("id"),
                                rs.getInt("category_id"),
                                rs.getLong("higher_level_sub_category_id"),
                                rs.getLong("middle_level_sub_category_id"),
                                rs.getLong("lower_level_sub_category_id"),
                                rs.getString("name"),
                                rs.getString("request_type"),
                                rs.getString("listing_type")));
            }
            System.out.println("count: " + items.size());
            return Response
                    .status(OK)
                    .header("success", true)
                    .entity(items)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .entity(new ErrorDTO("SERVER_ERROR",
                            "Get Service List Error: " + e.getMessage()))
                    .build();
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }
    */

    @GET
    @Path("/find-all/search-fields/{categoryName}/{higherLevelSubCategoryId}/{middleLevelSubCategoryId}/{lowerLevelSubCategoryId}/{orderType}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchFields(   @PathParam("categoryName") String categoryName,
                                    @PathParam("higherLevelSubCategoryId") Long higherLevelSubCategoryId,
                                    @PathParam("middleLevelSubCategoryId") Long middleLevelSubCategoryId,
                                    @PathParam("middleLevelSubCategoryId") Long lowerLevelSubCategoryId,
                                    @PathParam("orderType") String orderType) {

        try {

            System.out.println("CategoryDAO.findIdByName,  categoryName: " + categoryName
                                    + ", orderType : " + orderType);
            Integer categoryId
                    = CategoryDAO.findIdByName(categoryName, orderType);

            System.out.println("SearchFieldDAO.searchFields,  categoryId: " + categoryId
                    + ", higherLevelSubCategoryId : " + higherLevelSubCategoryId
                    + ", middleLevelSubCategoryId : " + middleLevelSubCategoryId
                    + ", lowerLevelSubCategoryId : " + lowerLevelSubCategoryId
                    + ", orderType : " + orderType );
            Map<String, SearchFieldDTO> items
                        = SearchFieldDAO.searchFields(
                                        new SearchFieldKeyDTO(  categoryId,
                                                                higherLevelSubCategoryId,
                                                                middleLevelSubCategoryId,
                                                                lowerLevelSubCategoryId,
                                                                orderType,
                                                                orderType ) );

            return JaxRsResponseFactory.success("Search field items read.", items);
        } catch (InternalSystemException e) {
            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (ItemNotFoundException e) {
            return JaxRsResponseFactory.fail(NOT_FOUND, e.getMessage());
        }
    }

    @POST
    @Path("/activate/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response activateAccount(ActivateAccountRequestDTO request) {

        final String READ_USER_SQL =
                " SELECT token                                                                      " +
                "   FROM users                                                                      " +
                "  WHERE msisdn LIKE '%" + request.getMsisdn() + "%'";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement(READ_USER_SQL);
//            stmt.setString(1, request.getMsisdn());
            rs = stmt.executeQuery();
            if (rs.next()) {
                String token = rs.getString("token");
                System.out.println("ActivateAccountRequestDTO: " + request +
                                    "token : " + token);
                if (token.equals( request.getCode() )) {
                    // update user status
                    final String UPDATE_USER_STATUS_SQL =
                            "   UPDATE users                                                        " +
                            "      SET status = 1                                                   " +
                            "    WHERE msisdn LIKE '%" + request.getMsisdn() + "%'";
                    try {
                        stmt = conn.prepareStatement(UPDATE_USER_STATUS_SQL);
//                        stmt.setString(1, request.getMsisdn());
                        stmt.executeUpdate();

                        return Response
                                .status(OK)
                                .header("success", true)
                                .header("message", "Account activated successfully.")
                                .build();
                    } catch (SQLException e) {
                        return Response
                                .status(INTERNAL_SERVER_ERROR)
                                .header("success", false)
                                .entity(new ErrorDTO("SERVER_ERROR",
                                        "Account activation failed. Error: " + e.getMessage()))
                                .build();
                    }
                } else {
                    return Response
                            .status(UNAUTHORIZED)
                            .header("success", false)
                            .entity(new ErrorDTO("LOGIN_ERROR", "Invalid activation code"))
                            .build();
                }
            } else {
                return Response
                        .status(UNAUTHORIZED)
                        .header("success", false)
                        .entity(new ErrorDTO("ACTIVATION_ERROR",
                                "Code could not be retrieved. Account might be activated already "))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .entity(new ErrorDTO("SERVER_ERROR",
                            "Account creation failed. Error: " + e.getMessage()))
                    .build();
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(RegistrationRequestDTO request) {

        final String READ_USER_SQL =
                " SELECT COUNT(*)  AS USER_COUNT                                                    " +
                "   FROM users                                                                      " +
                "  WHERE msisdn = ?                                                                 " ;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        System.out.println(READ_USER_SQL);
        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement(READ_USER_SQL);
            stmt.setString(1, request.getMsisdn());
            rs = stmt.executeQuery();
            if ( rs.next() ) {
                System.out.println("USER_COUNT : " + rs.getInt("USER_COUNT"));
                if (rs.getInt("USER_COUNT") > 0) {
                    return Response
                            .status(INTERNAL_SERVER_ERROR)
                            .header("success", false)
                            .entity(new ErrorDTO("SERVER_ERROR",
                                                "Mobile number already exists"))
                            .build();
                } else {

                    String email = "";
                    String hashedPassword = BCrypt.hashpw(request.getPassword());
                    String address = "";
                    long profile_image = 0L;
                    String token = "" + MathUtil.getRandomNumberInRange(1000, 9999);
                    String status = "0";

                    final String INSERT_USER_SQL =
                            "   INSERT INTO users( email, password, name, surname,                                  " +
                            "                      msisdn, dial_code, local_mobile_number,                          " +
                            "                      town, country_code, country, address, profile_image,             " +
                            "                      token, status)                                                   " +
                            "   VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)                                  ";
                    stmt = conn.prepareStatement(INSERT_USER_SQL);
                    stmt.setString(1, email);
                    stmt.setString(2, hashedPassword);
                    stmt.setString(3, request.getName());
                    stmt.setString(4, request.getLastName());
                    stmt.setString(5, request.getMsisdn());
                    stmt.setString(6, request.getDialCode());
                    stmt.setString(7, request.getLocalMobileNumber());
                    stmt.setString(8, request.getTown());
                    stmt.setString(9, request.getCountry());
                    stmt.setString(10, request.getCountryData());
                    stmt.setString(11, address);
                    stmt.setLong(12, profile_image);
                    stmt.setString(13, token);
                    stmt.setString(14, status);

                    try {
                        stmt.executeUpdate();

                        String message
                                = "Thank you for registering with WorldMix. Here is your activation code: " + token;
                        int smsResponseCode = SendSMS.sendSMS(request.getMsisdn(), message);
                        return Response
                                .status(OK)
                                .entity(
                                        new ErrorDTO(true,
                                                    "OK",
                                                 "Account created successfully. Please activate your account"))
                                .build();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return Response
                                .status(INTERNAL_SERVER_ERROR)
                                .header("success", false)
                                .entity(new ErrorDTO("SERVER_ERROR",
                                        "Account creation failed. Error: " + e.getMessage()))
                                .build();
                    }
                }
            } else {
                System.out.println("########################### NOT RESULT SET for : " + READ_USER_SQL);
                return Response
                        .status(INTERNAL_SERVER_ERROR)
                        .header("success", false)
                        .entity(new ErrorDTO("SERVER_ERROR",
                                "Account creation failed." ))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .entity(new ErrorDTO("SERVER_ERROR",
                            "Account creation failed. Error: " + e.getMessage()))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .entity(new ErrorDTO("SERVER_ERROR",
                            "Account creation failed. Error: " + e.getMessage()))
                    .build();
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }

    @POST
    @Path("/reset-password")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response resetPasword(RegistrationRequestDTO request) {

        final String READ_USER_SQL =
                " SELECT COUNT(*)  AS USER_COUNT                                                    " +
                "   FROM users                                                                      " +
                "  WHERE msisdn = ?                                                                 " ;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        System.out.println(READ_USER_SQL);
        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement(READ_USER_SQL);
            stmt.setString(1, request.getMsisdn());
            rs = stmt.executeQuery();
            if ( rs.next() ) {
                System.out.println("USER_COUNT : " + rs.getInt("USER_COUNT"));
                if (rs.getInt("USER_COUNT") == 0) {
                    return Response
                            .status(INTERNAL_SERVER_ERROR)
                            .header("success", false)
                            .entity(new ErrorDTO("SERVER_ERROR",
                                    "Mobile number not registered"))
                            .build();
                } else {

                    String email = "";
                    String hashedPassword = BCrypt.hashpw(request.getPassword());
                    String address = "";
                    long profile_image = 0L;
                    String token = "" + MathUtil.getRandomNumberInRange(1000, 9999);
                    String status = "0";

                    final String UPDATE_USER_SQL =
                            "   UPDATE users SET password = ?, token = ?, status = ?                           " +
                            "    WHERE msisdn = ?                                                              ";
                    stmt = conn.prepareStatement(UPDATE_USER_SQL);
                    stmt.setString(1, hashedPassword);
                    stmt.setString(2, token);
                    stmt.setString(3, status);
                    stmt.setString(4, request.getMsisdn());

                    try {
                        stmt.executeUpdate();

                        String message
                                = "In order to complete eWorldMix password change, " +
                                   "here is your activation code: " + token;
                        int smsResponseCode = SendSMS.sendSMS(request.getMsisdn(), message);
                        return Response
                                .status(OK)
                                .entity(
                                        new ErrorDTO(true,
                                                "OK",
                                                "Account password reset. Please activate your account"))
                                .build();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return Response
                                .status(INTERNAL_SERVER_ERROR)
                                .header("success", false)
                                .entity(new ErrorDTO("SERVER_ERROR",
                                        "Password reset failed. Error: " + e.getMessage()))
                                .build();
                    }
                }
            } else {
                System.out.println("########################### NOT RESULT SET for : " + READ_USER_SQL);
                return Response
                        .status(INTERNAL_SERVER_ERROR)
                        .header("success", false)
                        .entity(new ErrorDTO("SERVER_ERROR",
                                "Password reset failed." ))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .entity(new ErrorDTO("SERVER_ERROR",
                            "Password reset failed. Error: " + e.getMessage()))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .entity(new ErrorDTO("SERVER_ERROR",
                            "Password reset failed. Error: " + e.getMessage()))
                    .build();
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }

    // is-email-registered
    @GET
    @Path("/is-email-registered/{userToken}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response isEmailRegistered(@PathParam("userToken") String userToken) {

        final String READ_USER_TOKEN_SQL =
                "   SELECT email, is_email_active                                                   " +
                "     FROM users                                                                    " +
                "    WHERE id = ?                                                                   ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            Long userId
                    = userId(userToken);

            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement( READ_USER_TOKEN_SQL );
            stmt.setLong(1, userId);

            rs = stmt.executeQuery();
            if (rs.next()) {

                String email
                        = rs.getString("email");
                String isEmailRegistered
                        = rs.getString("is_email_active");

                String result;
                if (email != null && isValidEmailAddress(email)) {

                     if ( "Y".equals(isEmailRegistered ) ){
                        result = "EMAIL_IS_ACTIVE";
                     } else {
                        result = "SEND_ACTIVATION_CODE";
                     }
                } else {
                    result = "EMAIL_NOT_REGISTERED";
                }

                return Response
                        .status(OK)
                        .entity(
                                new ErrorDTO(true,
                                        email,
                                        result))
                        .build();
            }

            return Response
                    .status(OK)
                    .entity(
                            new ErrorDTO(true,
                                    "OK",
                                    "USER_NOT_REGISTERED"))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .entity(new ErrorDTO("SERVER_ERROR",
                            "Email check failed. Error: " + e.getMessage()))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .entity(new ErrorDTO("SERVER_ERROR",
                            "Email check failed. Error: " + e.getMessage()))
                    .build();
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    @POST
    @Path("/register-email")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerEmail(RegistrationEmailRequestDTO request) {

        Connection conn = null;
        PreparedStatement stmt = null;

        final String INSERT_USER_SQL =
                "   UPDATE users                                                                    " +
                "      SET token = ?, is_email_active = ?                                           " +
                (          "N".equalsIgnoreCase(request.getResend()) ? " , email = ?  " : ""        ) +
                "    WHERE id = ?                                                                   ";

        try {

            Long userId
                    = userId(request.getUserToken());

            za.co.airwide.marketplace.dto.UserDTO userDTO
                    = UserDAO.findById(userId);

            String token = "" + MathUtil.getRandomNumberInRange(1000, 9999);

            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement( INSERT_USER_SQL );
            int idx = 1;
            stmt.setString(idx++, token );
            stmt.setString(idx++, "N" );
            if ("N".equalsIgnoreCase(request.getResend())) {
                stmt.setString(idx++, request.getEmailAddress());
            }
            stmt.setLong(idx, userId );

            // TODO process update email and token result
            stmt.executeUpdate();

            String toEmailAddress = request.getEmailAddress();
            String fromEmailAddress = "eworldmix@gmail.com";
            String toFullName = userDTO.getName() + " " + userDTO.getSurname();
            String textMessage
                    = "Thank you for registering with WorldMix. Here is your activation code: " + token;
            SendMailService.send(toEmailAddress, fromEmailAddress,  toFullName,
                    "eWorldMix Email Activation Code", textMessage);
            return Response
                    .status(OK)
                    .entity(
                            new ErrorDTO(true,
                                    request.getEmailAddress(),
                                    "Activation code sent to your email."))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .entity(new ErrorDTO("SERVER_ERROR",
                            "Email registration failed. Error: " + e.getMessage()))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .entity(new ErrorDTO("SERVER_ERROR",
                            "Email registration failed. Error: " + e.getMessage()))
                    .build();
        } finally {
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }

    @POST
    @Path("/activate-email")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response activateEmail(RegistrationEmailRequestDTO request) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            Long userId
                    = userId(request.getUserToken());

            final String READ_USER_TOKEN_SQL =
                    "   SELECT token                                                                    " +
                    "     FROM users                                                                    " +
                    "    WHERE id = ?                                                                   ";

            final String UPDATE_USER_SQL =
                    "   UPDATE users                                                                    " +
                    "      SET is_email_active = ?                                                      " +
                    "    WHERE id = ?                                                                   ";

            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement( READ_USER_TOKEN_SQL );
            stmt.setLong(1, userId );

            rs = stmt.executeQuery();

            if (rs.next()) {

                String token = rs.getString("token");
                if (token.equals(request.getToken())) {
                    stmt = conn.prepareStatement( UPDATE_USER_SQL );
                    stmt.setString(1, "Y" );
                    stmt.setLong(2, userId );

                    stmt.executeUpdate();

                    return Response
                            .status(OK)
                            .entity(
                                    new ErrorDTO(true,
                                            "OK",
                                            "Email activated successfully."))
                            .build();
                } else {
                    return Response
                            .status(UNAUTHORIZED)
                            .header("success", false)
                            .header("message", "Invalid activation code")
                            .entity(new ErrorDTO("LOGIN_ERROR", "Invalid activation code"))
                            .build();
                }
            } else {
                return Response
                        .status(UNAUTHORIZED)
                        .header("success", false)
                        .header("message", "Account Not Found")
                        .entity(new ErrorDTO("LOGIN_ERROR", "Account Not Found"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .entity(new ErrorDTO("SERVER_ERROR",
                            "Email activation failed. Error: " + e.getMessage()))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .entity(new ErrorDTO("SERVER_ERROR",
                            "Email activation failed. Error: " + e.getMessage()))
                    .build();
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }

    private void sendMessage(String fromMobileNumber, String toMobileNumber, String textMessage) {

        try {
            System.out.println("TEXT FROM: " + fromMobileNumber +
                                ", TO: " + toMobileNumber +
                                ", TEXT: " + textMessage );
            int smsResponseCode = SendSMS.sendSMS(toMobileNumber, textMessage);
            System.out.println("smsResponseCode: " + smsResponseCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessage( String toEmailAddress,
                              String fromEmailAddress,
                              String toFullName,
                              String fromMobileNumber,
                              String toMobileNumber,
                              String subject,
                              String textMessage
                               ) {

        try {
            System.out.println(
                    "TEXT FROM: " + fromMobileNumber +
                    ", TO: " + toMobileNumber +
                    ", TEXT: " + textMessage );
            SendMailService.send(toEmailAddress, fromEmailAddress, toFullName, subject, textMessage);
            System.out.println("Email Sent");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * array('msisdn' => $this->getMsisdn(), 'password' => $this->getPassword())
     * @param request
     * @return
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login( AuthenticationRequestDTO request,
                           @HeaderParam(HttpHeaders.HOST) String host) {

        System.out.println("Authenticating mobile number: " + request.getMsisdn());

        final String READ_USER_SQL =
                " SELECT id, password, status, name, surname, email, msisdn, address, town, country, profile_image " +
                "   FROM users                                                                                     " +
                "  WHERE msisdn = ?                                                                                " ;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement(READ_USER_SQL);
            stmt.setString(1, request.getMsisdn());
            rs = stmt.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                System.out.println("returning: " + hashedPassword);
                System.out.println("getPassword: " + request.getPassword());

                if (BCrypt.checkpw(request.getPassword(), hashedPassword)) {
                    // enum('0','1','2','3')
                    String status = rs.getString("status");
                    if ("0".equals(status)) {
                        return Response
                                .status(UNAUTHORIZED)
                                .header("success", false)
                                .header("message","Account  not  updated" )
                                .entity(new ErrorDTO("LOGIN_ERROR", "Account  not  updated"))
                                .build();
                    } else if ("1".equals(status)) {

                    /*
                    $paylod = [
                    'iat' => time(),
                            'iss' => $this->domain(),
                            'exp' => time() + (60*60*8),
                            'userId' => $row['id']
                            ]; //expires in 8 hours
                    */

//                    Map<String, Object> map = new HashMap<>();
//                    map.put("iat",System.currentTimeMillis());
//                    map.put("iss",host); // this->domain()
//                    map.put("exp",System.currentTimeMillis() + (60*60*8)); // expires in 8 hours
//                    map.put("userId",rs.getLong("id"));
                        String id = "" + rs.getLong("id");
                        String issuer = host;
                        String subject = null;
                        long ttlMillis = System.currentTimeMillis() + (60 * 60 * 8);
                        String javaWebToken
                                = JavaWebToken.createJWT(id, issuer, subject, ttlMillis);
                        System.out.println("JavaWebToken: " + javaWebToken);
                        User user
                                = new User(
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("email"),
                                rs.getString("msisdn"),
                                rs.getString("address"),
                                rs.getString("town"),
                                rs.getString("country"),
                                rs.getString("profile_image"),
                                rs.getString("status"));

                        user.setSuccess("true");
                        user.setMessage("Login successful");
                        user.setToken(javaWebToken);
                    /*
                        $token = JWT::encode($paylod, SECRETE_KEY);
                        $data = array('success' => true, 'statusCode' => SUCCESS_RESPONSE,
                        'message' => 'Login successful', 'token' => $token, 'details' => $details);
                    */
                        System.out.println(user);
                        return Response
                                .status(OK)
                                .header("success", true)
                                .header("message", "Login successful")
                                .header("token", javaWebToken)
                                .header("Access-Control-Allow-Origin", "*")
                                .entity(user)
                                .build();
                    } else if ("2".equals(status)) {
                        return Response
                                .status(UNAUTHORIZED)
                                .header("success", false)
                                .header("message", "Account Suspended")
                                .entity(new ErrorDTO("LOGIN_ERROR", "Account Suspended"))
                                .build();
                    } else {
                        return Response
                                .status(UNAUTHORIZED)
                                .header("success", false)
                                .header("message", "Account Blacklisted")
                                .entity(new ErrorDTO("LOGIN_ERROR", "Account Blacklisted"))
                                .build();
                    }

                } else {
                    return Response
                            .status(UNAUTHORIZED)
                            .header("success", false)
                            .header("messageType", "LOGIN_ERROR")
                            .header("message", "Invalid Login Credentials")
                            .entity(new ErrorDTO("LOGIN_ERROR", "Invalid Login Credentials"))
                            .build();
                }
            } else {
                return Response
                        .status(UNAUTHORIZED)
                        .header("success", false)
                        .header("message", "User not registered")
                        .entity(new ErrorDTO("LOGIN_ERROR", "User not registered"))
                        .build();
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }

        return Response.status(INTERNAL_SERVER_ERROR).entity("Error occurred").build();
    }

        /*
    private String domain() {


        if (!empty($_SERVER['HTTPS']) && ('on' == $_SERVER['HTTPS'])) {
            $uri = 'https://';
        } else {
            $uri = 'http://';
        }

        $uri .= $_SERVER['HTTP_HOST'];
        return $uri;

    }
    */

    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getMsg(@PathParam("param") String userRole) {


        List<UserDTO> result
                = new ArrayList<>();

        final String READ_USER_SQL =

                "  SELECT user_id, user_phone, user_email, user_first_name, user_surname, user_role, user_photo_url" +
                "    FROM users " +
                "   WHERE user_role = ? ";

        // auto close connection and preparedStatement
        try (Connection conn = MarketPlaceDAO.getConnection();
             PreparedStatement stmt
                     = conn.prepareStatement(READ_USER_SQL) ) {
            stmt.setString(1, userRole);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                result.add(
                        new UserDTO(rs.getString("user_id"),
                                rs.getString("user_phone"),
                                rs.getString("user_email"),
                                rs.getString("user_first_name"),
                                rs.getString("user_surname"),
                                rs.getString("user_role"),
                                rs.getString("user_photo_url")));
            }
            result.forEach(x -> System.out.println(x));
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("retuning: %s\n" + result);
        return result;
    }

    /*
       Column        |            Type             | Collation | Nullable | Default
---------------------+-----------------------------+-----------+----------+---------
 flight_school_id    | text                        |           | not null |
 student_id          | text                        |           | not null |
 instructor_id       | text                        |           | not null |
 aircraft_model      | text                        |           | not null |
 aircraft_reg_number | text                        |           | not null |
 uuid                | text                        |           | not null |
 start_date          | timestamp without time zone |           |          |
 end_date            | timestamp without time zone |           |          |
 description         | text                        |           |          |
 status              | text                        |           |          |
Indexes:
    "schedule_pkey" PRIMARY KEY, btree (flight_school_id, student_id, instructor_id, aircraft_model,
    aircraft_reg_number, uuid)

 */
    @POST
    @Path("/schedule")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response schedule(ScheduleDTO schedule) {

        List<UserDTO> result
                = new ArrayList<>();

        final String READ_USER_SQL =

                "  INSERT INTO schedule ( flight_school_id, student_id, instructor_id,                          " +
                "                         aircraft_model, aircraft_reg_number, uuid,                            " +
                "                         start_date, end_date, description, status )                           " +
                "        VALUES         (?, ?, ?, ?, ?, ?, ?, ? ,? , ? )                                        " ;

        // auto close connection and preparedStatement
        try (Connection conn = MarketPlaceDAO.getConnection();
             PreparedStatement stmt
                      = conn.prepareStatement(READ_USER_SQL) ) {
            stmt.setString(1, schedule.getFlightSchoolID());
            stmt.setString(2, schedule.getStudentID());
            stmt.setString(3, schedule.getInstructorID());
            stmt.setString(4, schedule.getAircraftModel());
            stmt.setString(5, schedule.getAircraftRegNumber());
            String uuid = "" + (System.currentTimeMillis() + 1);
            stmt.setString(6, uuid);
            stmt.setTimestamp(7, new Timestamp(schedule.getStartDate().getTime()));
            stmt.setTimestamp(8, new Timestamp(schedule.getEndDate().getTime()));
            stmt.setString(9, schedule.getDescription());
            String status = "confirmed";
            stmt.setString(10, status);

            stmt.executeUpdate();

            schedule.setUuid(uuid);
            schedule.setStatus(status);
            System.out.println("retuning: %s\n" + schedule);

            return Response.status(201).entity(schedule).build();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.status(500).entity("Error occurred").build();
    }

    @GET
    @Path("/find/higher-level-sub-category/{categoryName}/{orderType}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findHigherLevelSubCategory( @PathParam("categoryName") String categoryName,
                                                   @PathParam("orderType") String orderType) {

        try {
            List<HigherLevelSubCategoryDTO> items
                    = HigherLevelSubCategoryDAO.findByCategoryAndOrderType(categoryName, orderType);

            return JaxRsResponseFactory.success("HIGHER_LEVEL_SUB_CATEGORY items read.", items);
        } catch (InternalSystemException e) {
            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (ItemNotFoundException e) {
            return JaxRsResponseFactory.fail(NOT_FOUND, e.getMessage());
        }
    }

    @GET
    @Path("/find/middle-level-sub-category/{categoryName}/{higherLevelSubCategoryId}/{orderType}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findMiddleLevelSubCategory(
                      @PathParam("categoryName") String categoryName,
                      @PathParam("higherLevelSubCategoryId") Long higherLevelSubCategoryId,
                      @PathParam("orderType") String orderType) {

        try {
            List<MiddleLevelSubCategoryDTO> items
                    = MiddleLevelSubCategoryDAO.find(categoryName, higherLevelSubCategoryId, orderType);

            return JaxRsResponseFactory.success("MIDDLE_LEVEL_SUB_CATEGORY items read.", items);
        } catch (InternalSystemException e) {
            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (ItemNotFoundException e) {
            return JaxRsResponseFactory.fail(NOT_FOUND, e.getMessage());
        }
    }

    @GET
    @Path("/find/lower-level-sub-category/{categoryName}/{higherLevelSubCategoryId}/{middleLevelSubCategoryId}/{orderType}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findLowerleLevelSubCategory(
            @PathParam("categoryName") String categoryName,
            @PathParam("higherLevelSubCategoryId") Long higherLevelSubCategoryId,
            @PathParam("middleLevelSubCategoryId") Long middleLevelSubCategoryId,
            @PathParam("orderType") String orderType) {

        try {
            List<LowerLevelSubCategoryDTO> items
                    = LowerLevelSubCategoryDAO.find(
                            categoryName, higherLevelSubCategoryId, middleLevelSubCategoryId, orderType);

            return JaxRsResponseFactory.success("MIDDLE_LEVEL_SUB_CATEGORY items read.", items);
        } catch (InternalSystemException e) {
            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (ItemNotFoundException e) {
            return JaxRsResponseFactory.fail(NOT_FOUND, e.getMessage());
        }
    }

    @GET
    @Path("/find-all/countries")
    @Produces(MediaType.APPLICATION_JSON)
    public Response countries() {

        try {
            List<CountryDTO> items
                    = CountryListDAO.findAll();

            return JaxRsResponseFactory.success("Country list read.", items);
        } catch (InternalSystemException e) {
            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (ItemNotFoundException e) {
            return JaxRsResponseFactory.fail(NOT_FOUND, e.getMessage());
        }
    }

    @GET
    @Path("/find-all/{countryId}/states")
    @Produces(MediaType.APPLICATION_JSON)
    public Response states(@PathParam("countryId") Integer countryId) {

        try {
            List<StateDTO> items
                    = StateListDAO.findAll(countryId);

            return JaxRsResponseFactory.success("State list read.", items);
        } catch (InternalSystemException e) {
            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (ItemNotFoundException e) {
            return JaxRsResponseFactory.fail(NOT_FOUND, e.getMessage());
        }
    }

    @GET
    @Path("/find-all/{stateId}/cities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cities(@PathParam("stateId") Integer stateId) {

        try {
            List<CityDTO> items
                    = CityListDAO.findAll(stateId);

            return JaxRsResponseFactory.success("City list read.", items);
        } catch (InternalSystemException e) {
            return JaxRsResponseFactory.fail(INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (ItemNotFoundException e) {
            return JaxRsResponseFactory.fail(NOT_FOUND, e.getMessage());
        }
    }

    /*
    @POST
    @Path("/services/create-buy-order")
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response createBuyOrder(BuyOrderDTO request) {


        final String CREATE_SELL_ORDER_SQL =
                "   INSERT INTO buy_orders ( " +
                        "              user_id, category_id, product_class_id, product_id, order_date )" +
                        "   VALUES ( ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        System.out.println(CREATE_SELL_ORDER_SQL);
        try {
            conn = MarketPlaceDAO.getConnection();
            stmt = conn.prepareStatement( CREATE_SELL_ORDER_SQL );
            stmt.setLong(1, request.getUserId());
            stmt.setInt(2, request.getCategoryId());
            stmt.setInt(3, request.getProductClassId());
            stmt.setLong(4, request.getProductId());
            stmt.setTimestamp(5, new Timestamp(new java.util.Date().getTime()));

            stmt.executeUpdate();

            return Response
                    .status(OK)
                    .header("success", true)
                    .header("message", "Services Buy Order Created.")
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .header("message", "Create Buy Order Failed. Error: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(INTERNAL_SERVER_ERROR)
                    .header("success", false)
                    .header("message", "Create Buy Order Failed. Error: " + e.getMessage())
                    .build();
        } finally {
            try { rs.close(); } catch (Exception e){}
            try { stmt.close(); } catch (Exception e){}
            try { conn.close(); } catch (Exception e){}
        }
    }
    */

    private Long userId( String userToken ) {
        Claims claims
                = JavaWebToken.parseJWT(userToken);

        return Long.parseLong(claims.getId());
    }
}
