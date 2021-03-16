package com.example.demo.REST;

import com.example.demo.Entity.Album;
import com.example.demo.Service.AlbumService;
import com.example.demo.Service.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.FileNotFoundException;
import java.util.List;

@Controller
//@RequestMapping(value = "/album")
@RequestMapping(value = "/album", produces = {MediaType.APPLICATION_JSON_VALUE}, method = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class AlbumRestController implements WebMvcConfigurer {


    private AlbumService albumService;

    @Autowired
    public AlbumRestController(AlbumService albumService) {
        this.albumService = albumService;
    }

//    private AlbumService albumService;

    @GetMapping(value = "/")
    @ResponseBody
    public void getHome() {
        return;
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String showHomeHtml(){
        return "Home";
    }



//    @GetMapping(value = "/list")
//    @ResponseBody
//    public List<Album> getAlbums() {
//        return albumService.getAlbums();
//    }
//
//    @GetMapping(value = "/list", produces = MediaType.TEXT_HTML_VALUE)
//    public String getAlbumsHtml(Model model){
//        model.addAttribute("imgUtil", new ImageUtil());
//        model.addAttribute(albumService.getAlbums());
//        return "Albums";
//    }
//
//
//    @GetMapping(value = "/{ISRC}/{title}")
//    @ResponseBody
//    public Album getAlbum(@PathVariable String ISRC, @PathVariable("title") String title) {
//        return albumService.findByISRCAndTitle(ISRC, title);
//    }
//
//    @GetMapping(value = "/{ISRC}/{title}", produces = MediaType.TEXT_HTML_VALUE)
//    public String getAlbumHtml(Model model, @PathVariable String ISRC, @PathVariable("title") String title) {
//        Album album = albumService.findByISRCAndTitle(ISRC, title);
//        model.addAttribute("imgUtil", new ImageUtil());
//        model.addAttribute("album1", album);
//        return "Album";
//    }


    @PostMapping(value = "/create/{ISRC}/{title}/{description}/{year}/{artist_first_name}/{artist_last_name}")
    @ResponseBody
    public void createNewAlbum(@PathVariable("ISRC") String ISRC, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("year") int year, @PathVariable("artist_first_name") String artist_first_name, @PathVariable("artist_last_name") String artist_last_name) throws FileNotFoundException {
        albumService.createNewAlbumTestService(ISRC, title, description, year, artist_first_name, artist_last_name);
    }

    @PostMapping(value = "/create/{ISRC}/{title}/{description}/{year}/{artist_first_name}/{artist_last_name}", produces = MediaType.TEXT_HTML_VALUE)
    public String createNewAlbumHtml(@PathVariable("ISRC") String ISRC, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("year") int year, @PathVariable("artist_first_name") String artist_first_name, @PathVariable("artist_last_name") String artist_last_name) throws FileNotFoundException {
        albumService.createNewAlbumTestService(ISRC, title, description, year, artist_first_name, artist_last_name);
        return "Created";
    }

//
//
//    @DeleteMapping(value = "/{ISRC}")
//    @ResponseBody
//    public void deleteAlbum(@PathVariable("ISRC") String ISRC) {
//        albumService.deleteAlbum(ISRC);
//    }
//
//    @DeleteMapping(value = "/{ISRC}", produces = MediaType.TEXT_HTML_VALUE)
//    public String deleteAlbumHtml(@PathVariable("ISRC") String ISRC) {
//        albumService.deleteAlbum(ISRC);
//        return "Deleted";
//    }



//    @PutMapping(value = "/{ISRC}/{title}/{description}/{year}/{artist_first_name}/{artist_last_name}")
//    @ResponseBody
//    public void modifyAlbum(@PathVariable("ISRC") String ISRC, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("year") int year, @PathVariable("artist_first_name") String artist_first_name, @PathVariable("artist_last_name") String artist_last_name) throws FileNotFoundException {
//        albumService.modifyAlbum(ISRC, title, description, year, artist_first_name, artist_last_name);
//    }
//
//    @PutMapping(value = "/{ISRC}/{title}/{description}/{year}/{artist_first_name}/{artist_last_name}", produces = MediaType.TEXT_HTML_VALUE)
//    public String modifyAlbumHtml(@PathVariable("ISRC") String ISRC, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("year") int year, @PathVariable("artist_first_name") String artist_first_name, @PathVariable("artist_last_name") String artist_last_name) throws FileNotFoundException {
//        albumService.modifyAlbum(ISRC, title, description, year, artist_first_name, artist_last_name);
//        return "Modified";
//    }


    //CRUD For Cover Images
    //add ISRC to find album
//    http://localhost:8080/album/image/ISRC
//    @GetMapping("/image/{ISRC}")
//    public String getCoverImage(Model model, @PathVariable("ISRC") String ISRC){
//        Album thisAlbum = albumService.getCoverImage(ISRC);
//        model.addAttribute("imgUtil", new ImageUtil());
//        model.addAttribute("album", thisAlbum);
//        return "Images";
//    }
//
//    @DeleteMapping("/image/delete")
//    public String deleteCoverImage(Model model){
////        albumService.deleteCoverImage();
//        return "Images";
//    }
//
//    @PutMapping("/image/put")
//    public String updateCoverImage(Model model){
////        albumService.updateImage();
//        return "Images";
//    }

}

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

