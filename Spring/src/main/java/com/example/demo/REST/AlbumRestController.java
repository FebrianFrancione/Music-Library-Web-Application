package com.example.demo.REST;

import com.example.demo.Entity.Album;
import com.example.demo.Service.AlbumService;
import com.example.demo.Service.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping(value = "/album", method = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class AlbumRestController implements WebMvcConfigurer {


    private AlbumService albumService;

    @Autowired
    public AlbumRestController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/")
    public String showHome(Model model){
        return "Home";
    }


    @GetMapping("/list")
    public String getAlbums(Model model) {
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute(albumService.getAlbums());
        return "Albums";
    }

    @GetMapping("/{ISRC}/{title}")
    public String getAlbum(Model model, @PathVariable("ISRC") String ISRC, @PathVariable("title") String title) {
        Album album = albumService.findByISRCAndTitle(ISRC, title);
        if (album == null) {
            throw new RuntimeException("Album ISRC not found " + ISRC + title);
        }
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("album1", album);

        return "Album";
    }

    @PostMapping("/create/{ISRC}/{title}/{description}/{year}/{artist_first_name}/{artist_last_name}")
    public String createAlbum(@PathVariable("ISRC") String ISRC, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("year") int year, @PathVariable("artist_first_name") String artist_first_name, @PathVariable("artist_last_name") String artist_last_name){
        albumService.createNewAlbum(ISRC, title, description, year, artist_first_name, artist_last_name);
        return "Created";
    }


    @DeleteMapping("/{ISRC}")
    public String deleteAlbum(@PathVariable("ISRC") String ISRC){
        albumService.deleteAlbum(ISRC);
        return "Deleted";
    }

    @PutMapping("/{ISRC}/{title}/{description}/{year}/{artist_first_name}/{artist_last_name}")
    public String modifyAlbum(@PathVariable("ISRC") String ISRC, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("year") int year, @PathVariable("artist_first_name") String artist_first_name, @PathVariable("artist_last_name") String artist_last_name){
        albumService.modifyAlbum(ISRC, title, description, year, artist_first_name, artist_last_name);
        return "Modified";
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
}
