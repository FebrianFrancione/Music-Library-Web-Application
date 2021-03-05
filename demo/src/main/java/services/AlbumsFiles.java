package services;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Path("/files")
public class AlbumsFiles {

    private static final String FILE_PATH = "demo/src/main/java/savedFiles/test.txt";
//    private static final String FILE_PATH = "C:\\Users\\febri\\IdeaProjects\\SOEN-487-A2\\demo\\pom.xml";

    /**
     * The API method will return the file located at FILE_PATH. Modify the path to return whatever
     * @return file associated with FILE_PATH
     */
    @GET
    @Path("/txt")
    @Produces("text/plain")
    public File getFile() {
        return new File(FILE_PATH);
    }

    /**
     * This API method will upload a file to the specified file location
     * @param uploadedInputStream file to upload
     */
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void uploadFile(@FormDataParam("file") InputStream uploadedInputStream) {
        String fileLocation = "demo/src/main/java/savedFiles/"; // file to save to
        //saving file
        try {
            int read = 0;
            byte[] bytes = new byte[1024];
            FileOutputStream out = new FileOutputStream(fileLocation);
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {e.printStackTrace();}
    }

    @DELETE
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/delete")
    public void deleteAlbum() {
        File file = new File(FILE_PATH);
        file.delete();
//        if(albumsManager.deleteAlbum(ISRC)){
//            message = "Album " + ISRC + " successfully deleted!";
//            return Response.ok(message).build();
//        }
//
//        else{
//            message = "Album " + ISRC + " was not found";
//            return Response.status(Response.Status.NOT_FOUND).entity(message).build();
//        }
    }


}
