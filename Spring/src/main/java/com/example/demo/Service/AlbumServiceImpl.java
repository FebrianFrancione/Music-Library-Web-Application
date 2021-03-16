package com.example.demo.Service;

//import com.example.demo.DAO.AlbumRepository;
import com.example.demo.Entity.Album;
import com.example.demo.persistence.AlbumDao;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;


@Service
public class AlbumServiceImpl implements AlbumService{

//    private AlbumRepository albumRepository;
    AlbumDao albumDao = new AlbumDao();
//
//    @Autowired
//    public AlbumServiceImpl(AlbumDao albumDao) {
//        this.albumDao = albumDao;
//    }

//    public Album getAlbum(String ISRC){
//        return albumRepository.findAll().stream().filter(album -> album.getISRC().equals(ISRC)).findFirst().orElse(null);
//    }
//
//    public Album getAlbumHtml(String ISRC){
//        return albumRepository.findAll().stream().filter(album -> album.getISRC().equals(ISRC)).findFirst().orElse(null);
//    }



    @Override
    public Album findByISRCAndTitle(String ISRC, String title) throws Exception {
        return albumDao.findByISRCAndTitle(ISRC, title);
//        return albumRepository.findAll().stream().filter(album1 -> album1.getISRC().equals(ISRC) && album1.getTitle().equals(title)).findFirst().orElse(null);
    }

    @Override
    public List<Album> getAlbums() {
        return albumDao.getAlbums();
    }

    @Override
    public Album createNewAlbum(String ISRC, String title, String description, int year, String artist_first_name, String artist_last_name) throws FileNotFoundException {

//        if(getAlbum(ISRC) == null){
//            Album newAlbum = new Album(ISRC, title, description, year, artist_first_name, artist_last_name);
//            return albumRepository.save(newAlbum);
//        } else {
//            return null;
//        }

        Album newAlbum = new Album(ISRC, title, description,year,artist_first_name,artist_last_name);
        newAlbum.setCover_image_name("noName");
        newAlbum.setCover_image(null);
        newAlbum.setImage_mime("NoMime");
        albumDao.insertAlbum(newAlbum);
//        albumRepository.save(newAlbum);
        return newAlbum;
    }

//    @Override
//    public void deleteAlbum(String ISRC){
//        Album album = getAlbum(ISRC);
//        albumRepository.delete(album);
//    }
//
//    @Override
//    public void modifyAlbum(String ISRC, String title, String description, int year, String artist_first_name, String artist_last_name) throws FileNotFoundException {
//        deleteAlbum(ISRC);
//        createNewAlbum(ISRC, title, description, year, artist_first_name, artist_last_name);
//    }
//
//
//    //Cover Images CRUD
//
//    @Override
//    public Album getCoverImage(String ISRC) {
//       Album album = getAlbum(ISRC);
//       return album;
//    }
//
//    @Override
//    public void deleteCoverImage() {
//
//    }
//
//    @Override
//    public void updateCoverImage() {
//
//    }


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

