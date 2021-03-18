package com.example.demo.Service;

//import com.example.demo.DAO.AlbumRepository;
import com.example.demo.Entity.Album;
import com.example.demo.persistence.AlbumDao;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AlbumServiceImpl implements AlbumService{

    AlbumDao albumDao = new AlbumDao();

    @Override
    public Album findByISRCAndTitle(String ISRC, String title){
        return albumDao.findByISRCAndTitle(ISRC, title);
    }

    @Override
    public List<Album> getAlbums() {
        List<Album> sortedAlbums = albumDao.getAlbums().stream()
                .sorted((a,b) -> a.getTitle().compareTo(b.getTitle())).collect(Collectors.toList());
        return sortedAlbums;
    }

    @Override
    public boolean createNewAlbum(String ISRC, String title, String description, int year, String artist_first_name, String artist_last_name, String cover_image_name, String image_mime, byte[] cover_image) throws FileNotFoundException {
        image_mime = "image/" + image_mime;
        Album newAlbum = new Album(ISRC, title, description,year,artist_first_name,artist_last_name, cover_image_name, image_mime, cover_image );
        return albumDao.insertAlbum(newAlbum);
    }

    @Override
    public boolean deleteAlbum(String ISRC){
        return albumDao.delete(ISRC);
    }

    @Override
    public boolean modifyAlbum(String ISRC, String title, String description, int year, String artist_first_name, String artist_last_name) throws FileNotFoundException {
        return albumDao.updateAlbum(ISRC, title, description, year, artist_first_name, artist_last_name);
    }


    /* Not used

    @Override
    public Album upload(String ISRC, String title, String cover_image_name, String image_mime, byte[] cover_image) throws FileNotFoundException {

        image_mime = "image/" + image_mime;
        return albumDao.insertImage(ISRC, title, cover_image_name, image_mime, cover_image);
    }
   */
    @Override
    public Album upload2(String ISRC, String title, String description, int year, String artist_first_name, String artist_last_name, String cover_image_name, String image_mime, byte[] cover_image) throws FileNotFoundException {
        image_mime = "image/" + image_mime;
        return albumDao.insertImage2(ISRC, title, description,year,artist_first_name,artist_last_name, cover_image_name, image_mime, cover_image);
    }



    //Cover Images CRUD

    @Override
    public Album getCoverImage(String ISRC, String title) {
        return findByISRCAndTitle(ISRC, title);
    }

    @Override
    public boolean deleteCoverImage(String ISRC) {
        return albumDao.deleteImg(ISRC);
    }

    @Override
    public boolean updateCoverImage(String ISRC, String cover_image_name, String image_mime, byte[] cover_image) {
        return albumDao.updateImg(ISRC, cover_image_name, image_mime, cover_image);
    }


}



//
////    @Path("/album")
//
//    public class  AlbumRest {
//        private String message = "";
//        private static AlbumsManager albumsManager = new AlbumsManager();
//
//        @GET
//        @Produces({MediaType.TEXT_PLAIN})
//        @Path("/list")
//        public Response getAlbums() {
//
//            if(albumsManager.hasAlbums()) {
//                System.out.println();
//                return Response.ok(albumsManager.getAllAlbums()).build();
//            }
//            else{
//                message = "No albums to return!";
////            return Response.status(Response.Status.OK).entity(message).build();
//                return Response.status(Response.Status.BAD_REQUEST).entity(message).type(MediaType.TEXT_PLAIN).build();
//            }
//        }
//
//        @GET
//        @Produces(MediaType.TEXT_PLAIN)
//        @Path("{ISRC}/{title}")
//        public Response getAlbum(@PathParam("ISRC") String ISRC, @PathParam("title") String title) {
//            title = URLDecoder.decode(title);
//
//            Album album = albumsManager.getAlbum(ISRC,title);
//            if (album != null) {
//                return Response.status(Response.Status.OK).entity(album.toString()).build();
//            }else{
//                message = "Album not found!";
//                return Response.status(Response.Status.NOT_FOUND).entity(message).build();
//            }
//        }
//
//        @POST
//        @Produces(MediaType.TEXT_PLAIN)
//        @Path("/create/{ISRC}/{title}/{description}/{year}/{artist}")
//        public Response createAlbum(@PathParam("ISRC") String ISRC, @PathParam("title") String title, @PathParam("description") String description, @PathParam("year") int year, @PathParam("artist") String artist){
//            title = URLDecoder.decode(title);
//            description = URLDecoder.decode(description);
//            artist = URLDecoder.decode(artist);
//
//            if(ISRC.isEmpty() || title.isEmpty() || year == 0 || artist.isEmpty()){
//                message = "A Form parameter is incorrect!";
//                return Response.status(Response.Status.BAD_REQUEST).entity(message).type(MediaType.TEXT_PLAIN).build();
//            }
//            else{
//                Album album = albumsManager.createAlbum(ISRC, title, description, year, artist);
//                message = (album != null) ? "New Album created!: \n" + album : " This ISRC already exists, please use a unique ISRC";
//                return Response.ok(message).build();
//            }
//
//        }
//
//
//
//
//        @PUT
//        @Produces({MediaType.TEXT_PLAIN})
//        @Path("{ISRC}/{title}/{description}/{year}/{artist}")
//        public Response modifyAlbum(@PathParam("ISRC") String ISRC, @PathParam("title") String title, @PathParam("description") String description, @PathParam("year") int year, @PathParam("artist") String artist){
//            title = URLDecoder.decode(title);
//            description = URLDecoder.decode(description);
//            artist = URLDecoder.decode(artist);
//
//            if(ISRC == null || title == null || year == 0 || artist == null){
//                message = "A Form parameter is incorrect!";
//                return Response.status(Response.Status.BAD_REQUEST).entity(message).type(MediaType.TEXT_PLAIN).build();
//            }
//            else{
//                if(albumsManager.updateAlbum(ISRC, title, description, year, artist)){
//                    message = "Album modified :\n" + albumsManager.getAlbum(ISRC);
//                    return Response.ok(message).build();
//                }
//                else{
//                    message = "Album " + ISRC + " could not be modified";
//                    return Response.status(Response.Status.NOT_FOUND).entity(message).build();
//                }
//
//            }
//
//        }




//curl -v http://localhost:8080/core/album/ISRC/newTitle

//curl -v http://localhost:8080/core/album/list

//curl -v -d POST http://localhost:8080/core/album/create/ISRC4/title/null/1950/artist

//curl -v -X PUT http://localhost:8080/core/album/ISRC4/titleNEW/NEWDESC/1951/NEWARTIST

//curl -v -X DELETE http://localhost:8080/core/album/ISRC4


//artist curls

//curl -v -G http://localhost:8980/SOEN_REDO_war_exploded/artists --data-urlencode "nickname=Ronaldo"
//curl -v http://localhost:8980/SOEN_REDO_war_exploded/artists

//curl -v -d "nickname=febu&first_name=feb&last_name=fran&biography=" http://localhost:8980/SOEN_REDO_war_exploded/artists

//curl -v -X PUT -d "nickname=febu#first_name=fwefewffw#last_name=fwefwf#biography=newBIOOO" http://localhost:8980/SOEN_REDO_war_exploded/artists

//curl -v -X DELETE http://localhost:8980/SOEN_REDO_war_exploded/artists?nickname=Ronaldo

