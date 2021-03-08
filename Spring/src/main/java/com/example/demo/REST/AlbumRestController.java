package com.example.demo.REST;

import com.example.demo.Entity.Album;
import com.example.demo.Service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
import java.net.URLDecoder;
import java.util.List;

@RestController
//@RequestMapping("/album")
public class AlbumRestController implements WebMvcConfigurer {
    private AlbumService albumService;

    @Autowired
    public AlbumRestController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Album";
    }
//
//    @GetMapping("/")
//    public String showHome(Model model){
//        model.addAttribute("theDate", new java.util.Date());
//
//        return "Album";
//    }

    @GetMapping("/list")
//    @Produces({MediaType.TEXT_PLAIN})
//    @Path("/list")
    public List<Album> getAlbums() {
        return albumService.getAlbums();
    }
//
//        if(albumsManager.hasAlbums()) {
//            System.out.println();
//            return Response.ok(albumsManager.getAllAlbums()).build();
//        }
//        else{
//            message = "No albums to return!";
////            return Response.status(Response.Status.OK).entity(message).build();
//            return Response.status(Response.Status.BAD_REQUEST).entity(message).type(MediaType.TEXT_PLAIN).build();
//        }


//    @GetMapping("{ISRC}/{title}")
////    @Produces(MediaType.TEXT_PLAIN)
////    @Path("{ISRC}/{title}")
//    public Album getAlbum(String ISRC, String title) {
//        Album album = albumService.findById(ISRC, title);
//        if (album == null) {
//            throw new RuntimeException("Album ISRC not found " + ISRC + title);
//        }
//        return album;
//    }


//        title = URLDecoder.decode(title);
//
//        Album album = albumsManager.getAlbum(ISRC,title);
//        if (album != null) {
//            return Response.status(Response.Status.OK).entity(album.toString()).build();
//        }else{
//            message = "Album not found!";
//            return Response.status(Response.Status.NOT_FOUND).entity(message).build();
//        }
//    }

    @PostMapping("/create/{ISRC}/{title}/{description}/{year}/{artist}")
//    @Produces(MediaType.TEXT_PLAIN)
//    @Path("/create/{ISRC}/{title}/{description}/{year}/{artist}")
    public void createAlbum(@RequestBody String ISRC, @RequestBody String title, @RequestBody String description, @RequestBody int year, @RequestBody String artist) {

        albumService.createAlbum(ISRC,title,description,year,artist);
    }

//        title = URLDecoder.decode(title);
//        description = URLDecoder.decode(description);
//        artist = URLDecoder.decode(artist);

//        if(ISRC.isEmpty() || title.isEmpty() || year == 0 || artist.isEmpty()){
//            message = "A Form parameter is incorrect!";
//            return Response.status(Response.Status.BAD_REQUEST).entity(message).type(MediaType.TEXT_PLAIN).build();
//        }
//        else{
//            Album album = albumsManager.createAlbum(ISRC, title, description, year, artist);
//            message = (album != null) ? "New Album created!: \n" + album : " This ISRC already exists, please use a unique ISRC";
//            return Response.ok(message).build();
//        }

//    @POST
//    @Produces(MediaType.TEXT_PLAIN)
//    @Path("/create/{ISRC}/{title}/{description}/{year}/{artist}")
//    public String createAlbum(@PathParam("ISRC") String ISRC, @PathParam("title") String title, @PathParam("description") String description, @PathParam("year") int year, @PathParam("artist") String artist){
//            Album album = albumsManager.createAlbum(ISRC, title, description, year, artist);
//
////            message = (album != null) ? "Album created!: \n" + album : " This ISRC already exists, please use a unique ISRC";
////            return Response.ok(message).build();
//        return "ok" + album.toString();
//    }



//    @DELETE
//    @Produces({MediaType.TEXT_PLAIN})
//    @Path("{ISRC}")
//    public Response deleteAlbum(@PathParam("ISRC") String ISRC){
//        if(albumsManager.deleteAlbum(ISRC)){
//            message = "Album " + ISRC + " successfully deleted!";
//            return Response.ok(message).build();
//        }
//
//        else{
//            message = "Album " + ISRC + " was not found";
//            return Response.status(Response.Status.NOT_FOUND).entity(message).build();
//        }
//    }



//    @PUT
//    @Produces({MediaType.TEXT_PLAIN})
//    @Path("{ISRC}/{title}/{description}/{year}/{artist}")
//    public Response modifyAlbum(@PathParam("ISRC") String ISRC, @PathParam("title") String title, @PathParam("description") String description, @PathParam("year") int year, @PathParam("artist") String artist){
//        title = URLDecoder.decode(title);
//        description = URLDecoder.decode(description);
//        artist = URLDecoder.decode(artist);
//
//        if(ISRC == null || title == null || year == 0 || artist == null){
//            message = "A Form parameter is incorrect!";
//            return Response.status(Response.Status.BAD_REQUEST).entity(message).type(MediaType.TEXT_PLAIN).build();
//        }
//        else{
//            if(albumsManager.updateAlbum(ISRC, title, description, year, artist)){
//                message = "Album modified :\n" + albumsManager.getAlbum(ISRC);
//                return Response.ok(message).build();
//            }
//            else{
//                message = "Album " + ISRC + " could not be modified";
//                return Response.status(Response.Status.NOT_FOUND).entity(message).build();
//            }
//
//        }
//
//    }
}
